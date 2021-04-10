package org.chromium.base;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PiiElider {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f10592a;
    public static final Pattern b;
    public static final Pattern c = Pattern.compile("\\sat\\sorg\\.chromium\\.[^ ]+.");
    public static final Pattern d;
    public static final Pattern e = Pattern.compile("([0-9a-fA-F]{2}[-:]+){5}[0-9a-fA-F]{2}");
    public static final Pattern f = Pattern.compile("\\[\\w*:CONSOLE.*\\].*");
    public static final String[] g = {"org.chromium.", "com.google."};
    public static final String[] h = {"android.accessibilityservice", "android.accounts", "android.animation", "android.annotation", "android.app", "android.appwidget", "android.bluetooth", "android.content", "android.database", "android.databinding", "android.drm", "android.gesture", "android.graphics", "android.hardware", "android.inputmethodservice", "android.location", "android.media", "android.mtp", "android.net", "android.nfc", "android.opengl", "android.os", "android.preference", "android.print", "android.printservice", "android.provider", "android.renderscript", "android.sax", "android.security", "android.service", "android.speech", "android.support", "android.system", "android.telecom", "android.telephony", "android.test", "android.text", "android.transition", "android.util", "android.view", "android.webkit", "android.widget", "com.android.", "dalvik.", "java.", "javax.", "org.apache.", "org.json.", "org.w3c.dom.", "org.xml.", "org.xmlpull."};

    static {
        Pattern compile = Pattern.compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))");
        f10592a = compile;
        Pattern compile2 = Pattern.compile("(([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯]([a-zA-Z0-9 -퟿豈-﷏ﷰ-￯\\-]{0,61}[a-zA-Z0-9 -퟿豈-﷏ﷰ-￯]){0,1}\\.)+[a-zA-Z -퟿豈-﷏ﷰ-￯]{2,63}|" + compile + ")");
        b = compile2;
        d = Pattern.compile("(?:\\b|^)((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?(?:" + compile2 + ")(?:\\:\\d{1,5})?)(\\/(?:(?:[" + "a-zA-Z0-9 -퟿豈-﷏ﷰ-￯" + "\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
    }

    public static String a(String str) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (c.matcher(str).find()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        Matcher matcher = d.matcher(sb);
        int i = 0;
        while (matcher.find(i)) {
            int start = matcher.start();
            int end = matcher.end();
            String substring = sb.substring(start, end);
            String[] strArr = g;
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                z = true;
                if (i2 >= length) {
                    z2 = false;
                    break;
                } else if (substring.startsWith(strArr[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z2) {
                String[] strArr2 = h;
                int length2 = strArr2.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        z3 = false;
                        break;
                    } else if (substring.startsWith(strArr2[i3])) {
                        z3 = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (!z3) {
                    try {
                        Class.forName(substring, false, ContextUtils.getApplicationContext().getClassLoader());
                        z4 = true;
                    } catch (Throwable unused) {
                        z4 = false;
                    }
                    if (!z4) {
                        int lastIndexOf = substring.lastIndexOf(".");
                        if (lastIndexOf != -1) {
                            try {
                                Class.forName(substring.substring(0, lastIndexOf), false, ContextUtils.getApplicationContext().getClassLoader());
                            } catch (Throwable unused2) {
                            }
                        }
                        z = false;
                    }
                    if (!z) {
                        sb.replace(start, end, "HTTP://WEBADDRESS.ELIDED");
                        i = start + 24;
                        matcher = d.matcher(sb);
                    }
                }
            }
            i = end;
        }
        return sb.toString();
    }

    public static String sanitizeStacktrace(String str) {
        String[] split = str.split("\\n");
        split[0] = a(split[0]);
        for (int i = 1; i < split.length; i++) {
            if (split[i].startsWith("Caused by:")) {
                split[i] = a(split[i]);
            }
        }
        return TextUtils.join("\n", split);
    }
}
