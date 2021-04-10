package com.oculus.util.constants;

public class MicrophoneMuteState {
    public static final int MUTED = 1;
    public static final int UNKNOWN = 0;
    public static final int UNMUTED = 2;

    public static final int A00(String str) throws IllegalArgumentException {
        int hashCode = str.hashCode();
        if (hashCode != 73726283) {
            if (hashCode != 433141802) {
                if (hashCode == 435201618 && str.equals("UNMUTED")) {
                    return 2;
                }
            } else if (str.equals("UNKNOWN")) {
                return 0;
            }
        } else if (str.equals("MUTED")) {
            return 1;
        }
        throw new IllegalArgumentException("Unrecognized mute state");
    }
}
