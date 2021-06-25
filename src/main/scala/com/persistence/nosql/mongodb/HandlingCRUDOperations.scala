/*
 * Created by Saurav Sahu on 20/06/21, 10:06 PM.
 */

package com.persistence.nosql.mongodb

import com.persistence.nosql.mongodb.HandlingCRUDOperations.mongoClient
import com.typesafe.scalalogging.LazyLogging
import org.mongodb.scala._

import scala.language.postfixOps
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object HandlingCRUDOperations extends LazyLogging {
  val mongoClient : MongoClient = MongoClient("mongodb://localhost:27017")
}

class HandlingCRUDOperations {
  def createCollection(dbName : String, collectionName: String) = {
    Await.result(mongoClient.getDatabase(dbName).createCollection(collectionName).toFuture(), 2 seconds)
  }
  def getCollection(dbName : String, collectionName: String): MongoCollection[Document] = {
    mongoClient.getDatabase(dbName).getCollection(collectionName)
  }
  def getCollectionList(dbName : String) = {
    Await.result(mongoClient.getDatabase(dbName).listCollectionNames().toFuture(), 2 seconds)
  }
  def dropDatabase(dbName: String) = {
    Await.ready(mongoClient.getDatabase(dbName).drop().toFuture(), 3 seconds)
  }
  def dropCollection(dbName : String, collectionName: String) = {
    val collection = getCollection(dbName, collectionName)
    Await.ready(collection.drop().toFuture(), 3 seconds)
  }
  def insertDocument(collection: MongoCollection[Document], document: Document) = {
    Await.result(collection.insertOne(document).toFuture(), 2 seconds)
  }
}