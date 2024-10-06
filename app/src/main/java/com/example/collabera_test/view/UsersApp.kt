package com.example.collabera_test.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.collabera_test.view_model.UsersViewModel
import com.example.collabera_test.R
import com.example.collabera_test.view_model.UserDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CollaberaTopBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "userInput") {
                composable("userInput") {
                    UserInputScreen(navController = navController)
                }
                composable("userList/{userCount}") { backStackEntry ->
                    val userCount = backStackEntry.arguments?.getString("userCount")?.toInt() ?: 0
                    val usersViewModel: UsersViewModel = hiltViewModel<UsersViewModel, UsersViewModel.Factory>(
                        creationCallback = { factory -> factory.create(size = userCount) }
                    )
                    UsersScreen(
                        navController = navController,
                        usersUIState = usersViewModel.usersUIState,
                        contentPadding = it
                    )
                }
                composable("userDetail/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: "WALA?asdasd?"
                    val userDetailsViewModel: UserDetailsViewModel = hiltViewModel<UserDetailsViewModel, UserDetailsViewModel.Factory>(
                        creationCallback = { factory -> factory.create(uuid = userId) }
                    )
                    UserDetailsScreen(
                        userDetailsUIState = userDetailsViewModel.userDetailsUIState,
                        contentPadding = it
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollaberaTopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}