package signer

sealed class State<out T> {
    object Unknown : State<Nothing>()

    data class SignedIn<T>(
        val principle: T,
        val token: Token
    ) : State<T>()

    object SignedOut : State<Nothing>()
}
