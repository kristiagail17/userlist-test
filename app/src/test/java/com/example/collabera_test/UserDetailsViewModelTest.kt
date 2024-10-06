package com.example.collabera_test

import com.example.collabera_test.model.database.User
import com.example.collabera_test.model.repository.UserRepository
import com.example.collabera_test.view_model.UserDetailsUIState
import com.example.collabera_test.view_model.UserDetailsViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Rule
import org.junit.Test

class UserDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val uuid = "550e8400-e29b-41d4-a716-446655440000"

    @Test
    fun `Should update ui state to success when fetchAndSaveUsers is called`() {
        val expectedUser = User(name = "Alice")
        coEvery { userRepository.getUserById(any()) } returns expectedUser

        userDetailsViewModel = UserDetailsViewModel(userRepository, uuid)

        assert(userDetailsViewModel.userDetailsUIState == UserDetailsUIState.Success(expectedUser))
        assert((userDetailsViewModel.userDetailsUIState as UserDetailsUIState.Success).user == expectedUser)
    }

    @Test
    fun `Should update ui state to error when fetchAndSaveUsers throws exception`() {
        coEvery { userRepository.getUserById(any()) } throws Exception()

        userDetailsViewModel = UserDetailsViewModel(userRepository, uuid)

        assert(userDetailsViewModel.userDetailsUIState == UserDetailsUIState.Error)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}