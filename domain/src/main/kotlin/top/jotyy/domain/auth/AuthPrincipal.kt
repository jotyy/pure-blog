package top.jotyy.domain.auth

import io.ktor.auth.*

/**
 * Simple auth principal object
 */
data class AuthPrincipal(val userName: String) : Principal
