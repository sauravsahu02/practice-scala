/*
 * Created by Saurav Sahu on 21/06/21, 10:11 PM.
 */

package com.persistence.nosql.mongodb

import com.mongodb.MongoWriteException
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala._
import org.mongodb.scala.bson.BsonInt32
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime

import scala.concurrent.Await
import scala.language.postfixOps
import scala.util.{Failure, Try}

class HandlingCRUDOperationsTest extends FunSuite with LazyLogging{
  val demoObj = new HandlingCRUDOperations

  test("database and collection creation"){
    demoObj.dropDatabase("mydb")
    val collectionsBefore = demoObj.getCollectionList("mydb")
    assert(collectionsBefore.isEmpty)

    demoObj.createCollection("mydb", "test")
    val collectionsAfter = demoObj.getCollectionList("mydb")
    assert(collectionsAfter.head.equals("test"))
  }

  test("insert one document - try inserting the same document again"){
    val collection = demoObj.getCollection("mydb", "test")
    Await.ready(collection.drop().toFuture(), 2 seconds)

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

  test("insert multiple documents"){
    demoObj.dropCollection("mydb", "test")

    val documents = (1 to 100) map {i => Document("i" -> i)}
    val collection = demoObj.getCollection("mydb", "test")
    val insertedManyObservable = collection.insertMany(documents)
    val insertAndCount = for {
      _ <- insertedManyObservable
      countResult <- collection.countDocuments
    } yield {
      countResult
    }
    assert(Await.result(insertAndCount.toFuture(), 2 seconds).head == 100)
  }
  test("list of collections") {
    demoObj.dropDatabase("mydb")
    demoObj.createCollection("mydb", "test_foo")
    demoObj.createCollection("mydb", "test_bar")
    val collections = demoObj.getCollectionList("mydb")
    println(collections)
    assert(collections.toSet == Seq("test_foo", "test_bar").toSet)
  }
  test("count of documents") {

  }
  test("read an existing document"){

  }
  test("read a non-existing document"){

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
