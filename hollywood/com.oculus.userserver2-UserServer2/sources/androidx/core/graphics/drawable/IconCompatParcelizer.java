package androidx.core.graphics.drawable;

import X.AbstractC0056El;
import X.AnonymousClass2O;
import X.TK;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import java.nio.charset.Charset;

@RestrictTo({AnonymousClass2O.LIBRARY})
public class IconCompatParcelizer {
    public static IconCompat read(AbstractC0056El el) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.A02 = el.A01(iconCompat.A02, 1);
        byte[] bArr = iconCompat.A08;
        if (el.A09(2)) {
            Parcel parcel = ((TK) el).A05;
            int readInt = parcel.readInt();
            if (readInt < 0) {
                bArr = null;
            } else {
                bArr = new byte[readInt];
                parcel.readByteArray(bArr);
            }
        }
        iconCompat.A08 = bArr;
        Parcelable parcelable = iconCompat.A05;
        if (el.A09(3)) {
            parcelable = el.A02();
        }
        iconCompat.A05 = parcelable;
        iconCompat.A00 = el.A01(iconCompat.A00, 4);
        iconCompat.A01 = el.A01(iconCompat.A01, 5);
        Parcelable parcelable2 = iconCompat.A03;
        if (el.A09(6)) {
            parcelable2 = el.A02();
        }
        iconCompat.A03 = (ColorStateList) parcelable2;
        String str = iconCompat.A07;
        if (el.A09(7)) {
            str = ((TK) el).A05.readString();
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

    public static void write(IconCompat iconCompat, AbstractC0056El el) {
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
            el.A06(1);
            el.A07(i);
        }
        byte[] bArr2 = iconCompat.A08;
        if (bArr2 != null) {
            el.A06(2);
            Parcel parcel = ((TK) el).A05;
            parcel.writeInt(bArr2.length);
            parcel.writeByteArray(bArr2);
        }
        Parcelable parcelable = iconCompat.A05;
        if (parcelable != null) {
            el.A06(3);
            ((TK) el).A05.writeParcelable(parcelable, 0);
        }
        int i2 = iconCompat.A00;
        if (i2 != 0) {
            el.A06(4);
            el.A07(i2);
        }
        int i3 = iconCompat.A01;
        if (i3 != 0) {
            el.A06(5);
            el.A07(i3);
        }
        ColorStateList colorStateList = iconCompat.A03;
        if (colorStateList != null) {
            el.A06(6);
            ((TK) el).A05.writeParcelable(colorStateList, 0);
        }
        String str2 = iconCompat.A07;
        if (str2 != null) {
            el.A06(7);
            ((TK) el).A05.writeString(str2);
        }
    }
}
