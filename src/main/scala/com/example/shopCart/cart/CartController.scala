package com.example.shopCart.cart

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import cats.effect.Sync
import cats.implicits._
import com.example.shopCart.item.Item
import com.example.shopCart.user.User
import io.circe.generic.auto._

import java.util.UUID

class CartController[F[_]: Sync](cartService: CartService[F])
    extends Http4sDsl[F] {
  val prefixPath = "shopping"

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / prefixPath / userId =>
      for {
        cart <- cartService.get(User.Id(UUID.fromString(userId)))
        resp <- Ok(cart.asJson)
      } yield resp

    case DELETE -> Root / prefixPath / userId / itemId =>
      for {
        _ <- cartService.removeItem(
          User.Id(UUID.fromString(userId)),
          Item.Id(UUID.fromString(itemId))
        )
        resp <- NoContent()
      } yield resp

    case req @ POST -> Root / prefixPath / userId =>
      for {
        cart <- req.as[Cart]
        _ <- cartService.add(User.Id(UUID.fromString(userId)), cart)
        resp <- Created
      } yield resp

    case req @ PUT -> Root / prefixPath / userId =>
      for {
        cart <- req.as[Cart]
        _ <- cartService.update(User.Id(UUID.fromString(userId)), cart)
        resp <- Ok
      } yield resp

  }
}
