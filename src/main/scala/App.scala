package com.sproutmode

import unfiltered.request._
import unfiltered.response._

import com.typesafe.scalalogging.slf4j._

/** unfiltered plan */
class App extends unfiltered.filter.Plan with Logging {
  import QParams._

  def intent = {
    case GET(Path(p)) =>
      logger.info("GET %s" format p)
      Ok ~> ResponseString("ok")

    case POST(Path(p) & Params(params)) =>
      logger.info("POST %s [%s]" format (p, params))
      Ok ~> ResponseString("ok")

    case _ => Pass
  }
}
