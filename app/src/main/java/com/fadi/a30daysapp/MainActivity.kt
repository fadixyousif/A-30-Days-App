package com.fadi.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadi.a30daysapp.data.DaysData
import com.fadi.a30daysapp.model.Day
import com.fadi.a30daysapp.ui.theme.A30DaysAppTheme

// MainActivity class
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A30DaysAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Calling the DaysApp composable function
                    DaysApp()
                }
            }
        }
    }
}

// DaysApp composable function
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    // Scaffold with top bar and content
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // Top app bar with title and scroll behavior
            DaysTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) { innerPadding ->
        // Content of the app
        DaysList(
            days = DaysData.days,
            contentPadding = innerPadding
        )
    }
}

// DaysTopAppBar composable function
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    // Center aligned top app bar with title and colors
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
    )
}

// DaysList composable function
@Composable
fun DaysList(
    days: List<Day>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    // Lazy column for the list of days
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        // items for each day
        items(days) { day ->
            // Day item composable function
            DayItem(
                day = day,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}

// DayItem composable function
@Composable
fun DayItem(
    day: Day,
    modifier: Modifier = Modifier
) {
    // Remember for expanded state
    var expanded by remember { mutableStateOf(false) }

    // Card for the day item
    Card(
        // elevation, shape, and colors
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        // Column for the content
        Column(
            modifier = Modifier
                // animate content size
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(16.dp)
        ) {
            // Row for the day badge and title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Badge for the day
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    // text for the day badge
                    Text(
                        text = stringResource(R.string.day, day.dayNumber),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Spacer to push the icon to the right
                Spacer(modifier = Modifier.weight(1f))
                // Icon for expanding/collapsing the content with onClick function
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Spacer for vertical space
            Spacer(modifier = Modifier.height(12.dp))

            // Text for the title
            Text(
                text = stringResource(day.titleRes),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            // Spacer for vertical space
            Spacer(modifier = Modifier.height(12.dp))

            // Image for the day that is being generated.
            Image(
                painter = painterResource(day.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // if check for expanded is true, the content will be displayed.
            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(day.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.2f
                )
            }
        }
    }
}

// preview for the app
@Preview(showBackground = true)
@Composable
fun DaysAppPreview() {
    A30DaysAppTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            DaysApp()
        }
    }
}