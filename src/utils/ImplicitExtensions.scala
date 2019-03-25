package utils

import java.net.{URI, URISyntaxException}
import java.time.{Duration => JDuration}


import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.concurrent.{ExecutionContext, Future}
import scala.language.implicitConversions
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object ImplicitExtensions {
  implicit def jDurationToFiniteDuration(duration: JDuration): FiniteDuration = Duration.fromNanos(duration.toNanos)

  implicit class BooleanExtensions(val x: Boolean) extends AnyVal {
    def toTry(err: => Exception): Try[Unit] = if (x) Success(Unit) else Failure(err)
    def toFuture(err: => Exception): Future[Unit] = if (x) Future.successful(Unit) else Future.failed(err)
  }

  implicit class OptionReverse[A](val x: Option[Try[A]]) extends AnyVal {
    def toTryOfOpt: Try[Option[A]] = x match {
      case Some(Success(seq)) => Success(Some(seq))
      case Some(Failure(e)) => Failure(e)
      case None => Success(None)
    }
  }

  implicit class OptionExtensions[A](val x: Option[A]) extends AnyVal {
    def toTry(err: => Exception): Try[A] = x.fold[Try[A]](Failure(err))(Success(_))
    def toFuture(err: => Exception): Future[A] = x.fold[Future[A]](Future.failed(err))(Future.successful)
  }

  implicit class TryExtensions[T](val x: Try[T]) extends AnyVal {

    def failIfNotWith(p: T => Boolean, err: Exception): Try[T] = x.flatMap { v =>
      if (p(v)) Success(v) else Failure(err)
    }

    def failIfWith(p: T => Boolean, err: Exception): Try[T] = x.flatMap { v =>
      if (!p(v)) Success(v) else Failure(err)
    }


    def toFuture: Future[T] = Future.fromTry(x)

    def mapError(pf: PartialFunction[Throwable, Throwable]): Try[T] =
      x.recoverWith {
        case e =>
          if (pf.isDefinedAt(e))
            Failure(pf(e))
          else
            Failure(e)
      }

    def mapNonFatal(f: Throwable => Throwable): Try[T] =
      x.recoverWith {
        case NonFatal(e) => Failure(f(e))
        case other => Failure(other)
      }

    def toOption(errorHandler: Throwable => Unit): Try[Option[T]] =
      x.transform[Option[T]](
        x => Success(Option(x)),
        {
          case NonFatal(e) =>
            errorHandler(e)
            Success(None)
          case e =>
            Failure(e)
        }
      )
  }

  implicit class FutureExtensions[T](val x: Future[T]) extends AnyVal {
    def mapError(pf: PartialFunction[Throwable, Throwable])(implicit executor: ExecutionContext): Future[T] =
      x.recoverWith {
        case e =>
          if (pf.isDefinedAt(e))
            Future.failed(pf(e))
          else
            Future.failed(e)
      }

    def mapNonFatal(f: Throwable => Throwable)(implicit executor: ExecutionContext): Future[T] =
      x.recoverWith {
        case NonFatal(e) => Future.failed(f(e))
        case other => Future.failed(other)
      }

    def toOption(errorHandler: Throwable => Unit)(implicit executor: ExecutionContext): Future[Option[T]] =
      x.transform[Option[T]]((x: Try[T]) => x.toOption(errorHandler))
  }


  implicit class UriExtensions(val x: URI) extends AnyVal {
    private def parseParams(str: String): Map[String, Seq[String]] =
      str
        .split("&")
        .toStream
        .map {
          _.split("=").toList match {
            case Nil => None
            case key :: Nil => Some(key -> "")
            case key :: value :: Nil => Some(key -> value)
            case key :: _ :: _ => Some(key -> "")
          }
        }
        .collect { case Some(v) => v }
        .foldLeft(Map.empty[String, Seq[String]]) {
          case (acc, (key, value)) =>
            acc + (key -> (acc.getOrElse(key, Seq.empty[String]) :+ value))
        }

    def queryParams: Map[String, Seq[String]] = Option(x.getQuery).map(parseParams).getOrElse(Map.empty)

    def fragmentParams: Map[String, Seq[String]] = Option(x.getFragment).map(parseParams).getOrElse(Map.empty)

    def serverName = s"${x.getScheme}://${x.getAuthority}"

    def toOrigin: Try[String] = {
      def sanitize(str: String): Option[String] = Option(str).filter(!_.trim.isEmpty)

      (
        sanitize(x.getScheme),
        sanitize(x.getAuthority),
        sanitize(x.getQuery),
        sanitize(x.getPath),
        sanitize(x.getFragment)
      ) match {
        case (Some(scheme), Some(authority), None, None, None) => Success(s"$scheme://$authority")
        case _ => Failure(new URISyntaxException(x.toString, "it's not origin"))
      }
    }
  }

  implicit class FutureOptionExtensions[A](val x: Future[Option[A]]) extends AnyVal {
    def getOrFail(t: => Throwable)(implicit ex: ExecutionContext): Future[A] =
      x.flatMap {
        case Some(a) => Future.successful(a)
        case None => Future.failed(t)
      }

    def emptyOrFail(t: A => Throwable)(implicit ex: ExecutionContext): Future[Unit] =
      x.flatMap {
        case Some(a) => Future.failed(t(a))
        case _ => Future.successful(Unit)
      }
  }

}
