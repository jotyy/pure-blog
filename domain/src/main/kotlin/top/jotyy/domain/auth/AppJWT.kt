package top.jotyy.domain.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object AppJWT {
    const val CLAIM_NAME = "name"
    const val CLAIM_PWD = "password"
    private const val secret = "jotyy"
    private const val issuer = "pure-blog"
    private const val audience = "https://jotyy.top/"
    private const val VALIDITY_IN_MS = 36_000_00 * 24
    private val algorithm = Algorithm.HMAC256(secret)

    fun makeJwtVerifier(): JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .withAudience(audience)
            .build()

    fun generateToken(userName: String, password: String): String = JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim(CLAIM_NAME, userName)
        .withClaim(CLAIM_PWD, password)
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + VALIDITY_IN_MS)
}
