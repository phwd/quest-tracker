package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.library.model.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcelableModelHelper {
    public static Map<Image.ImageName, Image> convertImageListToMap(List<Image> imageList) {
        if (imageList == null) {
            return null;
        }
        HashMap<Image.ImageName, Image> images = new HashMap<>();
        for (Image image : imageList) {
            images.put(image.name, image);
        }
        return images;
    }

    public static byte[] marshallParcelableList(List<? extends Parcelable> parcelableList) {
        if (parcelableList == null || parcelableList.isEmpty()) {
            return new byte[0];
        }
        Parcel parcel = Parcel.obtain();
        parcel.writeTypedList(parcelableList);
        byte[] marshall = parcel.marshall();
        parcel.recycle();
        return marshall;
    }

    public static <T> List<T> unmarshallParcelableList(byte[] blob, Parcelable.Creator<T> creator) {
        if (blob == null) {
            return null;
        }
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(blob, 0, blob.length);
        parcel.setDataPosition(0);
        List parcelableList = parcel.createTypedArrayList(creator);
        parcel.recycle();
        if (parcelableList == null) {
            return null;
        }
        return parcelableList;
    }
}
