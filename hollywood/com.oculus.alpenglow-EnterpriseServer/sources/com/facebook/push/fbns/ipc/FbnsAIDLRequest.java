package com.facebook.push.fbns.ipc;

import X.AnonymousClass13c;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FbnsAIDLRequest extends FbnsAIDLResult {
    public static final Parcelable.Creator<FbnsAIDLRequest> CREATOR = new AnonymousClass13c();
    public int A00;

    public FbnsAIDLRequest(Parcel parcel) {
        super(parcel);
        this.A00 = parcel.readInt();
    }

    @Override // com.facebook.push.fbns.ipc.FbnsAIDLResult
    public final void A00(Parcel parcel, int i) {
        super.A00(parcel, i);
        parcel.writeInt(this.A00);
    }
}
