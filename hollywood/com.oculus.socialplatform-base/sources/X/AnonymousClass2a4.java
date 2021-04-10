package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.facebook.acra.CrashTimeDataCollector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: X.2a4  reason: invalid class name */
public final class AnonymousClass2a4 {
    public static SparseIntArray A02;
    public static final int[] A03 = {0, 4, 8};
    public HashMap<Integer, AnonymousClass2a6> A00 = new HashMap<>();
    public HashMap<String, AnonymousClass2aF> A01 = new HashMap<>();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        A02 = sparseIntArray;
        sparseIntArray.append(77, 25);
        A02.append(78, 26);
        A02.append(80, 29);
        A02.append(81, 30);
        A02.append(87, 36);
        A02.append(86, 35);
        A02.append(59, 4);
        A02.append(58, 3);
        A02.append(56, 1);
        A02.append(95, 6);
        A02.append(96, 7);
        A02.append(66, 17);
        A02.append(67, 18);
        A02.append(68, 19);
        A02.append(0, 27);
        A02.append(82, 32);
        A02.append(83, 33);
        A02.append(65, 10);
        A02.append(64, 9);
        A02.append(99, 13);
        A02.append(102, 16);
        A02.append(100, 14);
        A02.append(97, 11);
        A02.append(101, 15);
        A02.append(98, 12);
        A02.append(90, 40);
        A02.append(75, 39);
        A02.append(74, 41);
        A02.append(89, 42);
        A02.append(73, 20);
        A02.append(88, 37);
        A02.append(63, 5);
        A02.append(76, 82);
        A02.append(85, 82);
        A02.append(79, 82);
        A02.append(57, 82);
        A02.append(55, 82);
        A02.append(5, 24);
        A02.append(7, 28);
        A02.append(23, 31);
        A02.append(24, 8);
        A02.append(6, 34);
        A02.append(8, 2);
        A02.append(3, 23);
        A02.append(4, 21);
        A02.append(2, 22);
        A02.append(13, 43);
        A02.append(26, 44);
        A02.append(21, 45);
        A02.append(22, 46);
        A02.append(20, 60);
        A02.append(18, 47);
        A02.append(19, 48);
        A02.append(14, 49);
        A02.append(15, 50);
        A02.append(16, 51);
        A02.append(17, 52);
        A02.append(25, 53);
        A02.append(91, 54);
        A02.append(69, 55);
        A02.append(92, 56);
        A02.append(70, 57);
        A02.append(93, 58);
        A02.append(71, 59);
        A02.append(60, 61);
        A02.append(62, 62);
        A02.append(61, 63);
        A02.append(27, 64);
        A02.append(107, 65);
        A02.append(34, 66);
        A02.append(108, 67);
        A02.append(104, 79);
        A02.append(1, 38);
        A02.append(103, 68);
        A02.append(94, 69);
        A02.append(72, 70);
        A02.append(31, 71);
        A02.append(29, 72);
        A02.append(30, 73);
        A02.append(32, 74);
        A02.append(28, 75);
        A02.append(105, 76);
        A02.append(84, 77);
        A02.append(109, 78);
        A02.append(54, 80);
        A02.append(53, 81);
    }

    public static String A03(int i) {
        switch (i) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public final void A0B(int[] iArr) {
        int length = iArr.length;
        if (length >= 2) {
            A02(this, iArr[0]).A02.A0n = 2;
            A07(iArr[0], 3, 0, 3, 0);
            for (int i = 1; i < length; i++) {
                int i2 = i - 1;
                A07(iArr[i], 3, iArr[i2], 4, 0);
                A07(iArr[i2], 4, iArr[i], 3, 0);
            }
            A07(iArr[length - 1], 4, 0, 4, 0);
            return;
        }
        throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }

    public static AnonymousClass2a6 A01(Context context, AttributeSet attributeSet) {
        String str;
        AnonymousClass2aG r8;
        String str2;
        AnonymousClass2a6 r4 = new AnonymousClass2a6();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass2aI.A00);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (!(index == 1 || 23 == index || 24 == index)) {
                r4.A03.A06 = true;
                r4.A02.A0w = true;
                r4.A04.A04 = true;
                r4.A05.A0C = true;
            }
            switch (A02.get(index)) {
                case 1:
                    AnonymousClass2a9 r1 = r4.A02;
                    r1.A08 = A00(obtainStyledAttributes, index, r1.A08);
                    continue;
                case 2:
                    AnonymousClass2a9 r12 = r4.A02;
                    r12.A09 = obtainStyledAttributes.getDimensionPixelSize(index, r12.A09);
                    continue;
                case 3:
                    AnonymousClass2a9 r13 = r4.A02;
                    r13.A0A = A00(obtainStyledAttributes, index, r13.A0A);
                    continue;
                case 4:
                    AnonymousClass2a9 r14 = r4.A02;
                    r14.A0B = A00(obtainStyledAttributes, index, r14.A0B);
                    continue;
                case 5:
                    r4.A02.A0r = obtainStyledAttributes.getString(index);
                    continue;
                case 6:
                    AnonymousClass2a9 r15 = r4.A02;
                    r15.A0E = obtainStyledAttributes.getDimensionPixelOffset(index, r15.A0E);
                    continue;
                case 7:
                    AnonymousClass2a9 r16 = r4.A02;
                    r16.A0F = obtainStyledAttributes.getDimensionPixelOffset(index, r16.A0F);
                    continue;
                case 8:
                    AnonymousClass2a9 r17 = r4.A02;
                    r17.A0G = obtainStyledAttributes.getDimensionPixelSize(index, r17.A0G);
                    continue;
                case 9:
                    AnonymousClass2a9 r18 = r4.A02;
                    r18.A0H = A00(obtainStyledAttributes, index, r18.A0H);
                    continue;
                case 10:
                    AnonymousClass2a9 r19 = r4.A02;
                    r19.A0I = A00(obtainStyledAttributes, index, r19.A0I);
                    continue;
                case 11:
                    AnonymousClass2a9 r110 = r4.A02;
                    r110.A0J = obtainStyledAttributes.getDimensionPixelSize(index, r110.A0J);
                    continue;
                case 12:
                    AnonymousClass2a9 r111 = r4.A02;
                    r111.A0K = obtainStyledAttributes.getDimensionPixelSize(index, r111.A0K);
                    continue;
                case 13:
                    AnonymousClass2a9 r112 = r4.A02;
                    r112.A0L = obtainStyledAttributes.getDimensionPixelSize(index, r112.A0L);
                    continue;
                case 14:
                    AnonymousClass2a9 r113 = r4.A02;
                    r113.A0M = obtainStyledAttributes.getDimensionPixelSize(index, r113.A0M);
                    continue;
                case 15:
                    AnonymousClass2a9 r114 = r4.A02;
                    r114.A0N = obtainStyledAttributes.getDimensionPixelSize(index, r114.A0N);
                    continue;
                case 16:
                    AnonymousClass2a9 r115 = r4.A02;
                    r115.A0O = obtainStyledAttributes.getDimensionPixelSize(index, r115.A0O);
                    continue;
                case 17:
                    AnonymousClass2a9 r116 = r4.A02;
                    r116.A0P = obtainStyledAttributes.getDimensionPixelOffset(index, r116.A0P);
                    continue;
                case 18:
                    AnonymousClass2a9 r117 = r4.A02;
                    r117.A0Q = obtainStyledAttributes.getDimensionPixelOffset(index, r117.A0Q);
                    continue;
                case 19:
                    AnonymousClass2a9 r118 = r4.A02;
                    r118.A01 = obtainStyledAttributes.getFloat(index, r118.A01);
                    continue;
                case 20:
                    AnonymousClass2a9 r119 = r4.A02;
                    r119.A03 = obtainStyledAttributes.getFloat(index, r119.A03);
                    continue;
                case 21:
                    AnonymousClass2a9 r120 = r4.A02;
                    r120.A0a = obtainStyledAttributes.getLayoutDimension(index, r120.A0a);
                    continue;
                case 22:
                    AnonymousClass2aJ r82 = r4.A04;
                    int i2 = obtainStyledAttributes.getInt(index, r82.A03);
                    r82.A03 = i2;
                    r82.A03 = A03[i2];
                    continue;
                case 23:
                    AnonymousClass2a9 r121 = r4.A02;
                    r121.A0c = obtainStyledAttributes.getLayoutDimension(index, r121.A0c);
                    continue;
                case 24:
                    AnonymousClass2a9 r122 = r4.A02;
                    r122.A0V = obtainStyledAttributes.getDimensionPixelSize(index, r122.A0V);
                    continue;
                case 25:
                    AnonymousClass2a9 r123 = r4.A02;
                    r123.A0W = A00(obtainStyledAttributes, index, r123.A0W);
                    continue;
                case 26:
                    AnonymousClass2a9 r124 = r4.A02;
                    r124.A0X = A00(obtainStyledAttributes, index, r124.A0X);
                    continue;
                case 27:
                    AnonymousClass2a9 r125 = r4.A02;
                    r125.A0d = obtainStyledAttributes.getInt(index, r125.A0d);
                    continue;
                case 28:
                    AnonymousClass2a9 r126 = r4.A02;
                    r126.A0e = obtainStyledAttributes.getDimensionPixelSize(index, r126.A0e);
                    continue;
                case 29:
                    AnonymousClass2a9 r127 = r4.A02;
                    r127.A0f = A00(obtainStyledAttributes, index, r127.A0f);
                    continue;
                case 30:
                    AnonymousClass2a9 r128 = r4.A02;
                    r128.A0g = A00(obtainStyledAttributes, index, r128.A0g);
                    continue;
                case 31:
                    AnonymousClass2a9 r129 = r4.A02;
                    r129.A0h = obtainStyledAttributes.getDimensionPixelSize(index, r129.A0h);
                    continue;
                case 32:
                    AnonymousClass2a9 r130 = r4.A02;
                    r130.A0i = A00(obtainStyledAttributes, index, r130.A0i);
                    continue;
                case 33:
                    AnonymousClass2a9 r131 = r4.A02;
                    r131.A0j = A00(obtainStyledAttributes, index, r131.A0j);
                    continue;
                case 34:
                    AnonymousClass2a9 r132 = r4.A02;
                    r132.A0k = obtainStyledAttributes.getDimensionPixelSize(index, r132.A0k);
                    continue;
                case 35:
                    AnonymousClass2a9 r133 = r4.A02;
                    r133.A0l = A00(obtainStyledAttributes, index, r133.A0l);
                    continue;
                case 36:
                    AnonymousClass2a9 r134 = r4.A02;
                    r134.A0m = A00(obtainStyledAttributes, index, r134.A0m);
                    continue;
                case 37:
                    AnonymousClass2a9 r135 = r4.A02;
                    r135.A05 = obtainStyledAttributes.getFloat(index, r135.A05);
                    continue;
                case 38:
                    r4.A00 = obtainStyledAttributes.getResourceId(index, r4.A00);
                    continue;
                case 39:
                    AnonymousClass2a9 r136 = r4.A02;
                    r136.A04 = obtainStyledAttributes.getFloat(index, r136.A04);
                    continue;
                case 40:
                    AnonymousClass2a9 r137 = r4.A02;
                    r137.A06 = obtainStyledAttributes.getFloat(index, r137.A06);
                    continue;
                case 41:
                    AnonymousClass2a9 r138 = r4.A02;
                    r138.A0U = obtainStyledAttributes.getInt(index, r138.A0U);
                    continue;
                case 42:
                    AnonymousClass2a9 r139 = r4.A02;
                    r139.A0n = obtainStyledAttributes.getInt(index, r139.A0n);
                    continue;
                case 43:
                    AnonymousClass2aJ r140 = r4.A04;
                    r140.A00 = obtainStyledAttributes.getFloat(index, r140.A00);
                    continue;
                case 44:
                    AnonymousClass2aD r141 = r4.A05;
                    r141.A0B = true;
                    r141.A00 = obtainStyledAttributes.getDimension(index, r141.A00);
                    continue;
                case 45:
                    AnonymousClass2aD r142 = r4.A05;
                    r142.A02 = obtainStyledAttributes.getFloat(index, r142.A02);
                    continue;
                case 46:
                    AnonymousClass2aD r143 = r4.A05;
                    r143.A03 = obtainStyledAttributes.getFloat(index, r143.A03);
                    continue;
                case 47:
                    AnonymousClass2aD r144 = r4.A05;
                    r144.A04 = obtainStyledAttributes.getFloat(index, r144.A04);
                    continue;
                case 48:
                    AnonymousClass2aD r145 = r4.A05;
                    r145.A05 = obtainStyledAttributes.getFloat(index, r145.A05);
                    continue;
                case 49:
                    AnonymousClass2aD r146 = r4.A05;
                    r146.A06 = obtainStyledAttributes.getDimension(index, r146.A06);
                    continue;
                case 50:
                    AnonymousClass2aD r147 = r4.A05;
                    r147.A07 = obtainStyledAttributes.getDimension(index, r147.A07);
                    continue;
                case 51:
                    AnonymousClass2aD r148 = r4.A05;
                    r148.A08 = obtainStyledAttributes.getDimension(index, r148.A08);
                    continue;
                case 52:
                    AnonymousClass2aD r149 = r4.A05;
                    r149.A09 = obtainStyledAttributes.getDimension(index, r149.A09);
                    continue;
                case 53:
                    AnonymousClass2aD r150 = r4.A05;
                    r150.A0A = obtainStyledAttributes.getDimension(index, r150.A0A);
                    continue;
                case 54:
                    AnonymousClass2a9 r151 = r4.A02;
                    r151.A0o = obtainStyledAttributes.getInt(index, r151.A0o);
                    continue;
                case 55:
                    AnonymousClass2a9 r152 = r4.A02;
                    r152.A0R = obtainStyledAttributes.getInt(index, r152.A0R);
                    continue;
                case 56:
                    AnonymousClass2a9 r153 = r4.A02;
                    r153.A0p = obtainStyledAttributes.getDimensionPixelSize(index, r153.A0p);
                    continue;
                case 57:
                    AnonymousClass2a9 r154 = r4.A02;
                    r154.A0S = obtainStyledAttributes.getDimensionPixelSize(index, r154.A0S);
                    continue;
                case 58:
                    AnonymousClass2a9 r155 = r4.A02;
                    r155.A0q = obtainStyledAttributes.getDimensionPixelSize(index, r155.A0q);
                    continue;
                case 59:
                    AnonymousClass2a9 r156 = r4.A02;
                    r156.A0T = obtainStyledAttributes.getDimensionPixelSize(index, r156.A0T);
                    continue;
                case 60:
                    AnonymousClass2aD r157 = r4.A05;
                    r157.A01 = obtainStyledAttributes.getFloat(index, r157.A01);
                    continue;
                case 61:
                    AnonymousClass2a9 r158 = r4.A02;
                    r158.A0C = A00(obtainStyledAttributes, index, r158.A0C);
                    continue;
                case 62:
                    AnonymousClass2a9 r159 = r4.A02;
                    r159.A0D = obtainStyledAttributes.getDimensionPixelSize(index, r159.A0D);
                    continue;
                case 63:
                    AnonymousClass2a9 r160 = r4.A02;
                    r160.A00 = obtainStyledAttributes.getFloat(index, r160.A00);
                    continue;
                case 64:
                    AnonymousClass2aG r161 = r4.A03;
                    r161.A02 = A00(obtainStyledAttributes, index, r161.A02);
                    continue;
                case 65:
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        r8 = r4.A03;
                        str2 = obtainStyledAttributes.getString(index);
                    } else {
                        r8 = r4.A03;
                        str2 = AnonymousClass2aT.A01[obtainStyledAttributes.getInteger(index, 0)];
                    }
                    r8.A05 = str2;
                    continue;
                case 66:
                    r4.A03.A03 = obtainStyledAttributes.getInt(index, 0);
                    continue;
                case 67:
                    AnonymousClass2aG r162 = r4.A03;
                    r162.A01 = obtainStyledAttributes.getFloat(index, r162.A01);
                    continue;
                case 68:
                    AnonymousClass2aJ r163 = r4.A04;
                    r163.A01 = obtainStyledAttributes.getFloat(index, r163.A01);
                    continue;
                case 69:
                    r4.A02.A07 = obtainStyledAttributes.getFloat(index, 1.0f);
                    continue;
                case 70:
                    r4.A02.A02 = obtainStyledAttributes.getFloat(index, 1.0f);
                    continue;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    continue;
                case 72:
                    AnonymousClass2a9 r164 = r4.A02;
                    r164.A0Y = obtainStyledAttributes.getInt(index, r164.A0Y);
                    continue;
                case 73:
                    AnonymousClass2a9 r165 = r4.A02;
                    r165.A0Z = obtainStyledAttributes.getDimensionPixelSize(index, r165.A0Z);
                    continue;
                case 74:
                    r4.A02.A0t = obtainStyledAttributes.getString(index);
                    continue;
                case 75:
                    AnonymousClass2a9 r166 = r4.A02;
                    r166.A0x = obtainStyledAttributes.getBoolean(index, r166.A0x);
                    continue;
                case 76:
                    AnonymousClass2aG r167 = r4.A03;
                    r167.A04 = obtainStyledAttributes.getInt(index, r167.A04);
                    continue;
                case 77:
                    r4.A02.A0s = obtainStyledAttributes.getString(index);
                    continue;
                case 78:
                    AnonymousClass2aJ r168 = r4.A04;
                    r168.A02 = obtainStyledAttributes.getInt(index, r168.A02);
                    continue;
                case 79:
                    AnonymousClass2aG r169 = r4.A03;
                    r169.A00 = obtainStyledAttributes.getFloat(index, r169.A00);
                    continue;
                case 80:
                    AnonymousClass2a9 r170 = r4.A02;
                    r170.A0v = obtainStyledAttributes.getBoolean(index, r170.A0v);
                    continue;
                case 81:
                    AnonymousClass2a9 r171 = r4.A02;
                    r171.A0u = obtainStyledAttributes.getBoolean(index, r171.A0u);
                    continue;
                case 82:
                    str = "unused attribute 0x";
                    break;
                default:
                    str = "Unknown attribute 0x";
                    break;
            }
            Log.w("ConstraintSet", AnonymousClass006.A0A(str, Integer.toHexString(index), "   ", A02.get(index)));
        }
        obtainStyledAttributes.recycle();
        return r4;
    }

    public static AnonymousClass2a6 A02(AnonymousClass2a4 r3, int i) {
        HashMap<Integer, AnonymousClass2a6> hashMap = r3.A00;
        Integer valueOf = Integer.valueOf(i);
        if (!hashMap.containsKey(valueOf)) {
            r3.A00.put(valueOf, new AnonymousClass2a6());
        }
        return r3.A00.get(valueOf);
    }

    public static int[] A04(View view, String str) {
        int i;
        Object designInformation;
        String[] split = str.split(",");
        Context context = view.getContext();
        int length = split.length;
        int[] iArr = new int[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String trim = split[i2].trim();
            try {
                i = AnonymousClass2aV.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i = ((Number) designInformation).intValue();
            }
            iArr[i3] = i;
            i2++;
            i3++;
        }
        if (i3 != length) {
            return Arrays.copyOf(iArr, i3);
        }
        return iArr;
    }

    public final void A06(int i, int i2, int i3, int i4) {
        AnonymousClass2a9 r0;
        HashMap<Integer, AnonymousClass2a6> hashMap = this.A00;
        Integer valueOf = Integer.valueOf(i);
        if (!hashMap.containsKey(valueOf)) {
            this.A00.put(valueOf, new AnonymousClass2a6());
        }
        AnonymousClass2a6 r9 = this.A00.get(valueOf);
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    AnonymousClass2a9 r02 = r9.A02;
                    r02.A0W = i3;
                    r02.A0X = -1;
                    return;
                } else if (i4 == 2) {
                    AnonymousClass2a9 r03 = r9.A02;
                    r03.A0X = i3;
                    r03.A0W = -1;
                    return;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("left to ", A03(i4), " undefined"));
                }
            case 2:
                if (i4 == 1) {
                    AnonymousClass2a9 r04 = r9.A02;
                    r04.A0f = i3;
                    r04.A0g = -1;
                    return;
                } else if (i4 == 2) {
                    AnonymousClass2a9 r05 = r9.A02;
                    r05.A0g = i3;
                    r05.A0f = -1;
                    return;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
            case 3:
                if (i4 == 3) {
                    r0 = r9.A02;
                    r0.A0m = i3;
                    r0.A0l = -1;
                    break;
                } else if (i4 == 4) {
                    r0 = r9.A02;
                    r0.A0l = i3;
                    r0.A0m = -1;
                    break;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
            case 4:
                if (i4 == 4) {
                    r0 = r9.A02;
                    r0.A0A = i3;
                    r0.A0B = -1;
                    break;
                } else if (i4 == 3) {
                    r0 = r9.A02;
                    r0.A0B = i3;
                    r0.A0A = -1;
                    break;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
            case 5:
                if (i4 == 5) {
                    AnonymousClass2a9 r06 = r9.A02;
                    r06.A08 = i3;
                    r06.A0A = -1;
                    r06.A0B = -1;
                    r06.A0m = -1;
                    r06.A0l = -1;
                    return;
                }
                throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
            case 6:
                if (i4 == 6) {
                    AnonymousClass2a9 r07 = r9.A02;
                    r07.A0j = i3;
                    r07.A0i = -1;
                    return;
                } else if (i4 == 7) {
                    AnonymousClass2a9 r08 = r9.A02;
                    r08.A0i = i3;
                    r08.A0j = -1;
                    return;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
            case 7:
                if (i4 == 7) {
                    AnonymousClass2a9 r09 = r9.A02;
                    r09.A0H = i3;
                    r09.A0I = -1;
                    return;
                } else if (i4 == 6) {
                    AnonymousClass2a9 r010 = r9.A02;
                    r010.A0I = i3;
                    r010.A0H = -1;
                    return;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
            default:
                throw new IllegalArgumentException(AnonymousClass006.A0B(A03(i2), " to ", A03(i4), " unknown"));
        }
        r0.A08 = -1;
    }

    public final void A07(int i, int i2, int i3, int i4, int i5) {
        AnonymousClass2a9 r0;
        AnonymousClass2a9 r02;
        AnonymousClass2a9 r03;
        AnonymousClass2a9 r04;
        AnonymousClass2a9 r05;
        AnonymousClass2a9 r06;
        HashMap<Integer, AnonymousClass2a6> hashMap = this.A00;
        Integer valueOf = Integer.valueOf(i);
        if (!hashMap.containsKey(valueOf)) {
            this.A00.put(valueOf, new AnonymousClass2a6());
        }
        AnonymousClass2a6 r9 = this.A00.get(valueOf);
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    r0 = r9.A02;
                    r0.A0W = i3;
                    r0.A0X = -1;
                } else if (i4 == 2) {
                    r0 = r9.A02;
                    r0.A0X = i3;
                    r0.A0W = -1;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("Left to ", A03(i4), " undefined"));
                }
                r0.A0V = i5;
                return;
            case 2:
                if (i4 == 1) {
                    r02 = r9.A02;
                    r02.A0f = i3;
                    r02.A0g = -1;
                } else if (i4 == 2) {
                    r02 = r9.A02;
                    r02.A0g = i3;
                    r02.A0f = -1;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
                r02.A0e = i5;
                return;
            case 3:
                if (i4 == 3) {
                    r03 = r9.A02;
                    r03.A0m = i3;
                    r03.A0l = -1;
                } else if (i4 == 4) {
                    r03 = r9.A02;
                    r03.A0l = i3;
                    r03.A0m = -1;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
                r03.A08 = -1;
                r03.A0k = i5;
                return;
            case 4:
                if (i4 == 4) {
                    r04 = r9.A02;
                    r04.A0A = i3;
                    r04.A0B = -1;
                } else if (i4 == 3) {
                    r04 = r9.A02;
                    r04.A0B = i3;
                    r04.A0A = -1;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
                r04.A08 = -1;
                r04.A09 = i5;
                return;
            case 5:
                if (i4 == 5) {
                    AnonymousClass2a9 r07 = r9.A02;
                    r07.A08 = i3;
                    r07.A0A = -1;
                    r07.A0B = -1;
                    r07.A0m = -1;
                    r07.A0l = -1;
                    return;
                }
                throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
            case 6:
                if (i4 == 6) {
                    r05 = r9.A02;
                    r05.A0j = i3;
                    r05.A0i = -1;
                } else if (i4 == 7) {
                    r05 = r9.A02;
                    r05.A0i = i3;
                    r05.A0j = -1;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
                r05.A0h = i5;
                return;
            case 7:
                if (i4 == 7) {
                    r06 = r9.A02;
                    r06.A0H = i3;
                    r06.A0I = -1;
                } else if (i4 == 6) {
                    r06 = r9.A02;
                    r06.A0I = i3;
                    r06.A0H = -1;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("right to ", A03(i4), " undefined"));
                }
                r06.A0G = i5;
                return;
            default:
                throw new IllegalArgumentException(AnonymousClass006.A0B(A03(i2), " to ", A03(i4), " unknown"));
        }
    }

    public final void A08(ConstraintLayout constraintLayout) {
        Method method;
        Object[] objArr;
        String str;
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.A00.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.A00.containsKey(Integer.valueOf(id))) {
                try {
                    str = childAt.getContext().getResources().getResourceEntryName(childAt.getId());
                } catch (Exception unused) {
                    str = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
                }
                Log.w("ConstraintSet", AnonymousClass006.A07("id unknown ", str));
            } else if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (id != -1 && this.A00.containsKey(Integer.valueOf(id))) {
                Integer valueOf = Integer.valueOf(id);
                hashSet.remove(valueOf);
                AnonymousClass2a6 r9 = this.A00.get(valueOf);
                if (childAt instanceof Barrier) {
                    r9.A02.A0b = 1;
                }
                AnonymousClass2a9 r10 = r9.A02;
                int i2 = r10.A0b;
                if (i2 != -1 && i2 == 1) {
                    Barrier barrier = (Barrier) childAt;
                    barrier.setId(id);
                    barrier.A00 = r10.A0Y;
                    barrier.setMargin(r10.A0Z);
                    barrier.setAllowsGoneWidget(r10.A0x);
                    int[] iArr = r10.A0z;
                    if (iArr == null) {
                        String str2 = r10.A0t;
                        if (str2 != null) {
                            iArr = A04(barrier, str2);
                            r10.A0z = iArr;
                        }
                    }
                    barrier.setReferencedIds(iArr);
                }
                AnonymousClass2a8 r11 = (AnonymousClass2a8) childAt.getLayoutParams();
                r11.A00();
                r9.A00(r11);
                HashMap<String, AnonymousClass2aF> hashMap = r9.A01;
                Class<?> cls = childAt.getClass();
                for (String str3 : hashMap.keySet()) {
                    AnonymousClass2aF r15 = hashMap.get(str3);
                    String A07 = AnonymousClass006.A07("set", str3);
                    try {
                        switch (r15.A04.intValue()) {
                            case 0:
                                method = cls.getMethod(A07, Integer.TYPE);
                                objArr = new Object[]{Integer.valueOf(r15.A03)};
                                method.invoke(childAt, objArr);
                                break;
                            case 1:
                                method = cls.getMethod(A07, Float.TYPE);
                                objArr = new Object[]{Float.valueOf(r15.A01)};
                                method.invoke(childAt, objArr);
                                break;
                            case 2:
                                method = cls.getMethod(A07, Integer.TYPE);
                                objArr = new Object[]{Integer.valueOf(r15.A02)};
                                method.invoke(childAt, objArr);
                                break;
                            case 3:
                                method = cls.getMethod(A07, Drawable.class);
                                ColorDrawable colorDrawable = new ColorDrawable();
                                colorDrawable.setColor(r15.A02);
                                objArr = new Object[]{colorDrawable};
                                method.invoke(childAt, objArr);
                                break;
                            case 4:
                                method = cls.getMethod(A07, CharSequence.class);
                                objArr = new Object[]{r15.A05};
                                method.invoke(childAt, objArr);
                                break;
                            case 5:
                                method = cls.getMethod(A07, Boolean.TYPE);
                                objArr = new Object[]{Boolean.valueOf(r15.A06)};
                                method.invoke(childAt, objArr);
                                break;
                            case 6:
                                method = cls.getMethod(A07, Float.TYPE);
                                objArr = new Object[]{Float.valueOf(r15.A01)};
                                method.invoke(childAt, objArr);
                                break;
                        }
                    } catch (NoSuchMethodException e) {
                        Log.e("TransitionLayout", e.getMessage());
                        String name = cls.getName();
                        Log.e("TransitionLayout", AnonymousClass006.A0B(" Custom Attribute \"", str3, "\" not found on ", name));
                        Log.e("TransitionLayout", AnonymousClass006.A09(name, " must have a method ", A07));
                    } catch (IllegalAccessException | InvocationTargetException e2) {
                        Log.e("TransitionLayout", AnonymousClass006.A0B(" Custom Attribute \"", str3, "\" not found on ", cls.getName()));
                        e2.printStackTrace();
                    }
                }
                childAt.setLayoutParams(r11);
                AnonymousClass2aJ r3 = r9.A04;
                if (r3.A02 == 0) {
                    childAt.setVisibility(r3.A03);
                }
                childAt.setAlpha(r3.A00);
                AnonymousClass2aD r7 = r9.A05;
                childAt.setRotation(r7.A01);
                childAt.setRotationX(r7.A02);
                childAt.setRotationY(r7.A03);
                childAt.setScaleX(r7.A04);
                childAt.setScaleY(r7.A05);
                float f = r7.A06;
                if (!Float.isNaN(f)) {
                    childAt.setPivotX(f);
                }
                float f2 = r7.A07;
                if (!Float.isNaN(f2)) {
                    childAt.setPivotY(f2);
                }
                childAt.setTranslationX(r7.A08);
                childAt.setTranslationY(r7.A09);
                childAt.setTranslationZ(r7.A0A);
                if (r7.A0B) {
                    childAt.setElevation(r7.A00);
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Number number = (Number) it.next();
            AnonymousClass2a6 r4 = this.A00.get(number);
            AnonymousClass2a9 r32 = r4.A02;
            int i3 = r32.A0b;
            if (i3 != -1 && i3 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(number.intValue());
                int[] iArr2 = r32.A0z;
                if (iArr2 == null) {
                    String str4 = r32.A0t;
                    if (str4 != null) {
                        iArr2 = A04(barrier2, str4);
                        r32.A0z = iArr2;
                    }
                    barrier2.A00 = r32.A0Y;
                    barrier2.setMargin(r32.A0Z);
                    AnonymousClass2a8 r0 = new AnonymousClass2a8(-2, -2);
                    barrier2.A04();
                    r4.A00(r0);
                    constraintLayout.addView(barrier2, r0);
                }
                barrier2.setReferencedIds(iArr2);
                barrier2.A00 = r32.A0Y;
                barrier2.setMargin(r32.A0Z);
                AnonymousClass2a8 r02 = new AnonymousClass2a8(-2, -2);
                barrier2.A04();
                r4.A00(r02);
                constraintLayout.addView(barrier2, r02);
            }
            if (r32.A0y) {
                Guideline guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(number.intValue());
                AnonymousClass2a8 r03 = new AnonymousClass2a8(-2, -2);
                r4.A00(r03);
                constraintLayout.addView(guideline, r03);
            }
        }
    }

    public final void A0A(ConstraintLayout constraintLayout) {
        AnonymousClass2aF r2;
        int childCount = constraintLayout.getChildCount();
        this.A00.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            AnonymousClass2a8 r1 = (AnonymousClass2a8) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id != -1) {
                if (!this.A00.containsKey(Integer.valueOf(id))) {
                    this.A00.put(Integer.valueOf(id), new AnonymousClass2a6());
                }
                AnonymousClass2a6 r8 = this.A00.get(Integer.valueOf(id));
                HashMap<String, AnonymousClass2aF> hashMap = this.A01;
                HashMap<String, AnonymousClass2aF> hashMap2 = new HashMap<>();
                Class<?> cls = childAt.getClass();
                for (String str : hashMap.keySet()) {
                    AnonymousClass2aF r9 = hashMap.get(str);
                    try {
                        if (str.equals("BackgroundColor")) {
                            r2 = new AnonymousClass2aF(r9, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor()));
                        } else {
                            r2 = new AnonymousClass2aF(r9, cls.getMethod(AnonymousClass006.A07("getMap", str), new Class[0]).invoke(childAt, new Object[0]));
                        }
                        hashMap2.put(str, r2);
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                r8.A01 = hashMap2;
                r8.A00 = id;
                AnonymousClass2a9 r22 = r8.A02;
                r22.A0W = r1.A0g;
                r22.A0X = r1.A0h;
                r22.A0f = r1.A0p;
                r22.A0g = r1.A0q;
                r22.A0m = r1.A0u;
                r22.A0l = r1.A0t;
                r22.A0B = r1.A0Q;
                r22.A0A = r1.A0P;
                r22.A08 = r1.A0O;
                r22.A0i = r1.A0r;
                r22.A0j = r1.A0s;
                r22.A0I = r1.A0W;
                r22.A0H = r1.A0V;
                r22.A03 = r1.A0I;
                r22.A05 = r1.A0M;
                r22.A0r = r1.A0y;
                r22.A0C = r1.A0R;
                r22.A0D = r1.A0S;
                r22.A00 = r1.A0G;
                r22.A0E = r1.A0T;
                r22.A0F = r1.A0U;
                r22.A0d = r1.A0o;
                r22.A01 = r1.A0H;
                r22.A0P = r1.A0d;
                r22.A0Q = r1.A0e;
                r22.A0c = r1.width;
                r22.A0a = r1.height;
                r22.A0V = r1.leftMargin;
                r22.A0e = r1.rightMargin;
                r22.A0k = r1.topMargin;
                r22.A09 = r1.bottomMargin;
                r22.A06 = r1.A0N;
                r22.A04 = r1.A0J;
                r22.A0n = r1.A0v;
                r22.A0U = r1.A0f;
                r22.A0v = r1.A10;
                r22.A0u = r1.A0z;
                r22.A0o = r1.A0j;
                r22.A0R = r1.A0i;
                r22.A0p = r1.A0l;
                r22.A0S = r1.A0k;
                r22.A0q = r1.A0n;
                r22.A0T = r1.A0m;
                r22.A07 = r1.A0L;
                r22.A02 = r1.A0K;
                r22.A0s = r1.A0x;
                r22.A0O = r1.A0c;
                r22.A0J = r1.A0X;
                r22.A0L = r1.A0Z;
                r22.A0M = r1.A0a;
                r22.A0N = r1.A0b;
                r22.A0K = r1.A0Y;
                r22.A0G = r1.getMarginEnd();
                r22.A0h = r1.getMarginStart();
                AnonymousClass2aJ r12 = r8.A04;
                r12.A03 = childAt.getVisibility();
                r12.A00 = childAt.getAlpha();
                AnonymousClass2aD r82 = r8.A05;
                r82.A01 = childAt.getRotation();
                r82.A02 = childAt.getRotationX();
                r82.A03 = childAt.getRotationY();
                r82.A04 = childAt.getScaleX();
                r82.A05 = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                    r82.A06 = pivotX;
                    r82.A07 = pivotY;
                }
                r82.A08 = childAt.getTranslationX();
                r82.A09 = childAt.getTranslationY();
                r82.A0A = childAt.getTranslationZ();
                if (r82.A0B) {
                    r82.A00 = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    r22.A0x = barrier.A01.A02;
                    r22.A0z = barrier.getReferencedIds();
                    r22.A0Y = barrier.A00;
                    r22.A0Z = barrier.A01.A01;
                }
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public static int A00(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        if (resourceId == -1) {
            return typedArray.getInt(i, -1);
        }
        return resourceId;
    }

    public final void A05(int i, int i2) {
        A02(this, i).A02.A0c = i2;
    }

    public final void A09(ConstraintLayout constraintLayout) {
        A08(constraintLayout);
        constraintLayout.mConstraintSet = null;
        constraintLayout.requestLayout();
    }
}
