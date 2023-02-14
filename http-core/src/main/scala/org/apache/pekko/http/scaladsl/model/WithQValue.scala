/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package org.apache.pekko.http.scaladsl.model

/** Helper trait for objects that allow creating new instances with a modified qValue. */
trait WithQValue[T] {

  /** truncates Double qValue to float and returns a new instance with this qValue set */
  def withQValue(qValue: Double): T = withQValue(qValue.toFloat)
  def withQValue(qValue: Float): T
}