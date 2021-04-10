package X;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.core.content.FileProvider;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: X.0Lz  reason: invalid class name */
public final class AnonymousClass0Lz extends AnonymousClass0c5 {
    public static final PorterDuff.Mode A08 = PorterDuff.Mode.SRC_IN;
    public AnonymousClass0H9 A00;
    public boolean A01;
    public ColorFilter A02;
    public PorterDuffColorFilter A03;
    public boolean A04;
    public final Matrix A05;
    public final Rect A06;
    public final float[] A07;

    public static AnonymousClass080 A03(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme, @NonNull String str, @StyleableRes int i) {
        int next;
        if (AnonymousClass08F.A02(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            int i2 = typedValue.type;
            if (i2 >= 28 && i2 <= 31) {
                return new AnonymousClass080(null, null, typedValue.data);
            }
            Resources resources = typedArray.getResources();
            try {
                XmlResourceParser xml = resources.getXml(typedArray.getResourceId(i, 0));
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        String name = xml.getName();
                        int hashCode = name.hashCode();
                        if (hashCode != 89650992) {
                            if (hashCode == 1191572447 && name.equals("selector")) {
                                String name2 = xml.getName();
                                if (name2.equals("selector")) {
                                    ColorStateList A002 = A00(resources, xml, asAttributeSet, theme);
                                    return new AnonymousClass080(null, A002, A002.getDefaultColor());
                                }
                                throw new XmlPullParserException(AnonymousClass006.A07(xml.getPositionDescription(), ": invalid color state list tag ", name2));
                            }
                        } else if (name.equals("gradient")) {
                            return new AnonymousClass080(A02(resources, xml, asAttributeSet, theme), null, 0);
                        }
                        throw new XmlPullParserException(AnonymousClass006.A07(xml.getPositionDescription(), ": unsupported complex color tag ", name));
                    }
                } while (next != 1);
                throw new XmlPullParserException("No start tag found");
            } catch (Exception e) {
                Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            }
        }
        return new AnonymousClass080(null, null, 0);
    }

    private final PorterDuffColorFilter A01(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public static Shader A02(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        String str;
        int i;
        int color;
        int color2;
        int color3;
        int i2;
        Shader.TileMode tileMode;
        Shader.TileMode tileMode2;
        String name = xmlPullParser.getName();
        if (name.equals("gradient")) {
            TypedArray A012 = AnonymousClass08F.A01(resources, theme, attributeSet, AnonymousClass18M.A03);
            float A002 = AnonymousClass08F.A00(A012, xmlPullParser, "startX", 8, 0.0f);
            float A003 = AnonymousClass08F.A00(A012, xmlPullParser, "startY", 9, 0.0f);
            float A004 = AnonymousClass08F.A00(A012, xmlPullParser, "endX", 10, 0.0f);
            float A005 = AnonymousClass08F.A00(A012, xmlPullParser, "endY", 11, 0.0f);
            float A006 = AnonymousClass08F.A00(A012, xmlPullParser, "centerX", 3, 0.0f);
            float A007 = AnonymousClass08F.A00(A012, xmlPullParser, "centerY", 4, 0.0f);
            if (!AnonymousClass08F.A02(xmlPullParser, LoggerConstants.CONFIGURATION_FETCH_TYPE)) {
                i = 0;
            } else {
                i = A012.getInt(2, 0);
            }
            if (!AnonymousClass08F.A02(xmlPullParser, "startColor")) {
                color = 0;
            } else {
                color = A012.getColor(0, 0);
            }
            boolean A022 = AnonymousClass08F.A02(xmlPullParser, "centerColor");
            if (!AnonymousClass08F.A02(xmlPullParser, "centerColor")) {
                color2 = 0;
            } else {
                color2 = A012.getColor(7, 0);
            }
            if (!AnonymousClass08F.A02(xmlPullParser, "endColor")) {
                color3 = 0;
            } else {
                color3 = A012.getColor(1, 0);
            }
            if (!AnonymousClass08F.A02(xmlPullParser, "tileMode")) {
                i2 = 0;
            } else {
                i2 = A012.getInt(6, 0);
            }
            float A008 = AnonymousClass08F.A00(A012, xmlPullParser, "gradientRadius", 5, 0.0f);
            A012.recycle();
            AnonymousClass086 A042 = A04(resources, xmlPullParser, attributeSet, theme);
            if (A042 == null) {
                if (A022) {
                    A042 = new AnonymousClass086(color, color2, color3);
                } else {
                    A042 = new AnonymousClass086(color, color3);
                }
            }
            if (i != 1) {
                if (i == 2) {
                    return new SweepGradient(A006, A007, A042.A01, A042.A00);
                }
                int[] iArr = A042.A01;
                float[] fArr = A042.A00;
                if (i2 == 1) {
                    tileMode2 = Shader.TileMode.REPEAT;
                } else if (i2 != 2) {
                    tileMode2 = Shader.TileMode.CLAMP;
                } else {
                    tileMode2 = Shader.TileMode.MIRROR;
                }
                return new LinearGradient(A002, A003, A004, A005, iArr, fArr, tileMode2);
            } else if (A008 > 0.0f) {
                int[] iArr2 = A042.A01;
                float[] fArr2 = A042.A00;
                if (i2 == 1) {
                    tileMode = Shader.TileMode.REPEAT;
                } else if (i2 != 2) {
                    tileMode = Shader.TileMode.CLAMP;
                } else {
                    tileMode = Shader.TileMode.MIRROR;
                }
                return new RadialGradient(A006, A007, A008, iArr2, fArr2, tileMode);
            } else {
                str = "<gradient> tag requires 'gradientRadius' attribute with radial type";
            }
        } else {
            str = AnonymousClass006.A07(xmlPullParser.getPositionDescription(), ": invalid gradient color tag ", name);
        }
        throw new XmlPullParserException(str);
    }

    public final boolean canApplyTheme() {
        Drawable drawable = super.A00;
        if (drawable == null) {
            return false;
        }
        drawable.canApplyTheme();
        return false;
    }

    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        Rect rect = this.A06;
        copyBounds(rect);
        if (rect.width() > 0 && rect.height() > 0) {
            ColorFilter colorFilter = this.A02;
            if (colorFilter == null) {
                colorFilter = this.A03;
            }
            Matrix matrix = this.A05;
            canvas.getMatrix(matrix);
            float[] fArr = this.A07;
            matrix.getValues(fArr);
            float abs = Math.abs(fArr[0]);
            float abs2 = Math.abs(fArr[4]);
            float abs3 = Math.abs(fArr[1]);
            float abs4 = Math.abs(fArr[3]);
            if (!(abs3 == 0.0f && abs4 == 0.0f)) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (((float) rect.width()) * abs));
            int min2 = Math.min(2048, (int) (((float) rect.height()) * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                canvas.translate((float) rect.left, (float) rect.top);
                if (isAutoMirrored() && getLayoutDirection() == 1) {
                    canvas.translate((float) rect.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                rect.offsetTo(0, 0);
                AnonymousClass0H9 r1 = this.A00;
                Bitmap bitmap = r1.A04;
                if (!(bitmap != null && min == bitmap.getWidth() && min2 == r1.A04.getHeight())) {
                    r1.A04 = Bitmap.createBitmap(min, min2, Bitmap.Config.ARGB_8888);
                    r1.A0A = true;
                }
                if (!this.A01) {
                    this.A00.A00(min, min2);
                } else {
                    AnonymousClass0H9 r7 = this.A00;
                    if (!(!r7.A0A && r7.A02 == r7.A03 && r7.A06 == r7.A07 && r7.A0B == r7.A09 && r7.A00 == r7.A08.A04)) {
                        r7.A00(min, min2);
                        AnonymousClass0H9 r12 = this.A00;
                        r12.A02 = r12.A03;
                        r12.A06 = r12.A07;
                        r12.A00 = r12.A08.A04;
                        r12.A0B = r12.A09;
                        r12.A0A = false;
                    }
                }
                AnonymousClass0H9 r5 = this.A00;
                if (r5.A08.A04 < 255 || colorFilter != null) {
                    if (r5.A05 == null) {
                        Paint paint2 = new Paint();
                        r5.A05 = paint2;
                        paint2.setFilterBitmap(true);
                    }
                    r5.A05.setAlpha(r5.A08.A04);
                    r5.A05.setColorFilter(colorFilter);
                    paint = r5.A05;
                } else {
                    paint = null;
                }
                canvas.drawBitmap(r5.A04, (Rect) null, rect, paint);
                canvas.restoreToCount(save);
            }
        }
    }

    public final int getAlpha() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.getAlpha();
        }
        return this.A00.A08.A04;
    }

    public final int getChangingConfigurations() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.A00.getChangingConfigurations();
    }

    public final ColorFilter getColorFilter() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.getColorFilter();
        }
        return this.A02;
    }

    public final Drawable.ConstantState getConstantState() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return new AnonymousClass0HA(drawable.getConstantState());
        }
        this.A00.A01 = getChangingConfigurations();
        return this.A00;
    }

    public final int getIntrinsicHeight() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return (int) this.A00.A08.A00;
    }

    public final int getIntrinsicWidth() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return (int) this.A00.A08.A01;
    }

    public final int getOpacity() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    public final void invalidateSelf() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public final boolean isAutoMirrored() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.isAutoMirrored();
        }
        return this.A00.A09;
    }

    public final boolean isStateful() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (super.isStateful()) {
            return true;
        }
        AnonymousClass0H9 r0 = this.A00;
        if (r0 == null) {
            return false;
        }
        AnonymousClass0H8 r1 = r0.A08;
        Boolean bool = r1.A08;
        if (bool == null) {
            bool = Boolean.valueOf(r1.A0F.A01());
            r1.A08 = bool;
        }
        if (bool.booleanValue()) {
            return true;
        }
        ColorStateList colorStateList = this.A00.A03;
        if (colorStateList == null || !colorStateList.isStateful()) {
            return false;
        }
        return true;
    }

    public final Drawable mutate() {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.mutate();
        } else if (!this.A04 && super.mutate() == this) {
            this.A00 = new AnonymousClass0H9(this.A00);
            this.A04 = true;
            return this;
        }
        return this;
    }

    @Override // X.AnonymousClass0c5
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    public final boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        AnonymousClass0H9 r2 = this.A00;
        ColorStateList colorStateList = r2.A03;
        if (!(colorStateList == null || (mode = r2.A07) == null)) {
            this.A03 = A01(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        AnonymousClass0H8 r1 = r2.A08;
        Boolean bool = r1.A08;
        if (bool == null) {
            bool = Boolean.valueOf(r1.A0F.A01());
            r1.A08 = bool;
        }
        if (!bool.booleanValue()) {
            return z;
        }
        boolean A022 = r2.A08.A0F.A02(iArr);
        r2.A0A |= A022;
        if (!A022) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    public final void setAlpha(int i) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setAlpha(i);
            return;
        }
        AnonymousClass0H8 r1 = this.A00.A08;
        if (r1.A04 != i) {
            r1.A04 = i;
            invalidateSelf();
        }
    }

    public final void setAutoMirrored(boolean z) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.A00.A09 = z;
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        this.A02 = colorFilter;
        invalidateSelf();
    }

    public final void setTint(int i) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setTint(i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
            return;
        }
        AnonymousClass0H9 r1 = this.A00;
        if (r1.A03 != colorStateList) {
            r1.A03 = colorStateList;
            this.A03 = A01(colorStateList, r1.A07);
            invalidateSelf();
        }
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.setTintMode(mode);
            return;
        }
        AnonymousClass0H9 r1 = this.A00;
        if (r1.A07 != mode) {
            r1.A07 = mode;
            this.A03 = A01(r1.A03, mode);
            invalidateSelf();
        }
    }

    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList A00(@androidx.annotation.NonNull android.content.res.Resources r15, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r16, @androidx.annotation.NonNull android.util.AttributeSet r17, @androidx.annotation.Nullable android.content.res.Resources.Theme r18) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 234
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Lz.A00(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(X.AnonymousClass006.A05(r9.getPositionDescription(), ": <item> tag requires a 'color' attribute and a 'offset' attribute!"));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass086 A04(@androidx.annotation.NonNull android.content.res.Resources r8, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r9, @androidx.annotation.NonNull android.util.AttributeSet r10, @androidx.annotation.Nullable android.content.res.Resources.Theme r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Lz.A04(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):X.086");
    }

    public AnonymousClass0Lz() {
        this.A01 = true;
        this.A07 = new float[9];
        this.A05 = new Matrix();
        this.A06 = new Rect();
        this.A00 = new AnonymousClass0H9();
    }

    public AnonymousClass0Lz(@NonNull AnonymousClass0H9 r3) {
        this.A01 = true;
        this.A07 = new float[9];
        this.A05 = new Matrix();
        this.A06 = new Rect();
        this.A00 = r3;
        this.A03 = A01(r3.A03, r3.A07);
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int i;
        String str;
        StringBuilder sb;
        String str2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int next;
        ColorStateList A002;
        Drawable drawable = super.A00;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        AnonymousClass0H9 r6 = this.A00;
        r6.A08 = new AnonymousClass0H8();
        TypedArray A012 = AnonymousClass08F.A01(resources, theme, attributeSet, C01420Gw.A02);
        AnonymousClass0H9 r12 = this.A00;
        AnonymousClass0H8 r3 = r12.A08;
        if (!AnonymousClass08F.A02(xmlPullParser, "tintMode")) {
            i = -1;
        } else {
            i = A012.getInt(6, -1);
        }
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        if (i == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (i != 5) {
            if (i != 9) {
                switch (i) {
                    case 14:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        r12.A07 = mode;
        if (AnonymousClass08F.A02(xmlPullParser, "tint")) {
            TypedValue typedValue = new TypedValue();
            A012.getValue(1, typedValue);
            int i7 = typedValue.type;
            if (i7 != 2) {
                if (i7 < 28 || i7 > 31) {
                    Resources resources2 = A012.getResources();
                    try {
                        XmlResourceParser xml = resources2.getXml(A012.getResourceId(1, 0));
                        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                        do {
                            next = xml.next();
                            if (next == 2) {
                                String name = xml.getName();
                                if (name.equals("selector")) {
                                    A002 = A00(resources2, xml, asAttributeSet, theme);
                                } else {
                                    throw new XmlPullParserException(AnonymousClass006.A07(xml.getPositionDescription(), ": invalid color state list tag ", name));
                                }
                            }
                        } while (next != 1);
                        throw new XmlPullParserException("No start tag found");
                    } catch (Exception e) {
                        Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
                    }
                } else {
                    A002 = ColorStateList.valueOf(typedValue.data);
                }
                if (A002 != null) {
                    r12.A03 = A002;
                }
            } else {
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + 1 + ": " + typedValue);
            }
        }
        boolean z = r12.A09;
        if (AnonymousClass08F.A02(xmlPullParser, "autoMirrored")) {
            z = A012.getBoolean(5, z);
        }
        r12.A09 = z;
        r3.A03 = AnonymousClass08F.A00(A012, xmlPullParser, "viewportWidth", 7, r3.A03);
        float A003 = AnonymousClass08F.A00(A012, xmlPullParser, "viewportHeight", 8, r3.A02);
        r3.A02 = A003;
        if (r3.A03 <= 0.0f) {
            sb = new StringBuilder();
            sb.append(A012.getPositionDescription());
            str2 = "<vector> tag requires viewportWidth > 0";
        } else if (A003 > 0.0f) {
            r3.A01 = A012.getDimension(3, r3.A01);
            float dimension = A012.getDimension(2, r3.A00);
            r3.A00 = dimension;
            if (r3.A01 <= 0.0f) {
                sb = new StringBuilder();
                sb.append(A012.getPositionDescription());
                str2 = "<vector> tag requires width > 0";
            } else if (dimension > 0.0f) {
                r3.setAlpha(AnonymousClass08F.A00(A012, xmlPullParser, "alpha", 4, r3.getAlpha()));
                String string = A012.getString(0);
                if (string != null) {
                    r3.A09 = string;
                    r3.A0E.put(string, r3);
                }
                A012.recycle();
                r6.A01 = getChangingConfigurations();
                r6.A0A = true;
                AnonymousClass0H9 r122 = this.A00;
                AnonymousClass0H8 r4 = r122.A08;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(r4.A0F);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z2 = true;
                while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
                    if (eventType == 2) {
                        String name2 = xmlPullParser.getName();
                        AnonymousClass0c4 r2 = (AnonymousClass0c4) arrayDeque.peek();
                        if (FileProvider.ATTR_PATH.equals(name2)) {
                            AnonymousClass0M0 r13 = new AnonymousClass0M0();
                            TypedArray A013 = AnonymousClass08F.A01(resources, theme, attributeSet, C01420Gw.A0B);
                            r13.A0B = null;
                            if (AnonymousClass08F.A02(xmlPullParser, "pathData")) {
                                String string2 = A013.getString(0);
                                if (string2 != null) {
                                    ((AbstractC03330c3) r13).A02 = string2;
                                }
                                String string3 = A013.getString(2);
                                if (string3 != null) {
                                    ((AbstractC03330c3) r13).A03 = AnonymousClass08T.A00(string3);
                                }
                                r13.A09 = A03(A013, xmlPullParser, theme, "fillColor", 1);
                                r13.A00 = AnonymousClass08F.A00(A013, xmlPullParser, "fillAlpha", 12, r13.A00);
                                if (!AnonymousClass08F.A02(xmlPullParser, "strokeLineCap")) {
                                    i5 = -1;
                                } else {
                                    i5 = A013.getInt(8, -1);
                                }
                                Paint.Cap cap = r13.A07;
                                if (i5 == 0) {
                                    cap = Paint.Cap.BUTT;
                                } else if (i5 == 1) {
                                    cap = Paint.Cap.ROUND;
                                } else if (i5 == 2) {
                                    cap = Paint.Cap.SQUARE;
                                }
                                r13.A07 = cap;
                                if (!AnonymousClass08F.A02(xmlPullParser, "strokeLineJoin")) {
                                    i6 = -1;
                                } else {
                                    i6 = A013.getInt(9, -1);
                                }
                                Paint.Join join = r13.A08;
                                if (i6 == 0) {
                                    join = Paint.Join.MITER;
                                } else if (i6 == 1) {
                                    join = Paint.Join.ROUND;
                                } else if (i6 == 2) {
                                    join = Paint.Join.BEVEL;
                                }
                                r13.A08 = join;
                                r13.A02 = AnonymousClass08F.A00(A013, xmlPullParser, "strokeMiterLimit", 10, r13.A02);
                                r13.A0A = A03(A013, xmlPullParser, theme, "strokeColor", 3);
                                r13.A01 = AnonymousClass08F.A00(A013, xmlPullParser, "strokeAlpha", 11, r13.A01);
                                r13.A03 = AnonymousClass08F.A00(A013, xmlPullParser, "strokeWidth", 4, r13.A03);
                                r13.A04 = AnonymousClass08F.A00(A013, xmlPullParser, "trimPathEnd", 6, r13.A04);
                                r13.A05 = AnonymousClass08F.A00(A013, xmlPullParser, "trimPathOffset", 7, r13.A05);
                                r13.A06 = AnonymousClass08F.A00(A013, xmlPullParser, "trimPathStart", 5, r13.A06);
                                int i8 = ((AbstractC03330c3) r13).A01;
                                if (AnonymousClass08F.A02(xmlPullParser, "fillType")) {
                                    i8 = A013.getInt(13, i8);
                                }
                                ((AbstractC03330c3) r13).A01 = i8;
                            }
                            A013.recycle();
                            r2.A0C.add(r13);
                            String str3 = ((AbstractC03330c3) r13).A02;
                            if (str3 != null) {
                                r4.A0E.put(str3, r13);
                            }
                            z2 = false;
                            i2 = r122.A01;
                            i3 = ((AbstractC03330c3) r13).A00;
                        } else if ("clip-path".equals(name2)) {
                            AnonymousClass0M1 r14 = new AnonymousClass0M1();
                            if (AnonymousClass08F.A02(xmlPullParser, "pathData")) {
                                TypedArray A014 = AnonymousClass08F.A01(resources, theme, attributeSet, C01420Gw.A09);
                                String string4 = A014.getString(0);
                                if (string4 != null) {
                                    r14.A02 = string4;
                                }
                                String string5 = A014.getString(1);
                                if (string5 != null) {
                                    r14.A03 = AnonymousClass08T.A00(string5);
                                }
                                if (!AnonymousClass08F.A02(xmlPullParser, "fillType")) {
                                    i4 = 0;
                                } else {
                                    i4 = A014.getInt(2, 0);
                                }
                                r14.A01 = i4;
                                A014.recycle();
                            }
                            r2.A0C.add(r14);
                            String str4 = r14.A02;
                            if (str4 != null) {
                                r4.A0E.put(str4, r14);
                            }
                            i2 = r122.A01;
                            i3 = r14.A00;
                        } else if ("group".equals(name2)) {
                            AnonymousClass0c4 r132 = new AnonymousClass0c4();
                            TypedArray A015 = AnonymousClass08F.A01(resources, theme, attributeSet, C01420Gw.A0A);
                            r132.A09 = null;
                            r132.A02 = AnonymousClass08F.A00(A015, xmlPullParser, "rotation", 5, r132.A02);
                            r132.A00 = A015.getFloat(1, r132.A00);
                            r132.A01 = A015.getFloat(2, r132.A01);
                            r132.A03 = AnonymousClass08F.A00(A015, xmlPullParser, "scaleX", 3, r132.A03);
                            r132.A04 = AnonymousClass08F.A00(A015, xmlPullParser, "scaleY", 4, r132.A04);
                            r132.A05 = AnonymousClass08F.A00(A015, xmlPullParser, "translateX", 6, r132.A05);
                            r132.A06 = AnonymousClass08F.A00(A015, xmlPullParser, "translateY", 7, r132.A06);
                            String string6 = A015.getString(0);
                            if (string6 != null) {
                                r132.A08 = string6;
                            }
                            AnonymousClass0c4.A00(r132);
                            A015.recycle();
                            r2.A0C.add(r132);
                            arrayDeque.push(r132);
                            String str5 = r132.A08;
                            if (str5 != null) {
                                r4.A0E.put(str5, r132);
                            }
                            i2 = r122.A01;
                            i3 = r132.A07;
                        }
                        r122.A01 = i3 | i2;
                    } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                        arrayDeque.pop();
                    }
                    eventType = xmlPullParser.next();
                }
                if (!z2) {
                    this.A03 = A01(r6.A03, r6.A07);
                    return;
                } else {
                    str = "no path defined";
                    throw new XmlPullParserException(str);
                }
            } else {
                sb = new StringBuilder();
                sb.append(A012.getPositionDescription());
                str2 = "<vector> tag requires height > 0";
            }
        } else {
            sb = new StringBuilder();
            sb.append(A012.getPositionDescription());
            str2 = "<vector> tag requires viewportHeight > 0";
        }
        sb.append(str2);
        str = sb.toString();
        throw new XmlPullParserException(str);
    }
}
