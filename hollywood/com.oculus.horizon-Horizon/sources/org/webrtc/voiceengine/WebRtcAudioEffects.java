package org.webrtc.voiceengine;

import X.AnonymousClass006;
import android.annotation.TargetApi;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import com.facebook.BuildConfig;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.webrtc.Logging;

public class WebRtcAudioEffects {
    public static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
    public static final UUID AOSP_AUTOMATIC_GAIN_CONTROL = UUID.fromString("aa8130e0-66fc-11e0-bad0-0002a5d5c51b");
    public static final UUID AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    public static final boolean DEBUG = false;
    public static final String TAG = "WebRtcAudioEffects";
    public static Boolean canUseAcousticEchoCanceler;
    public static Boolean canUseAutomaticGainControl;
    public static Boolean canUseNoiseSuppressor;
    public AcousticEchoCanceler aec = null;
    public AutomaticGainControl agc = null;
    public NoiseSuppressor ns = null;
    public boolean shouldEnableAec = false;
    public boolean shouldEnableAgc = false;
    public boolean shouldEnableNs = false;

    @TargetApi(18)
    private boolean effectTypeIsVoIP(UUID uuid) {
        return (AudioEffect.EFFECT_TYPE_AEC.equals(uuid) && isAcousticEchoCancelerSupported()) || (AudioEffect.EFFECT_TYPE_AGC.equals(uuid) && isAutomaticGainControlSupported()) || (AudioEffect.EFFECT_TYPE_NS.equals(uuid) && isNoiseSuppressorSupported());
    }

    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (isAcousticEchoCancelerExcludedByUUID() != false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canUseAcousticEchoCanceler() {
        /*
            java.lang.Boolean r0 = org.webrtc.voiceengine.WebRtcAudioEffects.canUseAcousticEchoCanceler
            if (r0 != 0) goto L_0x0038
            boolean r0 = isAcousticEchoCancelerSupported()
            if (r0 == 0) goto L_0x001d
            boolean r0 = org.webrtc.voiceengine.WebRtcAudioUtils.useWebRtcBasedAcousticEchoCanceler()
            if (r0 != 0) goto L_0x001d
            boolean r0 = isAcousticEchoCancelerBlacklisted()
            if (r0 != 0) goto L_0x001d
            boolean r1 = isAcousticEchoCancelerExcludedByUUID()
            r0 = 1
            if (r1 == 0) goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            java.lang.Boolean r2 = new java.lang.Boolean
            r2.<init>(r0)
            org.webrtc.voiceengine.WebRtcAudioEffects.canUseAcousticEchoCanceler = r2
            java.lang.String r1 = "canUseAcousticEchoCanceler: "
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = r0.toString()
            java.lang.String r0 = "WebRtcAudioEffects"
            org.webrtc.Logging.d(r0, r1)
        L_0x0038:
            java.lang.Boolean r0 = org.webrtc.voiceengine.WebRtcAudioEffects.canUseAcousticEchoCanceler
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.voiceengine.WebRtcAudioEffects.canUseAcousticEchoCanceler():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (isAutomaticGainControlExcludedByUUID() != false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canUseAutomaticGainControl() {
        /*
            java.lang.Boolean r0 = org.webrtc.voiceengine.WebRtcAudioEffects.canUseAutomaticGainControl
            if (r0 != 0) goto L_0x0038
            boolean r0 = isAutomaticGainControlSupported()
            if (r0 == 0) goto L_0x001d
            boolean r0 = org.webrtc.voiceengine.WebRtcAudioUtils.useWebRtcBasedAutomaticGainControl()
            if (r0 != 0) goto L_0x001d
            boolean r0 = isAutomaticGainControlBlacklisted()
            if (r0 != 0) goto L_0x001d
            boolean r1 = isAutomaticGainControlExcludedByUUID()
            r0 = 1
            if (r1 == 0) goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            java.lang.Boolean r2 = new java.lang.Boolean
            r2.<init>(r0)
            org.webrtc.voiceengine.WebRtcAudioEffects.canUseAutomaticGainControl = r2
            java.lang.String r1 = "canUseAutomaticGainControl: "
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = r0.toString()
            java.lang.String r0 = "WebRtcAudioEffects"
            org.webrtc.Logging.d(r0, r1)
        L_0x0038:
            java.lang.Boolean r0 = org.webrtc.voiceengine.WebRtcAudioEffects.canUseAutomaticGainControl
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.voiceengine.WebRtcAudioEffects.canUseAutomaticGainControl():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (isNoiseSuppressorExcludedByUUID() != false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean canUseNoiseSuppressor() {
        /*
            java.lang.Boolean r0 = org.webrtc.voiceengine.WebRtcAudioEffects.canUseNoiseSuppressor
            if (r0 != 0) goto L_0x0038
            boolean r0 = isNoiseSuppressorSupported()
            if (r0 == 0) goto L_0x001d
            boolean r0 = org.webrtc.voiceengine.WebRtcAudioUtils.useWebRtcBasedNoiseSuppressor()
            if (r0 != 0) goto L_0x001d
            boolean r0 = isNoiseSuppressorBlacklisted()
            if (r0 != 0) goto L_0x001d
            boolean r1 = isNoiseSuppressorExcludedByUUID()
            r0 = 1
            if (r1 == 0) goto L_0x001e
        L_0x001d:
            r0 = 0
        L_0x001e:
            java.lang.Boolean r2 = new java.lang.Boolean
            r2.<init>(r0)
            org.webrtc.voiceengine.WebRtcAudioEffects.canUseNoiseSuppressor = r2
            java.lang.String r1 = "canUseNoiseSuppressor: "
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = r0.toString()
            java.lang.String r0 = "WebRtcAudioEffects"
            org.webrtc.Logging.d(r0, r1)
        L_0x0038:
            java.lang.Boolean r0 = org.webrtc.voiceengine.WebRtcAudioEffects.canUseNoiseSuppressor
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.voiceengine.WebRtcAudioEffects.canUseNoiseSuppressor():boolean");
    }

    public static WebRtcAudioEffects create() {
        return new WebRtcAudioEffects();
    }

    public static boolean isAcousticEchoCancelerBlacklisted() {
        List asList = Arrays.asList(WebRtcAudioUtils.BLACKLISTED_AEC_MODELS);
        String str = Build.MODEL;
        boolean contains = asList.contains(str);
        if (contains) {
            Logging.w(TAG, AnonymousClass006.A05(str, " is blacklisted for HW AEC usage!"));
        }
        return contains;
    }

    public static boolean isAutomaticGainControlBlacklisted() {
        List asList = Arrays.asList(WebRtcAudioUtils.BLACKLISTED_AGC_MODELS);
        String str = Build.MODEL;
        boolean contains = asList.contains(str);
        if (contains) {
            Logging.w(TAG, AnonymousClass006.A05(str, " is blacklisted for HW AGC usage!"));
        }
        return contains;
    }

    public static boolean isNoiseSuppressorBlacklisted() {
        List asList = Arrays.asList(WebRtcAudioUtils.BLACKLISTED_NS_MODELS);
        String str = Build.MODEL;
        boolean contains = asList.contains(str);
        if (contains) {
            Logging.w(TAG, AnonymousClass006.A05(str, " is blacklisted for HW NS usage!"));
        }
        return contains;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0092, code lost:
        if (canUseAcousticEchoCanceler() == false) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e8, code lost:
        if (canUseAutomaticGainControl() == false) goto L_0x00ea;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enable(int r11) {
        /*
        // Method dump skipped, instructions count: 396
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.voiceengine.WebRtcAudioEffects.enable(int):void");
    }

    public void release() {
        Logging.d(TAG, BuildConfig.BUILD_TYPE);
        AcousticEchoCanceler acousticEchoCanceler = this.aec;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.aec = null;
        }
        AutomaticGainControl automaticGainControl = this.agc;
        if (automaticGainControl != null) {
            automaticGainControl.release();
            this.agc = null;
        }
        NoiseSuppressor noiseSuppressor = this.ns;
        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            this.ns = null;
        }
    }

    public boolean setAEC(boolean z) {
        StringBuilder sb = new StringBuilder("setAEC(");
        sb.append(z);
        sb.append(")");
        Logging.d(TAG, sb.toString());
        if (!canUseAcousticEchoCanceler()) {
            Logging.w(TAG, "Platform AEC is not supported");
            this.shouldEnableAec = false;
            return false;
        } else if (this.aec == null || z == this.shouldEnableAec) {
            this.shouldEnableAec = z;
            return true;
        } else {
            Logging.e(TAG, "Platform AEC state can't be modified while recording");
            return false;
        }
    }

    public boolean setAGC(boolean z) {
        StringBuilder sb = new StringBuilder("setAGC(");
        sb.append(z);
        sb.append(")");
        Logging.d(TAG, sb.toString());
        if (!canUseAutomaticGainControl()) {
            Logging.w(TAG, "Platform AGC is not supported");
            this.shouldEnableAgc = false;
            return false;
        } else if (this.agc == null || z == this.shouldEnableAgc) {
            this.shouldEnableAgc = z;
            return true;
        } else {
            Logging.e(TAG, "Platform AGC state can't be modified while recording");
            return false;
        }
    }

    public boolean setNS(boolean z) {
        StringBuilder sb = new StringBuilder("setNS(");
        sb.append(z);
        sb.append(")");
        Logging.d(TAG, sb.toString());
        if (!canUseNoiseSuppressor()) {
            Logging.w(TAG, "Platform NS is not supported");
            this.shouldEnableNs = false;
            return false;
        } else if (this.ns == null || z == this.shouldEnableNs) {
            this.shouldEnableNs = z;
            return true;
        } else {
            Logging.e(TAG, "Platform NS state can't be modified while recording");
            return false;
        }
    }

    public WebRtcAudioEffects() {
        Logging.d(TAG, AnonymousClass006.A05("ctor", WebRtcAudioUtils.getThreadInfo()));
    }

    @TargetApi(18)
    public static boolean isAcousticEchoCancelerExcludedByUUID() {
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor descriptor : queryEffects) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_AEC) && descriptor.uuid.equals(AOSP_ACOUSTIC_ECHO_CANCELER)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAcousticEchoCancelerSupported() {
        if (AcousticEchoCanceler.isAvailable()) {
            return true;
        }
        return false;
    }

    @TargetApi(18)
    public static boolean isAutomaticGainControlExcludedByUUID() {
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor descriptor : queryEffects) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_AGC) && descriptor.uuid.equals(AOSP_AUTOMATIC_GAIN_CONTROL)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAutomaticGainControlSupported() {
        if (AutomaticGainControl.isAvailable()) {
            return true;
        }
        return false;
    }

    @TargetApi(18)
    public static boolean isNoiseSuppressorExcludedByUUID() {
        AudioEffect.Descriptor[] queryEffects = AudioEffect.queryEffects();
        for (AudioEffect.Descriptor descriptor : queryEffects) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_NS) && descriptor.uuid.equals(AOSP_NOISE_SUPPRESSOR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoiseSuppressorSupported() {
        if (NoiseSuppressor.isAvailable()) {
            return true;
        }
        return false;
    }
}
