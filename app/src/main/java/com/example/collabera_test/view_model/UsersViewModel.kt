package com.example.collabera_test.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.collabera_test.model.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(assistedFactory = UsersViewModel.Factory::class)
class UsersViewModel @AssistedInject constructor(private val userRepository: UserRepository, @Assisted private val size: Int): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(size: Int): UsersViewModel
    }

    var usersUIState: UsersUIState by mutableStateOf(UsersUIState.Loading)
        private set

    init {
        getUsers(size)
    }

    private fun getUsers(size: Int) {
        viewModelScope.launch {
            usersUIState = UsersUIState.Loading
            usersUIState = try {
                val users = userRepository.fetchAndSaveUsers(size)
                println("users retrieved: ${users.size}")
                UsersUIState.Success(
                    users = users
                )
            } catch (e: Exception) {
                println("failed? ${e.message}")
                UsersUIState.Error
            }
        }
    }
}