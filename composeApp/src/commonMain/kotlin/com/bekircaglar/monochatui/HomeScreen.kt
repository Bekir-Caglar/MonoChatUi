@file:OptIn(ExperimentalMaterial3Api::class)

package com.bekircaglar.monochatui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import monochatui.composeapp.generated.resources.Res
import monochatui.composeapp.generated.resources.ic_archive
import monochatui.composeapp.generated.resources.ic_chat
import monochatui.composeapp.generated.resources.ic_cube
import monochatui.composeapp.generated.resources.ic_cyclone
import monochatui.composeapp.generated.resources.ic_home
import monochatui.composeapp.generated.resources.ic_sandwatch
import monochatui.composeapp.generated.resources.ic_shop
import monochatui.composeapp.generated.resources.ic_star1
import monochatui.composeapp.generated.resources.ic_treedot
import monochatui.composeapp.generated.resources.ic_user1
import monochatui.composeapp.generated.resources.ic_user2
import monochatui.composeapp.generated.resources.ic_user3
import monochatui.composeapp.generated.resources.ic_user4
import monochatui.composeapp.generated.resources.ic_user5
import monochatui.composeapp.generated.resources.img
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource

data class BottomNavItem(
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
)
data class Messages(
    val drawable: DrawableResource,
    val text: String,
    val messageCount: Int? = 0,
    val iconColor: Color,
    val iconBackgroundColor: Color
)

data class Representative(
    val drawable: DrawableResource,
    val name: String,
    val date: String,
    val time :String,
    val status:Boolean,
)

val representatives = listOf(
    Representative(drawable = Res.drawable.ic_user1, "Bekir Çağlar", "22.06.2024", "12:30", true),
    Representative(drawable = Res.drawable.ic_user2, "Ali Velioğlu", "11.01.2024", "12:30", false),
    Representative(drawable = Res.drawable.ic_user3, "Sude Yıldız", "15.05.2024", "12:30", false),
    Representative(drawable = Res.drawable.ic_user4, "Tuğçe Demirbilek", "12.08.2024", "12:30", false),
    Representative(drawable = Res.drawable.ic_user5, "Mustafa Kır", "05.08.2024", "12:30", true),
    Representative(drawable = Res.drawable.img, "Selim Cevahir", "28.08.2024", "12:30", true),

    )


val messages = listOf(
    Messages(drawable = Res.drawable.ic_sandwatch, "Bekleyen", 2, iconColor = Color(242,141,57,), iconBackgroundColor = Color(255,239,226,)),
    Messages(drawable = Res.drawable.ic_star1, "Yeni", 12, iconColor = Color(68,142,186,), iconBackgroundColor = Color(215,241,255)),
    Messages(drawable = Res.drawable.ic_treedot, "Aktif", 45, iconColor = Color(108,165,141), iconBackgroundColor = Color(219,245,234,)),
    Messages(drawable =  Res.drawable.ic_archive, "Sonlanan", 414, iconColor = Color(129,133,143,), iconBackgroundColor = Color(235,237,239)),
)

