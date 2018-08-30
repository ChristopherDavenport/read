package io.chrisdavenport.read


/**
  * Read is a typeclass for working with the general inputs
  * to a programming language.
  *
  * Law:
  * a: A => (Show[A].show andThen Read[A].read)(a) === Right(a)
  **/
trait Read[A]{
  def read(s: String): Either[Throwable, A]
  final def unsafeRead(s: String): A = 
    read(s).fold(e => throw e, identity)
}

object Read {
  def apply[A](implicit ev: Read[A]): Read[A] = ev

  def readNonFatal[A](f: String => A): Read[A] = new Read[A]{
    override def read(s: String): Either[Throwable, A] = 
      catchNonFatal(f(s))
  } 

  def functor[A, B](r: Read[A])(f: A => B): Read[B] = new Read[B]{
    def read(s: String): Either[Throwable, B] = 
      mapEither(r.read(s))(f)
  }

  private def catchNonFatal[A](a: => A): Either[Throwable, A] = {
    import scala.util.control.NonFatal
    try {
      Right(a)
    } catch {
      case NonFatal(e) => Left(e)
    }
  }

  private def mapEither[E,A, B](e: Either[E, A])(f: A => B): Either[E, B] = e match {
    case Right(a) => Right(f(a))
    case l@Left(_) => l.asInstanceOf[Either[E, B]]
  }
}