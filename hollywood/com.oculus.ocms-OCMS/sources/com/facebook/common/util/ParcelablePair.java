package com.facebook.common.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.facebook.common.parcels.parcellite.ParcelUtil;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Iterator;

public class ParcelablePair<F, S> extends Pair<F, S> implements Parcelable, Iterable<Object> {
    public static final Parcelable.Creator<ParcelablePair> CREATOR = new Parcelable.Creator<ParcelablePair>() {
        /* class com.facebook.common.util.ParcelablePair.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelablePair createFromParcel(Parcel parcel) {
            return new ParcelablePair(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelablePair[] newArray(int i) {
            return new ParcelablePair[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public ParcelablePair(F f, S s) {
        super(f, s);
    }

    protected ParcelablePair(Parcel parcel) {
        this(ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel));
    }

    public static <A, B> ParcelablePair<A, B> create(A a, B b) {
        return new ParcelablePair<>(a, b);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.first);
        parcel.writeValue(this.second);
    }

    public Object[] toArray() {
        return new Object[]{this.first, this.second};
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return Iterators.forArray(toArray());
    }

    public String toString() {
        return getClass().getSimpleName() + Arrays.toString(toArray());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ParcelablePair)) {
            return false;
        }
        return Arrays.equals(toArray(), ((ParcelablePair) obj).toArray());
    }

    public int hashCode() {
        return Arrays.hashCode(toArray());
    }
}
