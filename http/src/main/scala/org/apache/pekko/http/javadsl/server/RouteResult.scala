/*
 * Copyright (C) 2018-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko.http.javadsl.server

import org.apache.pekko
import pekko.http.javadsl.model.HttpResponse

trait RouteResult {}

trait Complete extends RouteResult {
  def getResponse: HttpResponse
}

trait Rejected extends RouteResult {
  def getRejections: java.lang.Iterable[Rejection]
}

object RouteResults {
  import pekko.http.scaladsl.{ server => s }
  import pekko.japi.Util
  import pekko.http.impl.util.JavaMapping
  import JavaMapping.Implicits._
  import RoutingJavaMapping._

  def complete(response: HttpResponse): Complete = {
    s.RouteResult.Complete(JavaMapping.toScala(response))
  }

  def rejected(rejections: java.lang.Iterable[Rejection]): Rejected = {
    s.RouteResult.Rejected(Util.immutableSeq(rejections).map(_.asScala))
  }

}