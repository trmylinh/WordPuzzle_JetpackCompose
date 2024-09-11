package com.example.wordpuzzle.ui.screen.selectPuzzle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wordpuzzle.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ItemSelectPuzzle(isLocked: Boolean, image: Int){
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxSize().padding(vertical = 15.sdp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        )
    ) {
        Box(Modifier.align(Alignment.CenterHorizontally)){
            Image(
                painter = painterResource(
                    id =if (isLocked) R.drawable.slice2
                    else R.drawable.slice7
                ),
                contentScale = ContentScale.FillWidth,
                contentDescription = null)

            Column(Modifier.align(Alignment.Center)) {
                Image(
                    painter = painterResource(id = image),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    contentDescription = null)

                Box(Modifier.padding(vertical = 5.sdp).align(Alignment.CenterHorizontally)){
                    Image(
                        painter = painterResource(id = R.drawable.bar),
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = null)

                    Text(text = "0/10", modifier = Modifier.align(Alignment.Center))
                }

            }
        }
    }
}