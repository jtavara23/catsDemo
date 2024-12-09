package com.example.shopCart.order

import com.example.shopCart.item.Item
import com.example.shopCart.payment.Payment
import com.example.shopCart.user.User
import io.estatico.newtype.macros.newtype

import java.util.UUID

final case class OrderItem(itemId: Item.Id,
                           price: Double,
                           quantity: Item.Quantity)

final case class Order(id: Order.Id,
                       status: String,
                       userId: User.Id,
                       paymentId: Option[Payment.Id],
                       items: List[OrderItem],
                       totalPrice: Double)

final case class OrderCheckout(userId: User.Id,
                               items: List[OrderItem],
                               totalPrice: Double,
                               status: String = "Awaiting")

final case class OrderPayment(id: Order.Id,
                              paymentId: Payment.Id,
                              status: String = "Processing")

object Order {
  @newtype case class Id(value: UUID)
}
