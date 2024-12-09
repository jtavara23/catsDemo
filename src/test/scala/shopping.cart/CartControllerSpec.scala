package shopping.cart

import cats.effect.IO
import org.http4s._
import org.http4s.implicits._
import org.http4s.circe._
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers
import io.circe.generic.auto._
import com.example.shopCart.cart.{Cart, CartController, CartItem, CartService, CartServiceImpl}
import com.example.shopCart.user.User
import org.mockito.MockitoSugar.mock
import org.mockito.scalatest.MockitoSugar
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

import java.util.UUID

class CartControllerSpec extends AsyncFreeSpec with AsyncIOSpec with Matchers {

  "CartController" should {

    "GET /shopping-cart/:userId should return the cart for the user" in {
      val cartService = mockService

      val controller = new CartController(cartService)[IO]
      val request = Request[IO](uri = uri"/shopping-cart", method = Method.GET)
      val response = controller.routes.orNotFound.run(request)

      response.flatMap { resp =>
        resp.status shouldBe Status.Ok
        resp.as[Cart].map { cart =>
          cart.items should have size 1
        }
      }
    }
  }
  def mockService: CartService[IO] = mock[CartService[IO]]
}
