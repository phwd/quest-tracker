package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;

/* renamed from: X.1J  reason: invalid class name */
public class AnonymousClass1J implements Parcelable.Creator<ResultReceiver> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ResultReceiver createFromParcel(Parcel parcel) {
        return new ResultReceiver(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ResultReceiver[] newArray(int i) {
        return new ResultReceiver[i];
    }
}
