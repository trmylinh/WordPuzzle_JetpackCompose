package com.example.wordpuzzle.ui.screen.selectPuzzle

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wordpuzzle.R

val listPuzzle = listOf(
    R.drawable.slice1,
    R.drawable.slice3,
    R.drawable.slice4,
    R.drawable.slice5,
    R.drawable.slice6,
    R.drawable.countries,
    R.drawable.hobbies,
    R.drawable.sport,
)

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SelectPuzzle(onBackPressed: () -> Unit = {}){
   Box{
       Image(
           modifier = Modifier.fillMaxSize(),
           painter = painterResource(id = R.drawable.background),
           contentScale = ContentScale.Crop,
           contentDescription = null
       )

       Scaffold(
           containerColor = Color.Transparent,
           topBar = {
               Row(
                   modifier = Modifier
                       .align(Alignment.TopCenter)
                       .statusBarsPadding(),

               ) {
                   Image(
                       modifier = Modifier
                           .fillMaxWidth(0.2f)
                           .align(Alignment.CenterVertically).clickable { onBackPressed() },
                       painter = painterResource(id = R.drawable.back),
                       contentDescription = null
                   )

                   Image(
                       modifier = Modifier
                           .fillMaxWidth(0.8f)
                           .align(Alignment.CenterVertically),
                       painter = painterResource(id = R.drawable.select_puzzle_title),
                       contentDescription = null
                   )
               }
           },
       ){ innerPadding ->
           LazyColumn(modifier = Modifier.padding(innerPadding)){
               items(listPuzzle.size){ index ->
                   ItemSelectPuzzle(true, listPuzzle[index])
               }
           }
       }

   }
}