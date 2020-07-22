package top.jotyy.core.service

import core.data.entity.UserEntity
import top.jotyy.core.data.param.UserParam
import top.jotyy.repository.UserRepository

/**
 * User Service
 */
interface UserService {

    /**
     * Get all user
     *
     * @return [UserEntity] user list
     */
    fun getAll(): List<UserEntity>

    /**
     * Get user by id
     *
     * @param id userId
     * @return [UserEntity] User
     */
    fun getById(id: Int): UserEntity

    /**
     * Create a new user
     *
     * @param userParam user info
     */
    fun add(userParam: UserParam)

    /**
     * Modify a user
     *
     * @param userParam user info
     * @param id userId
     */
    fun update(userParam: UserParam, id: Int)

    /**
     * Delete a user by id
     *
     * @param id userId
     */
    fun delete(id: Int)
}

class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override fun getAll(): List<UserEntity> = userRepository.getAll()

    override fun getById(id: Int): UserEntity = userRepository.getById(id)

    override fun add(userParam: UserParam) = userRepository.add(userParam)

    override fun update(userParam: UserParam, id: Int) = userRepository.update(userParam, id)

    override fun delete(id: Int) = userRepository.delete(id)
}
