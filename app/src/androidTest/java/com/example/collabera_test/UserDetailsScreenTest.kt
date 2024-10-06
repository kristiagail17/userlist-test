package com.example.collabera_test

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.collabera_test.model.database.User
import com.example.collabera_test.ui.theme.CollaberatestTheme
import com.example.collabera_test.view.UserDetailsScreen
import com.example.collabera_test.view_model.UserDetailsUIState
import org.junit.Rule
import org.junit.Test

class UserDetailsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testUserDetailsScreenDisplaysUser() {
        val expectedUser = User(
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
        )


        composeTestRule.setContent {
            CollaberatestTheme {
                UserDetailsScreen(
                    UserDetailsUIState.Success(expectedUser),
                    Modifier.fillMaxSize(),
                    PaddingValues(30.dp)
                )
            }
        }

        composeTestRule.onNodeWithText(expectedUser.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedUser.gender).assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedUser.location).assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedUser.email).assertIsDisplayed()
        composeTestRule.onNodeWithText("${expectedUser.age} years old | ${expectedUser.dob}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedUser.phone).assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedUser.cell).assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedUser.id).assertIsDisplayed()

    }


    @Test
    fun testUserDetailsScreenDisplaysLoading() {
        composeTestRule.setContent {
            CollaberatestTheme {
                UserDetailsScreen(
                    UserDetailsUIState.Loading,
                    Modifier.fillMaxSize()
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun testUserDetailsScreenDisplaysError() {
        composeTestRule.setContent {
            CollaberatestTheme {
                UserDetailsScreen(
                    UserDetailsUIState.Error,
                    Modifier.fillMaxSize()
                )
            }
        }

        composeTestRule.onNodeWithText("Failed to load").assertIsDisplayed()
    }
}