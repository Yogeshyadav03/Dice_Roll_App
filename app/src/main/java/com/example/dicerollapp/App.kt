package com.example.dicerollapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun App(modifier: Modifier = Modifier, innerPadding: PaddingValues = PaddingValues()) {
    var scorePlayer1 = remember { mutableStateOf(0) }
    var scorePlayer2 = remember { mutableStateOf(0) }

    var isPlayer1Turn = remember { mutableStateOf(true) }

    var currentImage = remember { mutableIntStateOf(0) }


    var images = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    fun reset(){
        scorePlayer1.value = 0
        scorePlayer2.value = 0
        isPlayer1Turn.value = true
        currentImage.value = 0

    }

    if (scorePlayer1.value >= 20 || scorePlayer2.value >= 20) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (scorePlayer1.value > scorePlayer2.value) {
                    Text(
                        text = "Player 1 Won",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(text = "Player 2 Won",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { reset() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape =  RoundedCornerShape(30),) {
                    Text(text = "Reset",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp))
                }
            }
        }
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (isPlayer1Turn.value)
                Text(
                    text = "Player 1 Turn",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            else
                Text(
                    text = "Player 2 Turn",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )

            Spacer(modifier = Modifier.height(120.dp))
          Row(
              horizontalArrangement = Arrangement.SpaceEvenly
          )
            {
              Text(
                  text = "Player 1 Score: ${scorePlayer1.value}",
                  fontSize = 20.sp,
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold
              )

                Spacer(modifier = Modifier.width(30.dp))

              Text(
                  text = "Player 2 Score: ${scorePlayer2.value}",
                  fontSize = 20.sp,
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold

              )
          }

            Spacer(modifier = Modifier.height(80.dp))
            Image(

                painter = if (currentImage.value == 0){
                  painterResource(id = R.drawable.dice_logo)
                }else painterResource(images.get(currentImage.value - 1)),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(58.dp))
            
            Row {
                Button(onClick = {
                    val random =
                        Random.nextInt(6) + 1//0 to 6 value aayegi last wala value inculde nhi krta
                    currentImage.value = random
                    scorePlayer1.value += random
                    isPlayer1Turn.value = !isPlayer1Turn.value
                    println(isPlayer1Turn)
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape =  RoundedCornerShape(30),
                    enabled = if (isPlayer1Turn.value) true else false) {
                    Text(
                        text = "Player 1",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)

                    )
                }
                Spacer(modifier = Modifier.width(30.dp))

                Button(
                    onClick = {
                        val random = Random.nextInt(6) + 1
                        currentImage.value = random
                        scorePlayer2.value += random
                        isPlayer1Turn.value = !isPlayer1Turn.value
                        println(isPlayer1Turn)
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape =  RoundedCornerShape(30),
                    enabled = if (isPlayer1Turn.value) false else true
                ) {
                    Text(text = "Player 2",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)


                        )
                }
            }
        }
    }
}