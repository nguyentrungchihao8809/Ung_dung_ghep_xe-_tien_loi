package com.example.hatd.ui.driver.TaoChuyenDi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaoChuyenDiScreen(onBackClick: () -> Unit = {}) {
    var diemDi by remember { mutableStateOf("") }
    var diemDen by remember { mutableStateOf("") }

    val nearbyPlaces = listOf<Pair<String, String>>() // tạm thời rỗng

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Ảnh nền full màn hình
        Image(
            painter = painterResource(id = R.drawable.nentaoyeucauchuyendi),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.9f), // làm nền hơi trong suốt để text dễ đọc
            contentScale = ContentScale.Crop
        )

        // Lớp phủ nội dung
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Hàng tiêu đề
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                // Căn giữa chữ "Đi cùng HATD"
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = "Đi cùng HATD",
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 30.dp)
                    )

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Ô nhập điểm đi
            OutlinedTextField(
                value = diemDi,
                onValueChange = { diemDi = it },
                label = { Text("Điểm đi") },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF3085E0),
                    unfocusedBorderColor = Color(0xFF3085E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Ô nhập điểm đến
            OutlinedTextField(
                value = diemDen,
                onValueChange = { diemDen = it },
                label = { Text("Điểm đến") },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF3085E0),
                    unfocusedBorderColor = Color(0xFF3085E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Text(
                text = "Điểm đến gần đây",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(start= 20.dp,top = 20.dp)
            )


            Spacer(modifier = Modifier.height(8.dp,))

            if (nearbyPlaces.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Chưa có dữ liệu",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }
            } else {
                LazyColumn {
                    items(nearbyPlaces.size) { index ->
                        val place = nearbyPlaces[index]
                        NearbyPlaceCard(name = place.first, address = place.second)
                    }
                }
            }
        }
    }
}

@Composable
fun NearbyPlaceCard(name: String, address: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                Icons.Default.Place,
                contentDescription = null,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(name, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(address, fontSize = 13.sp, color = Color.DarkGray)
            }
        }
    }
}
