package com.oculus.util.constants;

public class MicrophoneMuteState {
    public static final int MUTED = 1;
    public static final int UNKNOWN = 0;
    public static final int UNMUTED = 2;

    public static final int validate(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                return i;
            default:
                throw new IllegalArgumentException("Unrecognized mute state");
        }
    }

    public static final String toString(int i) {
        switch (i) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "MUTED";
            case 2:
                return "UNMUTED";
            default:
                throw new IllegalArgumentException("Unrecognized mute state");
        }
    }

    public static final int valueOf(String s) throws IllegalArgumentException {
        char c = 65535;
        switch (s.hashCode()) {
            case 73726283:
                if (s.equals("MUTED")) {
                    c = 1;
                    break;
                }
                break;
            case 433141802:
                if (s.equals("UNKNOWN")) {
                    c = 0;
                    break;
                }
                break;
            case 435201618:
                if (s.equals("UNMUTED")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                throw new IllegalArgumentException("Unrecognized mute state");
        }
    }
}
