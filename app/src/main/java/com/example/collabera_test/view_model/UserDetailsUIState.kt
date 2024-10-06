package com.example.collabera_test.view_model

import com.example.collabera_test.model.database.User

sealed interface UserDetailsUIState {
    data class Success(val user: User) : UserDetailsUIState
    data object Error : UserDetailsUIState
    data object Loading : UserDetailsUIState
}