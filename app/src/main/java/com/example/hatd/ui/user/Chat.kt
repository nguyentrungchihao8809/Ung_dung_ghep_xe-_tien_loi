package com.example.hatd.ui.user.Chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hatd.R

@Composable
fun ChatScreen() {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F9))
    ) {
        // Thanh tiêu đề
        TopBarChat()

        //  Nội dung tin nhắn
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            //  Tin nhắn của mình
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 50.dp),
                horizontalArrangement = Arrangement.End
            ) {
                ChatBubble(text = "Hứa Anh Tới Đón đến rồi đây", isSender = true)
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.anhdriver),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            //  Tin nhắn của người khác
            Row(
                modifier = Modifier.padding(vertical = 40.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anhuser),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    ChatBubble(text = "Tôi không thấy", isSender = false)
                    ChatBubble(text = "Anh ở đâu", isSender = false)
                }
            }
        }

        //  Ô nhập tin nhắn
        Box(
            modifier = Modifier
                .offset(y = (-10).dp)
        ) {
            MessageInput(
                value = message,
                onValueChange = { message = it },
                onSend = { /* TODO: xử lý gửi tin */ }
            )
        }

        //  Ảnh bàn phím
        Image(
            painter = painterResource(id = R.drawable.banphim),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
        )
    }
}

//  THANH TIÊU ĐỀ
@Composable
fun TopBarChat() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF4FC3F7))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.quaylai),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(id = R.drawable.anhdriver),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .offset(x = (-20).dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        // “Hứa Anh Tới Đón”
        Text(
            text = "Hứa Anh Tới Đón",
            fontSize = 23.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 50.dp)
                .offset(x = (-30).dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.phone),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(top = 20.dp)
        )
    }
}

// BONG BÓNG TIN NHẮN
@Composable
fun ChatBubble(text: String, isSender: Boolean) {
    val backgroundColor = if (isSender) Color(0xFF4FC3F7) else Color.White
    val textColor = if (isSender) Color.White else Color.Black
    val alignment = if (isSender) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = alignment
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(text = text, color = textColor, fontSize = 20.sp)
        }
    }
}

// Ô NHẬP TIN NHẮN
@Composable
fun MessageInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(50))
                .border(1.dp, Color(0xFF4FC3F7), RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.daucong),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 6.dp),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text("Nhắn tin....", color = Color.Gray, fontSize = 20.sp)
                    }
                    innerTextField()
                }
            )

            Image(
                painter = painterResource(id = R.drawable.nutgui),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
