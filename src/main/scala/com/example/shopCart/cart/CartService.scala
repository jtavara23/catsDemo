package com.example.shopCart.cart

import cats.effect.Sync
import com.example.shopCart.item.Item
import com.example.shopCart.user.User

import scala.collection.mutable

trait CartService[F[_]] {
  def add(userId: User.Id, cart: Cart): F[Unit]
  def update(userId: User.Id, cart: Cart): F[Unit]
  def removeItem(userId: User.Id, itemId: Item.Id): F[Unit]
  def delete(userId: User.Id): F[Unit]
  def get(userId: User.Id): F[Cart]
}

case class CartServiceImpl[F[_]: Sync]() extends CartService[F] {
  val carts = mutable.Map.empty[User.Id, Cart]

  override def add(userId: User.Id, cart: Cart): F[Unit] = Sync[F].delay {
    val existingCart = carts.getOrElse(userId, Cart.empty)
    val updatedCart =
      existingCart.copy(items = existingCart.items ++ cart.items)
    carts.update(userId, updatedCart)
  }

  override def update(userId: User.Id, cart: Cart): F[Unit] = Sync[F].delay {
    carts.get(userId).foreach { existingCart =>
      val updatedItems = existingCart.items.map { item =>
        cart.items.find(_.itemId == item.itemId).getOrElse(item)
      }
      carts.update(userId, existingCart.copy(items = updatedItems))
    }
  }

  override def removeItem(userId: User.Id, itemId: Item.Id): F[Unit] =
    Sync[F].delay {
      carts.get(userId).foreach { existingCart =>
        val updatedItems = existingCart.items.filterNot(_.itemId == itemId)
        carts.update(userId, existingCart.copy(items = updatedItems))
      }
    }

  override def delete(userId: User.Id): F[Unit] = Sync[F].delay {
    carts.remove(userId)
  }

  override def get(userId: User.Id): F[Cart] = Sync[F].delay {
    carts.getOrElse(userId, Cart.empty)
  }
}
