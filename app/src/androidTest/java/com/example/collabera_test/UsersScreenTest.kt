package com.example.collabera_test

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.collabera_test.model.database.User
import com.example.collabera_test.ui.theme.CollaberatestTheme
import com.example.collabera_test.view.UsersScreen
import com.example.collabera_test.view_model.UsersUIState
import org.junit.Rule
import org.junit.Test

class UsersScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testUsersScreenDisplaysUsers() {
        val expectedUsers = listOf(
            User(
                name = "Alice",
                gender = "Female",
                location = "Location A",
                timezone = "Timezone A",
                email = "Email@Email.com",
                dob = "May 12 1987",
                age = "23 years old",
                phone = "+574235788",
                cell = "+67423576788",
                id = "ID-VAL",
                picture = "https://randomuser.me/api/portraits/women/1.jpg"
            ),
            User(
                name = "Bob",
                gender = "Male",
                location = "Location B",
                timezone = "Timezone B",
                email = "Email@Email2.com",
                dob = "May 13 1987",
                age = "32 years old",
                phone = "+574215788",
                cell = "+674234576788",
                id = "ID-VAL",
                picture = "https://randomuser.me/api/portraits/men/1.jpg"
            )
        )

        composeTestRule.setContent {
            CollaberatestTheme {
                UsersScreen(
                    rememberNavController(),
                    UsersUIState.Success(expectedUsers),
                    Modifier.fillMaxSize()
                )
            }
        }

        expectedUsers.forEach { user ->
            composeTestRule.onNodeWithText(user.name).assertIsDisplayed()
            composeTestRule.onNodeWithText(user.gender).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.location).assertIsDisplayed()
            composeTestRule.onNodeWithText(user.timezone).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.email).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.dob).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.age).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.phone).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.cell).assertDoesNotExist()
            composeTestRule.onNodeWithText(user.id).assertDoesNotExist()
        }

    }


    @Test
    fun testUsersScreenDisplaysLoading() {
        composeTestRule.setContent {
            CollaberatestTheme {
                UsersScreen(
                    rememberNavController(),
                    UsersUIState.Loading,
                    Modifier.fillMaxSize()
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun testUsersScreenDisplaysError() {
        composeTestRule.setContent {
            CollaberatestTheme {
                UsersScreen(
                    rememberNavController(),
                    UsersUIState.Error,
                    Modifier.fillMaxSize()
                )
            }
        }

        composeTestRule.onNodeWithText("Failed to load").assertIsDisplayed()
    }
}