package com.example.hatd.ui.auth.intro

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hatd.R
import kotlinx.coroutines.delay

// ------------------------- TỔNG HỢP PAGER -------------------------
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 5 })

    // 🔹 Tự động chuyển trang sau 3 giây
    LaunchedEffect(pagerState.currentPage) {
        delay(3000)
        val nextPage = (pagerState.currentPage + 1) % 5
        pagerState.animateScrollToPage(nextPage)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> Intro1Screen(navController)
                1 -> Intro2Screen(navController)
                2 -> Intro3Screen(navController)
                3 -> Intro4Screen(navController)
                4 -> Intro5Screen(navController)
            }
        }

        // 🔘 Dots indicator
        DotsIndicator(
            totalDots = 5,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 370.dp)
        )
    }
}

// ------------------------- DOTS INDICATOR -------------------------
@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(totalDots) { index ->
            val color = if (index == selectedIndex) Color(0xFF4FC3F7) else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (index == selectedIndex) 10.dp else 8.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}

// ------------------------- TRANG 1 -------------------------
@Composable
fun Intro1Screen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo HATD",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 55.dp, top = 200.dp)
                .width(300.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Chào Mừng đến với HATD!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 400.dp)
                .align(Alignment.TopCenter)
        )


        Text(
            text = "Cùng chúng tôi đồng hành, mọi chuyến đi của bạn sẽ trở nên đơn giản hơn, tiết kiệm hơn và tràn đầy kết nối tuyệt vời.",
            color = Color.Black,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 450.dp, start = 20.dp, end = 20.dp)
        )

        OutlinedButton(
            onClick = {
                navController.navigate("singup")
            },
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF4ABDE0)),
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier
                .padding(start = 60.dp, top = 680.dp)
                .width(300.dp)
                .height(50.dp)
        ) {
            Text("Login", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier
                .padding(top = 795.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("From", fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(4.dp))
            Text("HATD", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color(0xFF2A5EE1))
        }
    }
}

// ------------------------- TRANG 2 -------------------------
@Composable
fun Intro2Screen(navController: NavController) {
    IntroTemplate(
        imageRes = R.drawable.bg2,
        title = "Cùng đi, cùng chia sẻ",
        desc = "Cùng nhau di chuyển, chia sẻ những khoảnh khắc ý nghĩa và tạo nên những kỷ niệm trên suốt hành trình.",
        navController = navController
    )
}

// ------------------------- TRANG 3 -------------------------
@Composable
fun Intro3Screen(navController: NavController) {
    IntroTemplate(
        imageRes = R.drawable.bg1,
        title = "Đúng giờ",
        desc = "Cùng nhau di chuyển, đến nơi đúng giờ – Mỗi hành trình đều trân trọng thời gian của bạn!",
        navController = navController
    )
}

// ------------------------- TRANG 4 -------------------------
@Composable
fun Intro4Screen(navController: NavController) {
    IntroTemplate(
        imageRes = R.drawable.bg1,
        title = "Thông minh và Tiết kiệm",
        desc = "Di chuyển thông minh, tiết kiệm hơn – lựa chọn di chuyển thông minh hơn cùng HATD.",
        navController = navController
    )
}

// ------------------------- TRANG 5 -------------------------
@Composable
fun Intro5Screen(navController: NavController) {
    IntroTemplate(
        imageRes = R.drawable.bg1,
        title = "Một chạm",
        desc = "Một chạm để kết nối, một chạm để thanh toán – Cùng nhau chia sẻ mọi hành trình.",
        navController = navController
    )
}

// ------------------------- TEMPLATE CHUNG -------------------------
@Composable
fun IntroTemplate(
    imageRes: Int,
    title: String,
    desc: String,
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo HATD",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 55.dp, top = 200.dp)
                .width(300.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 400.dp)
                .align(Alignment.TopCenter)
        )


        Text(
            text = desc,
            color = Color.Black,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 450.dp, start = 20.dp, end = 20.dp)
        )

        OutlinedButton(
            onClick = { navController.navigate("singup") },
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF4ABDE0)),
            border = BorderStroke(2.dp, Color.White),
            modifier = Modifier
                .padding(start = 60.dp, top = 680.dp)
                .width(300.dp)
                .height(50.dp)
        ) {
            Text("Login", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier
                .padding(top = 795.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("From", fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(4.dp))
            Text("HATD", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color(0xFF2A5EE1))
        }
    }
}
