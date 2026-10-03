

package com.example.classmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classmate.ui.theme.ClassMateTheme

data class ClassSchedule(
    val subject: String,
    val day: String,
    val startTime: String,
    val endTime: String,
    val room: String,
    val instructor: String
)

private val PrimaryBlue = Color(0xFF4F46E5)
private val PrimaryLight = Color(0xFFEDE9FE)
private val AppBackground = Color(0xFFF7F7FB)
private val CardWhite = Color.White
private val TextDark = Color(0xFF1F2937)
private val TextGray = Color(0xFF6B7280)
private val GreenSoft = Color(0xFFDCFCE7)
private val GreenText = Color(0xFF15803D)
private val OrangeSoft = Color(0xFFFFEDD5)
private val OrangeText = Color(0xFFC2410C)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClassMateTheme {
                ClassMateApp()
            }
        }
    }
}

@Composable
fun ClassMateApp() {

    var selectedScreen by remember {
        mutableIntStateOf(0)
    }

    val schedules = remember {
        mutableStateListOf(
            ClassSchedule(
                subject = "Mobile Application Development",
                day = "Monday",
                startTime = "8:00 AM",
                endTime = "10:00 AM",
                room = "Room 204",
                instructor = "Prof. Santos"
            ),
            ClassSchedule(
                subject = "Database Management",
                day = "Tuesday",
                startTime = "10:00 AM",
                endTime = "12:00 PM",
                room = "Lab 2",
                instructor = "Prof. Cruz"
            ),
            ClassSchedule(
                subject = "Web Development",
                day = "Wednesday",
                startTime = "1:00 PM",
                endTime = "3:00 PM",
                room = "Room 305",
                instructor = "Prof. Garcia"
            )
        )
    }

    Scaffold(
        containerColor = AppBackground,
        bottomBar = {
            NavigationBar(
                containerColor = CardWhite,
                modifier = Modifier.navigationBarsPadding()
            ) {

                NavigationBarItem(
                    selected = selectedScreen == 0,
                    onClick = {
                        selectedScreen = 0
                    },
                    icon = {
                        Text(
                            text = "⌂",
                            fontSize = 22.sp
                        )
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = selectedScreen == 1,
                    onClick = {
                        selectedScreen = 1
                    },
                    icon = {
                        Text(
                            text = "≡",
                            fontSize = 22.sp
                        )
                    },
                    label = {
                        Text("Schedule")
                    }
                )

                NavigationBarItem(
                    selected = selectedScreen == 2,
                    onClick = {
                        selectedScreen = 2
                    },
                    icon = {
                        Text(
                            text = "+",
                            fontSize = 24.sp
                        )
                    },
                    label = {
                        Text("Add Class")
                    }
                )

                NavigationBarItem(
                    selected = selectedScreen == 3,
                    onClick = {
                        selectedScreen = 3
                    },
                    icon = {
                        Text(
                            text = "◷",
                            fontSize = 22.sp
                        )
                    },
                    label = {
                        Text("Reminders")
                    }
                )
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (selectedScreen) {

                0 -> {
                    HomeScreen(
                        schedules = schedules
                    )
                }

                1 -> {
                    ScheduleScreen(
                        schedules = schedules
                    )
                }

                2 -> {
                    AddClassScreen(
                        onAddClass = { newClass ->
                            schedules.add(newClass)
                            selectedScreen = 1
                        }
                    )
                }

                3 -> {
                    ReminderScreen(
                        schedules = schedules
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    schedules: List<ClassSchedule>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Good day!",
                fontSize = 16.sp,
                color = TextGray
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Welcome to ClassMate",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryBlue
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "Your Schedule",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "${schedules.size} Classes",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Stay organized and never miss a class.",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }
        }

        item {

            Text(
                text = "Upcoming Classes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
        }

        if (schedules.isEmpty()) {

            item {

                EmptyState(
                    message = "You don't have any classes yet."
                )
            }

        } else {

            items(
                items = schedules.take(3)
            ) { classItem ->

                ClassCard(
                    classItem = classItem
                )
            }
        }

        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
fun ScheduleScreen(
    schedules: List<ClassSchedule>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "My Schedule",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "View all your classes",
                fontSize = 15.sp,
                color = TextGray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }

        if (schedules.isEmpty()) {

            item {

                EmptyState(
                    message = "Add a class to see your schedule."
                )
            }

        } else {

            items(schedules) { classItem ->

                ClassCard(
                    classItem = classItem
                )
            }
        }

        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
fun ClassCard(
    classItem: ClassSchedule
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = classItem.subject,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )

                    Text(
                        text = classItem.instructor,
                        fontSize = 14.sp,
                        color = TextGray
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = PrimaryLight,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                ) {

                    Text(
                        text = classItem.day,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryBlue
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = classItem.startTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryBlue
                )

                Text(
                    text = " — ",
                    fontSize = 14.sp,
                    color = TextGray
                )

                Text(
                    text = classItem.endTime,
                    fontSize = 14.sp,
                    color = TextGray
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Text(
                    text = "• ${classItem.room}",
                    fontSize = 14.sp,
                    color = TextGray
                )
            }
        }
    }
}

@Composable
fun AddClassScreen(
    onAddClass: (ClassSchedule) -> Unit
) {

    var subject by remember {
        mutableStateOf("")
    }

    var day by remember {
        mutableStateOf("")
    }

    var startTime by remember {
        mutableStateOf("")
    }

    var endTime by remember {
        mutableStateOf("")
    }

    var room by remember {
        mutableStateOf("")
    }

    var instructor by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Add Class",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Create a new class schedule",
                fontSize = 15.sp,
                color = TextGray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }

        item {

            OutlinedTextField(
                value = subject,
                onValueChange = {
                    subject = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Subject / Course")
                },
                placeholder = {
                    Text("e.g. IT102")
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )
        }

        item {

            OutlinedTextField(
                value = day,
                onValueChange = {
                    day = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Day")
                },
                placeholder = {
                    Text("e.g. Monday")
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                OutlinedTextField(
                    value = startTime,
                    onValueChange = {
                        startTime = it
                    },
                    modifier = Modifier.weight(1f),
                    label = {
                        Text("Start")
                    },
                    placeholder = {
                        Text("8:00 AM")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp)
                )

                OutlinedTextField(
                    value = endTime,
                    onValueChange = {
                        endTime = it
                    },
                    modifier = Modifier.weight(1f),
                    label = {
                        Text("End")
                    },
                    placeholder = {
                        Text("10:00 AM")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp)
                )
            }
        }

        item {

            OutlinedTextField(
                value = room,
                onValueChange = {
                    room = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Room / Location")
                },
                placeholder = {
                    Text("e.g. Room 204")
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )
        }

        item {

            OutlinedTextField(
                value = instructor,
                onValueChange = {
                    instructor = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Instructor")
                },
                placeholder = {
                    Text("e.g. Prof. Santos")
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )
        }

        item {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {

                    if (
                        subject.isNotBlank() &&
                        day.isNotBlank() &&
                        startTime.isNotBlank() &&
                        endTime.isNotBlank()
                    ) {

                        onAddClass(
                            ClassSchedule(
                                subject = subject,
                                day = day,
                                startTime = startTime,
                                endTime = endTime,
                                room = room.ifBlank {
                                    "TBA"
                                },
                                instructor = instructor.ifBlank {
                                    "TBA"
                                }
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {

                Text(
                    text = "Save Class",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
fun ReminderScreen(
    schedules: List<ClassSchedule>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Reminders",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Stay prepared for your classes",
                fontSize = 15.sp,
                color = TextGray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = OrangeSoft
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Class Reminders",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = OrangeText
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Your scheduled classes are listed below.",
                        fontSize = 14.sp,
                        color = TextDark
                    )
                }
            }
        }

        if (schedules.isEmpty()) {

            item {

                EmptyState(
                    message = "No reminders available."
                )
            }

        } else {

            items(schedules) { classItem ->

                ReminderCard(
                    classItem = classItem
                )
            }
        }

        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
fun ReminderCard(
    classItem: ClassSchedule
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(
                        color = GreenSoft,
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "✓",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = GreenText
                )
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = classItem.subject,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "${classItem.day} • ${classItem.startTime}",
                    fontSize = 13.sp,
                    color = TextGray
                )

                Text(
                    text = classItem.room,
                    fontSize = 13.sp,
                    color = TextGray
                )
            }

            Text(
                text = "ON",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = GreenText
            )
        }
    }
}

@Composable
fun EmptyState(
    message: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "No Classes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = message,
                fontSize = 14.sp,
                color = TextGray
            )
        }
    }
}