package io.chrisdavenport.read

import scala.util.control.NoStackTrace

sealed trait ReadException extends Exception
object ReadException {
  def apply(): ReadException = ReadFailure
  def unapply(t: Throwable): Option[ReadException] = t match {
    case ReadFailure => Some(ReadFailure)
    case _ => None
  }

  final case object ReadFailure extends ReadException with NoStackTrace

}

