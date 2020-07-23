package top.jotyy.core.abstraction

interface IService<E, P> {
    suspend fun getAll(): Result<List<E>>
    suspend fun getById(id: Int): Result<E>
    suspend fun add(param: P): Result<E>
    suspend fun update(id: Int, param: P): Result<E>
    suspend fun delete(id: Int): Result<String>
}
