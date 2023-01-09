/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.http.javadsl.testkit

import akka.http.javadsl.model.HttpRequest
import akka.http.javadsl.server.Route

/**
 * A wrapped route that has a `run` method to run a request through the underlying route to create
 * a [[TestResponse]].
 *
 * A TestRoute is created by deriving a test class from the concrete RouteTest implementation for your
 * testing framework (like [[JUnitRouteTest]] for JUnit) and then using its `testRoute` method to wrap
 * a route with testing support.
 */
trait TestRoute {
  def underlying: Route

  /**
   * Run the request against the sealed route, meaning that exceptions and rejections will be handled by
   * the default exception and rejection handlers. The default handlers will convert exceptions and
   * rejections into HTTP responses with corresponding status codes (like 404 when no route matches
   * the path or 500 in cases of exceptions).
   *
   * If you want to assert on the original rejections instead, use [[TestRoute#runWithRejections]].
   */
  def run(request: HttpRequest): TestRouteResult

  /**
   * Run the request against the "semi-sealed" route, meaning that exceptions will be handled by the
   * default exception handler but rejections will not be handled. This means that the test will not
   * see HTTP responses with error status codes for routes that rejected a request. Instead, the
   * [[TestRouteResult]] allows access to the original rejection containing all the rejection details
   * in structured form. Use [[TestRouteResult#assertRejections]] to check that a route rejected a
   * request with expected rejections.
   *
   * Otherwise, to assert on the actual error HTTP response generated by the default rejection handler,
   * use the [[TestRoute#run]] method, instead.
   */
  def runWithRejections(request: HttpRequest): TestRouteResult

  /**
   * Similar to [[TestRoute#run]] but runs the request through a full HTTP client and server stack.
   *
   * Run the request against the sealed route, meaning that exceptions and rejections will be handled by
   * the default exception and rejection handlers. The default handlers will convert exceptions and
   * rejections into HTTP responses with corresponding status codes (like 404 when no route matches
   * the path or 500 in cases of exceptions).
   *
   * If you want to assert on the original rejections instead, use [[TestRoute#runWithRejections]].
   */
  def runClientServer(request: HttpRequest): TestRouteResult
}