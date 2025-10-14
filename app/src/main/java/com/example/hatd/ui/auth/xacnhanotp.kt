import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            InputScreen()
        }
    }
}

@Composable
fun InputScreen() {
    // State để lưu dữ liệu nhập
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Nhập thông tin của bạn", style = MaterialTheme.typography.titleMedium)

        // Input tên
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Tên") },
            modifier = Modifier.fillMaxWidth()
        )

        // Input số
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Số") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        // Nút bấm
        Button(
            onClick = {
                result = "Tên: $name, Số: $number"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Xác nhận")
        }

        // Hiển thị kết quả
        if (result.isNotEmpty()) {
            Text(text = "Kết quả: $result", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputScreen() {
    MyApp()
}
