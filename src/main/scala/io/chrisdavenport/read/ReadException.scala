package io.chrisdavenport.read

import scala.util.control.NoStackTrace

final case object ReadException extends Exception with NoStackTrace

// sealed trait ReadException extends Exception
// object ReadException {
//   final case object ReadFailure extends ReadException with NoStackTrace

// }

