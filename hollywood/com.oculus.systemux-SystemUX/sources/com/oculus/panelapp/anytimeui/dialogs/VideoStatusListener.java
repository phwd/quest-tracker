package com.oculus.panelapp.anytimeui.dialogs;

public interface VideoStatusListener {
    void onVideoStatus(VideoState videoState);

    public enum VideoState {
        UNKNOWN,
        CLOSED,
        FAILED,
        FINISHED,
        INITIALIZING,
        PAUSED,
        PLAYING,
        READY,
        STOPPED;

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public static VideoState getFromString(String str) {
            char c;
            switch (str.hashCode()) {
                case -1884319283:
                    if (str.equals("stopped")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -1357520532:
                    if (str.equals("closed")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1281977283:
                    if (str.equals("failed")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -995321554:
                    if (str.equals("paused")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -673660814:
                    if (str.equals("finished")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -493563858:
                    if (str.equals("playing")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -400079795:
                    if (str.equals("initializing")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 108386723:
                    if (str.equals("ready")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    return CLOSED;
                case 1:
                    return FAILED;
                case 2:
                    return FINISHED;
                case 3:
                    return INITIALIZING;
                case 4:
                    return PAUSED;
                case 5:
                    return PLAYING;
                case 6:
                    return READY;
                case 7:
                    return STOPPED;
                default:
                    return UNKNOWN;
            }
        }
    }
}
