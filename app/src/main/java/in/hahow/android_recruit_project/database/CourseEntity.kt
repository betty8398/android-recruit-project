package `in`.hahow.android_recruit_project.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val status: ClassStatus,
    val coverImageUrl: String,
    val title: String,
    val numSoldTickets: Int,
    val successCriteriaNumSoldTickets: Int,
    val proposalDueTime: LocalDateTime, //TODO 應考慮時間轉換
)


