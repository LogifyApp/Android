package com.example.logify.view.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.R
import com.example.logify.ui.theme.BlueBar
import com.example.logify.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployerBottomAppBar(unreadMessageCount: Int) {
    BottomAppBar(
        containerColor = BlueBar,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBlue,
                unselectedIconColor = BlueBar,
                indicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(50))
                .scale(1.5f)
        )
        Spacer(modifier = Modifier.weight(0.45f))
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.drivers),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBlue,
                unselectedIconColor = BlueBar,
                indicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(50))
                .scale(1.5f)
        )
        Spacer(modifier = Modifier.weight(0.45f))
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.cargo),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBlue,
                unselectedIconColor = BlueBar,
                indicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(50))
                .scale(1.5f)
        )
        Spacer(modifier = Modifier.weight(0.45f))
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        if (unreadMessageCount > 0) {
                            Badge {
                                Text(unreadMessageCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.chat),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkBlue,
                unselectedIconColor = BlueBar,
                indicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(50))
                .scale(1.5f)
        )
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Preview
@Composable
fun Preview(){
    EmployerBottomAppBar(1)
}