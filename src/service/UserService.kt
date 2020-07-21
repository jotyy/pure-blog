package top.jotyy.service

import top.jotyy.model.dto.UserDTO
import top.jotyy.model.entity.UserEntity
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
     * @param userDTO user info
     */
    fun add(userDTO: UserDTO)

    /**
     * Modify a user
     *
     * @param userDTO user info
     * @param id userId
     */
    fun update(userDTO: UserDTO, id: Int)

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

    override fun add(userDTO: UserDTO) = userRepository.add(userDTO)

    override fun update(userDTO: UserDTO, id: Int) = userRepository.update(userDTO, id)

    override fun delete(id: Int) = userRepository.delete(id)
}
