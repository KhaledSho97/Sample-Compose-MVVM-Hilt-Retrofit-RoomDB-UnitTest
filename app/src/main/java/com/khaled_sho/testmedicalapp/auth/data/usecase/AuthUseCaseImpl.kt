package com.khaled_sho.testmedicalapp.auth.data.usecase


import com.khaled_sho.testmedicalapp.auth.data.model.User
import com.khaled_sho.testmedicalapp.auth.data.repository.AuthRepository
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
) : AuthUseCase {

    override suspend fun getLoggedUser(user_: User) = repository.getLoggedUser(user_)
    override suspend fun getLoggedUser() = repository.getLoggedUser()

    override suspend fun updateUser(user: User) = repository.updateUser(user)
    override suspend fun logout() = repository.logout()

    override suspend fun insertUser(user: User) = repository.updateUser(user)


    override suspend fun deleteUser(user: User): Int =
        repository.deleteUser(user)

}