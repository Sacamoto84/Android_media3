package com.example.media3

import android.content.Context
import android.net.Uri
import android.os.Handler
import androidx.media3.common.MediaItem
import androidx.media3.common.audio.SonicAudioProcessor
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.audio.AudioCapabilities
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.audio.AudioSink
import androidx.media3.exoplayer.audio.DefaultAudioSink

import androidx.media3.exoplayer.audio.MediaCodecAudioRenderer
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.transformer.EditedMediaItem
import myAudioProcessor


@androidx.media3.common.util.UnstableApi
fun initPlayer(context : Context) {

    var player: ExoPlayer? = null

    //val fftAudioProcessor = FFTAudioProcessor()

    // We need to create a renderers factory to inject our own audio processor at the end of the list

    val renderersFactory = object : DefaultRenderersFactory(context) {

        override fun buildAudioRenderers(
            context: Context,
            extensionRendererMode: Int,
            mediaCodecSelector: MediaCodecSelector,
            enableDecoderFallback: Boolean,
            audioSink: AudioSink,
            eventHandler: Handler,
            eventListener: AudioRendererEventListener,
            out: ArrayList<Renderer>
        ) {


            val sonicAudioProcessor = myAudioProcessor()//SonicAudioProcessor()

            val myAudioSink : AudioSink=  DefaultAudioSink.Builder(context)
                .setAudioCapabilities(AudioCapabilities.getCapabilities(context))
                .setAudioProcessors(arrayOf(sonicAudioProcessor))
                .build()

            out.add(
                MediaCodecAudioRenderer(
                    context,
                    mediaCodecSelector,
                    enableDecoderFallback,
                    eventHandler,
                    eventListener,
                    myAudioSink
                )
            )

            super.buildAudioRenderers(
                context,
                extensionRendererMode,
                mediaCodecSelector,
                enableDecoderFallback,
                audioSink,
                eventHandler,
                eventListener,
                out
            )
        }
    }

    player = ExoPlayer.Builder(context, renderersFactory)
        .build()

//    val visualizer = findViewById<ExoVisualizer>(R.id.visualizer)
//    visualizer.processor = fftAudioProcessor

    // Online radio:
    //val uri = Uri.parse("http://listen.livestreamingservice.com/181-xsoundtrax_128k.mp3")
    // 1 kHz test sound:
    // val uri = Uri.parse("https://www.mediacollege.com/audio/tone/files/1kHz_44100Hz_16bit_05sec.mp3")
    // 10 kHz test sound:
    // val uri = Uri.parse("https://www.mediacollege.com/audio/tone/files/10kHz_44100Hz_16bit_05sec.mp3")
    // Sweep from 20 to 20 kHz
    // val uri = Uri.parse("https://www.churchsoundcheck.com/CSC_sweep_20-20k.wav")

//    val mediaSource = ProgressiveMediaSource.Factory(
//        DefaultDataSourceFactory(context, "ExoVisualizer")
//    ).createMediaSource(MediaItem.Builder().setUri(uri).build())

    player.playWhenReady = true

    //player.setMediaSource(mediaSource)


    //val uri = Uri.parse("asset:///1.mp3")
    //val uri = Uri.parse("asset:///CH Blow Me_beats_in_phase Rc.mp3")
    val uri = Uri.parse("asset:///Get Hard.mp3")

    val a = EditedMediaItem.Builder(MediaItem.fromUri(uri)).build()
    player.setMediaItem(a.mediaItem)
    player.prepare()
    player.play()

}