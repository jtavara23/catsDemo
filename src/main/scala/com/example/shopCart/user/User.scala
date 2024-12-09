package com.example.shopCart.user

import java.util.UUID
import io.estatico.newtype.macros.newtype

object User {
  @newtype case class Id(value: UUID)
}
