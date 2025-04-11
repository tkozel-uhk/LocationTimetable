package cz.uhk.umte.tt.ui.activities

/**
 * Datová třída reprezentující událost v ActivityScreenu.
 * Používá se ve viewmodelu ActivitiesViewModel
 */
sealed class ActivitiesUiEvent {
        /**
         * Datová třída reprezentující událost změny lokace ve vstupním poli.
         */
        data class LocationChanged(val newLocation: String) : ActivitiesUiEvent()
        /**
         * Objekt/Singleton reprezentující událost načtení rozvrhových aktivit.
         */
        object FetchActivities : ActivitiesUiEvent()
}