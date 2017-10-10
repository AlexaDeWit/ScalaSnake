package com.github.alexadewit.test.scalasnake

import java.util.UUID

import com.github.alexadewit.scalasnake.SnakeService
import org.scalatest.{FlatSpec, Matchers}
import cats.effect._
import io.circe.syntax._
import org.http4s.dsl.io._
import com.github.alexadewit.scalasnake.requests.GameStartRequest
import com.github.alexadewit.scalasnake.requests.GameStartRequestCodec._
import org.http4s.{Method, Request}

object serviceFixture extends SnakeService[IO]

class SnakeServiceTest extends FlatSpec with Matchers {

  "The Root Endpoint" should "echo back the input value" in {
    val input = GameStartRequest(10, 10, UUID.randomUUID())
    val req = Request[IO](Method.POST, uri("/start")).withBody(input.asJson.toString()).unsafeRunSync
    val resp = serviceFixture.route().orNotFound.run(req).as[GameStartRequest].unsafeRunSync
    resp shouldBe input
  }
}
