package top.jotyy.core.abstraction

import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import top.jotyy.core.exception.EmptyResultError

interface IRepository<E, P> {
    suspend fun <T, R> dbExecute(
        block: suspend () -> T?,
        transform: (T?) -> Result<R>
    ): Result<R> {
        return newSuspendedTransaction {
            block().let {
                return@newSuspendedTransaction transform(it)
            }
        }
    }

    fun <T> emptyOrNot(t: T?): Result<T> =
        t?.let { Success(t) } ?: Failure(EmptyResultError())

    suspend fun getAll(): Result<List<E>>
    suspend fun getById(id: Int): Result<E>
    suspend fun add(param: P): Result<E>
    suspend fun update(id: Int, param: P): Result<E>
    suspend fun delete(id: Int): Result<String>
}
