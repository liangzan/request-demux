package com.sproutmode

import unfiltered.request._
import unfiltered.response._

import org.clapper.avsl.Logger

/** unfiltered plan */
class App extends unfiltered.filter.Plan {
  import QParams._

  val logger = Logger(classOf[App])

  def intent = {
    case GET(Path(p)) =>
      logger.info("GET %s" format p)
      Ok ~> ResponseString("ok")

    case POST(Path(p) & Params(params)) =>
      logger.info("POST %s" format p)
      Ok ~> ResponseString("ok")

    case _ => Pass
  }
}
