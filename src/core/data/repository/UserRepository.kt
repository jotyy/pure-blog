package core.data.repository

import core.functional.Either
import core.functional.Failure
import core.functional.Success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.core.data.mapper.toModel
import top.jotyy.core.data.model.UserModel
import top.jotyy.core.data.param.AddUserParam
import top.jotyy.core.data.table.User
import top.jotyy.core.data.table.Users

class UserRepository {

    suspend fun getUserByUsername(userName: String) =
        flow<Either<UserModel>> {
            var userModel: UserModel? = null
            transaction {
                userModel = User.find {
                    Users.userName eq userName
                }.first().toModel()
            }
            emit(Success(userModel!!))
        }.catch {
            emit(Failure(it.message ?: ""))
        }

    suspend fun addUser(parameter: AddUserParam) =
        flow<Either<String>> {
            transaction {
                Users.insertAndGetId {
                    it[userName] = parameter.userName
                    it[password] = parameter.password
                    it[nickName] = parameter.nickName
                }
            }
            emit(Success(parameter.userName))
        }
            .catch {
                emit(Failure(it.message ?: ""))
            }

    suspend fun deleteUser(id: Int) =
        flow<Either<*>> {
            transaction {
                Users.deleteWhere {
                    Users.id eq id
                }
            }
            emit(Success(id))
        }.catch {
            emit(Failure(it.message ?: ""))
        }
}
