package net.masterthought

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

class AgentSpec extends WordSpec with MustMatchers {

  import TestData._

  "Calling 'obtain' on an Agent" should {
    "remember his ability" in {
      val agent = new Agent
      agent.obtain("first", default)
      agent.skills must not be 'empty
    }
  }

  "Calling 'use' on an Agent" should {
    "recall the ability and use it" in {
      val agent = Agent()
      agent.obtain("first", default)
      agent.use("first").asInstanceOf[MyClass].name must be === "Default"
    }
  }

  "Calling 'bearInMind' on an Agent" should {
    "update his memory when empty" in {
      val agent = Agent()
      agent.bearInMind("category", "key", "value")
      agent.memory.isEmpty must be === false
    }
    "override existing values for same category and key" in {
      val agent = Agent()
      agent.bearInMind("category", "key", "value")
      agent.recallFromMemory("category", "key") must be === "value"
      agent.bearInMind("category", "key", "value2")
      agent.recallFromMemory("category", "key") must be === "value2"
    }

    "store data of different types" in {
      val agent = Agent()
      agent.bearInMind("category","key","value")
      agent.bearInMind("category",default,default)
      agent.bearInMind(default,default,default)
      agent.memory.isEmpty must be === false
      agent.memory.currentSize must be === 3
    }
  }

  "Calling 'recall' on an Agent" should {
    "return the stored object" in {
      val agent = Agent()
      agent.bearInMind("category", "key", "value")
      agent.recallFromMemory("category", "key") must be === "value"
    }
    "or return 'None' if the item is not there" in {
      val agent = Agent()
      agent.recallFromMemory("category", "key") must be === None
    }
  }

  "Storing object to agent's memory" should {
    val agent = Agent()
    "be possible" in {
      agent.bearInMind(default, default, default)
    }
    "as well as remembering them" in {
      agent.recallFromMemory(default, default) must not be None
      agent.recallFromMemory(default, default) must be === default
    }
  }


}

case class MyClass(name: String)

object TestData {
  val default = MyClass("Default")
}
