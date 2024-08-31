package com.example.logify.view.screens.driver

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logify.data.Message
import com.example.logify.ui.theme.BackgroundLightBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//TODO Fix problem with twitching of TopAppBar
//TODO Fix UX-bug (after opening the keyboard lowest message should be seen)

@Composable
fun ChatScreen() {
    val messages = remember { mutableStateListOf<Message>() }
    var messageText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var showScrollToBottomButton by remember { mutableStateOf(false) }

    val isUserAtBottom by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            messages.isEmpty() || (layoutInfo.visibleItemsInfo.isNotEmpty() && layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1)
        }
    }

    LaunchedEffect(isUserAtBottom) {
        if (isUserAtBottom) {
            showScrollToBottomButton = false
        } else {
            delay(300)
            if (!isUserAtBottom) {
                showScrollToBottomButton = true
            }
        }
    }

    Scaffold(
        topBar = { ChatTopBar() },
        bottomBar = {
            MessageInput(
                messageText,
                onTextChange = { messageText = it },
                onSend = {
                    if (messageText.isNotBlank()) {
                        coroutineScope.launch {
                            messages.add(Message(messageText, isUser = true))
                            messageText = ""
                            delay(50)
                            if (isUserAtBottom) {
                                listState.animateScrollToItem(messages.size - 1)
                            }
                        }
                    }
                },
                onDone = {
                    coroutineScope.launch {
                        delay(50) // Ensure that the keyboard has opened before scrolling
                        listState.animateScrollToItem(messages.size - 1)
                    }
                }
            )
        },
        floatingActionButton = {
            if (showScrollToBottomButton) {
                FloatingActionButton(onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(messages.size - 1)
                    }
                }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Scroll to bottom")
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLightBlue)
                .padding(innerPadding)
        ) {
            ChatMessages(messages, listState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar() {
    val context = LocalContext.current
    val phoneNumber = "1234567890"

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Employer's name", color = Color.White)
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* Handle back navigation */ }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                context.startActivity(intent)
            }) {
                Icon(Icons.Default.Call, contentDescription = "Call", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1976D2)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ChatMessages(messages: List<Message>, listState: LazyListState) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(messages) { message ->
            ChatMessageRow(message)
        }
    }
}

@Composable
fun ChatMessageRow(message: Message) {
    val backgroundColor = if (message.isUser) Color(0xFF1976D2) else Color.White
    val textColor = if (message.isUser) Color.White else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            Text(text = message.text, color = textColor)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(
    messageText: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit,
    onDone: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = messageText,
            onValueChange = onTextChange,
            placeholder = { Text(text = "Enter your message") },
            modifier = Modifier
                .weight(1f)
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSend()
                    onDone()
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )
        IconButton(onClick = {
            onSend()
            onDone()
        }) {
            Icon(Icons.Default.Send, contentDescription = "Send", tint = Color(0xFF1976D2))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
    ChatScreen()
}