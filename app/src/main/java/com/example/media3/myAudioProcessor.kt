import androidx.media3.common.audio.AudioProcessor
import java.nio.ByteBuffer

@androidx.media3.common.util.UnstableApi
class myAudioProcessor : AudioProcessor {


    companion object {
        const val SAMPLE_SIZE = 4096

        // From DefaultAudioSink.java:160 'MIN_BUFFER_DURATION_US'
        private const val EXO_MIN_BUFFER_DURATION_US: Long = 250000

        // From DefaultAudioSink.java:164 'MAX_BUFFER_DURATION_US'
        private const val EXO_MAX_BUFFER_DURATION_US: Long = 750000

        // From DefaultAudioSink.java:173 'BUFFER_MULTIPLICATION_FACTOR'
        private const val EXO_BUFFER_MULTIPLICATION_FACTOR = 4

        // Extra size next in addition to the AudioTrack buffer size
        private const val BUFFER_EXTRA_SIZE = SAMPLE_SIZE * 8
    }



    override fun configure(inputAudioFormat: AudioProcessor.AudioFormat): AudioProcessor.AudioFormat {
        TODO("Not yet implemented")
    }

    override fun isActive(): Boolean {
        TODO("Not yet implemented")
    }

    override fun queueInput(inputBuffer: ByteBuffer) {
        TODO("Not yet implemented")
    }

    override fun queueEndOfStream() {
        TODO("Not yet implemented")
    }

    override fun getOutput(): ByteBuffer {
        TODO("Not yet implemented")
    }

    override fun isEnded(): Boolean {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

}