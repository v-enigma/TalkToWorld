package com.example.talktoworld.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TalkToWorld(){
      val viewModel : TalkViewModel = viewModel(factory = TalkViewModel.Factory)
      val contentOfMars = viewModel.talkUiState
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            title = { Text("Mars Photos") })
    }) {
        Surface(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            HomeScreen( onReload = viewModel::getMarsPhoto,
                contentOfMars,
                Modifier
                    .padding()
                    .fillMaxSize()
            )
        }
    }
}