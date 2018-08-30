package io.chrisdavenport.read

import Read.readNonFatal

/**
    with EitherInstances
    with DurationInstances
    with ListInstances
    with MapInstances
    with OptionInstances
    with QueueInstances
    with SetInstances
    with StreamInstances
    with SymbolInstances
    with TupleInstances
    with UnitInstances
    with VectorInstances
**/
object instances extends AllInstances

trait AllInstances {
  implicit val readBigDecimal: Read[BigDecimal] = readNonFatal(BigDecimal(_))
  implicit val readBigInt : Read[BigInt] = readNonFatal(BigInt(_))
  implicit val readBoolean : Read[Boolean] = readNonFatal(_.toBoolean)
  implicit val readByte : Read[Byte] = readNonFatal(_.toByte)
  implicit val readChar: Read[Char] = readNonFatal(_.head)
  implicit val readDouble: Read[Double] = readNonFatal(_.toDouble)
  implicit val readFloat: Read[Float] = readNonFatal(_.toFloat)
  implicit val readInt: Read[Int] = readNonFatal(_.toInt)
  implicit val readLong : Read[Long] = readNonFatal(_.toLong)
  implicit val readShort : Read[Short] = readNonFatal(_.toShort)
  implicit val readString : Read[String] = readNonFatal(identity)
  implicit val readUUID : Read[java.util.UUID] =
    readNonFatal(java.util.UUID.fromString)
  implicit val readUnit : Read[Unit] = new Read[Unit]{
    def read(s: String): Either[Throwable, Unit] = s match {
      case "()" => Right(())
      case _ => Left(ReadException)
    }
  }

}