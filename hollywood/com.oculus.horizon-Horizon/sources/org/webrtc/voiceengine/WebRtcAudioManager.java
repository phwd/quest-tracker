package org.webrtc.voiceengine;

import X.AnonymousClass006;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import com.facebook.internal.AnalyticsEvents;
import org.webrtc.Logging;

public class WebRtcAudioManager {
    public static final String[] AUDIO_MODES = {"MODE_NORMAL", "MODE_RINGTONE", "MODE_IN_CALL", "MODE_IN_COMMUNICATION"};
    public static final int BITS_PER_SAMPLE = 16;
    public static final int CHANNELS = 1;
    public static final boolean DEBUG = false;
    public static final int DEFAULT_FRAME_PER_BUFFER = 256;
    public static final String TAG = "WebRtcAudioManager";
    public static boolean blacklistDeviceForOpenSLESUsage;
    public static boolean blacklistDeviceForOpenSLESUsageIsOverridden;
    public final AudioManager audioManager;
    public int channels;
    public final Context context;
    public boolean hardwareAEC;
    public boolean hardwareAGC;
    public boolean hardwareNS;
    public boolean initialized = false;
    public int inputBufferSize;
    public boolean lowLatencyOutput;
    public final long nativeAudioManager;
    public int nativeChannels;
    public int nativeSampleRate;
    public int outputBufferSize;
    public int sampleRate;

    public WebRtcAudioManager(Context context2, long j) {
        Logging.d(TAG, AnonymousClass006.A05("ctor", WebRtcAudioUtils.getThreadInfo()));
        this.context = context2;
        this.nativeAudioManager = j;
        this.audioManager = (AudioManager) context2.getSystemService("audio");
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.channels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.outputBufferSize, this.inputBufferSize, j);
    }

    private native void nativeCacheAudioParameters(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, int i3, int i4, long j);

    private void storeAudioParameters() {
        int minOutputFrameSize;
        this.channels = 1;
        this.sampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = WebRtcAudioEffects.canUseAcousticEchoCanceler();
        this.hardwareAGC = WebRtcAudioEffects.canUseAutomaticGainControl();
        this.hardwareNS = WebRtcAudioEffects.canUseNoiseSuppressor();
        boolean isLowLatencyOutputSupported = isLowLatencyOutputSupported();
        this.lowLatencyOutput = isLowLatencyOutputSupported;
        if (isLowLatencyOutputSupported) {
            minOutputFrameSize = getLowLatencyOutputFramesPerBuffer();
        } else {
            minOutputFrameSize = getMinOutputFrameSize(this.sampleRate, this.channels);
        }
        this.outputBufferSize = minOutputFrameSize;
        this.inputBufferSize = getMinInputFrameSize(this.sampleRate, this.channels);
    }

    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private void dispose() {
        Logging.d(TAG, AnonymousClass006.A05("dispose", WebRtcAudioUtils.getThreadInfo()));
    }

    public static String getAudioModeString(int i) {
        if (i < 0) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        String[] strArr = AUDIO_MODES;
        if (i < strArr.length) {
            return strArr[i];
        }
        return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
    }

    public static int getMinInputFrameSize(int i, int i2) {
        int i3 = i2 << 1;
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        assertTrue(z);
        return AudioRecord.getMinBufferSize(i, 16, 2) / i3;
    }

    public static int getMinOutputFrameSize(int i, int i2) {
        int i3;
        int i4 = i2 << 1;
        if (i2 == 1) {
            i3 = 4;
        } else if (i2 != 2) {
            return -1;
        } else {
            i3 = 12;
        }
        return AudioTrack.getMinBufferSize(i, i3, 2) / i4;
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        String property = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        if (property == null) {
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        return Integer.parseInt(property);
    }

    private boolean hasEarpiece() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean init() {
        Logging.d(TAG, AnonymousClass006.A05("init", WebRtcAudioUtils.getThreadInfo()));
        if (!this.initialized) {
            Logging.d(TAG, AnonymousClass006.A05("audio mode is: ", getAudioModeString(this.audioManager.getMode())));
            this.initialized = true;
        }
        return true;
    }

    private boolean isCommunicationModeEnabled() {
        if (this.audioManager.getMode() == 3) {
            return true;
        }
        return false;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean deviceIsBlacklistedForOpenSLESUsage;
        if (blacklistDeviceForOpenSLESUsageIsOverridden) {
            deviceIsBlacklistedForOpenSLESUsage = blacklistDeviceForOpenSLESUsage;
        } else {
            deviceIsBlacklistedForOpenSLESUsage = WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        }
        if (deviceIsBlacklistedForOpenSLESUsage) {
            Logging.e(TAG, AnonymousClass006.A05(Build.MODEL, " is blacklisted for OpenSL ES usage!"));
        }
        return deviceIsBlacklistedForOpenSLESUsage;
    }

    private boolean isLowLatencyOutputSupported() {
        if (this.context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency")) {
            return true;
        }
        return false;
    }

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean z) {
        synchronized (WebRtcAudioManager.class) {
            blacklistDeviceForOpenSLESUsageIsOverridden = true;
            blacklistDeviceForOpenSLESUsage = z;
        }
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        assertTrue(isLowLatencyOutputSupported());
        String property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (property != null) {
            return Integer.parseInt(property);
        }
        return 256;
    }

    private int getNativeOutputSampleRate() {
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return 8000;
        } else if (WebRtcAudioUtils.isDefaultSampleRateOverridden()) {
            Logging.d(TAG, AnonymousClass006.A02("Default sample rate is overriden to ", WebRtcAudioUtils.getDefaultSampleRateHz(), " Hz"));
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        } else {
            int sampleRateOnJellyBeanMR10OrHigher = getSampleRateOnJellyBeanMR10OrHigher();
            Logging.d(TAG, AnonymousClass006.A02("Sample rate is set to ", sampleRateOnJellyBeanMR10OrHigher, " Hz"));
            return sampleRateOnJellyBeanMR10OrHigher;
        }
    }

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    public static boolean isAutomaticGainControlSupported() {
        return WebRtcAudioEffects.canUseAutomaticGainControl();
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    public static boolean isOpenSLESSupported() {
        return true;
    }

    public boolean isLowLatencyInputSupported() {
        if (isLowLatencyOutputSupported()) {
            return true;
        }
        return false;
    }
}
