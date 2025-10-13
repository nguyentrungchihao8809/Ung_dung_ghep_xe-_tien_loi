package com.example.hatd.ui.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.BorderStroke

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.hatd.R

@Composable
fun loginintro() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bgintro), // ảnh trong res/drawable
            contentDescription = null,
            contentScale = ContentScale.Crop, // phủ kín màn hình
            modifier = Modifier.fillMaxSize()
        )
        OutlinedButton(
            onClick = { /* TODO: hành động khi bấm */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4ABDE0)),
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier
                .padding(start = 60.dp, top = 680.dp)
                .width(300.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text("Chào Mừng đến với HATD!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 50.dp, top = 400.dp)
            )
        Text("Form",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 150.dp,top = 800.dp)
        )
        Text("HATD",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF2A5EE1),
            modifier = Modifier
                .padding(start = 195.dp, top = 795.dp )
        )
    }
}
