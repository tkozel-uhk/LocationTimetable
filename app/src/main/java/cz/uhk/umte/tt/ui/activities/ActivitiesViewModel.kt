package cz.uhk.umte.tt.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.uhk.umte.tt.repo.ActivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel pro ActivityScreen
 */
class ActivitiesViewModel(private val repository: ActivityRepository) : ViewModel() {
    //stav ActivityScreenu
    private val _uiState = MutableStateFlow(ActivitiesUiState())
    val uiState: StateFlow<ActivitiesUiState> = _uiState

    /**
     * Obsluha událostí v ActivityScreenu
     */
    fun onEvent(event: ActivitiesUiEvent) {
        when (event) {
            //update texfieldu
            is ActivitiesUiEvent.LocationChanged -> {
                _uiState.update { it.copy(locationInput = event.newLocation) }
            }
            //tlačítko na načítání aktivit pro danou místnost
            is ActivitiesUiEvent.FetchActivities -> {
                fetchActivities(_uiState.value.locationInput)
            }
        }
    }

    /**
     * Získání aktivit z API a aktualizace stavu
     */
    private fun fetchActivities(location: String) {
        viewModelScope.launch { //voláme coroutine
            repository.getActivities(location) //řekneme o nová data, získáme flow
                .collect { result ->  //vyzvedneme výsledek flow do stavu
                    _uiState.update { it.copy(activitiesResult = result) } //změna stavu
                }
        }
    }
}