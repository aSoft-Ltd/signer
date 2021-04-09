import kotlinx.coroutines.delay
import signer.State
import signer.credentials.EmailPassword
import test.asyncTest
import kotlin.test.Test

class SyntaxCheckTest {
    private val signer = MockSigner()

    @Test
    fun should_sign_in_a_user() = asyncTest {
        signer.onAuthStateChanged {
            println("Listener 1: $it")
        }
        signer.signInWith(EmailPassword("test@test.com", "123456"))
        delay(500)
        signer.onAuthStateChanged {
            println("Listener 2: $it")
        }
        delay(2)
    }
}