package com.sweak.qralarm.ui.screens.shared.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.sweak.qralarm.R
import com.sweak.qralarm.ui.screens.shared.popBackStackThrottled

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    IconButton(
        onClick = { navController.popBackStackThrottled(lifecycleOwner) },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "Back button",
            tint = MaterialTheme.colors.secondary
        )
    }
}