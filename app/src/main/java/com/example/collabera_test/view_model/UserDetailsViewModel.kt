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

@HiltViewModel(assistedFactory = UserDetailsViewModel.Factory::class)
class UserDetailsViewModel @AssistedInject constructor(private val userRepository: UserRepository, @Assisted private val uuid: String): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(uuid: String): UserDetailsViewModel
    }

    var userDetailsUIState: UserDetailsUIState by mutableStateOf(UserDetailsUIState.Loading)
        private set

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            userDetailsUIState = UserDetailsUIState.Loading
            userDetailsUIState = try {
                val user = userRepository.getUserById(uuid)
                println("user retrieved: ${user?.uuid}")
                user?.let {
                    UserDetailsUIState.Success(
                        user = it
                    )
                }?: UserDetailsUIState.Error
            } catch (e: Exception) {
                println("failed: ${e.message}")
                UserDetailsUIState.Error
            }
        }
    }
}