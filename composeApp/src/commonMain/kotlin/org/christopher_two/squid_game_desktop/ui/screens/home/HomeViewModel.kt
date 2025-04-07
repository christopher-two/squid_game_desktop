package org.christopher_two.squid_game_desktop.ui.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.network.firebase.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.christopher_two.squid_game_desktop.ui.components.game.GlassState
import org.christopher_two.squid_game_desktop.utils.data.HomeState
import kotlin.random.Random

class HomeViewModel(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun observe() {
        viewModelScope.launch {
            firebaseRepository.getPlayers().collect { players ->
                update { copy(statusPlayers = players) }
            }
        }
    }

    fun observeServos() {
        viewModelScope.launch {
            firebaseRepository.getServos().collect { servos ->
                update {
                    copy(glasses = servos.mapIndexed { index, isActive ->
                        GlassState(
                            id = index,
                            isActive = isActive,
                            left = statusPlayers?.find { it.numPlayer == (index + 1).toString() },
                            right = statusPlayers?.find { it.numPlayer == (index + 2).toString() }
                        )
                    })
                }
            }
        }
    }

    fun updateRenewed() {
        val maxRenewed = 455_555_555
        val minRenewed = 0

        _state.value.statusPlayers?.let { players ->
            val totalPlayers = players.size
            if (totalPlayers == 0) return  // Evitar división por cero

            val alivePlayers = players.count { it.isAlive == true }
            val deadPlayers = totalPlayers - alivePlayers

            // Calcular el porcentaje renovado
            val renewedValue = if (deadPlayers == 0) {
                minRenewed
            } else {
                (deadPlayers.toDouble() / totalPlayers) * maxRenewed
            }.toInt().toString()

            update {
                copy(
                    rewards = "₩$renewedValue"
                )
            }
        }
    }

    fun toggleGlass(id: Int) {
        viewModelScope.launch {
            firebaseRepository.updateServo(id, true)
        }
    }

    fun restart() {
        viewModelScope.launch {
            for (i in 0..15) {
                firebaseRepository.updateServo(i, false)
            }
            savePlayers()
            resetWinners()
        }
    }

    fun skipGlass() {
        viewModelScope.launch {
            val currentGlasses = _state.value.glasses ?: return@launch
            var lastActivatedIndex = -1

            // Intentar activar servos aleatorios
            for (i in 0..15) {
                val glass = currentGlasses.getOrNull(i) ?: continue
                if (!glass.isActive && Random.nextBoolean()) {
                    firebaseRepository.updateServo(i, true)
                    lastActivatedIndex = i
                    break
                }
            }

            // Si no se activó ninguno, activar el último
            if (lastActivatedIndex == -1) {
                val lastGlass = currentGlasses[15]
                if (!lastGlass.isActive) {
                    firebaseRepository.updateServo(15, true)
                }
            }
        }
    }

    fun killPlayer(id: String) {
        viewModelScope.launch {
            firebaseRepository.killPlayer(id)
        }
    }

    fun winnerPlayer(id: String, isWinner: Boolean) {
        viewModelScope.launch {
            for (player in _state.value.statusPlayers ?: emptyList()) {
                if (player.id.toString() == id) {
                    firebaseRepository.winnerPlayer(id, isWinner)
                }
            }
        }
    }

    suspend fun savePlayers() {
        for (player in _state.value.statusPlayers ?: emptyList()) {
            player.isAlive?.let {
                firebaseRepository.savePlayer(player.id.toString(), true)
                print(it)
            }
        }
    }

    suspend fun resetWinners() {
        for (player in _state.value.statusPlayers ?: emptyList()) {
            player.isWinner?.let {
                firebaseRepository.winnerPlayer(
                    player.id.toString(),
                    false
                )
            }
        }
    }

    fun update(update: HomeState.() -> HomeState) {
        _state.value = update(_state.value)
    }
}