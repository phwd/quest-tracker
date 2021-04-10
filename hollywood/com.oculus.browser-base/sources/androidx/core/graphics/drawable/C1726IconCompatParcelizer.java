package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import java.nio.charset.Charset;
import java.util.Objects;

/* renamed from: androidx.core.graphics.drawable.IconCompatParcelizer  reason: case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1726IconCompatParcelizer {
    public static C1725IconCompat read(Ls1 ls1) {
        C1725IconCompat iconCompat = new C1725IconCompat();
        iconCompat.b = ls1.i(iconCompat.b, 1);
        byte[] bArr = iconCompat.d;
        if (ls1.h(2)) {
            Ms1 ms1 = (Ms1) ls1;
            int readInt = ms1.e.readInt();
            if (readInt < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[readInt];
                ms1.e.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.d = bArr;
        iconCompat.e = ls1.j(iconCompat.e, 3);
        iconCompat.f = ls1.i(iconCompat.f, 4);
        iconCompat.g = ls1.i(iconCompat.g, 5);
        iconCompat.h = (ColorStateList) ls1.j(iconCompat.h, 6);
        String str = iconCompat.j;
        if (ls1.h(7)) {
            str = ((Ms1) ls1).e.readString();
        }
        iconCompat.j = str;
        String str2 = iconCompat.k;
        if (ls1.h(8)) {
            str2 = ((Ms1) ls1).e.readString();
        }
        iconCompat.k = str2;
        iconCompat.i = PorterDuff.Mode.valueOf(iconCompat.j);
        switch (iconCompat.b) {
            case -1:
                Parcelable parcelable = iconCompat.e;
                if (parcelable != null) {
                    iconCompat.c = parcelable;
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid icon");
                }
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.e;
                if (parcelable2 == null) {
                    byte[] bArr3 = iconCompat.d;
                    iconCompat.c = bArr3;
                    iconCompat.b = 3;
                    iconCompat.f = 0;
                    iconCompat.g = bArr3.length;
                    break;
                } else {
                    iconCompat.c = parcelable2;
                    break;
                }
            case 2:
            case 4:
            case 6:
                String str3 = new String(iconCompat.d, Charset.forName("UTF-16"));
                iconCompat.c = str3;
                if (iconCompat.b == 2 && iconCompat.k == null) {
                    iconCompat.k = str3.split(":", -1)[0];
                    break;
                }
            case 3:
                iconCompat.c = iconCompat.d;
                break;
        }
        return iconCompat;
    }

    public static void write(C1725IconCompat iconCompat, Ls1 ls1) {
        Objects.requireNonNull(ls1);
        iconCompat.j = iconCompat.i.name();
        switch (iconCompat.b) {
            case -1:
                iconCompat.e = (Parcelable) iconCompat.c;
                break;
            case 1:
            case 5:
                iconCompat.e = (Parcelable) iconCompat.c;
                break;
            case 2:
                iconCompat.d = ((String) iconCompat.c).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.d = (byte[]) iconCompat.c;
                break;
            case 4:
            case 6:
                iconCompat.d = iconCompat.c.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.b;
        if (-1 != i) {
            ls1.m(i, 1);
        }
        byte[] bArr = iconCompat.d;
        if (bArr != null) {
            ls1.l(2);
            Ms1 ms1 = (Ms1) ls1;
            ms1.e.writeInt(bArr.length);
            ms1.e.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.e;
        if (parcelable != null) {
            ls1.l(3);
            ((Ms1) ls1).e.writeParcelable(parcelable, 0);
        }
        int i2 = iconCompat.f;
        if (i2 != 0) {
            ls1.m(i2, 4);
        }
        int i3 = iconCompat.g;
        if (i3 != 0) {
            ls1.m(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.h;
        if (colorStateList != null) {
            ls1.l(6);
            ((Ms1) ls1).e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.j;
        if (str != null) {
            ls1.l(7);
            ((Ms1) ls1).e.writeString(str);
        }
        String str2 = iconCompat.k;
        if (str2 != null) {
            ls1.l(8);
            ((Ms1) ls1).e.writeString(str2);
        }
    }
}
