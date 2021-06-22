/*
 * Created by Saurav Sahu on 21/06/21, 10:11 PM.
 */

package com.persistence.nosql.mongodb

import com.mongodb.MongoWriteException
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala.Document
import org.mongodb.scala.bson.BsonInt32
import org.scalatest.FunSuite
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import scala.language.postfixOps

import scala.concurrent.Await
import scala.util.{Failure, Try}

class HandlingCRUDOperationsTest extends FunSuite with LazyLogging{
  val demoObj = new HandlingCRUDOperations

  test("database and collection creation"){
    demoObj.dropDatabase("mydb")
    val collectionsBefore = demoObj.getCollectionList("mydb")
    assert(collectionsBefore.isEmpty)

    val createCollectionTest = demoObj.createCollection("mydb", "test")
    Await.ready(createCollectionTest.toFuture(), 2 seconds)
    val collectionsAfter = demoObj.getCollectionList("mydb")
    assert(collectionsAfter.head.equals("test"))
  }

  test("insert one document - try inserting the same document again"){
    val collection = demoObj.getCollection("mydb", "test")
    Await.result(collection.drop().toFuture(), 2 seconds)

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

  test("insert multiple document"){

  }
  test("insert(attempt to insert) duplicate document - existing id"){

  }
  test("list of databases") {

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
