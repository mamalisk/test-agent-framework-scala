package net.masterthought

trait Mission {

  def accomplish(agent: Agent): Option[List[Any]]
}
