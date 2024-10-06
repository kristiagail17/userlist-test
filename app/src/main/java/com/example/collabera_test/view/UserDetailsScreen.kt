package com.example.collabera_test.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage
import com.example.collabera_test.model.database.User
import com.example.collabera_test.view_model.UserDetailsUIState

@Composable
fun UserDetailsScreen(
    userDetailsUIState: UserDetailsUIState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (userDetailsUIState) {
        is UserDetailsUIState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UserDetailsUIState.Success -> DetailsScreen(userDetailsUIState.user, modifier = modifier.padding(contentPadding))
        is UserDetailsUIState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}


@Composable
fun DetailsScreen(user: User, modifier: Modifier) {
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = user.picture,
                contentDescription = null,
                modifier = Modifier
                    .height(146.dp)
                    .clip(shape = RoundedCornerShape(size = 32.dp))
                    .align(Alignment.CenterHorizontally)
            )

            Text(text = user.name, style = MaterialTheme.typography.headlineSmall)
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(end = 12.dp))
            Text(text = user.location)
            Text(text = "${user.age} years old | ${user.dob}")
            Text(text = user.gender)
            Text(text = user.phone)
            Text(text = user.cell)
            Text(text = user.email)
            Text(text = user.id)
        }
    }
}