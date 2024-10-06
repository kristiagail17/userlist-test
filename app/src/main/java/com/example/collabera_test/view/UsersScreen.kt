package com.example.collabera_test.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.collabera_test.model.database.User
import com.example.collabera_test.view_model.UsersUIState

@Composable
fun UsersScreen(
    navController: NavController,
    usersUIState: UsersUIState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (usersUIState) {
        is UsersUIState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UsersUIState.Success -> ResultScreen(
            navController, usersUIState.users, modifier = modifier.padding(contentPadding)
        )
        is UsersUIState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ResultScreen(navController: NavController, users: List<User>, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        LazyColumn {
            items(users) { user ->
                UserCard(navController = navController, user = user)
            }
        }
    }
}

@Composable
fun UserCard(navController: NavController, user: User) {
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 100.dp)
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { navController.navigate("userDetail/${user.uuid}") }
    ) {
        Row (
            modifier = Modifier.padding(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = user.picture,
                    contentDescription = null,
                    modifier = Modifier
                        .height(146.dp)
                        .clip(shape = RoundedCornerShape(size = 32.dp)
                    )
                )
            }
            Column {
                Text(text = user.name, style = MaterialTheme.typography.headlineSmall)
                HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(end = 12.dp))
                Text(text = user.location)
            }
        }
    }
}
