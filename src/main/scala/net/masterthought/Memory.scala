package net.masterthought

import scala.collection.mutable.Map

case class Memory(id: String) {

  private var core: Map[Any, List[(Any, Any)]] = Map[Any, List[(Any, Any)]]()

  def isEmpty: Boolean = core.isEmpty

  def keyIsFound(key: Any, option: Option[List[(Any, Any)]]): (Any, Any) = option.get.find(pair => pair._1 == key || pair.equals(key)).getOrElse(("", ""))

  def updateKeyValuePairs(pair: (Any, Any), keyValuePairs: Option[List[(Any, Any)]]): List[(Any, Any)] = {
    val actualPairs: List[(Any, Any)] = keyValuePairs.get
    var result = actualPairs
      result = for {actualPair <- actualPairs
        if(actualPair._1 != pair._1)
      } yield actualPair
      result = result.+:(pair)
    result
  }

  def +(category: Any, keyValue: (Any, Any)) = remember(category, keyValue)

  def get(category: Any, key: Any): Any = recall(category, key)

  def remember(category: Any, keyValue: (Any, Any)) = {
    if (getCategories().contains(category)) {
      core(category) = Option(updateKeyValuePairs(keyValue, core.get(category))).get
    } else {
      core += (category -> List[(Any, Any)](keyValue))
    }
  }

  def recall(category: Any, key: Any): Any = {
    val pairs = getAllForCategory(category)
    if (pairs == None || pairs.isEmpty) return None
    pairs.find(pair => (pair._1 == key || pair._1.equals(key))).getOrElse(notFoundEx)._2
  }

  def getAllForCategory(cat: Any): List[(Any, Any)] = {
    if (getCategories().contains(cat)) {
      return core.get(cat).get
    } else return List[(Any, Any)]()

  }

  private def getCategories(): Set[Any] = core.keys.toSet
  private def notFoundEx = throwEx("Not found")
  def throwEx(message: String*) = throw new RuntimeException(message.toString())
}
