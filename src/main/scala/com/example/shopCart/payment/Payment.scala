package com.example.shopCart.payment

import com.example.shopCart.order.Order
import io.estatico.newtype.macros.newtype

import java.util.UUID
final case class Payment(order: Order, card: String)

object Payment {
  @newtype case class Id(value: UUID)
}
