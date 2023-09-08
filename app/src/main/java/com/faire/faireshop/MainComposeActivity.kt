package com.faire.faireshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.faire.faireshop.ui.compose.productslist.ProductsListScreen
import com.faire.faireshop.ui.theme.FaireShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaireShopTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
                    }
                ) { contentPadding ->
                    //put nav host here
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    ) {
                        ProductsListScreen()
                    }
                }
            }
        }
    }
}

