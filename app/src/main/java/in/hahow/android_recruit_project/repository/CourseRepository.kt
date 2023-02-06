package `in`.hahow.android_recruit_project.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.database.ClassStatus
import `in`.hahow.android_recruit_project.database.CourseEntity
import `in`.hahow.android_recruit_project.database.CourseResp
import `in`.hahow.android_recruit_project.util.readResAsString
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CourseRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCourseData(context: Context): List<CourseEntity>? {
        return getCourseDataFromFile(context)?.let { respToCourseData(it) }
    }

    private fun getCourseDataFromFile(context: Context): CourseResp? {
        val data = context.readResAsString(R.raw.data)
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val adapter: JsonAdapter<CourseResp> = moshi.adapter(CourseResp::class.java)
        return adapter.fromJson(data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun respToCourseData(courseResp: CourseResp): List<CourseEntity> {
        return courseResp.data.let {
            it.map { resp ->
                CourseEntity(
                    status = when (resp.status) {
                        ClassStatus.Incubating.name -> {
                            ClassStatus.Incubating
                        }
                        ClassStatus.Success.name -> {
                            ClassStatus.Success
                        }
                        ClassStatus.Published.name -> {
                            ClassStatus.Published
                        }
                        else -> {
                            ClassStatus.Incubating
                        }
                    },
                    coverImageUrl = resp.coverImageUrl,
                    title = resp.title,
                    numSoldTickets = resp.numSoldTickets,
                    successCriteriaNumSoldTickets = resp.successCriteria.numSoldTickets,
                    proposalDueTime = if (resp.proposalDueTime != null) {
                        stringToLocalDateTime(resp.proposalDueTime!!)
                    } else {
                        LocalDateTime.now()
                    }
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun stringToLocalDateTime(timeStr: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000'Z'")
        return LocalDateTime.parse(timeStr, formatter)
    }
}