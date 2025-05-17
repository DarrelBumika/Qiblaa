package com.project.qiblaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.project.qiblaa.data.Date
import com.project.qiblaa.data.Time
import com.project.qiblaa.ui.theme.Primary
import com.project.qiblaa.ui.theme.QiblaaTheme
import com.project.qiblaa.ui.theme.White
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QiblaaTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    UpperSection(
                        time = "Siang",
                    )
                    MiddleSection()
                    BottomSection()
                }
            }
        }
    }
}

@Composable
fun UpperSection(
    time: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Assalamu'alaikum, Selamat $time!",
            style = MaterialTheme.typography.titleMedium,
        )
        DateTimeCard()
    }
}

@Composable
fun DateTimeCard() {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 12.dp
            )
            .background(
                color = Primary,
                shape = RoundedCornerShape(
                    size = 8.dp
                )
            )
            .clip(
                shape = RoundedCornerShape(
                    size = 8.dp
                )
            )
    ) {
        DisplayDate(
            date = Date(
                day = 1,
                month = 1,
                year = 2025,
                dayOfWeek = "Senin",
                monthName = "Januari",
            )
        )
        DisplayTime(
            time = Time(
                hour = 12,
                minute = 30,
                second = 0,
                amPm = "PM",
                is24HourFormat = true,
                timeZone = "WIB",
            )
        )
        DisplayUpcomingEvent(
            time = Time(
                hour = 1,
                minute = 30,
                second = 0
            ),
            event = "Asar"
        )
    }
}

@Composable
fun DisplayDate(
    date: Date
) {
    Row {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Row(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        bottomStart = 8.dp
                    )
                )
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "${date.day} ${date.monthName} ${date.year}",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun DisplayTime(
    time: Time
) {
    Text(
        modifier = Modifier
            .padding(
                horizontal = 24.dp
            ),
        style = MaterialTheme.typography.displayMedium.copy(color = White),
        text = buildAnnotatedString {
            append("${time.hour}:${time.minute} ")
            withStyle(
                style = SpanStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
            ) {
                append(time.timeZone)
            }
        }
    )
}

@Composable
fun DisplayUpcomingEvent(
    time: Time,
    event: String
) {
    Text(
        modifier = Modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 12.dp,
                bottom = 24.dp
            ),
        style = MaterialTheme.typography.bodySmall.copy(color = White),
        text = buildAnnotatedString {
            append("${time.hour} jam ${time.minute} menit menuju ")
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(event)
            }
        }
    )
}

@Composable
fun MiddleSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Sudahkah Anda sholat hari ini?",
            style = MaterialTheme.typography.titleMedium,
        )
        DailyPrayTracker()
        PrayTimeCard(
            location = "Jakarta, Indonesia"
        )
    }
}

@Composable
fun DailyPrayTracker() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 12.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        DailyPrayTrackerCard(
            prayName = "Shubuh",
            prayIcon = R.drawable.ic_shubuh,
            isDone = true,
            modifier = Modifier
                .weight(1f, true)
        )
        DailyPrayTrackerCard(
            prayName = "Dzuhur",
            prayIcon = R.drawable.ic_dzuhur,
            modifier = Modifier
                .weight(1f, true)
        )
        DailyPrayTrackerCard(
            prayName = "Ashar",
            prayIcon = R.drawable.ic_ashar,
            modifier = Modifier
                .weight(1f, true)
        )
        DailyPrayTrackerCard(
            prayName = "Maghrib",
            prayIcon = R.drawable.ic_maghrib,
            modifier = Modifier
                .weight(1f, true)
        )
        DailyPrayTrackerCard(
            prayName = "Isya",
            prayIcon = R.drawable.ic_isya,
            modifier = Modifier
                .weight(1f, true)
        )
    }
}

