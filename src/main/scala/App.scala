package com.sproutmode

import unfiltered.request._
import unfiltered.response._

import dispatch._
import Defaults._

import com.typesafe.scalalogging.slf4j._
import com.typesafe.config._
import scala.collection.JavaConverters._

/** unfiltered plan */
class App extends unfiltered.filter.Plan with Logging {
  import QParams._

  val conf = ConfigFactory.load()

  def intent = {
    case GET(Path(p)) =>
      logger.info("GET %s" format p)
      Ok ~> ResponseString("ok")

    case POST(Path(p) & Params(params)) =>
      logger.info("POST %s [%s]" format (p, params))
      broadcastRequest(params)
      Ok ~> ResponseString("ok")

    case _ => Pass
  }

  def subscriberRequest(destUrl: String, params: Map[String, Seq[String]]) {
    Http(url(destUrl) << params.mapValues(_.mkString))
  }

  def broadcastRequest(params: Map[String, Seq[String]]) {
    val urlList = conf.getStringList("demux.urls").asScala
    for (url <- urlList)
      subscriberRequest(url, params)
  }
}
