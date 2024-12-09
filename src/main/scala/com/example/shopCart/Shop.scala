package com.example.shopCart

import cats.effect.Sync
import com.example.shopCart.cart.{CartController, CartService}

final class Shop[F[_]: Sync](val cartController: CartController[F])

object Shop {
  def apply[F[_]: Sync](cartService: CartService[F]): Shop[F] = {
    val cartController = new CartController[F](cartService)
    new Shop[F](cartController)
  }
}
