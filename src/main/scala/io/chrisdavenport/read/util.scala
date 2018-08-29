package io.chrisdavenport.read

import scala.util.control.NonFatal

private[read] object util {
  def leftMap[E,A, G](e: Either[E, A])(f: E => G): Either[G, A] = 
    e.fold(f.andThen(Left.apply), Right.apply)

  def catchNonFatal[A](a: => A): Either[Throwable, A] = 
    try {
      Right(a)
    } catch {
      case NonFatal(e) => Left(e)
    }

  def parseNonFatal[A](a: => A): Either[ReadException, A] = 
    leftMap(catchNonFatal(a))(_ => ReadException())

  def readNonFatal[A](f: String => A): Read[A] = new Read[A]{
    override def read(s: String): Either[ReadException, A] = 
      parseNonFatal(f(s))
  } 
}