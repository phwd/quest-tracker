package androidx.core.graphics.drawable;

import X.AnonymousClass02C;
import X.AnonymousClass0CX;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.nio.charset.Charset;

@RestrictTo({AnonymousClass02C.LIBRARY})
public class IconCompatParcelizer {
    public static IconCompat read(AnonymousClass0CX r4) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.A02 = r4.A02(iconCompat.A02, 1);
        byte[] bArr = iconCompat.A08;
        if (r4.A0I(2)) {
            bArr = r4.A0J();
        }
        iconCompat.A08 = bArr;
        Parcelable parcelable = iconCompat.A05;
        if (r4.A0I(3)) {
            parcelable = r4.A03();
        }
        iconCompat.A05 = parcelable;
        iconCompat.A00 = r4.A02(iconCompat.A00, 4);
        iconCompat.A01 = r4.A02(iconCompat.A01, 5);
        Parcelable parcelable2 = iconCompat.A03;
        if (r4.A0I(6)) {
            parcelable2 = r4.A03();
        }
        iconCompat.A03 = (ColorStateList) parcelable2;
        String str = iconCompat.A07;
        if (r4.A0I(7)) {
            str = r4.A07();
        }
        iconCompat.A07 = str;
        iconCompat.A04 = PorterDuff.Mode.valueOf(str);
        switch (iconCompat.A02) {
            case -1:
                Parcelable parcelable3 = iconCompat.A05;
                if (parcelable3 != null) {
                    iconCompat.A06 = parcelable3;
                    return iconCompat;
                }
                throw new IllegalArgumentException("Invalid icon");
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable4 = iconCompat.A05;
                if (parcelable4 != null) {
                    iconCompat.A06 = parcelable4;
                    return iconCompat;
                }
                byte[] bArr2 = iconCompat.A08;
                iconCompat.A06 = bArr2;
                iconCompat.A02 = 3;
                iconCompat.A00 = 0;
                iconCompat.A01 = bArr2.length;
                return iconCompat;
            case 2:
            case 4:
            case 6:
                iconCompat.A06 = new String(iconCompat.A08, Charset.forName("UTF-16"));
                return iconCompat;
            case 3:
                iconCompat.A06 = iconCompat.A08;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, AnonymousClass0CX r4) {
        byte[] bArr;
        String str;
        iconCompat.A07 = iconCompat.A04.name();
        switch (iconCompat.A02) {
            case -1:
            case 1:
            case 5:
                iconCompat.A05 = (Parcelable) iconCompat.A06;
                break;
            case 2:
                str = (String) iconCompat.A06;
                bArr = str.getBytes(Charset.forName("UTF-16"));
                iconCompat.A08 = bArr;
                break;
            case 3:
                bArr = (byte[]) iconCompat.A06;
                iconCompat.A08 = bArr;
                break;
            case 4:
            case 6:
                str = iconCompat.A06.toString();
                bArr = str.getBytes(Charset.forName("UTF-16"));
                iconCompat.A08 = bArr;
                break;
        }
        int i = iconCompat.A02;
        if (-1 != i) {
            r4.A09(1);
            r4.A0A(i);
        }
        byte[] bArr2 = iconCompat.A08;
        if (bArr2 != null) {
            r4.A09(2);
            r4.A0G(bArr2);
        }
        Parcelable parcelable = iconCompat.A05;
        if (parcelable != null) {
            r4.A09(3);
            r4.A0B(parcelable);
        }
        int i2 = iconCompat.A00;
        if (i2 != 0) {
            r4.A09(4);
            r4.A0A(i2);
        }
        int i3 = iconCompat.A01;
        if (i3 != 0) {
            r4.A09(5);
            r4.A0A(i3);
        }
        ColorStateList colorStateList = iconCompat.A03;
        if (colorStateList != null) {
            r4.A09(6);
            r4.A0B(colorStateList);
        }
        String str2 = iconCompat.A07;
        if (str2 != null) {
            r4.A09(7);
            r4.A0E(str2);
        }
    }
}
