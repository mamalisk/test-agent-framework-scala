package net.masterthought

case class Agent() {

  val id = System.currentTimeMillis().toString

  var skills: Map[String, Any] = Map.empty

  var memory: Memory = Memory(id)

  def obtain(key: String, any: Any) = skills = skills + (key -> any)

  def bearInMind(category: Any, key: Any, value: Any) = memory + (category, (key, value))

  def recallFromMemory(category: Any, key: Any): Any = memory get (category, key)

  def use(skill: String): Any = skills.get(skill).get

  def +(key: String, any: AnyRef) = obtain(key, any)

  def results = List()

  def doThe(missions: Mission*) = missions.foreach(results ++ _.accomplish(this))

}
