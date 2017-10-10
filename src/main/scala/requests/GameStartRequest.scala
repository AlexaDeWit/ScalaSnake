package com.github.alexadewit.scalasnake.requests

import java.util.UUID

import cats.effect._
import io.circe._
import org.http4s.EntityEncoder
import org.http4s.circe._



case class GameStartRequest(Width: Int, height: Int, gameId: UUID) {

}

object GameStartRequestCodec {

  implicit val decodeGameStartRequest : Decoder[GameStartRequest] =
    Decoder.forProduct3("width", "height", "game_id")(
      (w: Int, h: Int, id: String) => GameStartRequest(w, h, UUID.fromString(id))
    )

  implicit val encodeGameStartRequest : Encoder[GameStartRequest] =
    Encoder.forProduct3("width", "height", "game_id")(
      req => ( req.Width, req.height, req.gameId.toString )
    )

  implicit def gameStartRequestDecoder[F[_]: Effect] = jsonOf[F, GameStartRequest]
}
