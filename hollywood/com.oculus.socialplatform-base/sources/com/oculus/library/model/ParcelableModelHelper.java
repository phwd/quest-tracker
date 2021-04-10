package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.library.model.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcelableModelHelper {
    public static <T> List<T> unmarshallParcelableList(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr != null) {
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            ArrayList<T> createTypedArrayList = obtain.createTypedArrayList(creator);
            obtain.recycle();
            if (createTypedArrayList != null) {
                return createTypedArrayList;
            }
        }
        return null;
    }

    public static Map<Image.ImageName, Image> convertImageListToMap(List<Image> list) {
        if (list == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Image image : list) {
            hashMap.put(image.name, image);
        }
        return hashMap;
    }

    public static byte[] marshallParcelableList(List<? extends Parcelable> list) {
        if (list == null || list.isEmpty()) {
            return new byte[0];
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeTypedList(list);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
