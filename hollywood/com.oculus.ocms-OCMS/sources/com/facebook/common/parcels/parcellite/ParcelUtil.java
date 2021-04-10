package com.facebook.common.parcels.parcellite;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public class ParcelUtil {
    public static <T> void writeSet(Parcel parcel, @Nullable Set<T> set) {
        if (set == null) {
            parcel.writeList(null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(set);
        parcel.writeList(arrayList);
    }

    @Nullable
    public static <T> Set<T> readSet(Parcel parcel) {
        return readSet(parcel, List.class.getClassLoader());
    }

    @Nullable
    public static <T> Set<T> readSet(Parcel parcel, ClassLoader classLoader) {
        ArrayList readArrayList = parcel.readArrayList(classLoader);
        if (readArrayList == null) {
            return null;
        }
        return new HashSet(readArrayList);
    }

    @Nullable
    public static <T> T readNext(Parcel parcel) {
        return (T) parcel.readValue(ParcelUtil.class.getClassLoader());
    }
}
