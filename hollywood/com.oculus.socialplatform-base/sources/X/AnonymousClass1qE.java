package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: X.1qE  reason: invalid class name */
public final class AnonymousClass1qE {
    public int A00 = 0;
    public Typeface A01;
    public C11101qc A02;
    public C11101qc A03;
    public C11101qc A04;
    public C11101qc A05;
    public C11101qc A06;
    public C11101qc A07;
    public C11101qc A08;
    public boolean A09;
    public int A0A = -1;
    @NonNull
    public final TextView A0B;
    @NonNull
    public final C10761pd A0C;

    public static void A03(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            } else {
                continue;
            }
            if (i <= 0) {
                return;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02f7, code lost:
        if (r11 == null) goto L_0x03c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02fb, code lost:
        if (r11 == null) goto L_0x03c9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(android.content.Context r34, X.C10901qA r35) {
        /*
        // Method dump skipped, instructions count: 1025
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qE.A01(android.content.Context, X.1qA):void");
    }

    private void A02(Drawable drawable, C11101qc r3) {
        if (drawable != null && r3 != null) {
            C10821pj.A02(drawable, r3, this.A0B.getDrawableState());
        }
    }

    public final void A04() {
        if (!(this.A04 == null && this.A08 == null && this.A05 == null && this.A02 == null)) {
            Drawable[] compoundDrawables = this.A0B.getCompoundDrawables();
            A02(compoundDrawables[0], this.A04);
            A02(compoundDrawables[1], this.A08);
            A02(compoundDrawables[2], this.A05);
            A02(compoundDrawables[3], this.A02);
        }
        if (this.A06 != null || this.A03 != null) {
            Drawable[] compoundDrawablesRelative = this.A0B.getCompoundDrawablesRelative();
            A02(compoundDrawablesRelative[0], this.A06);
            A02(compoundDrawablesRelative[2], this.A03);
        }
    }

    public final void A05(int i) {
        C10761pd r4 = this.A0C;
        if (r4.A09 instanceof AnonymousClass1qJ) {
            return;
        }
        if (i == 0) {
            r4.A03 = 0;
            r4.A01 = -1.0f;
            r4.A00 = -1.0f;
            r4.A02 = -1.0f;
            r4.A07 = new int[0];
            r4.A06 = false;
        } else if (i == 1) {
            DisplayMetrics displayMetrics = r4.A08.getResources().getDisplayMetrics();
            C10761pd.A02(r4, TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (C10761pd.A03(r4)) {
                r4.A06();
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A03("Unknown auto-size text type: ", i));
        }
    }

    public final void A06(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        C10761pd r4 = this.A0C;
        if (!(r4.A09 instanceof AnonymousClass1qJ)) {
            DisplayMetrics displayMetrics = r4.A08.getResources().getDisplayMetrics();
            C10761pd.A02(r4, TypedValue.applyDimension(i4, (float) i, displayMetrics), TypedValue.applyDimension(i4, (float) i2, displayMetrics), TypedValue.applyDimension(i4, (float) i3, displayMetrics));
            if (C10761pd.A03(r4)) {
                r4.A06();
            }
        }
    }

    public final void A07(Context context, int i) {
        String string;
        C10901qA r2 = new C10901qA(context, context.obtainStyledAttributes(i, C11081qa.A0M));
        TypedArray typedArray = r2.A02;
        if (typedArray.hasValue(14)) {
            this.A0B.setAllCaps(typedArray.getBoolean(14, false));
        }
        if (typedArray.hasValue(0) && typedArray.getDimensionPixelSize(0, -1) == 0) {
            this.A0B.setTextSize(0, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        }
        A01(context, r2);
        if (Build.VERSION.SDK_INT >= 26 && typedArray.hasValue(13) && (string = typedArray.getString(13)) != null) {
            this.A0B.setFontVariationSettings(string);
        }
        r2.A04();
        Typeface typeface = this.A01;
        if (typeface != null) {
            this.A0B.setTypeface(typeface, this.A00);
        }
    }

    public final void A08(@Nullable ColorStateList colorStateList) {
        C11101qc r1 = this.A07;
        if (r1 == null) {
            r1 = new C11101qc();
            this.A07 = r1;
        }
        r1.A00 = colorStateList;
        boolean z = false;
        if (colorStateList != null) {
            z = true;
        }
        r1.A02 = z;
        this.A04 = r1;
        this.A08 = r1;
        this.A05 = r1;
        this.A02 = r1;
        this.A06 = r1;
        this.A03 = r1;
    }

    public final void A09(@Nullable PorterDuff.Mode mode) {
        C11101qc r1 = this.A07;
        if (r1 == null) {
            r1 = new C11101qc();
            this.A07 = r1;
        }
        r1.A01 = mode;
        boolean z = false;
        if (mode != null) {
            z = true;
        }
        r1.A03 = z;
        this.A04 = r1;
        this.A08 = r1;
        this.A05 = r1;
        this.A02 = r1;
        this.A06 = r1;
        this.A03 = r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:194:0x0380  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d4  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0A(@androidx.annotation.Nullable android.util.AttributeSet r23, int r24) {
        /*
        // Method dump skipped, instructions count: 910
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qE.A0A(android.util.AttributeSet, int):void");
    }

    public final void A0B(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        C10761pd r4 = this.A0C;
        if (!(r4.A09 instanceof AnonymousClass1qJ)) {
            int length = iArr.length;
            int i2 = 0;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = r4.A08.getResources().getDisplayMetrics();
                    do {
                        iArr2[i2] = Math.round(TypedValue.applyDimension(i, (float) iArr[i2], displayMetrics));
                        i2++;
                    } while (i2 < length);
                }
                r4.A07 = C10761pd.A05(iArr2);
                if (!C10761pd.A04(r4)) {
                    throw new IllegalArgumentException(AnonymousClass006.A07("None of the preset sizes is valid: ", Arrays.toString(iArr)));
                }
            } else {
                r4.A05 = false;
            }
            if (C10761pd.A03(r4)) {
                r4.A06();
            }
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final boolean A0C() {
        C10761pd r1 = this.A0C;
        if ((r1.A09 instanceof AnonymousClass1qJ) || r1.A03 == 0) {
            return false;
        }
        return true;
    }

    public AnonymousClass1qE(@NonNull TextView textView) {
        this.A0B = textView;
        this.A0C = new C10761pd(textView);
    }

    public static C11101qc A00(Context context, C10911qB r1, int i) {
        ColorStateList A022 = r1.A02(context, i);
        if (A022 == null) {
            return null;
        }
        C11101qc r12 = new C11101qc();
        r12.A02 = true;
        r12.A00 = A022;
        return r12;
    }
}
