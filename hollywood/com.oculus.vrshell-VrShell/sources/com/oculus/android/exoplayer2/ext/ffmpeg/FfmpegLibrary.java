package com.oculus.android.exoplayer2.ext.ffmpeg;

import com.oculus.android.exoplayer2.ExoPlayerLibraryInfo;
import com.oculus.android.exoplayer2.util.LibraryLoader;
import com.oculus.android.exoplayer2.util.MimeTypes;

public final class FfmpegLibrary {
    private static final LibraryLoader LOADER = new LibraryLoader("avutil", "avresample", "avcodec", "ffmpeg");

    private static native String ffmpegGetVersion();

    private static native boolean ffmpegHasDecoder(String str);

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ffmpeg");
    }

    private FfmpegLibrary() {
    }

    public static void setLibraries(String... strArr) {
        LOADER.setLibraries(strArr);
    }

    public static boolean isAvailable() {
        return LOADER.isAvailable();
    }

    public static String getVersion() {
        if (isAvailable()) {
            return ffmpegGetVersion();
        }
        return null;
    }

    public static boolean supportsFormat(String str) {
        String codecName;
        if (isAvailable() && (codecName = getCodecName(str)) != null && ffmpegHasDecoder(codecName)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    static String getCodecName(String str) {
        char c;
        switch (str.hashCode()) {
            case -1606874997:
                if (str.equals(MimeTypes.AUDIO_AMR_WB)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1095064472:
                if (str.equals(MimeTypes.AUDIO_DTS)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1003765268:
                if (str.equals(MimeTypes.AUDIO_VORBIS)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -432837260:
                if (str.equals(MimeTypes.AUDIO_MPEG_L1)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -432837259:
                if (str.equals(MimeTypes.AUDIO_MPEG_L2)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -53558318:
                if (str.equals(MimeTypes.AUDIO_AAC)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 187078296:
                if (str.equals(MimeTypes.AUDIO_AC3)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1503095341:
                if (str.equals(MimeTypes.AUDIO_AMR_NB)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1504470054:
                if (str.equals(MimeTypes.AUDIO_ALAC)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1504578661:
                if (str.equals(MimeTypes.AUDIO_E_AC3)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1504619009:
                if (str.equals(MimeTypes.AUDIO_FLAC)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1504831518:
                if (str.equals(MimeTypes.AUDIO_MPEG)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1504891608:
                if (str.equals(MimeTypes.AUDIO_OPUS)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1505942594:
                if (str.equals(MimeTypes.AUDIO_DTS_HD)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1556697186:
                if (str.equals(MimeTypes.AUDIO_TRUEHD)) {
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
                return "aac";
            case 1:
            case 2:
            case 3:
                return "mp3";
            case 4:
                return "ac3";
            case 5:
                return "eac3";
            case 6:
                return "truehd";
            case 7:
            case '\b':
                return "dca";
            case '\t':
                return "vorbis";
            case '\n':
                return "opus";
            case 11:
                return "amrnb";
            case '\f':
                return "amrwb";
            case '\r':
                return "flac";
            case 14:
                return "alac";
            default:
                return null;
        }
    }
}
