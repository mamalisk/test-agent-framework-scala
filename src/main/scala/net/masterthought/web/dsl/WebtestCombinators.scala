package net.masterthought.web.dsl

import util.parsing.combinator.JavaTokenParsers
import net.masterthought.Agent
import org.openqa.selenium.{By, WebDriver}
import util.parsing.input.CharSequenceReader

/**
 * Created with IntelliJ IDEA.
 * User: kostasmamalis
 * Date: 16/05/2013
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
case class WebTestDsl(agent:Agent) extends JavaTokenParsers{

  case class Click(agent:Agent, by:By){
      val driver = agent.use("driver").asInstanceOf[WebDriver]
      def apply = driver.findElement(by).click()
  }


  def PerformWebAction(agent: Agent, parser: WebTestDsl.this.type#Parser[Object]) : String = {
    println(parser)
    parser.toString
  }

  def actionOnElement = action ~ prefix ~ item  ^^ {
    case a ~ p ~ i =>  (PerformWebAction(agent,item))
  }


  def actionOnElement2 = action ~ string ^^ {
    case a ~ s => println
  }

  def string = stringLiteral

  var result : String = ""

  def action = "performing" ~> actions
  def actions = "click" |"read" | "clear"
  def prefix = "on" | "in" | "the"
  def item = "element" ~> "located" ~>  "by"  ~> locatorType ~>  locatorValue  ^^ {
    x =>  result = x.substring(1,x.length -1)
      new String(x)
  }

  def locatorType = "Id" | "Xpath"| "css" | "text" | "class" | "name"
  def locatorValue = stringLiteral

}

object executor extends App {

  val webTest = new WebTestDsl(Agent())
  webTest.parseAll(webTest.actionOnElement, """performing click on element located by Id "test"""") match  {
    case webTest.Success(_,t) => println(t.asInstanceOf[CharSequenceReader].source.toString)
  }
  println(webTest.result)

}
