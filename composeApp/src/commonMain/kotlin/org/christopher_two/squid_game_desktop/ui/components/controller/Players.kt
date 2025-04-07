package org.christopher_two.squid_game_desktop.ui.components.controller

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.network.firebase.models.StatusPlayer
import org.christopher_two.squid_game_desktop.ui.screens.home.HomeViewModel
import org.christopher_two.squid_game_desktop.utils.data.HomeState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import squid_game_desktop.composeapp.generated.resources.Res
import squid_game_desktop.composeapp.generated.resources.account_circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.add_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.credit_card_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.elderly_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.height_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.monitor_weight_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.paid_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.remove_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.savings_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.skull_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
import squid_game_desktop.composeapp.generated.resources.stars_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24

@Composable
fun Players(state: HomeState, viewModel: HomeViewModel) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(10.dp),
        content = {
            state.statusPlayers.also { list ->
                list?.also {
                    items(it) {
                        PlayerBox(it, viewModel)
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                } ?: run {
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    )
}

@Composable
fun PlayerBox(player: StatusPlayer, viewModel: HomeViewModel) {
    val backgroundColor = remember(player) {
        when {
            player.isWinner == true -> Color(0xff0d9b03).copy(alpha = 0.7f)
            player.isAlive == false -> Color.Red.copy(alpha = 0.4f)
            else -> Color(0xff04625f).copy(alpha = 0.4f)
        }
    }

    val textColor = remember(player) {
        when {
            player.isWinner == true -> Color.White
            player.isAlive == false -> Color.Red
            else -> Color(0xFF249f9c).copy(alpha = 0.8f)
        }
    }

    val hoverInteraction = remember { MutableInteractionSource() }
    val isHovered by hoverInteraction.collectIsHoveredAsState()

    val elevation by animateDpAsState(
        targetValue = if (isHovered) 8.dp else 2.dp,
        animationSpec = tween(300)
    )

    var expanded by remember { mutableStateOf(false) }
    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 700.dp else 100.dp,
        animationSpec = tween(400)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .hoverable(hoverInteraction)
            .clickable { expanded = !expanded }
            .shadow(elevation = elevation, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        AnimatedVisibility(visible = expanded) {
            ExpandedContent(player, textColor)
        }

        if (!expanded) {
            CollapsedContent(player, textColor, expanded, viewModel)
        }
    }
}

@Composable
private fun CollapsedContent(
    player: StatusPlayer,
    textColor: Color,
    expanded: Boolean,
    viewModel: HomeViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PlayerImage(player.image.toString(), 60.dp)

        Spacer(Modifier.width(16.dp))

        Column(Modifier.weight(1f)) {
            Text(
                text = player.numPlayer ?: "Unknown",
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                    contentDescription = null,
                    tint = if (player.isActive == true) Color.Green else Color.Red,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = if (player.isActive == true) "Active" else "Inactive",
                    color = if (player.isActive == true) Color.Green else Color.Red,
                    fontSize = 14.sp
                )
            }
        }

        Icon(
            painter = painterResource(if (expanded) Res.drawable.remove_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24 else Res.drawable.add_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
            contentDescription = "Expand",
            tint = textColor
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { viewModel.killPlayer(player.id.toString()) },
                modifier = Modifier.size(34.dp),
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.skull_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                        contentDescription = null,
                        tint = Color(0xff9b0303),
                        modifier = Modifier.size(34.dp)
                    )
                }
            )
            IconButton(
                onClick = { viewModel.winnerPlayer(player.id.toString(), player.isWinner == false) },
                modifier = Modifier.size(34.dp),
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.savings_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24),
                        contentDescription = null,
                        tint = Color(0xff0d9b03),
                        modifier = Modifier.size(34.dp)
                    )
                }
            )
        }
    }
}

@Composable
private fun ExpandedContent(player: StatusPlayer, textColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            PlayerImage(player.image.toString(), 100.dp)
            Spacer(Modifier.height(8.dp))
            Text(
                text = "#${player.numPlayer}",
                color = textColor.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }

        HorizontalDivider(Modifier.fillMaxWidth(), 1.dp, textColor.copy(alpha = 0.2f))

        InfoRow(
            icon = Res.drawable.account_circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
            title = "Personal Info",
            items = listOf(
                "Age: ${player.age}" to Res.drawable.elderly_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
                "Height: ${player.height}" to Res.drawable.height_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
                "Weight: ${player.weight}" to Res.drawable.monitor_weight_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
                "Gender: ${player.gender}" to Res.drawable.stars_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
                "Sexuality: ${player.sex}" to Res.drawable.circle_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24
            ),
            textColor = textColor
        )

        InfoRow(
            icon = Res.drawable.paid_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
            title = "Location",
            items = listOf(
                "City: ${player.city}" to null,
                "Country: ${player.country}" to null
            ),
            textColor = textColor
        )

        InfoRow(
            icon = Res.drawable.credit_card_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24,
            title = "Financial Status",
            items = listOf(
                "History: ${player.financialHistory}" to null,
                "Civil: ${player.civilStatus}" to null
            ),
            textColor = textColor
        )
    }
}

@Composable
private fun InfoRow(
    icon: DrawableResource,
    title: String,
    items: List<Pair<String, DrawableResource?>>,
    textColor: Color
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        items.forEach { (text, iconRes) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp)
            ) {
                iconRes?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = textColor.copy(alpha = 0.8f),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                }
                Text(
                    text = text,
                    color = textColor.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun PlayerImage(url: String, size: Dp) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(colorScheme.surfaceVariant)
            .border(2.dp, colorScheme.primary, CircleShape)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "Player image",
            modifier = Modifier.fillMaxSize().clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}