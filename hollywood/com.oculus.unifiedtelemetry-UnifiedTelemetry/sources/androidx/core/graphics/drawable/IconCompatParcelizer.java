package androidx.core.graphics.drawable;

import X.AnonymousClass2C;
import X.CW;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.nio.charset.Charset;

@RestrictTo({AnonymousClass2C.LIBRARY})
public class IconCompatParcelizer {
    public static IconCompat read(CW cw) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.A02 = cw.A02(iconCompat.A02, 1);
        byte[] bArr = iconCompat.A08;
        if (cw.A0I(2)) {
            bArr = cw.A0J();
        }
        iconCompat.A08 = bArr;
        Parcelable parcelable = iconCompat.A05;
        if (cw.A0I(3)) {
            parcelable = cw.A03();
        }
        iconCompat.A05 = parcelable;
        iconCompat.A00 = cw.A02(iconCompat.A00, 4);
        iconCompat.A01 = cw.A02(iconCompat.A01, 5);
        Parcelable parcelable2 = iconCompat.A03;
        if (cw.A0I(6)) {
            parcelable2 = cw.A03();
        }
        iconCompat.A03 = (ColorStateList) parcelable2;
        String str = iconCompat.A07;
        if (cw.A0I(7)) {
            str = cw.A07();
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

    public static void write(IconCompat iconCompat, CW cw) {
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
            cw.A09(1);
            cw.A0A(i);
        }
        byte[] bArr2 = iconCompat.A08;
        if (bArr2 != null) {
            cw.A09(2);
            cw.A0G(bArr2);
        }
        Parcelable parcelable = iconCompat.A05;
        if (parcelable != null) {
            cw.A09(3);
            cw.A0B(parcelable);
        }
        int i2 = iconCompat.A00;
        if (i2 != 0) {
            cw.A09(4);
            cw.A0A(i2);
        }
        int i3 = iconCompat.A01;
        if (i3 != 0) {
            cw.A09(5);
            cw.A0A(i3);
        }
        ColorStateList colorStateList = iconCompat.A03;
        if (colorStateList != null) {
            cw.A09(6);
            cw.A0B(colorStateList);
        }
        String str2 = iconCompat.A07;
        if (str2 != null) {
            cw.A09(7);
            cw.A0E(str2);
        }
    }
}
