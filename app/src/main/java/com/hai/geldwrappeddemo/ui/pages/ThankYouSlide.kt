package com.hai.geldwrappeddemo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ThankYouSlide(){
   Box(
       modifier = Modifier
           .fillMaxSize()
           .background(Color(0xFF2E2E2E)),
       contentAlignment = Alignment.Center
   ){
      Column(horizontalAlignment = Alignment.CenterHorizontally){
         Text(
            text = "Thanks for exploring your Financial Wrapped!",
             color = Color.White,
             textAlign = TextAlign.Center
         )
          Spacer(modifier=Modifier.height(24.dp))
          Button(onClick = {
            //TODO
            }){
              Text(text = "Share")
          }
      }
   }
}