@Composable
fun HomeScreen() {

    val items = listOf(
        BottomNavItem("Home", Res.drawable.ic_home, Res.drawable.ic_home),
        BottomNavItem("Chat", Res.drawable.ic_chat, Res.drawable.ic_chat, true, 2),
        BottomNavItem("Cube", Res.drawable.ic_cube, Res.drawable.ic_cube),
        BottomNavItem("Shop", Res.drawable.ic_shop, Res.drawable.ic_shop),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MonoChat",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = Bold
                        ),
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_home),
                        contentDescription = "Home",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                },
                actions = {
                       BadgedBox(
                           badge = {
                               Badge(containerColor = MaterialTheme.colorScheme.primary)
                           }
                       ) {
                           IconButton(
                               onClick = { /*TODO*/ },
                               modifier = Modifier.size(24.dp)
                           ) {
                               Icon(
                                   imageVector = Icons.Outlined.Notifications,
                                   contentDescription = "notification",
                               )
                           }
                       }

                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },





        bottomBar = {
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding()
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp,)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .height(55.dp)
                    ,
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(),){
                        items.forEachIndexed { index, item ->
                            this@NavigationBar.NavigationBarItem(

                                selected = selectedItemIndex == index,
//                                alwaysShowLabel = false,
//                                label = {
//                                    Text(
//                                        text = item.title,
//                                    )
//                                },
                                onClick = {
                                    selectedItemIndex = index
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null){
                                                Badge{
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if (item.hasNews){
                                                Badge()
                                            }

                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(if (index == selectedItemIndex)item.selectedIcon else item.unSelectedIcon, ),
                                            contentDescription = item.title,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                },
                                colors = NavigationBarItemColors(
                                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedIndicatorColor = Color.Transparent,
                                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                                    disabledTextColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }

            }

        }





    ) { innerPadding ->
        // İçerik buraya eklenebilir
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).background(color = MaterialTheme.colorScheme.surface)) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
                .clip(shape =MaterialTheme.shapes.medium )
                .background(color = MaterialTheme.colorScheme.surface)
                .border(width = 1.dp, color = Color.LightGray, shape = MaterialTheme.shapes.medium)

            ){
                Column {
                    Text(
                        text = "Mesajlar",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(16.dp)
                    )

                    LazyHorizontalGrid(rows = GridCells.Fixed(2),modifier= Modifier
                        .height(140.dp)
                        .padding(start = 8.dp)) {
                        items(messages.size){
                            val message = messages[it]
                            Messages(drawable = message.drawable, text =message.text, msgCount = message.messageCount, iconColor = message.iconColor, iconBackgroundColor = message.iconBackgroundColor)
                        }
                    }

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 16.dp)
                .clip(shape =MaterialTheme.shapes.medium )
                .background(color = MaterialTheme.colorScheme.surface)
                .border(width = 1.dp, color = Color.LightGray, shape = MaterialTheme.shapes.medium)

            ){
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(

                            text = "Temsilci Aktifliği",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = Bold,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(16.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedButton(
                            onClick = { /*TODO*/ },

                            modifier = Modifier
                                .padding(16.dp)
                                .height(32.dp)
                        ) {
                            Text(
                                text = "Tümü",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = Bold,
                                    fontSize = 12.sp
                                ),
                                color = Color.DarkGray
                            )
                            Icon(Icons.Outlined.KeyboardArrowDown, contentDescription = "more", tint = Color.Gray,)
                        }
                    }

                    LazyColumn(modifier = Modifier
                        .fillMaxWidth(),

                    ){
                        items(representatives.size){
                            val representative = representatives[it]
                            RepresentativePerson(drawable =representative.drawable, name = representative.name, date = representative.date, time = representative.time, status = representative.status)
                        }

                    }






                }


            }

            Spacer(modifier = Modifier.height(16.dp))

//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(horizontal = 16.dp)
//                .clip(shape =MaterialTheme.shapes.medium )
//                .background(color = MaterialTheme.colorScheme.surface)
//                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
//
//            ){
//
//            }

        }


    }
}


@Composable
fun RepresentativePerson(modifier: Modifier = Modifier,drawable:DrawableResource, name: String,date: String,time: String,status: Boolean){
    Box( contentAlignment = Alignment.Center) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            ) {
                    Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // FillHeight yerine Crop kullanabilirsiniz
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .shadow(4.dp, CircleShape)
                    )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = Bold
                        ),
                    )
                    Text(
                        text = "$date $time",
                        style = MaterialTheme.typography.bodySmall.copy(
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )

                }
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButton(
                    contentPadding = PaddingValues(0.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonColors(
                        containerColor = if (status) Color(220, 245, 232,) else Color(
                            235,
                            236,
                            241
                        ),
                        contentColor = if (status) Color(100, 190, 142) else Color(158, 162, 178),
                        disabledContentColor = if (status) Color(100, 190, 142) else Color(
                            158,
                            162,
                            178
                        ),
                        disabledContainerColor = if (status) Color(220, 245, 232,) else Color(
                            235,
                            236,
                            241
                        ),
                    ),
                    modifier = Modifier
                        .height(26.dp)
                        .width(80.dp)
                ) {
                    Text(
                        text = if (status) "Çevrimiçi" else "Çevrimdışı",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = Bold,
                            fontSize = 10.sp
                        ),
                    )
                }
            }
            HorizontalDivider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
            )
        }

    }


}






@Composable
fun Messages(modifier: Modifier = Modifier,drawable:DrawableResource, text: String,msgCount:Int? = 0,iconColor: Color, iconBackgroundColor: Color){
    Surface(shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(4.dp),) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp)
                .width(150.dp)

            ,
            )
        {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier
                    .shadow(elevation = 4.dp, CircleShape)
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .background(color = iconBackgroundColor)
                    .padding(15.dp)

            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = msgCount.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = Bold,
                        fontSize = 20.sp
                    ),
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall.copy(),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}