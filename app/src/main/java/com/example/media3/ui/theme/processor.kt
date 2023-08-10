package com.example.media3.ui.theme
import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.audio.AudioProcessor.UnhandledAudioFormatException
import androidx.media3.common.audio.BaseAudioProcessor
import androidx.media3.common.util.Assertions
import androidx.media3.exoplayer.audio.AudioSink

//import com.google.errorprone.annotations.CanIgnoreReturnValue

import java.nio.ByteBuffer


/**
 * An [AudioProcessor] that applies a mapping from input channels onto specified output
 * channels. This can be used to reorder, duplicate or discard channels.
 *//* package */

@androidx.media3.common.util.UnstableApi
internal class MyAudioProcessor : BaseAudioProcessor() {

    private var pendingOutputChannels: IntArray? = null
    private var outputChannels: IntArray? = null

    /**
     * Resets the channel mapping. After calling this method, call [.configure] to
     * start using the new channel map.
     *
     *
     * See [AudioSink.configure].
     *
     * @param outputChannels The mapping from input to output channel indices, or `null` to
     * leave the input unchanged.
     */
    fun setChannelMap(outputChannels: IntArray?) {
        pendingOutputChannels = outputChannels
    }

    //@CanIgnoreReturnValue
    @Throws(UnhandledAudioFormatException::class)
    public override fun onConfigure(inputAudioFormat: AudioProcessor.AudioFormat): AudioProcessor.AudioFormat {
        val outputChannels = pendingOutputChannels ?: return AudioProcessor.AudioFormat.NOT_SET
        if (inputAudioFormat.encoding != C.ENCODING_PCM_16BIT) {
            throw UnhandledAudioFormatException(inputAudioFormat)
        }
        var active = inputAudioFormat.channelCount != outputChannels.size
        for (i in outputChannels.indices) {
            val channelIndex = outputChannels[i]
            if (channelIndex >= inputAudioFormat.channelCount) {
                throw UnhandledAudioFormatException(inputAudioFormat)
            }
            active = active or (channelIndex != i)
        }
        return if (active) AudioProcessor.AudioFormat(
            inputAudioFormat.sampleRate,
            outputChannels.size,
            C.ENCODING_PCM_16BIT
        ) else AudioProcessor.AudioFormat.NOT_SET
    }

    override fun queueInput(inputBuffer: ByteBuffer) {
        val outputChannels = Assertions.checkNotNull(outputChannels)
        var position = inputBuffer.position()
        val limit = inputBuffer.limit()
        val frameCount = (limit - position) / inputAudioFormat.bytesPerFrame
        val outputSize = frameCount * outputAudioFormat.bytesPerFrame
        val buffer = replaceOutputBuffer(outputSize)
        while (position < limit) {
            for (channelIndex in outputChannels) {
                buffer.putShort(inputBuffer.getShort(position + 2 * channelIndex))
            }
            position += inputAudioFormat.bytesPerFrame
        }
        inputBuffer.position(limit)
        buffer.flip()
    }

    override fun onFlush() {
        outputChannels = pendingOutputChannels
    }

    override fun onReset() {
        outputChannels = null
        pendingOutputChannels = null
    }
}