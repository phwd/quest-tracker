package com.oculus.horizon.abuse_prevention;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_platformplugin_PlatformPluginManager_ULSEP_BINDING_ID"})
public class AudioCapture {
    public static final int AUDIO_BUFFER_LENGTH = 48000;
    public static final int AUDIO_RECORDER_INTERVAL_MS = 300;
    public static final String TAG = "AudioCapture";
    public final byte[] mAudioBytesBuffer = new byte[96000];
    @Nullable
    public final AudioRecording mAudioRecording;
    public final short[] mAudioShortsBuffer = new short[48000];
    @Nullable
    public AudioCaptureTask mCaptureTask;
    public final Object mLock = new Object();
    public final long mNativeAudioRenderer;
    @Inject
    @Eager
    public final PlatformPluginManager mPlatformPluginManager;
    public final boolean mStarted;

    public final class AudioCaptureTask extends Thread {
        public final int mChannels;
        public final int mFrequency;
        public final /* synthetic */ AudioCapture this$0;
    }

    public interface AudioRecording {
    }

    @Inject
    public AudioCapture(AbstractC06640p5 r2) {
        this.mPlatformPluginManager = (PlatformPluginManager) AnonymousClass117.A00(160, r2);
    }
}
