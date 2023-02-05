package `in`.hahow.android_recruit_project

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import `in`.hahow.android_recruit_project.screen.CourseScreen
import `in`.hahow.android_recruit_project.screen.PrevCourse
import `in`.hahow.android_recruit_project.ui.theme.AndroidrecruitprojectTheme
import `in`.hahow.android_recruit_project.viewmodel.CourseViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidrecruitprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val vm = CourseViewModel()
//                    CourseScreen(vm)
                    PrevCourse()
                }
            }
        }
    }
}