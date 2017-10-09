package com.github.alexadewit.scalasnake
import fs2._
import cats.effect._
import cats.implicits._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp

object WebsocketChat extends WebsocketChatApp[IO]

class WebsocketChatApp[F[_] : Effect] extends StreamApp[F] {

  def stream(args: List[String], requestShutdown: F[Unit]): Stream[F, Nothing] =
        BlazeBuilder[F]
        .bindHttp(8080, "0.0.0.0")
        .mountService((new SnakeService[F]()).route(),"/")
        .serve

}