package defpackage;

import com.oculus.os.Version;

/* renamed from: ng1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3928ng1 {
    public static String a(AbstractC1248Uk uk) {
        StringBuilder sb = new StringBuilder(uk.size());
        for (int i = 0; i < uk.size(); i++) {
            byte a2 = uk.a(i);
            if (a2 == 34) {
                sb.append("\\\"");
            } else if (a2 == 39) {
                sb.append("\\'");
            } else if (a2 != 92) {
                switch (a2) {
                    case Version.VERSION_7:
                        sb.append("\\a");
                        continue;
                    case Version.VERSION_8:
                        sb.append("\\b");
                        continue;
                    case Version.VERSION_9:
                        sb.append("\\t");
                        continue;
                    case Version.VERSION_10:
                        sb.append("\\n");
                        continue;
                    case Version.VERSION_11:
                        sb.append("\\v");
                        continue;
                    case Version.VERSION_12:
                        sb.append("\\f");
                        continue;
                    case Version.VERSION_13:
                        sb.append("\\r");
                        continue;
                    default:
                        if (a2 < 32 || a2 > 126) {
                            sb.append('\\');
                            sb.append((char) (((a2 >>> 6) & 3) + 48));
                            sb.append((char) (((a2 >>> 3) & 7) + 48));
                            sb.append((char) ((a2 & 7) + 48));
                            break;
                        } else {
                            sb.append((char) a2);
                            continue;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
