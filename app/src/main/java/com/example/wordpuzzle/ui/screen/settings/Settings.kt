package com.example.wordpuzzle.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.wordpuzzle.R
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Settings() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Image(
            painter = painterResource(id = R.drawable.settings_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.sdp),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
        ) {
            CustomSwitchBtn(
                modifier = Modifier
                    .padding(end = 15.sdp),
                height = 50.sdp,
                width = 90.sdp,
                circleButtonPadding = 4.sdp,
                outerBackgroundOnResource = R.drawable.soundbtn2,
                outerBackgroundOffResource = R.drawable.soundbtn1,
                circleBackgroundOnResource = R.drawable.soundfxbtn,
                circleBackgroundOffResource = R.drawable.soundfxbtn,
                stateOn = 1,
                stateOff = 0,
                initialValue = 0,
                onCheckedChanged = {}
            )
            CustomSwitchBtn(
                modifier = Modifier
                    .padding(start = 15.sdp),
                height = 50.sdp,
                width = 90.sdp,
                circleButtonPadding = 4.sdp,
                outerBackgroundOnResource = R.drawable.soundbtn2,
                outerBackgroundOffResource = R.drawable.soundbtn1,
                circleBackgroundOnResource = R.drawable.soundbtn_music,
                circleBackgroundOffResource = R.drawable.soundbtn_music,
                stateOn = 1,
                stateOff = 0,
                initialValue = 0,
                onCheckedChanged = {}
            )
        }

        SettingOptionBtn(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun CustomSwitchBtn(
    modifier: Modifier,
    height: Dp,
    width: Dp,
    circleButtonPadding: Dp,
    outerBackgroundOnResource: Int,
    outerBackgroundOffResource: Int,
    circleBackgroundOnResource: Int,
    circleBackgroundOffResource: Int,
    stateOn: Int,
    stateOff: Int,
    initialValue: Int,
    onCheckedChanged: (checked: Boolean) -> Unit
) {
    Column(modifier = modifier) {
        val swipeableState = rememberSwipeableState(
            initialValue = initialValue,
            confirmStateChange = { newState ->
                if (newState == stateOff) {
                    onCheckedChanged(false)
                } else {
                    onCheckedChanged(true)
                }
                true
            }
        )
        val sizePx = with(LocalDensity.current) {
            (width - height).toPx()
        } // Minus the height to avoid the inner box going outside
        val anchors =
            mapOf(0f to stateOff, sizePx to stateOn) // Maps anchor points (in px) to states

        val scope = rememberCoroutineScope()

        Row(
            modifier = Modifier
                .height(height)
                .width(width)
                .clip(RoundedCornerShape(height))
                .border(0.sdp, Color.Transparent, CircleShape)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
                .background(Color.Transparent)
                .then(
                    if (swipeableState.currentValue == stateOff)
                        Modifier.paint(
                            painterResource(id = outerBackgroundOffResource),
                            contentScale = ContentScale.FillBounds
                        )
                    else
                        Modifier.paint(
                            painterResource(id = outerBackgroundOnResource),
                            contentScale = ContentScale.FillBounds
                        )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .offset {
                        IntOffset(swipeableState.offset.value.roundToInt(), 0)
                    }
                    .size(height)
                    .padding(circleButtonPadding)
                    .clip(RoundedCornerShape(50))
                    .then(
                        if (swipeableState.currentValue == stateOff) {
                            Modifier.paint(
                                painterResource(id = circleBackgroundOffResource),
                                contentScale = ContentScale.FillBounds
                            )
                        } else {
                            Modifier.paint(
                                painterResource(id = circleBackgroundOnResource),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    )
                    .clickable {
                        scope.launch {
                            if (swipeableState.currentValue == stateOff) {
                                swipeableState.animateTo(stateOn)
                            } else {
                                swipeableState.animateTo(stateOff)
                            }
                        }
                    }
            )
        }
    }
}

@Composable
fun SettingOptionBtn(modifier: Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.privacy_policy_button),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.sdp)
                .clickable {},
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.reset_game_progress_button),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 80.sdp)
                .clickable {},
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.back_btn),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.sdp)
                .clickable {},
            contentDescription = null
        )
    }
}