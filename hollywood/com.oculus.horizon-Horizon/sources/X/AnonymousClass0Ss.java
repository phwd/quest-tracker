package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.push.fbns.ipc.FbnsAIDLResult;

/* renamed from: X.0Ss  reason: invalid class name */
public class AnonymousClass0Ss implements Parcelable.Creator<FbnsAIDLResult> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FbnsAIDLResult createFromParcel(Parcel parcel) {
        return new FbnsAIDLResult(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FbnsAIDLResult[] newArray(int i) {
        return new FbnsAIDLResult[i];
    }
}