@Composable
fun DailyPrayTrackerCard(
    modifier: Modifier = Modifier,
    prayName: String,
    prayIcon: Int,
    isDone: Boolean = false
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isDone) White else Primary,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
                .border(
                    width = 1.dp,
                    color = Primary,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
                .padding(
                    vertical = 12.dp,
                    horizontal = 8.dp
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(prayIcon),
                contentDescription = null,
                tint = if (isDone) Primary else White,
            )
            Text(
                text = prayName,
                style = MaterialTheme.typography.labelSmall.copy(if (isDone) Primary else White),
            )
        }
        if (isDone) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomCenter)
                    .offset(
                        y = 12.dp
                    )
                    .background(
                        color = Primary,
                        shape = RoundedCornerShape(
                            size = 12.dp
                        )
                    ),
            )
        }
    }
}

@Composable
fun PrayTimeCard(location: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Primary,
                shape = RoundedCornerShape(
                    size = 8.dp
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp)
            )
            Text(
                text = location,
                style = MaterialTheme.typography.labelLarge.copy(color = White),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = White
        )

        SinglePrayTime(
            prayName = "Shubuh",
            prayIcon = R.drawable.ic_shubuh,
            prayTime = Time(
                hour = 5,
                minute = 0,
                second = 0,
                timeZone = "WIB"
            ),
        )
        SinglePrayTime(
            prayName = "Terbit",
            prayIcon = R.drawable.ic_sunrise,
            prayTime = Time(
                hour = 6,
                minute = 0,
                second = 0,
                timeZone = "WIB"
            ),
            isNotification = false
        )
        SinglePrayTime(
            prayName = "Dzuhur",
            prayIcon = R.drawable.ic_dzuhur,
            prayTime = Time(
                hour = 12,
                minute = 0,
                second = 0,
                timeZone = "WIB"
            ),
            isNotification = false
        )
        SinglePrayTime(
            prayName = "Ashar",
            prayIcon = R.drawable.ic_ashar,
            prayTime = Time(
                hour = 15,
                minute = 0,
                second = 0,
                timeZone = "WIB"
            ),
        )
        SinglePrayTime(
            prayName = "Maghrib",
            prayIcon = R.drawable.ic_maghrib,
            prayTime = Time(
                hour = 18,
                minute = 0,
                second = 0,
                timeZone = "WIB"
            ),
        )
        SinglePrayTime(
            prayName = "Isya",
            prayIcon = R.drawable.ic_isya,
            prayTime = Time(
                hour = 19,
                minute = 0,
                second = 0,
                timeZone = "WIB"
            ),
        )
        SinglePrayTime(
            prayName = "Imsak",
            prayIcon = R.drawable.ic_imsak,
            prayTime = Time(
                hour = 4,
                minute = 45,
                second = 0,
                timeZone = "WIB"
            ),
        )
    }
}

@Composable
fun SinglePrayTime(
    prayName: String,
    prayIcon: Int,
    prayTime: Time,
    isNotification: Boolean = true,
) {
    val prayHour = String.format(Locale.ROOT, "%02d", prayTime.hour)
    val prayMinute = String.format(Locale.ROOT, "%02d", prayTime.minute)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(prayIcon),
            contentDescription = null,
            tint = White,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = "${prayHour}:${prayMinute} ${prayTime.timeZone}",
            style = MaterialTheme.typography.labelLarge.copy(color = White),
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = prayName,
            style = MaterialTheme.typography.labelLarge.copy(color = White),
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
        if (isNotification) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.ic_notification_off),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun BottomSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f, true)
                .background(
                    color = Primary,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_notes),
                    contentDescription = null,
                    tint = White
                )
                Text(
                    text = "Recap Sholat",
                    style = MaterialTheme.typography.titleMedium.copy(color = White),
                )
            }
            Image(
                painter = painterResource(R.drawable.banner_notes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f, true)
                .background(
                    color = Primary,
                    shape = RoundedCornerShape(
                        size = 8.dp
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_compass),
                    contentDescription = null,
                    tint = White
                )
                Text(
                    text = "Arah Kiblat",
                    style = MaterialTheme.typography.titleMedium.copy(color = White),
                )
            }
            Image(
                painter = painterResource(R.drawable.banner_kiblat),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    QiblaaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UpperSection(
                time = "Siang",
            )
            MiddleSection()
            BottomSection()
        }
    }
}
