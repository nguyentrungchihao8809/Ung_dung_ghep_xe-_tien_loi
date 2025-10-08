package com.example.hatd

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        val db = FirebaseDatabase.getInstance().getReference("test")
        db.setValue("Firebase connected ✅")
            .addOnSuccessListener { Log.d("Firebase", "Ghi thành công!") }
            .addOnFailureListener { e -> Log.e("Firebase", "Lỗi: ${e.message}") }

        setContent {
            // TODO: Jetpack Compose UI
        }
    }
}
