package com.example

import cats.effect.{IO, IOApp}
import com.example.shopCart.Shop
import com.example.shopCart.cart.{CartService, CartServiceImpl}
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._

object Main extends IOApp.Simple {
  def run: IO[Unit] = {

    val cartService: CartService[IO] = CartServiceImpl[IO]()
    val shop = Shop[IO](cartService)

    val httpApp = (shop.cartController.routes).orNotFound

    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
  }
}
