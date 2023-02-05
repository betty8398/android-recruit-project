package `in`.hahow.android_recruit_project.database

import androidx.compose.runtime.Immutable

data class CourseResp(
    var `data`: List<Data?>? = null
) {
    data class Data(
        var successCriteria: SuccessCriteria? = null,
        var numSoldTickets: Int? = null, // 0
        var status: ClassStatus? = null,
        var proposalDueTime: String? = null, // 2022-01-06T16:00:00.000Z
        var coverImageUrl: String? = null, // https://images.api.hahow.in/images/614eca15a39712000619b672
        var title: String? = null, // 學習 AI 一把抓：點亮人工智慧技能樹
        var totalVideoLengthInSeconds: Int? = null // 21449
    ) {
        data class SuccessCriteria(
            var numSoldTickets: Int? = null // 30
        )
    }
}



@Immutable
enum class ClassStatus(val status: String) {
    Incubating("INCUBATING"),
    Published("PUBLISHED"),
    Success("SUCCESS")
}
