package X;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.NonNull;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: X.1qD  reason: invalid class name and case insensitive filesystem */
public final class C10921qD extends AbstractC10301ln {
    public static final PorterDuff.Mode A08 = PorterDuff.Mode.SRC_IN;
    public C11031qS A00;
    public boolean A01;
    public ColorFilter A02;
    public PorterDuffColorFilter A03;
    public boolean A04;
    public final Matrix A05;
    public final Rect A06;
    public final float[] A07;

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x018b, code lost:
        if (r2.size() <= 0) goto L_0x0193;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x018d, code lost:
        r1 = new X.C001504o(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0193, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0194, code lost:
        if (r1 != null) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0196, code lost:
        if (r16 == false) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0198, code lost:
        r1 = new X.C001504o(r17, r15, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01a0, code lost:
        r1 = new X.C001504o(r17, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a9, code lost:
        if (r18 == 1) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01ab, code lost:
        if (r18 == 2) goto L_0x01bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01ad, code lost:
        r2 = r1.A01;
        r0 = r1.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b1, code lost:
        if (r5 == 1) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b3, code lost:
        if (r5 == 2) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01b6, code lost:
        r27 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01b9, code lost:
        r27 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bc, code lost:
        r3 = new android.graphics.SweepGradient(r20, r19, r1.A01, r1.A00);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01cc, code lost:
        if (r25 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01ce, code lost:
        r2 = r1.A01;
        r0 = r1.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01d2, code lost:
        if (r5 == 1) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01d4, code lost:
        if (r5 == 2) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d7, code lost:
        r28 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01da, code lost:
        r28 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01dd, code lost:
        r28 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01df, code lost:
        r3 = new android.graphics.RadialGradient(r20, r19, r25, r2, r0, r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01ef, code lost:
        r27 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01f1, code lost:
        r3 = new android.graphics.LinearGradient(r21, r22, r23, r24, r2, r0, r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0202, code lost:
        return new X.C001204i(r3, null, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x020a, code lost:
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.C001204i A02(@androidx.annotation.NonNull android.content.res.TypedArray r24, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r25, @androidx.annotation.Nullable android.content.res.Resources.Theme r26, @androidx.annotation.NonNull java.lang.String r27, @androidx.annotation.StyleableRes int r28) {
        /*
        // Method dump skipped, instructions count: 570
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10921qD.A02(android.content.res.TypedArray, org.xmlpull.v1.XmlPullParser, android.content.res.Resources$Theme, java.lang.String, int):X.04i");
    }

    private final PorterDuffColorFilter A01(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
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
            if (!(abs3 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z && abs4 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z)) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (((float) rect.width()) * abs));
            int min2 = Math.min(2048, (int) (((float) rect.height()) * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                canvas.translate((float) rect.left, (float) rect.top);
                if (isAutoMirrored() && getLayoutDirection() == 1) {
                    canvas.translate((float) rect.width(), AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                    canvas.scale(-1.0f, 1.0f);
                }
                rect.offsetTo(0, 0);
                C11031qS r1 = this.A00;
                Bitmap bitmap = r1.A04;
                if (!(bitmap != null && min == bitmap.getWidth() && min2 == r1.A04.getHeight())) {
                    r1.A04 = Bitmap.createBitmap(min, min2, Bitmap.Config.ARGB_8888);
                    r1.A0A = true;
                }
                if (!this.A01) {
                    this.A00.A00(min, min2);
                } else {
                    C11031qS r7 = this.A00;
                    if (!(!r7.A0A && r7.A02 == r7.A03 && r7.A06 == r7.A07 && r7.A0B == r7.A09 && r7.A00 == r7.A08.A04)) {
                        r7.A00(min, min2);
                        C11031qS r12 = this.A00;
                        r12.A02 = r12.A03;
                        r12.A06 = r12.A07;
                        r12.A00 = r12.A08.A04;
                        r12.A0B = r12.A09;
                        r12.A0A = false;
                    }
                }
                C11031qS r5 = this.A00;
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
            return new AnonymousClass1pz(drawable.getConstantState());
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
        C11031qS r0 = this.A00;
        if (r0 == null) {
            return false;
        }
        C10941qH r1 = r0.A08;
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
            this.A00 = new C11031qS(this.A00);
            this.A04 = true;
            return this;
        }
        return this;
    }

    @Override // X.AbstractC10301ln
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
        C11031qS r2 = this.A00;
        ColorStateList colorStateList = r2.A03;
        if (!(colorStateList == null || (mode = r2.A07) == null)) {
            this.A03 = A01(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        C10941qH r1 = r2.A08;
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
        C10941qH r1 = this.A00.A08;
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
        C11031qS r1 = this.A00;
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
        C11031qS r1 = this.A00;
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
    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.ColorStateList A00(@androidx.annotation.NonNull android.content.res.Resources r15, @androidx.annotation.NonNull org.xmlpull.v1.XmlPullParser r16, @androidx.annotation.NonNull android.util.AttributeSet r17, @androidx.annotation.Nullable android.content.res.Resources.Theme r18) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 262
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10921qD.A00(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):android.content.res.ColorStateList");
    }

    public C10921qD() {
        this.A01 = true;
        this.A07 = new float[9];
        this.A05 = new Matrix();
        this.A06 = new Rect();
        this.A00 = new C11031qS();
    }

    public C10921qD(@NonNull C11031qS r3) {
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
        C11031qS r6 = this.A00;
        r6.A08 = new C10941qH();
        TypedArray A012 = C001904x.A01(resources, theme, attributeSet, C11091qb.A02);
        C11031qS r4 = this.A00;
        C10941qH r3 = r4.A08;
        if (!C001904x.A02(xmlPullParser, "tintMode")) {
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
                    case 15:
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
        r4.A07 = mode;
        if (C001904x.A02(xmlPullParser, "tint")) {
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
                                A002 = A00(resources2, xml, asAttributeSet, theme);
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
                    r4.A03 = A002;
                }
            } else {
                StringBuilder sb = new StringBuilder("Failed to resolve attribute at index ");
                sb.append(1);
                sb.append(": ");
                sb.append(typedValue);
                throw new UnsupportedOperationException(sb.toString());
            }
        }
        boolean z = r4.A09;
        if (C001904x.A02(xmlPullParser, "autoMirrored")) {
            z = A012.getBoolean(5, z);
        }
        r4.A09 = z;
        r3.A03 = C001904x.A00(A012, xmlPullParser, "viewportWidth", 7, r3.A03);
        float A003 = C001904x.A00(A012, xmlPullParser, "viewportHeight", 8, r3.A02);
        r3.A02 = A003;
        if (r3.A03 <= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            throw new XmlPullParserException(AnonymousClass006.A07(A012.getPositionDescription(), "<vector> tag requires viewportWidth > 0"));
        } else if (A003 > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            r3.A01 = A012.getDimension(3, r3.A01);
            float dimension = A012.getDimension(2, r3.A00);
            r3.A00 = dimension;
            if (r3.A01 <= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                throw new XmlPullParserException(AnonymousClass006.A07(A012.getPositionDescription(), "<vector> tag requires width > 0"));
            } else if (dimension > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                r3.setAlpha(C001904x.A00(A012, xmlPullParser, "alpha", 4, r3.getAlpha()));
                String string = A012.getString(0);
                if (string != null) {
                    r3.A09 = string;
                    r3.A0E.put(string, r3);
                }
                A012.recycle();
                r6.A01 = getChangingConfigurations();
                r6.A0A = true;
                C11031qS r12 = this.A00;
                C10941qH r42 = r12.A08;
                ArrayDeque arrayDeque = new ArrayDeque();
                arrayDeque.push(r42.A0F);
                int eventType = xmlPullParser.getEventType();
                int depth = xmlPullParser.getDepth() + 1;
                boolean z2 = true;
                while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
                    if (eventType == 2) {
                        String name = xmlPullParser.getName();
                        C10971qM r2 = (C10971qM) arrayDeque.peek();
                        if ("path".equals(name)) {
                            AnonymousClass1qU r13 = new AnonymousClass1qU();
                            TypedArray A013 = C001904x.A01(resources, theme, attributeSet, C11091qb.A0B);
                            r13.A0B = null;
                            if (C001904x.A02(xmlPullParser, "pathData")) {
                                String string2 = A013.getString(0);
                                if (string2 != null) {
                                    ((AbstractC11071qY) r13).A02 = string2;
                                }
                                String string3 = A013.getString(2);
                                if (string3 != null) {
                                    ((AbstractC11071qY) r13).A03 = AnonymousClass05B.A01(string3);
                                }
                                r13.A09 = A02(A013, xmlPullParser, theme, "fillColor", 1);
                                r13.A00 = C001904x.A00(A013, xmlPullParser, "fillAlpha", 12, r13.A00);
                                if (!C001904x.A02(xmlPullParser, "strokeLineCap")) {
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
                                if (!C001904x.A02(xmlPullParser, "strokeLineJoin")) {
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
                                r13.A02 = C001904x.A00(A013, xmlPullParser, "strokeMiterLimit", 10, r13.A02);
                                r13.A0A = A02(A013, xmlPullParser, theme, "strokeColor", 3);
                                r13.A01 = C001904x.A00(A013, xmlPullParser, "strokeAlpha", 11, r13.A01);
                                r13.A03 = C001904x.A00(A013, xmlPullParser, "strokeWidth", 4, r13.A03);
                                r13.A04 = C001904x.A00(A013, xmlPullParser, "trimPathEnd", 6, r13.A04);
                                r13.A05 = C001904x.A00(A013, xmlPullParser, "trimPathOffset", 7, r13.A05);
                                r13.A06 = C001904x.A00(A013, xmlPullParser, "trimPathStart", 5, r13.A06);
                                int i8 = ((AbstractC11071qY) r13).A01;
                                if (C001904x.A02(xmlPullParser, "fillType")) {
                                    i8 = A013.getInt(13, i8);
                                }
                                ((AbstractC11071qY) r13).A01 = i8;
                            }
                            A013.recycle();
                            r2.A0C.add(r13);
                            String str = ((AbstractC11071qY) r13).A02;
                            if (str != null) {
                                r42.A0E.put(str, r13);
                            }
                            z2 = false;
                            i2 = r12.A01;
                            i3 = ((AbstractC11071qY) r13).A00;
                        } else if ("clip-path".equals(name)) {
                            C11151qi r14 = new C11151qi();
                            if (C001904x.A02(xmlPullParser, "pathData")) {
                                TypedArray A014 = C001904x.A01(resources, theme, attributeSet, C11091qb.A09);
                                String string4 = A014.getString(0);
                                if (string4 != null) {
                                    r14.A02 = string4;
                                }
                                String string5 = A014.getString(1);
                                if (string5 != null) {
                                    r14.A03 = AnonymousClass05B.A01(string5);
                                }
                                if (!C001904x.A02(xmlPullParser, "fillType")) {
                                    i4 = 0;
                                } else {
                                    i4 = A014.getInt(2, 0);
                                }
                                r14.A01 = i4;
                                A014.recycle();
                            }
                            r2.A0C.add(r14);
                            String str2 = r14.A02;
                            if (str2 != null) {
                                r42.A0E.put(str2, r14);
                            }
                            i2 = r12.A01;
                            i3 = r14.A00;
                        } else if ("group".equals(name)) {
                            C10971qM r132 = new C10971qM();
                            TypedArray A015 = C001904x.A01(resources, theme, attributeSet, C11091qb.A0A);
                            r132.A09 = null;
                            r132.A02 = C001904x.A00(A015, xmlPullParser, "rotation", 5, r132.A02);
                            r132.A00 = A015.getFloat(1, r132.A00);
                            r132.A01 = A015.getFloat(2, r132.A01);
                            r132.A03 = C001904x.A00(A015, xmlPullParser, "scaleX", 3, r132.A03);
                            r132.A04 = C001904x.A00(A015, xmlPullParser, "scaleY", 4, r132.A04);
                            r132.A05 = C001904x.A00(A015, xmlPullParser, "translateX", 6, r132.A05);
                            r132.A06 = C001904x.A00(A015, xmlPullParser, "translateY", 7, r132.A06);
                            String string6 = A015.getString(0);
                            if (string6 != null) {
                                r132.A08 = string6;
                            }
                            C10971qM.A00(r132);
                            A015.recycle();
                            r2.A0C.add(r132);
                            arrayDeque.push(r132);
                            String str3 = r132.A08;
                            if (str3 != null) {
                                r42.A0E.put(str3, r132);
                            }
                            i2 = r12.A01;
                            i3 = r132.A07;
                        }
                        r12.A01 = i3 | i2;
                    } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                        arrayDeque.pop();
                    }
                    eventType = xmlPullParser.next();
                }
                if (!z2) {
                    this.A03 = A01(r6.A03, r6.A07);
                    return;
                }
                throw new XmlPullParserException("no path defined");
            } else {
                throw new XmlPullParserException(AnonymousClass006.A07(A012.getPositionDescription(), "<vector> tag requires height > 0"));
            }
        } else {
            throw new XmlPullParserException(AnonymousClass006.A07(A012.getPositionDescription(), "<vector> tag requires viewportHeight > 0"));
        }
    }
}
