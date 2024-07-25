package com.example.wordpuzzle.ui.screen.mainMenu

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wordpuzzle.R
import kotlinx.coroutines.delay

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenu(){
    Column(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop
        ),
        verticalArrangement = Arrangement.SpaceAround) {
        TransitionAnimation(Modifier.align(Alignment.CenterHorizontally))

        //button
        Column(Modifier
            .align(Alignment.CenterHorizontally)){
            Image(
                painter = painterResource(id = R.drawable.play),
                modifier = Modifier.padding(bottom = 10.dp),
                contentDescription = null)
            Image(
                painter = painterResource(id = R.drawable.exit),
                modifier = Modifier.padding(top = 10.dp),
                contentDescription = null)
        }

        Image(
            painter = painterResource(id = R.drawable.settings_2),
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.End)
                .padding(end = 15.dp),
            contentDescription = null)
    }
}

@Composable
fun TransitionAnimation(modifier: Modifier){
    var isAnimated by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isAnimated, label = "transition")

    // Automatically change isAnimated after a delay
    LaunchedEffect(Unit) {
        while (true) {
            isAnimated = !isAnimated
            delay(1000) // Adjust delay time as needed
        }
    }

    val layerIconOffset by transition.animateOffset(
        transitionSpec = {
            if(this.targetState){
               tween(1000)
            } else {
                tween(1000)
            }
        }, label = "layerIconOffset"){ animated ->
        if(animated){
            Offset(-50f, 20f)
        } else {
            Offset(-5f, 20f)
        }
    }

    val layerIconSize by transition.animateDp(transitionSpec = {
        tween(1000)
    }, ""){animated ->
        if(animated) 80.dp else 80.dp
    }

    Box(modifier = modifier){
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ){
            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = null)
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ){
            Image(
                painter = painterResource(id = R.drawable.layer2),
                contentDescription = null,
                modifier = Modifier
                    .size(layerIconSize)
                    .alpha(1.0f)
                    .offset(layerIconOffset.x.dp, layerIconOffset.y.dp))
        }
    }
}