package com.example.collabera_test

import com.example.collabera_test.model.database.User
import com.example.collabera_test.model.repository.UserRepository
import com.example.collabera_test.view_model.UsersUIState
import com.example.collabera_test.view_model.UsersViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Rule
import org.junit.Test


class UsersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var usersViewModel: UsersViewModel
    private val userRepository: UserRepository = mockk(relaxed = true)

    @Test
    fun `Should update ui state to success when fetchAndSaveUsers is called`() {
        val expectedUsers = listOf(User(name = "Alice"), User(name = "Bob"))
        coEvery { userRepository.fetchAndSaveUsers(any()) } returns expectedUsers

        usersViewModel = UsersViewModel(userRepository, 2)

        assert(usersViewModel.usersUIState == UsersUIState.Success(expectedUsers))
        assert((usersViewModel.usersUIState as UsersUIState.Success).users == expectedUsers)
    }

    @Test
    fun `Should update ui state to error when fetchAndSaveUsers throws exception`() {
        coEvery { userRepository.fetchAndSaveUsers(any()) } throws Exception()

        usersViewModel = UsersViewModel(userRepository, 2)

        assert(usersViewModel.usersUIState == UsersUIState.Error)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}

