package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import androidx.versionedparcelable.VersionedParcel;
import java.nio.charset.Charset;

public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.mType = versionedParcel.readInt(iconCompat.mType, 1);
        iconCompat.mData = versionedParcel.readByteArray(iconCompat.mData, 2);
        iconCompat.mParcelable = versionedParcel.readParcelable(iconCompat.mParcelable, 3);
        iconCompat.mInt1 = versionedParcel.readInt(iconCompat.mInt1, 4);
        iconCompat.mInt2 = versionedParcel.readInt(iconCompat.mInt2, 5);
        iconCompat.mTintList = (ColorStateList) versionedParcel.readParcelable(iconCompat.mTintList, 6);
        iconCompat.mTintModeStr = versionedParcel.readString(iconCompat.mTintModeStr, 7);
        iconCompat.mTintMode = PorterDuff.Mode.valueOf(iconCompat.mTintModeStr);
        switch (iconCompat.mType) {
            case -1:
                if (iconCompat.mParcelable != null) {
                    iconCompat.mObj1 = iconCompat.mParcelable;
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid icon");
                }
            case 1:
            case 5:
                if (iconCompat.mParcelable == null) {
                    iconCompat.mObj1 = iconCompat.mData;
                    iconCompat.mType = 3;
                    iconCompat.mInt1 = 0;
                    iconCompat.mInt2 = iconCompat.mData.length;
                    break;
                } else {
                    iconCompat.mObj1 = iconCompat.mParcelable;
                    break;
                }
            case 2:
            case 4:
            case 6:
                iconCompat.mObj1 = new String(iconCompat.mData, Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.mObj1 = iconCompat.mData;
                break;
        }
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        iconCompat.mTintModeStr = iconCompat.mTintMode.name();
        switch (iconCompat.mType) {
            case -1:
                iconCompat.mParcelable = (Parcelable) iconCompat.mObj1;
                break;
            case 1:
            case 5:
                iconCompat.mParcelable = (Parcelable) iconCompat.mObj1;
                break;
            case 2:
                iconCompat.mData = ((String) iconCompat.mObj1).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.mData = (byte[]) iconCompat.mObj1;
                break;
            case 4:
            case 6:
                iconCompat.mData = iconCompat.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        if (-1 != iconCompat.mType) {
            versionedParcel.writeInt(iconCompat.mType, 1);
        }
        if (iconCompat.mData != null) {
            versionedParcel.writeByteArray(iconCompat.mData, 2);
        }
        if (iconCompat.mParcelable != null) {
            versionedParcel.writeParcelable(iconCompat.mParcelable, 3);
        }
        if (iconCompat.mInt1 != 0) {
            versionedParcel.writeInt(iconCompat.mInt1, 4);
        }
        if (iconCompat.mInt2 != 0) {
            versionedParcel.writeInt(iconCompat.mInt2, 5);
        }
        if (iconCompat.mTintList != null) {
            versionedParcel.writeParcelable(iconCompat.mTintList, 6);
        }
        if (iconCompat.mTintModeStr != null) {
            versionedParcel.writeString(iconCompat.mTintModeStr, 7);
        }
    }
}
