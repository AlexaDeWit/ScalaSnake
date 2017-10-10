package com.github.alexadewit.scalasnake

import io.circe.syntax._
import cats.effect.{Effect, IO}
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._
import com.github.alexadewit.scalasnake.requests.GameStartRequest
import com.github.alexadewit.scalasnake.requests.GameStartRequestCodec._

class SnakeService[F[_]](implicit F: Effect[F]) extends Http4sDsl[F] {


  def route(): HttpService[F] = HttpService {
    case req @ POST -> Root / "start" =>
      req.decode[GameStartRequest] { gameStart =>
        Ok(gameStart.asJson)
      }
  }

}
