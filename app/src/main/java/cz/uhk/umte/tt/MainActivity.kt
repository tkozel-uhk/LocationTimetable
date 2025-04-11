package cz.uhk.umte.tt

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.uhk.umte.tt.repo.ActivityRepository
import cz.uhk.umte.tt.ui.activities.ActivitiesScreen
import cz.uhk.umte.tt.ui.activities.ActivitiesViewModel
import cz.uhk.umte.tt.ui.theme.LocationTimetableTheme
import org.koin.android.ext.android.get
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.koinInject

/**
 * Hlavní aktivita aplikace
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationTimetableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ActivitiesScreen()
                }
            }
        }
    }
}

