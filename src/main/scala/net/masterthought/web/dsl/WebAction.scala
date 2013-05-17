package net.masterthought.web.dsl

import net.masterthought.Agent
import org.openqa.selenium.{WebElement, WebDriver, By}

trait WebAbility {

  def driver : Agent => Option[WebDriver] = (agent:Agent) => Option(agent.use("driver").asInstanceOf[WebDriver])
  def locate : (Agent,By) => Option[WebElement] = (agent:Agent, by:By) => Option(driver(agent).get.findElement(by))
  def click : (Agent,By) => Option[Unit] = (agent:Agent, by:By) => Option(locate(agent,by).get.click())
  def quit : (Agent) => Option[Unit] = (agent:Agent) => Option(driver(agent).get.quit)
  def navigate :(Agent,String) => Option[Unit] = (agent:Agent, url:String) => Option(driver(agent).get.get(url))
  def read :(Agent,By) => Option[String] = (agent:Agent, by:By) => Option(locate(agent,by).get.getText)
}

