package top.jotyy.domain.extensions

import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.mapToMessage
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.ValidationFailure

fun ConstraintViolationException.toFailure(): Failure = ValidationFailure(
    this.constraintViolations
        .mapToMessage()
        .map {
            "Parameter ${it.property}: ${it.message}"
        }
        .first()
)
