package com.facebook.assistant.oacr;

import X.C0139Dd;
import X.C0818iN;
import X.C0819iO;
import X.C0820iQ;
import X.FU;
import X.MQ;
import android.os.Handler;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public class AudioPlayerChannelForwarder implements WritableByteChannel {
    public static final String NATIVE_EXECUTOR_TAG = "AudioPlayerChannelForwarder_NativeExecutorThread";
    public final Handler mHandler;
    public final String mInteractionId;
    public WritableByteChannel mRealChannel;

    public class AudioPlayerChannel implements WritableByteChannel {
        public final String TAG = "AudioPlayerChannelForwarder$AudioPlayerChannel";
        public final FU mAssistantTTSPlayer;
        public final String mInteractionId;
        public final OacrClientListener mOacrClientListener;

        public boolean isOpen() {
            return true;
        }

        public AudioPlayerChannel(String str, String str2, FU fu, OacrClientListener oacrClientListener) {
            this.mInteractionId = str2;
            C0139Dd.A0I("AudioPlayerChannelForwarder$AudioPlayerChannel", "onTtsStart, mimeType: %s, interactionId %s", str, str2);
            this.mAssistantTTSPlayer = fu;
            this.mOacrClientListener = oacrClientListener;
            oacrClientListener.onTtsStart(new C0820iQ(str, fu));
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() {
            C0139Dd.A0G(this.TAG, "onTtsEnd, interactionId: %s", this.mInteractionId);
            this.mOacrClientListener.onTtsEnd(new C0819iO(this.mAssistantTTSPlayer));
        }

        @Override // java.nio.channels.WritableByteChannel
        public int write(ByteBuffer byteBuffer) {
            this.mOacrClientListener.onTtsAudioData(new C0818iN(byteBuffer, this.mAssistantTTSPlayer));
            return byteBuffer.remaining();
        }
    }

    public AudioPlayerChannelForwarder(Handler handler, final String str, final String str2, final FU fu, final OacrClientListener oacrClientListener) {
        this.mHandler = handler;
        this.mInteractionId = str2;
        handler.post(new MQ("OacrVoiceInteractionListener: onTtsAudioData") {
            /* class com.facebook.assistant.oacr.AudioPlayerChannelForwarder.AnonymousClass1 */

            public void run() {
                AudioPlayerChannelForwarder audioPlayerChannelForwarder = AudioPlayerChannelForwarder.this;
                audioPlayerChannelForwarder.mRealChannel = new AudioPlayerChannel(str, str2, fu, oacrClientListener);
            }
        });
    }

    public boolean isOpen() {
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        C0139Dd.A0F(NATIVE_EXECUTOR_TAG, "onTtsEnd, interactionId: %s", this.mInteractionId);
        this.mHandler.post(new MQ("OacrVoiceInteractionListener: onTtsEnd") {
            /* class com.facebook.assistant.oacr.AudioPlayerChannelForwarder.AnonymousClass3 */

            public void run() {
                try {
                    AudioPlayerChannelForwarder.this.mRealChannel.close();
                } catch (IOException e) {
                    C0139Dd.A0W(AudioPlayerChannelForwarder.NATIVE_EXECUTOR_TAG, e, "couldn't play tts audio, interaction %s: ", AudioPlayerChannelForwarder.this.mInteractionId);
                }
            }
        });
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(final ByteBuffer byteBuffer) {
        C0139Dd.A0F(NATIVE_EXECUTOR_TAG, "onTtsAudioData, interactionId %s: ", this.mInteractionId);
        this.mHandler.post(new MQ("OacrVoiceInteractionListener: onTtsAudioData") {
            /* class com.facebook.assistant.oacr.AudioPlayerChannelForwarder.AnonymousClass2 */

            public void run() {
                try {
                    AudioPlayerChannelForwarder.this.mRealChannel.write(byteBuffer);
                } catch (IOException e) {
                    C0139Dd.A0W(AudioPlayerChannelForwarder.NATIVE_EXECUTOR_TAG, e, "couldn't play tts audio, interaction %s: ", AudioPlayerChannelForwarder.this.mInteractionId);
                }
            }
        });
        return byteBuffer.remaining();
    }
}
