package com.example.hatd.ui.driver.ChatDriver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.hatd.R

data class ChatMessage(val message: String, val isSentByMe: Boolean)

@Composable
fun ChatDriverScreen(navController: NavController) {
    var message by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F9))
    ) {
        // Thanh tiêu đề
        TopBarChat(
            navController = navController,
            onCall = { navController.navigate("CallDriver") }
        )

        // Danh sách tin nhắn
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            state = listState,
            reverseLayout = false
        ) {
            items(messages) { msg ->
                ChatMessageItem(msg)
            }
        }

        // Ô nhập tin nhắn
        MessageInput(
            value = message,
            onValueChange = { message = it },
            onSend = {
                if (message.isNotBlank()) {
                    messages.add(ChatMessage(message, true))
                    message = ""

                    // Scroll xuống cuối
                    scope.launch {
                        listState.animateScrollToItem(messages.size - 1)
                    }
                }
            }
        )
    }
}

@Composable
fun TopBarChat(navController: NavController, onCall: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF4FC3F7))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nút Back
        IconButton(onClick = { navController.navigate("TheoDoiLoTrinhChungUser") }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = R.drawable.anhuser),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "User",
            fontSize = 23.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(1f))

        // Nút Call
        IconButton(onClick = onCall) {
            Icon(Icons.Filled.Call, contentDescription = "Call", tint = Color.White)
        }
    }
}

@Composable
fun ChatMessageItem(msg: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (msg.isSentByMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Avatar người nhận
        if (!msg.isSentByMe) {
            Image(
                painter = painterResource(id = R.drawable.anhdriver),
                contentDescription = "Receiver Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        // Bong bóng chat
        Box(
            modifier = Modifier
                .background(
                    if (msg.isSentByMe) Color(0xFF4FC3F7) else Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = msg.message,
                color = if (msg.isSentByMe) Color.White else Color.Black,
                fontSize = 18.sp
            )
        }

        // Avatar người gửi (nếu muốn hiển thị bên phải)
        if (msg.isSentByMe) {
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(id = R.drawable.anhdriver),
                contentDescription = "Sender Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun MessageInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (value.isEmpty()) {
                        Text("Nhắn tin...", color = Color.Gray, fontSize = 18.sp)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onSend) {
            Icon(Icons.Filled.Send, contentDescription = "Send", tint = Color(0xFF4FC3F7))
        }
    }
}
