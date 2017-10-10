package com.github.alexadewit.test.scalasnake

import java.util.UUID

import com.github.alexadewit.scalasnake.SnakeService
import org.scalatest.{FlatSpec, Matchers}
import cats.effect._
import io.circe.syntax._
import org.http4s.dsl.io.{uri, _}
import org.http4s.circe._
import com.github.alexadewit.scalasnake.requests.GameStartRequest
import com.github.alexadewit.scalasnake.requests.GameStartRequestCodec._
import org.http4s.{Method, Request}

object serviceFixture extends SnakeService[IO]

class SnakeServiceTest extends FlatSpec with Matchers {

  "The Root Endpoint" should "echo back the input value" in {
    val input = GameStartRequest(10, 10, UUID.randomUUID())
    val resp = for {
      req <- Request[IO](Method.POST, uri("/start")).withBody(input.asJson)
      resp <- serviceFixture.route().orNotFound.run(req).as[GameStartRequest]
    } yield resp
    resp.unsafeRunSync shouldBe input
  }
}
