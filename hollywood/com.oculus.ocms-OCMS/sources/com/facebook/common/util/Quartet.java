package com.facebook.common.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.parcels.parcellite.ParcelUtil;

public class Quartet<T1, T2, T3, T4> extends Triplet<T1, T2, T3> implements Parcelable {
    public static final Parcelable.Creator<Quartet> CREATOR = new Parcelable.Creator<Quartet>() {
        /* class com.facebook.common.util.Quartet.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Quartet createFromParcel(Parcel parcel) {
            return new Quartet(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Quartet[] newArray(int i) {
            return new Quartet[i];
        }
    };
    public final T4 fourth;

    public Quartet(T1 t1, T2 t2, T3 t3, T4 t4) {
        super(t1, t2, t3);
        this.fourth = t4;
    }

    protected Quartet(Parcel parcel) {
        this(ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel));
    }

    public static <A, B, C, D> Quartet<A, B, C, D> create(A a, B b, C c, D d) {
        return new Quartet<>(a, b, c, d);
    }

    @Override // com.facebook.common.util.ParcelablePair, com.facebook.common.util.Triplet
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(this.fourth);
    }

    @Override // com.facebook.common.util.ParcelablePair, com.facebook.common.util.Triplet
    public Object[] toArray() {
        return new Object[]{this.first, this.second, this.third, this.fourth};
    }
}
