package `in`.hahow.android_recruit_project.database

import androidx.compose.runtime.Immutable
import com.squareup.moshi.JsonClass

/**
 * 接受 json 檔的抽象層
 * **/
@JsonClass(generateAdapter = true)
data class CourseResp(
    var `data`: List<Data>
) {
    /**
     * @param successCriteria 成功募資條件
     * @param numSoldTickets 目前銷售量
     * @param status 開課狀態
     * @param proposalDueTime 募資截止日
     * @param coverImageUrl 課程封面圖
     * @param title 課程名稱
     * @param totalVideoLengthInSeconds 總課程時長
     * **/
    data class Data(
        var successCriteria: SuccessCriteria,
        var numSoldTickets: Int, // 0
        var status: String,
        var proposalDueTime: String?=null, // 2022-01-06T16:00:00.000Z
        var coverImageUrl: String, // https://images.api.hahow.in/images/614eca15a39712000619b672
        var title: String, // 學習 AI 一把抓：點亮人工智慧技能樹
        var totalVideoLengthInSeconds: Int?=null // 21449
    ) {
        data class SuccessCriteria(
            var numSoldTickets: Int // 30
        )
    }
}


/**
 * 開課狀態
 * */
@Immutable
enum class ClassStatus(val status: String) {
    Incubating("INCUBATING"),
    Published("PUBLISHED"),
    Success("SUCCESS")
    //TODO 可再加上募資失敗
}
