package com.sproutmode

import unfiltered.request._
import unfiltered.response._

import dispatch._
import Defaults._

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
      subscriberRequest(params)
      Ok ~> ResponseString("ok")

    case _ => Pass
  }

  def subscriberUrl = url("http://127.0.0.1:3501/bongo")
  def subscriberRequest(params: Map[String, Seq[String]]) {
    Http(subscriberUrl << params.mapValues(_.mkString))
  }
}
