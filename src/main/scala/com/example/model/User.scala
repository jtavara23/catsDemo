package com.example.model

import java.util.UUID
import io.estatico.newtype.macros.newtype

object User {
  @newtype case class Id(value: UUID)
}