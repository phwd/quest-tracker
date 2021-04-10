package X;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleableRes;
import com.facebook.acra.AppComponentStats;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.04x  reason: invalid class name and case insensitive filesystem */
public final class C001904x {
    @NonNull
    public static TypedArray A01(@NonNull Resources resources, @Nullable Resources.Theme theme, @NonNull AttributeSet attributeSet, @NonNull int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static boolean A02(@NonNull XmlPullParser xmlPullParser, @NonNull String str) {
        if (xmlPullParser.getAttributeValue(AppComponentStats.ANDROID_XML_NS, str) != null) {
            return true;
        }
        return false;
    }

    public static float A00(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i, float f) {
        if (!A02(xmlPullParser, str)) {
            return f;
        }
        return typedArray.getFloat(i, f);
    }
}
