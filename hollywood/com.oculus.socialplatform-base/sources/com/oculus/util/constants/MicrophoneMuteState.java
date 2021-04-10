package com.oculus.util.constants;

import com.facebook.acra.CrashTimeDataCollector;

public class MicrophoneMuteState {
    public static final int MUTED = 1;
    public static final int UNKNOWN = 0;
    public static final int UNMUTED = 2;

    public static final String toString(int i) {
        if (i == 0) {
            return CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        }
        if (i == 1) {
            return "MUTED";
        }
        if (i == 2) {
            return "UNMUTED";
        }
        throw new IllegalArgumentException("Unrecognized mute state");
    }

    public static final int validate(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        throw new IllegalArgumentException("Unrecognized mute state");
    }

    public static final int valueOf(String str) throws IllegalArgumentException {
        int hashCode = str.hashCode();
        if (hashCode != 73726283) {
            if (hashCode != 433141802) {
                if (hashCode == 435201618 && str.equals("UNMUTED")) {
                    return 2;
                }
            } else if (str.equals(CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN)) {
                return 0;
            }
        } else if (str.equals("MUTED")) {
            return 1;
        }
        throw new IllegalArgumentException("Unrecognized mute state");
    }
}
