package com.project.qiblaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QiblaaTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) { innerPadding ->
                    UpperSection(
                        time = "Siang",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UpperSection(time: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        Text(
            text = "Assalamu'alaikum, Selamat $time!",
            style = MaterialTheme.typography.titleMedium,
        )
        DateTimeCard()
    }
}

@Composable
fun DateTimeCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
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
            ),
            modifier = Modifier
        )
        DisplayTime(
            time = Time(
                hour = 12,
                minute = 30,
                second = 0,
                amPm = "PM",
                is24HourFormat = true,
                timeZone = "WIB",
            ),
            modifier = Modifier
        )
        DisplayUpcomingEvent(
            time = Time(
                hour = 1,
                minute = 30,
                second = 0
            ),
            event = "Asar",
            modifier = Modifier
        )
    }
}

@Composable
fun DisplayDate(date: Date, modifier: Modifier = Modifier) {
    Row {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        bottomStart = 8.dp
                    )
                )
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp
                )
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
    time: Time,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
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
fun DisplayUpcomingEvent(time: Time, event: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
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

@Preview(showBackground = true)
@Composable
fun UpperSectionPreview() {
    QiblaaTheme {
        Scaffold { innerPadding ->
            UpperSection(
                time = "Siang",
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            )
        }
    }
}
