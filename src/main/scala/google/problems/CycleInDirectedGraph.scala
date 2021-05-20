package google.problems

import scala.collection._

class CycleInDirectedGraph(val inputArr: Array[(Int, Int)]) {
  // Algorithm : Start from each candidate vertex, do a dfs on all 'unmarked' neighbouring vertices, and unmark the current vertex
  // Common Mistake: Ensure that the return value 'true' is transmitted down the stack
  // Optimization: Vertex, with no arrow coming out of it, can never be part of any cycle, so no DFS required for them.
  type M = Map[Int, List[Int]]
  def hasCycle() : Boolean = {
    def groupConnectedVertices() : M = {
      val groupedConnectionMap = mutable.Map[Int, List[Int]]().withDefaultValue(List.empty)
      inputArr.map { case (from, to) =>
        groupedConnectionMap(from) = to :: groupedConnectionMap(from)
      }
      groupedConnectionMap
    }
    val connectionsMap = groupConnectedVertices()
    var traversed = connectionsMap.keys.map(vertex => vertex -> false).to(mutable.Map)  // Scala 2.13 feature
    /**
     * A cycle is found that's when any visited vertex gets visited once again.
     * @param vertex
     * @return true if cycle is found, returns false otherwise.
     */
    def dfs(vertex : Int) : Boolean = {
      if (!connectionsMap.contains(vertex)){
        return false  // dead-end
      }
      if (traversed(vertex) == true) {
        return true   // already visited in ongoing route
      }
      traversed.update(vertex, true)
      connectionsMap(vertex).foreach { x =>
        if (dfs(x)) return true
      }
      traversed.update(vertex, false)
      false
    }
    for (c <- traversed.keys){
      if (dfs(c)) return true
    }
    false
  }
}

/* Alternative method for grouping */
//    def groupConnectedVerticesAlt() : M = {
//      inputArr.groupBy(_._1).map {
//        case (from: Int, connectionsTuples: Array[(Int, Int)]) =>
//          from -> connectionsTuples.map(_._2).toList
//      }
//    }
//    @annotation.tailrec
//    def hasCycleInConnections(directlyConnectedVertices : List[Int]): Boolean = {
//      directlyConnectedVertices match {
//        case Nil => false
//        case head :: tail => {
//          if (dfs(head)) return true
//          hasCycleInConnections(tail)
//        }
//        case _ => false
//      }
//    }