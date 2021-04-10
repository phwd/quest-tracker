package libraries.marauder.analytics.utils.json;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class JsonUtilsEscape {
    public static void escape(StringBuilder sb, String str) {
        sb.append("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.getType(charAt) == 15) {
                sb.append(String.format("\\u%04x", Integer.valueOf(charAt)));
            } else {
                if (charAt == '\\' || charAt == '\"') {
                    sb.append('\\');
                }
                sb.append(charAt);
            }
        }
        sb.append("\"");
    }
}
