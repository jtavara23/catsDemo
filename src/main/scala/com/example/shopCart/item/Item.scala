package com.example.shopCart.item

import io.estatico.newtype.macros.newtype

import java.util.UUID

final case class Item(id: Item.Id,
                      name: Item.Name,
                      description: Item.Description,
                      price: Double)

final case class CreateItem(name: Item.Name,
                            description: Item.Description,
                            price: Double)

final case class UpdateItem(id: Item.Id, price: Double)

object Item {
  @newtype case class Id(value: UUID)
  @newtype case class Name(value: String)
  @newtype case class Description(value: String)
  @newtype case class Quantity(value: Int)
}
