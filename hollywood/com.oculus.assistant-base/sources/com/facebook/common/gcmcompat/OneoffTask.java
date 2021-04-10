package com.facebook.common.gcmcompat;

import X.C0827j3;
import X.C3;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class OneoffTask extends Task {
    public static final Parcelable.Creator CREATOR = new C3();
    public long A00;
    public long A01;

    @Override // com.facebook.common.gcmcompat.Task
    public final void A01(Bundle bundle) {
        super.A01(bundle);
        bundle.putLong("window_start", this.A01);
        bundle.putLong("window_end", this.A00);
    }

    @Override // com.facebook.common.gcmcompat.Task
    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.A01);
        parcel.writeLong(this.A00);
    }

    public OneoffTask(Parcel parcel) {
        super(parcel);
        this.A01 = parcel.readLong();
        this.A00 = parcel.readLong();
    }

    public OneoffTask(C0827j3 j3Var) {
        super(j3Var);
        this.A01 = j3Var.A01;
        this.A00 = j3Var.A00;
    }
}
