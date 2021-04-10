package com.facebook.common.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.parcels.parcellite.ParcelUtil;

public class Triplet<F, S, T> extends ParcelablePair<F, S> implements Parcelable {
    public static final Parcelable.Creator<Triplet> CREATOR = new Parcelable.Creator<Triplet>() {
        /* class com.facebook.common.util.Triplet.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Triplet createFromParcel(Parcel parcel) {
            return new Triplet(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Triplet[] newArray(int i) {
            return new Triplet[i];
        }
    };
    public final T third;

    public Triplet(F f, S s, T t) {
        super(f, s);
        this.third = t;
    }

    protected Triplet(Parcel parcel) {
        this(ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel));
    }

    public static <A, B, C> Triplet<A, B, C> create(A a, B b, C c) {
        return new Triplet<>(a, b, c);
    }

    @Override // com.facebook.common.util.ParcelablePair
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(this.third);
    }

    @Override // com.facebook.common.util.ParcelablePair
    public Object[] toArray() {
        return new Object[]{this.first, this.second, this.third};
    }
}
