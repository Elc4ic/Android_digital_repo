package com.example.digital_kaf.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digital_kaf.R
import com.example.digital_kaf.ui.components.PrimaryButton
import com.example.digital_kaf.ui.theme.Typography

@Composable
fun WelcomeScreen(
    navController: NavController? = null,
) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        bitmap = ImageBitmap.imageResource(R.drawable.bicycle_image),
                        contentDescription = "WelcomeImage"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            stringResource(R.string.welcome_main_text),
                            style = Typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            stringResource(R.string.welcome_sub_text),
                            style = Typography.labelLarge,
                        )
                        PrimaryButton(
                            onClick = { navController?.navigate(Routes.Registration.route) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                stringResource(R.string.sign_up),
                                style = Typography.bodyMedium
                            )
                        }
                        TextButton(
                            onClick = { navController?.navigate(Routes.Login.route) }
                        ) {
                            Text(
                                stringResource(R.string.already_account),
                                style = Typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun WelcomePreview() {
    WelcomeScreen()
}