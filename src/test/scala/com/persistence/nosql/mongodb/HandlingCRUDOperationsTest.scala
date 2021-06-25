/*
 * Created by Saurav Sahu on 21/06/21, 10:11 PM.
 */

package com.persistence.nosql.mongodb

import com.mongodb.MongoWriteException
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala._
import org.mongodb.scala.bson.{BsonInt32, BsonNull, BsonString}
import org.mongodb.scala.model.Filters.{equal, exists}
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.concurrent.Await
import scala.language.postfixOps
import scala.util.{Failure, Try}

class HandlingCRUDOperationsTest extends FunSuite with LazyLogging with BeforeAndAfter{
  val demoObj = new HandlingCRUDOperations

  before {
    demoObj.dropDatabase("mydb")
  }

  test("database and collection creation"){
    val collectionsBefore = demoObj.getCollectionList("mydb")
    assert(collectionsBefore.isEmpty)

    demoObj.createCollection("mydb", "test")
    val collectionsAfter = demoObj.getCollectionList("mydb")
    assert(collectionsAfter.head.equals("test"))
  }

  test("insert one document - try inserting the same document again"){
    val collection = demoObj.getCollection("mydb", "test")
    val doc: Document = Document("_id" -> 99999, "name" -> "MongoDB", "count" -> 1,
      "info" -> Document("x" -> 203, "y" -> 102))
    assert(demoObj.insertDocument(collection, doc).getInsertedId.equals(BsonInt32(99999)))

    // try inserting the same document once more
    Try(demoObj.insertDocument(collection, doc)) match {
      case Failure(e: MongoWriteException) =>
        assert(e.getMessage.contains("E11000 duplicate key error collection"))
      case _ => fail("Duplicate key should not be allowed.")
    }
  }

  test("insert multiple documents - get count of documents"){
    val documents = (1 to 100) map { i => Document("i" -> i) }
    val collection = demoObj.getCollection("mydb", "test")
    val insertAndCount = for {
      _ <- collection.insertMany(documents)
      countResult <- collection.countDocuments
    } yield {
      countResult
    }
    val res = Await.result(insertAndCount.toFuture(), 2 seconds)
    assert(res.head == 100)
  }

  test("list of collections") {
    demoObj.createCollection("mydb", "test_foo")
    demoObj.createCollection("mydb", "test_bar")
    val collections = demoObj.getCollectionList("mydb")
    assert(collections.toSet == Seq("test_foo", "test_bar").toSet)
  }

  test("query for document - existing or non-exisiting"){
    val collection = demoObj.getCollection("mydb", "test")
    val doc101: Document = Document("_id" -> 101, "name" -> "Foo", "count" -> 15)
    val doc102: Document = Document("_id" -> 102, "name" -> "Bar", "count" -> 10)
    val doc103: Document = Document("""{"_id": 103, "name": null}""")
    val doc104: Document = Document("""{"_id": 420, "count": 18}""")

    Await.ready(collection.insertMany(Seq(doc101, doc102, doc103, doc104)).toFuture(), 2 seconds)

    /* Equality Filter */
    val doc101_ = Await.result(collection.find(equal("_id", 101)).first.toFuture, 2 seconds)
    val doc102_ = Await.result(collection.find(equal("_id", 102)).first.toFuture, 2 seconds)
    assert(doc101_.getString("name") == "Foo")
    assert(doc102_.getInteger("count") == 10)

    /* Look out for non-existing field in an existing document, return a default value */
    assert(doc101_.getOrElse("unknownField", "hello") == BsonString("hello"))

    /* Check for explicitly assigned NULL field */
    val docWithNullName = Await.result(collection.find(equal("name", BsonNull())).first.toFuture, 2 seconds)
    assert(docWithNullName.getInteger("_id") == 103)

    /* Look out for document which doesn't have "name" as one of its fields */
    val nameLessDoc  = Await.result(collection.find(exists("name", exists = false)).first.toFuture, 2 seconds)
    assert(nameLessDoc.getInteger("_id") == 420)

    val docIdNotExisting = Await.result(collection.find(equal("_id", -99)).first.toFuture, 2 seconds)
    assert(docIdNotExisting == null)
  }

  test("update an existing document"){

  }

  test("update a non-existing document"){

  }

  test("delete an existing document"){

  }

  test("delete a non-existing document"){

  }

  test("delete an existing collection"){

  }

  test("delete a non-existing collection"){

  }

  test("delete an existing database"){

  }

  test("delete a non-existing database"){

  }

  test("query a collection") {

  }

  test("create a single index") {

  }

  test("create a compound index") {

  }
}
