package com.example.collabera_test.view_model

import com.example.collabera_test.model.database.User

sealed interface UsersUIState {
    data class Success(val users: List<User>) : UsersUIState
    data object Error : UsersUIState
    data object Loading : UsersUIState
}