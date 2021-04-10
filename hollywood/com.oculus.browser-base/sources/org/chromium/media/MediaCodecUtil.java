package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaCodecUtil {
    public static boolean a(MediaCodec mediaCodec, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        if (mediaCodec == null) {
            return false;
        }
        try {
            MediaCodecInfo codecInfo = mediaCodec.getCodecInfo();
            return !codecInfo.isEncoder() && !e(str) && (capabilitiesForType = codecInfo.getCapabilitiesForType(str)) != null && capabilitiesForType.isFeatureSupported("adaptive-playback");
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("MediaCodecUtil", "Cannot retrieve codec information", e);
            return false;
        }
    }

    public static C5961zd0 b(String str, int i, MediaCrypto mediaCrypto) {
        C5961zd0 zd0 = new C5961zd0();
        if (!isDecoderSupportedForDevice(str)) {
            AbstractC1220Ua0.a("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
            return zd0;
        }
        try {
            if ((!str.startsWith("video") || i != 1) && (!str.startsWith("audio") || mediaCrypto == null || !mediaCrypto.requiresSecureDecoderComponent(str))) {
                if (i == 2) {
                    zd0.f11756a = MediaCodec.createByCodecName(getDefaultCodecName(str, 0, true));
                } else if (str.equals("audio/raw")) {
                    zd0.f11756a = MediaCodec.createByCodecName("OMX.google.raw.decoder");
                } else {
                    zd0.f11756a = MediaCodec.createDecoderByType(str);
                }
                zd0.b = a(zd0.f11756a, str);
                return zd0;
            }
            String defaultCodecName = getDefaultCodecName(str, 0, false);
            if (defaultCodecName.equals("")) {
                return zd0;
            }
            MediaCodec createByCodecName = MediaCodec.createByCodecName(defaultCodecName);
            zd0.b = a(createByCodecName, str);
            createByCodecName.release();
            zd0.f11756a = MediaCodec.createByCodecName(defaultCodecName + ".secure");
            return zd0;
        } catch (Exception e) {
            AbstractC1220Ua0.a("MediaCodecUtil", "Failed to create MediaCodec: %s, codecType: %d", str, Integer.valueOf(i), e);
            zd0.f11756a = null;
        }
    }

    public static C5961zd0 c(String str) {
        int i;
        C5961zd0 zd0 = new C5961zd0();
        Integer d = d(str);
        if (d == null) {
            return zd0;
        }
        try {
            zd0.f11756a = MediaCodec.createEncoderByType(str);
            zd0.b = false;
            int intValue = d.intValue();
            if (intValue == 0 || intValue == 1 || intValue == 2) {
                i = 0;
            } else {
                if (intValue != 3) {
                    if (intValue != 4) {
                        throw new IllegalArgumentException("Invalid HWEncoder decoder parameter.");
                    }
                }
                i = 1;
            }
            zd0.c = i;
        } catch (Exception e) {
            AbstractC1220Ua0.a("MediaCodecUtil", "Failed to create MediaCodec: %s", str, e);
        }
        return zd0;
    }

    public static boolean canDecode(String str, boolean z) {
        if (!isDecoderSupportedForDevice(str)) {
            AbstractC1220Ua0.a("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
            return false;
        }
        C0071Bd0 bd0 = new C0071Bd0();
        if (bd0.b()) {
            Iterator it = bd0.iterator();
            while (true) {
                C0010Ad0 ad0 = (C0010Ad0) it;
                if (!ad0.hasNext()) {
                    return false;
                }
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) ad0.next();
                if (!mediaCodecInfo.isEncoder()) {
                    try {
                        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                        if (capabilitiesForType == null) {
                            continue;
                        } else if (z && capabilitiesForType.isFeatureSupported("secure-playback")) {
                            return true;
                        } else {
                            if (!z && !capabilitiesForType.isFeatureRequired("secure-playback")) {
                                return true;
                            }
                        }
                    } catch (IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
        } else {
            MediaCodec mediaCodec = b(str, z ? 1 : 0, null).f11756a;
            if (mediaCodec == null) {
                return false;
            }
            try {
                mediaCodec.release();
            } catch (IllegalStateException e) {
                AbstractC1220Ua0.a("MediaCodecUtil", "Cannot release media codec", e);
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ba A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00bf A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Integer d(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 208
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaCodecUtil.d(java.lang.String):java.lang.Integer");
    }

    public static boolean e(String str) {
        if ((!str.equals("video/avc") && !str.equals("video/avc1")) || !Build.VERSION.RELEASE.equals("4.4.2") || !Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
            return false;
        }
        String str2 = Build.MODEL;
        if (str2.startsWith("GT-I9300") || str2.startsWith("SCH-I535")) {
            return true;
        }
        return false;
    }

    public static boolean f(String str) {
        if (!str.startsWith("OMX.google.") && str.startsWith("OMX.")) {
            return false;
        }
        return true;
    }

    public static String getDefaultCodecName(String str, int i, boolean z) {
        Iterator it = new C0071Bd0().iterator();
        while (true) {
            C0010Ad0 ad0 = (C0010Ad0) it;
            if (ad0.hasNext()) {
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) ad0.next();
                if (mediaCodecInfo.isEncoder() == i && (!z || f(mediaCodecInfo.getName()))) {
                    for (String str2 : mediaCodecInfo.getSupportedTypes()) {
                        if (str2.equalsIgnoreCase(str)) {
                            return mediaCodecInfo.getName();
                        }
                    }
                    continue;
                }
            } else {
                AbstractC1220Ua0.a("MediaCodecUtil", "Decoder for type %s is not supported on this device", str);
                return "";
            }
        }
    }

    public static int[] getEncoderColorFormatsForMime(String str) {
        Iterator it = new C0071Bd0().iterator();
        while (true) {
            C0010Ad0 ad0 = (C0010Ad0) it;
            if (!ad0.hasNext()) {
                return null;
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) ad0.next();
            if (mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                for (String str2 : supportedTypes) {
                    if (str2.equalsIgnoreCase(str)) {
                        try {
                            return mediaCodecInfo.getCapabilitiesForType(str2).colorFormats;
                        } catch (IllegalArgumentException unused) {
                            continue;
                        }
                    }
                }
                continue;
            }
        }
    }

    public static Object[] getSupportedCodecProfileLevels() {
        int i;
        ArrayList arrayList = new ArrayList();
        Iterator it = new C0071Bd0().iterator();
        while (true) {
            C0010Ad0 ad0 = (C0010Ad0) it;
            if (!ad0.hasNext()) {
                return arrayList.toArray();
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) ad0.next();
            String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
            int length = supportedTypes.length;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    String str = supportedTypes[i3];
                    if (!isDecoderSupportedForDevice(str)) {
                        Object[] objArr = new Object[1];
                        objArr[i2] = str;
                        AbstractC1220Ua0.f("MediaCodecUtil", "Decoder for type %s disabled on this device", objArr);
                    } else {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                            str.endsWith("vp9");
                            MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = capabilitiesForType.profileLevels;
                            int length2 = codecProfileLevelArr.length;
                            for (int i4 = i2; i4 < length2; i4++) {
                                MediaCodecInfo.CodecProfileLevel codecProfileLevel = codecProfileLevelArr[i4];
                                try {
                                    if (str.endsWith("vp9")) {
                                        i = 7;
                                    } else if (str.endsWith("vp8")) {
                                        i = 6;
                                    } else if (str.endsWith("avc")) {
                                        i = 1;
                                    } else if (str.endsWith("hevc")) {
                                        i = 8;
                                    } else {
                                        throw new C5504wv(null);
                                    }
                                    arrayList.add(new CodecProfileLevelList$CodecProfileLevelAdapter(i, AbstractC5674xv.b(i, codecProfileLevel.profile), AbstractC5674xv.a(i, codecProfileLevel.level)));
                                } catch (C5504wv unused) {
                                }
                            }
                        } catch (IllegalArgumentException unused2) {
                        }
                    }
                    i3++;
                    i2 = 0;
                }
            }
        }
    }

    public static boolean isDecoderSupportedForDevice(String str) {
        if (str.equals("video/x-vnd.on2.vp8")) {
            if (Build.MANUFACTURER.toLowerCase(Locale.getDefault()).equals("samsung")) {
                String str2 = Build.MODEL;
                if (str2.startsWith("GT-I9190") || str2.startsWith("GT-I9195")) {
                    return false;
                }
            }
            if (Build.HARDWARE.startsWith("mt")) {
                return false;
            }
            return true;
        } else if (str.equals("video/x-vnd.on2.vp9")) {
            if (Build.MODEL.equals("Nexus Player")) {
                return false;
            }
            return true;
        } else if (!str.equals("video/av01") || Build.VERSION.SDK_INT >= 29) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEncoderSupportedByDevice(String str) {
        return d(str) != null;
    }

    public static boolean isSetOutputSurfaceSupported() {
        String str = Build.HARDWARE;
        return !str.equalsIgnoreCase("hi6210sft") && !str.equalsIgnoreCase("hi6250");
    }

    public static boolean platformSupportsCbcsEncryption(int i) {
        return i >= 25;
    }
}
