package net.masterthought.predicates

object Polling {

  val seconds = 10
  val interval = 1

  def pollFor(condition: Any => Boolean) = {
    for (i <- 1 to seconds by interval) {
      if (!(condition.apply(_)).asInstanceOf[Boolean]) {
        Thread.sleep(1000)
      }
    }
  }
}

