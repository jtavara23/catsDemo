package com.example.model

import java.util.UUID
import io.estatico.newtype.macros.newtype
final case class Payment(order: Order, card: String)

object Payment {
  @newtype case class Id(value: UUID)
}
