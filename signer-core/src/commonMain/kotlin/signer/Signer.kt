package signer

import later.Later
import signer.credentials.Credential

interface Signer<T> {
    val state: State<T>
    fun signInWith(credentials: Credential): Later<Token>
    fun onAuthStateChanged(callback: (State<T>) -> Unit)
}