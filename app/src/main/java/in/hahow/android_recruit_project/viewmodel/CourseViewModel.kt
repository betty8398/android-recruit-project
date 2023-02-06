package `in`.hahow.android_recruit_project.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import `in`.hahow.android_recruit_project.database.ClassStatus
import `in`.hahow.android_recruit_project.database.CourseEntity
import `in`.hahow.android_recruit_project.repository.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

class CourseViewModel : ViewModel() {
    private var _courseData: MutableStateFlow<List<CourseEntity>> = MutableStateFlow(emptyList())
    val courseData: StateFlow<List<CourseEntity>> = _courseData

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCourseData(context: Context) {
        val repository = CourseRepository()
        repository.getCourseData(context)?.let {
            _courseData.value = it
        }
    }
}