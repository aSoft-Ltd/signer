import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import later.Later
import signer.credentials.Credential
import signer.Signer
import signer.State
import signer.Token
import signer.credentials.EmailPassword

class MockSigner : Signer<TestUser> {
    private val stateManager = MutableStateFlow<State<TestUser>>(State.Unknown)
    override val state: State<TestUser>
        get() = stateManager.value

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun signInWith(credentials: Credential) = Later<Token> { res, rej ->
        when (credentials) {
            is EmailPassword -> {
                val user = TestUser(credentials.email, credentials.password)
                stateManager.value = State.SignedIn(user, user.toString())
                res(user.toString())
            }
            else -> rej(Throwable("Invalid credential type"))
        }.let { }
    }

    override fun onAuthStateChanged(callback: (State<TestUser>) -> Unit) {
        scope.launch {
            stateManager.collect { callback(it) }
        }
    }
}