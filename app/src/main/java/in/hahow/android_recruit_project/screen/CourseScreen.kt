package `in`.hahow.android_recruit_project.screen

import `in`.hahow.android_recruit_project.database.ClassStatus
import `in`.hahow.android_recruit_project.database.CourseEntity


import `in`.hahow.android_recruit_project.viewmodel.CourseViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.ui.theme.green
import `in`.hahow.android_recruit_project.ui.theme.grey
import `in`.hahow.android_recruit_project.ui.theme.orange
import java.time.Duration
import java.time.LocalDateTime

/**
 * 課程頁面
 * **/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CourseScreen(
) {
    val courseVM = CourseViewModel()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        courseVM.setCourseData(context)
    }
    val now = remember { LocalDateTime.now() }
    val courseList by courseVM.courseData.collectAsState()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(courseList) { course ->
            CourseCard(course, now)
        }
    }
}

/**
 * 課程為單位資訊卡片
 * **/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CourseCard(course: CourseEntity, now: LocalDateTime) {
    Card(modifier = Modifier.padding(4.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CourseImage(coverImageUrl = course.coverImageUrl, courseStatus = course.status, now)
            CourseInfo(
                modifier = Modifier.padding(4.dp),
                sale = course.numSoldTickets,
                target = course.successCriteriaNumSoldTickets,
                courseName = course.title,
                proposalDueTime = course.proposalDueTime,
                now = now
            )
        }
    }
}

/**
 * 課程卡片中用到的圖片
 * **/
@Composable
fun CourseImage(
    coverImageUrl: String?,
    courseStatus: ClassStatus,
    now: LocalDateTime
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.35f)
            .aspectRatio(1.6f),
        contentAlignment = Alignment.BottomEnd,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(coverImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.small)
        )
        StatusLabel(status = courseStatus)
    }

}


@Composable
private fun StatusLabel(
    modifier: Modifier = Modifier,
    status: ClassStatus,
) {
    val (labelColor, stringRes) = remember(status) {
        when (status) {
            ClassStatus.Incubating -> grey to R.string.status_incubating
            ClassStatus.Success -> orange to R.string.status_success
            ClassStatus.Published -> green to R.string.status_published
        }
    }
    val shape = MaterialTheme.shapes.small.copy(
        topEnd = androidx.compose.foundation.shape.CornerSize(0.dp),
        bottomStart = androidx.compose.foundation.shape.CornerSize(0.dp),
    )
    Text(
        text = stringResource(stringRes),
        modifier = modifier
            .background(color = labelColor, shape = shape)
            .padding(4.dp)
            .clip(shape),
        color = MaterialTheme.colors.onPrimary,
        style = MaterialTheme.typography.caption,
    )
}

/**
 * 課程卡片中用到的資訊
 * **/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CourseInfo(
    target: Int,
    sale: Int,
    courseName: String,
    proposalDueTime: LocalDateTime,
    now: LocalDateTime,
    modifier: Modifier = Modifier
) {
    val countDown by remember {
        derivedStateOf() {
            val diffTime = Duration.between(now, proposalDueTime)
            if (diffTime.isNegative) {
                -diffTime.toDays()
            } else {
                diffTime.toDays()
            }
        }
    }
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = courseName,
            maxLines = 2,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            ProgressBar(
                modifier = Modifier.weight(1f),
                target = target,
                sale = sale
            )
            Spacer(Modifier.weight(1f))
            if (countDown > 0) {
                CountDown(modifier = Modifier.weight(1f), countDownDayTime = countDown)
            } else {
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CountDown(
    modifier: Modifier = Modifier,
    countDownDayTime: Long,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.countdown),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
        Text(stringResource(R.string.goalTime, countDownDayTime))

    }
}

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    target: Int,
    sale: Int,
) {
    Column(modifier = modifier) {
        if (sale > target) {
            Text(text = "100%")
            LinearProgressIndicator(progress = 1f)
        } else {
            Text(stringResource(R.string.person, sale, target))
            val percent = sale / target
            LinearProgressIndicator(progress = percent.toFloat())
        }
    }
}