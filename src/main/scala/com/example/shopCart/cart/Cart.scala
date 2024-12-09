package com.example.shopCart.cart

import com.example.shopCart.item.Item

case class Cart(items: List[CartItem])

object Cart {
  val empty: Cart = Cart(Nil)
}

case class CartItem(itemId: Item.Id, quantity: Item.Quantity)
