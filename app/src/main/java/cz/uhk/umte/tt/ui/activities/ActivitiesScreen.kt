package cz.uhk.umte.tt.ui.activities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.uhk.umte.tt.R
import cz.uhk.umte.tt.model.StagActivity
import org.koin.compose.koinInject
import androidx.compose.ui.tooling.preview.Preview
import java.util.UUID

/**
 * Komponenta pro zobrazení výchozí obrazovky se vstupním polem pro zadání lokace,
 * tlačítkem na načítání aktivit a seznamem aktivit.
 */
@Composable
fun ActivitiesScreen(viewModel: ActivitiesViewModel = koinInject()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LocationInput(
            locationInput = uiState.locationInput,
            onLocationChanged = { viewModel.onEvent(ActivitiesUiEvent.LocationChanged(it)) },
            onFetchActivities = { viewModel.onEvent(ActivitiesUiEvent.FetchActivities) },
        )

        when {
            uiState.activitiesResult.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.activitiesResult.error != null -> {
                Text(
                    "Chyba: ${uiState.activitiesResult.error}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            uiState.activitiesResult.data != null -> {
                ActivityList(activities = uiState.activitiesResult.data!!)
            }
        }
    }
}

/**
 * Komponenta pro vstupní pole lokace a tlačítko pro načtení rozvrhových aktivit.
 */
@Composable
fun LocationInput(
    locationInput: String,
    onLocationChanged: (String) -> Unit,
    onFetchActivities: () -> Unit,
) {
    OutlinedTextField(
        value = locationInput,
        onValueChange = onLocationChanged,
        label = { Text(stringResource(R.string.inpuLabel)) },
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = onFetchActivities) {
        Text(stringResource(R.string.btReadAct))
    }
    Spacer(modifier = Modifier.height(16.dp))
}


/**
 * Komponenta se seznamem rozvrh. aktivit.
 */
@Composable
fun ActivityList(activities: List<StagActivity>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(activities) { stagActivity ->
            ActivityItem(activity = stagActivity)
        }
    }
}

/**
 * komponeta pro zobrazení jednotlivé aktivity v seznamu
 */
@Composable
fun ActivityItem(activity: StagActivity) {
    Column(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = activity.name, style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold, color = Color(0xFF0060A0)
        )
        Text(text = activity.description, style = MaterialTheme.typography.bodyMedium)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = activity.teacher, style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = activity.type, style = MaterialTheme.typography.bodySmall, color = Color.Red)
        }

    }
}


/**
 * Preview se vzorovými daty
 */
@Preview
@Composable
fun ActivityListPreview() {
    val sampleActivities = listOf(
        StagActivity("ALGDS", "Algoritmy a datové struktury", "Jan Novák", "Cvičení"),
        StagActivity("PRO1", "Algoritmy a datové struktury", "Josef Novotný", "Cvičení"),
        StagActivity("UMTE", "Úvod do mobilních technologií", "Tomáš Kozel", "Přednáška")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LocationInput(locationInput = "J1", onLocationChanged = {}, onFetchActivities = {})
        ActivityList(activities = sampleActivities)
    }

}

