package com.example.media3

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.common.audio.AudioProcessorChain
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.RenderersFactory
import androidx.media3.exoplayer.audio.DefaultAudioSink
import androidx.media3.exoplayer.audio.TeeAudioProcessor
import androidx.media3.transformer.EditedMediaItem
import androidx.media3.transformer.Effects
import com.example.media3.ui.theme.Media3Theme
import kotlinx.coroutines.flow.MutableStateFlow


val enL  = MutableStateFlow(true)
val enR  = MutableStateFlow(true)

class MainActivity : ComponentActivity() {



    @androidx.media3.common.util.UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {

        initPlayer(this)

        // Добавление аудиопроцессора к ExoPlayer

        super.onCreate(savedInstanceState)
        setContent {
            Media3Theme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

   Column {

       Row {

           Column {
               Text(text = "EN Left ${enL.collectAsState().value}")
               Button(onClick = { enL.value = !enL.value }) {}
           }

           Spacer(modifier = Modifier.width(32.dp))

           Column {
               Text(text = "EN Right ${enR.collectAsState().value}")
               Button(onClick = { enR.value = !enR.value }) {}
           }

       }
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Media3Theme {
        Greeting("Android")
    }
}