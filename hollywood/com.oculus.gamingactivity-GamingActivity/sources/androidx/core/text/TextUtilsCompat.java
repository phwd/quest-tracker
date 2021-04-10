package androidx.core.text;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import com.facebook.soloader.Elf64_Ehdr;
import java.util.Locale;

public final class TextUtilsCompat {
    private static final String ARAB_SCRIPT_SUBTAG = "Arab";
    private static final String HEBR_SCRIPT_SUBTAG = "Hebr";
    private static final Locale ROOT = new Locale("", "");

    @NonNull
    public static String htmlEncode(@NonNull String s) {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.htmlEncode(s);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case MotionEventCompat.AXIS_GENERIC_3 /*{ENCODED_INT: 34}*/:
                    sb.append("&quot;");
                    break;
                case MotionEventCompat.AXIS_GENERIC_7 /*{ENCODED_INT: 38}*/:
                    sb.append("&amp;");
                    break;
                case MotionEventCompat.AXIS_GENERIC_8 /*{ENCODED_INT: 39}*/:
                    sb.append("&#39;");
                    break;
                case Elf64_Ehdr.e_shnum /*{ENCODED_INT: 60}*/:
                    sb.append("&lt;");
                    break;
                case Elf64_Ehdr.e_shstrndx /*{ENCODED_INT: 62}*/:
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (locale != null && !locale.equals(ROOT)) {
            String scriptSubtag = ICUCompat.maximizeAndGetScript(locale);
            if (scriptSubtag == null) {
                return getLayoutDirectionFromFirstChar(locale);
            }
            if (scriptSubtag.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) || scriptSubtag.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG)) {
                return 1;
            }
        }
        return 0;
    }

    private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale) {
        switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            case 1:
            case 2:
                return 1;
            default:
                return 0;
        }
    }

    private TextUtilsCompat() {
    }
}
