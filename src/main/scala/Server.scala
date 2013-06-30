package com.sproutmode

import unfiltered.request._
import unfiltered.response._

/** embedded server */
object Server {
  val port = 3500
  def main(args: Array[String]) {
    val http = unfiltered.jetty.Http.local(port) // this will not be necessary in 0.4.0
    http.filter(new App).run()
  }
}
