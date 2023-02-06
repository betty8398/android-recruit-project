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
//        _courseData.value = listOf(
//            CourseEntity(
//                uid = 0,
//                status = ClassStatus.Published,
//                coverImageUrl = "https://images.api.hahow.in/images/614eca15a39712000619b672",
//                title = "學習 AI 一把抓：點亮人工智慧技能樹",
//                numSoldTickets = 30,
//                successCriteriaNumSoldTickets = 20,
//                proposalDueTime = LocalDateTime.now(),//2022-01-06T16:00:00.000Z
//            ),
//            CourseEntity(
//                uid = 0,
//                status = ClassStatus.Success,
//                coverImageUrl = "https://images.api.hahow.in/images/614eca15a39712000619b672",
//                title = "學習 AI 一把抓：點亮人工智慧技能樹",
//                numSoldTickets = 30,
//                successCriteriaNumSoldTickets = 20,
//                proposalDueTime = LocalDateTime.now(),//2022-01-06T16:00:00.000Z
//            ),
//            CourseEntity(
//                uid = 0,
//                status = ClassStatus.Incubating,
//                coverImageUrl = "https://images.api.hahow.in/images/614eca15a39712000619b672",
//                title = "學習 AI 一把抓：點亮人工智慧技能樹",
//                numSoldTickets = 0,
//                successCriteriaNumSoldTickets = 20,
//                proposalDueTime = LocalDateTime.now().plusDays(1),//2022-01-06T16:00:00.000Z
//            )
//        )
    }
}