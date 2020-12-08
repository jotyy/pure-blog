package top.jotyy.domain.extensions

import org.valiktor.ConstraintViolationException
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.ValidationFailure

fun ConstraintViolationException.toFailure(): Failure = ValidationFailure(
    this.constraintViolations
        .map {
            "Parameter ${it.property} requires ${it.constraint.name}"
        }
        .first()
)
