package com.example.wordpuzzle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordpuzzle.ui.screen.mainMenu.MainMenu
import com.example.wordpuzzle.ui.screen.selectPuzzle.SelectPuzzle
import com.example.wordpuzzle.ui.screen.settings.Settings
import com.example.wordpuzzle.ui.theme.WordPuzzleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordPuzzleTheme {
                Surface {
                    MainApp()
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainApp(){
    val navController = rememberNavController()
    WordPuzzleTheme {
       NavHost(navController = navController, startDestination = "mainMenu") {

           // route: mainMenu
           // return Composable tuong ung voi route
           composable("mainMenu") {
               MainMenu(
                   navigateSelectPuzzleScreen = { navController.navigate("selectPuzzle") },
                   navigateSettingsScreen = { navController.navigate("settings") }
               )
           }

           composable("selectPuzzle") {
               SelectPuzzle(onBackPressed = {navController.popBackStack()})
           }

           composable("settings") {
               Settings()
           }

       }
    }
}