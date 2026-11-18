package com.example.my_first_application

import android.R.attr.bottom
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_first_application.ui.theme.My_First_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            My_First_ApplicationTheme {
                My_First_ApplicationApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun My_First_ApplicationApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.FEED) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val screenModifier = Modifier.padding(innerPadding)
            when (currentDestination){
                AppDestinations.FEED -> FeedScreen(screenModifier)
                AppDestinations.MESSAGES -> MessageScreen(screenModifier)
                AppDestinations.SAVED -> SavedScreen (screenModifier)
                AppDestinations.PROFILE -> ProfileScreen (screenModifier)
                AppDestinations.SETTINGS -> SettingsScreen (screenModifier)
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    FEED("Стрічка", R.drawable.ic_home),
    MESSAGES("Чати", R.drawable.ic_message),
    SAVED("Збережене", R.drawable.ic_saved),
    PROFILE("Профіль", R.drawable.ic_account_box),
    SETTINGS("Налаштування", R.drawable.ic_settings)
}

@Composable
fun FeedScreen(modifier: Modifier = Modifier){
    var textContent by remember {mutableStateOf ("Стрічка новин")}
    Column(
        modifier = modifier.fillMaxSize()
                            .background(Color(0xFFB9E6F6))
                            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = textContent, textAlign = TextAlign.Center, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic, color = Color(0xFF0B4052), fontFamily = FontFamily.Serif)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { textContent = "Стрічку успішно оновлено"},
            modifier = Modifier.padding(bottom = 25.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B4052),
                contentColor = Color.White)){
            Text(text = "Оновити стрічку")
        }
    }
}

@Composable
fun MessageScreen(modifier: Modifier = Modifier){
    var textContent by rememberSaveable {mutableStateOf ("Чати")}
    Column(
        modifier = modifier.fillMaxSize()
                           .background(Color(0xFFB9E6F6))
                           .padding(start = 25.dp, top = 25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        Text(text = textContent, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic, color = Color(0xFF0B4052), fontFamily = FontFamily.Serif)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {textContent = "Створено новий чат"},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B4052),
                contentColor = Color.White)){
            Text(text = "Новий чат")
        }
    }
}

class SavedViewModel: ViewModel(){
    var textContent by mutableStateOf ("Збережені дописи")
        private set
    fun updateText() {textContent = "Допис видалено"}
}
@Composable
fun SavedScreen(modifier: Modifier = Modifier, viewModel: SavedViewModel = viewModel()){
    Column(
        modifier = modifier.fillMaxSize()
                           .background(Color(0xFFB9E6F6))
                            .padding(bottom = 30.dp, end = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ){
        Text(text = viewModel.textContent, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic, color = Color(0xFF0B4052), fontFamily = FontFamily.Serif)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {viewModel.updateText()},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B4052),
                contentColor = Color.White)){
            Text(text = "Видалити")
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
    var textContent by rememberSaveable {mutableStateOf ("Особистий профіль")}
    Column(
        modifier = modifier.fillMaxSize()
                           .background(Color(0xFFB9E6F6)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = textContent, textAlign = TextAlign.Center, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic, color = Color(0xFF0B4052), fontFamily = FontFamily.Serif)
        Spacer(modifier = Modifier.height(50.dp))
        Button (onClick = {textContent = "Профіль успішно оновлено"},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B4052),
                contentColor = Color.White)){
            Text(text = "Редагувати профіль")
        }
    }
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier){
    var textContent by remember {mutableStateOf ("Налаштування")}
    Column(
        modifier = modifier.fillMaxSize()
                           .background(Color(0xFFB9E6F6))
                            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = textContent, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic, color = Color(0xFF0B4052), fontFamily = FontFamily.Serif)
        Spacer(modifier = Modifier.height (28.dp))
        Button (onClick = {textContent = "Успішний вихід з акаунту!"},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B4052),
                contentColor = Color.White)){
        Text(text = "Вийти з акаунту")
        }
    }
}