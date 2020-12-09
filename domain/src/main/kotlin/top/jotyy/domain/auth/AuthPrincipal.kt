package top.jotyy.domain.auth

import io.ktor.auth.*

data class AuthPrincipal(val userName: String) : Principal
