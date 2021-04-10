package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.util.Xml;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: X.04U  reason: invalid class name */
public final class AnonymousClass04U {
    public int A00 = 0;
    public Typeface A01;
    public AnonymousClass05X A02;
    public AnonymousClass05X A03;
    public AnonymousClass05X A04;
    public AnonymousClass05X A05;
    public AnonymousClass05X A06;
    public AnonymousClass05X A07;
    public AnonymousClass05X A08;
    public boolean A09;
    public int A0A = -1;
    @NonNull
    public final TextView A0B;
    @NonNull
    public final AnonymousClass04W A0C;

    public static void A05(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
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
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01e8, code lost:
        if (r13 == null) goto L_0x02b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01ec, code lost:
        if (r13 == null) goto L_0x02b5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A03(android.content.Context r33, X.AnonymousClass05Y r34) {
        /*
        // Method dump skipped, instructions count: 749
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass04U.A03(android.content.Context, X.05Y):void");
    }

    private void A04(Drawable drawable, AnonymousClass05X r3) {
        if (drawable != null && r3 != null) {
            AnonymousClass17F.A02(drawable, r3, this.A0B.getDrawableState());
        }
    }

    public final void A06() {
        if (!(this.A04 == null && this.A08 == null && this.A05 == null && this.A02 == null)) {
            Drawable[] compoundDrawables = this.A0B.getCompoundDrawables();
            A04(compoundDrawables[0], this.A04);
            A04(compoundDrawables[1], this.A08);
            A04(compoundDrawables[2], this.A05);
            A04(compoundDrawables[3], this.A02);
        }
        if (this.A06 != null || this.A03 != null) {
            Drawable[] compoundDrawablesRelative = this.A0B.getCompoundDrawablesRelative();
            A04(compoundDrawablesRelative[0], this.A06);
            A04(compoundDrawablesRelative[2], this.A03);
        }
    }

    public final void A07(int i) {
        AnonymousClass04W r4 = this.A0C;
        if (r4.A09 instanceof C04170eC) {
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
            AnonymousClass04W.A02(r4, TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (AnonymousClass04W.A03(r4)) {
                r4.A06();
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A01("Unknown auto-size text type: ", i));
        }
    }

    public final void A08(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        AnonymousClass04W r4 = this.A0C;
        if (!(r4.A09 instanceof C04170eC)) {
            DisplayMetrics displayMetrics = r4.A08.getResources().getDisplayMetrics();
            AnonymousClass04W.A02(r4, TypedValue.applyDimension(i4, (float) i, displayMetrics), TypedValue.applyDimension(i4, (float) i2, displayMetrics), TypedValue.applyDimension(i4, (float) i3, displayMetrics));
            if (AnonymousClass04W.A03(r4)) {
                r4.A06();
            }
        }
    }

    public final void A09(Context context, int i) {
        String string;
        AnonymousClass05Y r2 = new AnonymousClass05Y(context, context.obtainStyledAttributes(i, AnonymousClass18N.A0M));
        TypedArray typedArray = r2.A02;
        if (typedArray.hasValue(14)) {
            this.A0B.setAllCaps(typedArray.getBoolean(14, false));
        }
        if (typedArray.hasValue(0) && typedArray.getDimensionPixelSize(0, -1) == 0) {
            this.A0B.setTextSize(0, 0.0f);
        }
        A03(context, r2);
        if (Build.VERSION.SDK_INT >= 26 && typedArray.hasValue(13) && (string = typedArray.getString(13)) != null) {
            this.A0B.setFontVariationSettings(string);
        }
        r2.A04();
        Typeface typeface = this.A01;
        if (typeface != null) {
            this.A0B.setTypeface(typeface, this.A00);
        }
    }

    public final void A0A(@Nullable ColorStateList colorStateList) {
        AnonymousClass05X r1 = this.A07;
        if (r1 == null) {
            r1 = new AnonymousClass05X();
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

    public final void A0B(@Nullable PorterDuff.Mode mode) {
        AnonymousClass05X r1 = this.A07;
        if (r1 == null) {
            r1 = new AnonymousClass05X();
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

    /* JADX WARNING: Removed duplicated region for block: B:153:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d5  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0C(@androidx.annotation.Nullable android.util.AttributeSet r21, int r22) {
        /*
        // Method dump skipped, instructions count: 707
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass04U.A0C(android.util.AttributeSet, int):void");
    }

    public final void A0D(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        AnonymousClass04W r4 = this.A0C;
        if (!(r4.A09 instanceof C04170eC)) {
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
                r4.A07 = AnonymousClass04W.A05(iArr2);
                if (!AnonymousClass04W.A04(r4)) {
                    throw new IllegalArgumentException(AnonymousClass006.A05("None of the preset sizes is valid: ", Arrays.toString(iArr)));
                }
            } else {
                r4.A05 = false;
            }
            if (AnonymousClass04W.A03(r4)) {
                r4.A06();
            }
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final boolean A0E() {
        AnonymousClass04W r1 = this.A0C;
        if ((r1.A09 instanceof C04170eC) || r1.A03 == 0) {
            return false;
        }
        return true;
    }

    public AnonymousClass04U(@NonNull TextView textView) {
        this.A0B = textView;
        this.A0C = new AnonymousClass04W(textView);
    }

    public static AnonymousClass05X A00(Context context, AnonymousClass04E r1, int i) {
        ColorStateList A032 = r1.A03(context, i);
        if (A032 == null) {
            return null;
        }
        AnonymousClass05X r12 = new AnonymousClass05X();
        r12.A02 = true;
        r12.A00 = A032;
        return r12;
    }

    @Nullable
    public static AnonymousClass082 A01(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        List list;
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), AnonymousClass18M.A01);
        String string = obtainAttributes.getString(0);
        String string2 = obtainAttributes.getString(4);
        String string3 = obtainAttributes.getString(5);
        int resourceId = obtainAttributes.getResourceId(1, 0);
        int integer = obtainAttributes.getInteger(2, 1);
        int integer2 = obtainAttributes.getInteger(3, 500);
        obtainAttributes.recycle();
        if (string == null || string2 == null || string3 == null) {
            ArrayList arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        arrayList.add(A02(xmlPullParser, resources));
                    } else {
                        A05(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new C03900dT((AnonymousClass084[]) arrayList.toArray(new AnonymousClass084[arrayList.size()]));
        }
        while (xmlPullParser.next() != 3) {
            A05(xmlPullParser);
        }
        if (resourceId == 0) {
            list = Collections.emptyList();
        } else {
            TypedArray obtainTypedArray = resources.obtainTypedArray(resourceId);
            try {
                if (obtainTypedArray.length() == 0) {
                    list = Collections.emptyList();
                } else {
                    list = new ArrayList();
                    if (obtainTypedArray.getType(0) == 1) {
                        for (int i = 0; i < obtainTypedArray.length(); i++) {
                            int resourceId2 = obtainTypedArray.getResourceId(i, 0);
                            if (resourceId2 != 0) {
                                String[] stringArray = resources.getStringArray(resourceId2);
                                ArrayList arrayList2 = new ArrayList();
                                for (String str : stringArray) {
                                    arrayList2.add(Base64.decode(str, 0));
                                }
                                list.add(arrayList2);
                            }
                        }
                    } else {
                        String[] stringArray2 = resources.getStringArray(resourceId);
                        ArrayList arrayList3 = new ArrayList();
                        for (String str2 : stringArray2) {
                            arrayList3.add(Base64.decode(str2, 0));
                        }
                        list.add(arrayList3);
                    }
                }
            } finally {
                obtainTypedArray.recycle();
            }
        }
        return new C03890dS(new AnonymousClass09F(string, string2, string3, list), integer, integer2);
    }

    public static AnonymousClass084 A02(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), AnonymousClass18M.A02);
        int i = 1;
        if (obtainAttributes.hasValue(8)) {
            i = 8;
        }
        int i2 = obtainAttributes.getInt(i, 400);
        int i3 = 2;
        if (obtainAttributes.hasValue(6)) {
            i3 = 6;
        }
        boolean z = false;
        if (1 == obtainAttributes.getInt(i3, 0)) {
            z = true;
        }
        int i4 = 3;
        if (obtainAttributes.hasValue(9)) {
            i4 = 9;
        }
        int i5 = 4;
        if (obtainAttributes.hasValue(7)) {
            i5 = 7;
        }
        String string = obtainAttributes.getString(i5);
        int i6 = obtainAttributes.getInt(i4, 0);
        int i7 = 0;
        if (obtainAttributes.hasValue(5)) {
            i7 = 5;
        }
        int resourceId = obtainAttributes.getResourceId(i7, 0);
        String string2 = obtainAttributes.getString(i7);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            A05(xmlPullParser);
        }
        return new AnonymousClass084(string2, i2, z, string, i6, resourceId);
    }
}
