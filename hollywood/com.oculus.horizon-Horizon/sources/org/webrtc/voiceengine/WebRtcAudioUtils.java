package org.webrtc.voiceengine;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import java.util.Arrays;
import java.util.List;
import org.webrtc.Logging;

public final class WebRtcAudioUtils {
    public static final String[] BLACKLISTED_AEC_MODELS = {"D6503", "ONE A2005"};
    public static final String[] BLACKLISTED_AGC_MODELS = {"Nexus 10", "Nexus 9"};
    public static final String[] BLACKLISTED_NS_MODELS = {"Nexus 10", "Nexus 9", "ONE A2005"};
    public static final String[] BLACKLISTED_OPEN_SL_ES_MODELS = new String[0];
    public static final int DEFAULT_SAMPLE_RATE_HZ = 16000;
    public static final String TAG = "WebRtcAudioUtils";
    public static int defaultSampleRateHz = 16000;
    public static boolean isDefaultSampleRateOverridden;
    public static boolean useWebRtcBasedAcousticEchoCanceler;
    public static boolean useWebRtcBasedAutomaticGainControl;
    public static boolean useWebRtcBasedNoiseSuppressor;

    public static boolean deviceIsBlacklistedForOpenSLESUsage() {
        return Arrays.asList(BLACKLISTED_OPEN_SL_ES_MODELS).contains(Build.MODEL);
    }

    public static List<String> getBlackListedModelsForAecUsage() {
        return Arrays.asList(BLACKLISTED_AEC_MODELS);
    }

    public static List<String> getBlackListedModelsForAgcUsage() {
        return Arrays.asList(BLACKLISTED_AGC_MODELS);
    }

    public static List<String> getBlackListedModelsForNsUsage() {
        return Arrays.asList(BLACKLISTED_NS_MODELS);
    }

    public static synchronized int getDefaultSampleRateHz() {
        int i;
        synchronized (WebRtcAudioUtils.class) {
            i = defaultSampleRateHz;
        }
        return i;
    }

    public static String getThreadInfo() {
        StringBuilder sb = new StringBuilder("@[name=");
        Thread currentThread = Thread.currentThread();
        sb.append(currentThread.getName());
        sb.append(", id=");
        sb.append(currentThread.getId());
        sb.append("]");
        return sb.toString();
    }

    public static synchronized boolean isDefaultSampleRateOverridden() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            z = isDefaultSampleRateOverridden;
        }
        return z;
    }

    public static void logDeviceInfo(String str) {
        StringBuilder sb = new StringBuilder("Android SDK: ");
        sb.append(Build.VERSION.SDK_INT);
        sb.append(", Release: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append(", Brand: ");
        sb.append(Build.BRAND);
        sb.append(", Device: ");
        sb.append(Build.DEVICE);
        sb.append(", Id: ");
        sb.append(Build.ID);
        sb.append(", Hardware: ");
        sb.append(Build.HARDWARE);
        sb.append(", Manufacturer: ");
        sb.append(Build.MANUFACTURER);
        sb.append(", Model: ");
        sb.append(Build.MODEL);
        sb.append(", Product: ");
        sb.append(Build.PRODUCT);
        Logging.d(str, sb.toString());
    }

    public static boolean runningOnEmulator() {
        if (!Build.HARDWARE.equals("goldfish") || !Build.BRAND.startsWith("generic_")) {
            return false;
        }
        return true;
    }

    public static synchronized void setDefaultSampleRateHz(int i) {
        synchronized (WebRtcAudioUtils.class) {
            isDefaultSampleRateOverridden = true;
            defaultSampleRateHz = i;
        }
    }

    public static synchronized void setWebRtcBasedAcousticEchoCanceler(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            useWebRtcBasedAcousticEchoCanceler = z;
        }
    }

    public static synchronized void setWebRtcBasedAutomaticGainControl(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            useWebRtcBasedAutomaticGainControl = z;
        }
    }

    public static synchronized void setWebRtcBasedNoiseSuppressor(boolean z) {
        synchronized (WebRtcAudioUtils.class) {
            useWebRtcBasedNoiseSuppressor = z;
        }
    }

    public static synchronized boolean useWebRtcBasedAcousticEchoCanceler() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            if (useWebRtcBasedAcousticEchoCanceler) {
                Logging.w(TAG, "Overriding default behavior; now using WebRTC AEC!");
            }
            z = useWebRtcBasedAcousticEchoCanceler;
        }
        return z;
    }

    public static synchronized boolean useWebRtcBasedAutomaticGainControl() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            if (useWebRtcBasedAutomaticGainControl) {
                Logging.w(TAG, "Overriding default behavior; now using WebRTC AGC!");
            }
            z = useWebRtcBasedAutomaticGainControl;
        }
        return z;
    }

    public static synchronized boolean useWebRtcBasedNoiseSuppressor() {
        boolean z;
        synchronized (WebRtcAudioUtils.class) {
            if (useWebRtcBasedNoiseSuppressor) {
                Logging.w(TAG, "Overriding default behavior; now using WebRTC NS!");
            }
            z = useWebRtcBasedNoiseSuppressor;
        }
        return z;
    }

    public static boolean hasPermission(Context context, String str) {
        if (context.checkPermission(str, Process.myPid(), Process.myUid()) == 0) {
            return true;
        }
        return false;
    }

    public static boolean runningOnGingerBreadOrHigher() {
        return true;
    }

    public static boolean runningOnJellyBeanMR1OrHigher() {
        return true;
    }

    public static boolean runningOnJellyBeanMR2OrHigher() {
        return true;
    }

    public static boolean runningOnJellyBeanOrHigher() {
        return true;
    }

    public static boolean runningOnLollipopOrHigher() {
        return true;
    }
}
