package com.example.media3

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


class MainActivity : ComponentActivity() {



    @androidx.media3.common.util.UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {

        initPlayer(this)

        // Добавление аудиопроцессора к ExoPlayer


//        val uri = Uri.parse("asset:///1.mp3")
//        val media = MediaItem.fromUri(uri)
//
//        val aa = DefaultAudioSink.Builder(this)
//            .build()
//
//        val a = EditedMediaItem.Builder(MediaItem.fromUri(uri))
//            .setEffects(
//                Effects(
//                    /* audioProcessors= */ listOf(
//
//
//
//                    ),
//                    /* videoEffects= */ listOf(
//                        //                    ScaleAndRotateTransformation.Builder()
//                        //                        .setRotationDegrees(90f)
//                        //                        .build()
//                    )
//                )
//            ).build()
//
//        player.setMediaItem(a.mediaItem)
//        player.prepare()
//        player.repeatMode = REPEAT_MODE_ONE
//
//        player.play()



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
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Media3Theme {
        Greeting("Android")
    }
}