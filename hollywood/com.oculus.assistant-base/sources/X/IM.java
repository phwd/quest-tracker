package X;

import com.facebook.assistant.oacr.OacrConstants;

public final class IM {
    public static String A00(double... dArr) {
        int length = dArr.length;
        if (length == 0) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (double d : dArr) {
            sb.append(d);
            sb.append(",,,");
        }
        int length2 = sb.length();
        if (length > 0) {
            i = 3;
        }
        sb.setLength(length2 - i);
        return sb.toString();
    }

    public static String A01(int... iArr) {
        int length = iArr.length;
        if (length == 0) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 : iArr) {
            sb.append(i2);
            sb.append(",,,");
        }
        int length2 = sb.length();
        if (length > 0) {
            i = 3;
        }
        sb.setLength(length2 - i);
        return sb.toString();
    }

    public static String A02(long... jArr) {
        int length = jArr.length;
        if (length == 0) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (long j : jArr) {
            sb.append(j);
            sb.append(",,,");
        }
        int length2 = sb.length();
        if (length > 0) {
            i = 3;
        }
        sb.setLength(length2 - i);
        return sb.toString();
    }

    public static String A03(String... strArr) {
        int length = strArr.length;
        if (length == 0) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            sb.append(str);
            sb.append(",,,");
        }
        int length2 = sb.length();
        if (length > 0) {
            i = 3;
        }
        sb.setLength(length2 - i);
        return sb.toString();
    }

    public static String A04(boolean... zArr) {
        int length = zArr.length;
        if (length == 0) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (boolean z : zArr) {
            sb.append(z);
            sb.append(",,,");
        }
        int length2 = sb.length();
        if (length > 0) {
            i = 3;
        }
        sb.setLength(length2 - i);
        return sb.toString();
    }
}
