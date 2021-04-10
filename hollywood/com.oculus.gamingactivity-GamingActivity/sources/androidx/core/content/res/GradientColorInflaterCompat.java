package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
final class GradientColorInflaterCompat {
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;

    private GradientColorInflaterCompat() {
    }

    static Shader createFromXml(@NonNull Resources resources, @NonNull XmlPullParser parser, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int type;
        AttributeSet attrs = Xml.asAttributeSet(parser);
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type == 2) {
            return createFromXmlInner(resources, parser, attrs, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    static Shader createFromXmlInner(@NonNull Resources resources, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        String name = parser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(parser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray a = TypedArrayUtils.obtainAttributes(resources, theme, attrs, R.styleable.GradientColor);
        float startX = TypedArrayUtils.getNamedFloat(a, parser, "startX", R.styleable.GradientColor_android_startX, 0.0f);
        float startY = TypedArrayUtils.getNamedFloat(a, parser, "startY", R.styleable.GradientColor_android_startY, 0.0f);
        float endX = TypedArrayUtils.getNamedFloat(a, parser, "endX", R.styleable.GradientColor_android_endX, 0.0f);
        float endY = TypedArrayUtils.getNamedFloat(a, parser, "endY", R.styleable.GradientColor_android_endY, 0.0f);
        float centerX = TypedArrayUtils.getNamedFloat(a, parser, "centerX", R.styleable.GradientColor_android_centerX, 0.0f);
        float centerY = TypedArrayUtils.getNamedFloat(a, parser, "centerY", R.styleable.GradientColor_android_centerY, 0.0f);
        int type = TypedArrayUtils.getNamedInt(a, parser, "type", R.styleable.GradientColor_android_type, 0);
        int startColor = TypedArrayUtils.getNamedColor(a, parser, "startColor", R.styleable.GradientColor_android_startColor, 0);
        boolean hasCenterColor = TypedArrayUtils.hasAttribute(parser, "centerColor");
        int centerColor = TypedArrayUtils.getNamedColor(a, parser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
        int endColor = TypedArrayUtils.getNamedColor(a, parser, "endColor", R.styleable.GradientColor_android_endColor, 0);
        int tileMode = TypedArrayUtils.getNamedInt(a, parser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
        float gradientRadius = TypedArrayUtils.getNamedFloat(a, parser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0f);
        a.recycle();
        ColorStops colorStops = checkColors(inflateChildElements(resources, parser, attrs, theme), startColor, endColor, hasCenterColor, centerColor);
        switch (type) {
            case 1:
                if (gradientRadius > 0.0f) {
                    return new RadialGradient(centerX, centerY, gradientRadius, colorStops.mColors, colorStops.mOffsets, parseTileMode(tileMode));
                }
                throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
            case 2:
                return new SweepGradient(centerX, centerY, colorStops.mColors, colorStops.mOffsets);
            default:
                return new LinearGradient(startX, startY, endX, endY, colorStops.mColors, colorStops.mOffsets, parseTileMode(tileMode));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0071, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r16.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' " + "attribute!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.core.content.res.GradientColorInflaterCompat.ColorStops inflateChildElements(@androidx.annotation.NonNull android.content.res.Resources r15, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r16, @androidx.annotation.NonNull android.util.AttributeSet r17, @androidx.annotation.Nullable android.content.res.Resources.Theme r18) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.GradientColorInflaterCompat.inflateChildElements(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.core.content.res.GradientColorInflaterCompat$ColorStops");
    }

    private static ColorStops checkColors(@Nullable ColorStops colorItems, @ColorInt int startColor, @ColorInt int endColor, boolean hasCenterColor, @ColorInt int centerColor) {
        if (colorItems != null) {
            return colorItems;
        }
        if (hasCenterColor) {
            return new ColorStops(startColor, centerColor, endColor);
        }
        return new ColorStops(startColor, endColor);
    }

    private static Shader.TileMode parseTileMode(int tileMode) {
        switch (tileMode) {
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return Shader.TileMode.CLAMP;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ColorStops {
        final int[] mColors;
        final float[] mOffsets;

        ColorStops(@NonNull List<Integer> colorsList, @NonNull List<Float> offsetsList) {
            int size = colorsList.size();
            this.mColors = new int[size];
            this.mOffsets = new float[size];
            for (int i = 0; i < size; i++) {
                this.mColors[i] = colorsList.get(i).intValue();
                this.mOffsets[i] = offsetsList.get(i).floatValue();
            }
        }

        ColorStops(@ColorInt int startColor, @ColorInt int endColor) {
            this.mColors = new int[]{startColor, endColor};
            this.mOffsets = new float[]{0.0f, 1.0f};
        }

        ColorStops(@ColorInt int startColor, @ColorInt int centerColor, @ColorInt int endColor) {
            this.mColors = new int[]{startColor, centerColor, endColor};
            this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
        }
    }
}
