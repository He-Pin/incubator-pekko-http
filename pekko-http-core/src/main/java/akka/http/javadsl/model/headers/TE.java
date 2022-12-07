/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.http.javadsl.model.headers;

/**
 *  Model for the `Transfer-Encoding` header.
 *  Specification: http://tools.ietf.org/html/draft-ietf-httpbis-p1-messaging-26#section-3.3.1
 */
public abstract class TE extends akka.http.scaladsl.model.HttpHeader {
    public abstract Iterable<akka.http.javadsl.model.TransferEncoding> getAcceptableEncodings();

    public static TE create(akka.http.javadsl.model.TransferEncoding... acceptableEncodings) {
        return new akka.http.scaladsl.model.headers.TE(akka.http.impl.util.Util.<akka.http.javadsl.model.TransferEncoding, akka.http.scaladsl.model.TransferEncoding>convertArray(acceptableEncodings));
    }
}