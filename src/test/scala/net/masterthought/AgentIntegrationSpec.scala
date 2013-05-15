package net.masterthought

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver

class AgentIntegrationSpec extends WordSpec with MustMatchers {

  "Assigning FirefoxDriver to an agent" should {
    "store this skill" in {
      val agent = new Agent
      val firefox = new FirefoxDriver
      agent.obtain("first", firefox)
      agent.skills must not be 'empty
      firefox.quit()
    }
  }

  "Recalling FirefoxDriver from an Agent" should {
    "be possible" in {
      val agent = Agent()
      agent.obtain("browser", new FirefoxDriver)
      agent.use("browser").asInstanceOf[WebDriver].quit()
    }
  }


}

