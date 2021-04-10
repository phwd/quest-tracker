package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.push.fbns.ipc.FbnsAIDLRequest;

/* renamed from: X.0Sr  reason: invalid class name */
public class AnonymousClass0Sr implements Parcelable.Creator<FbnsAIDLRequest> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FbnsAIDLRequest createFromParcel(Parcel parcel) {
        return new FbnsAIDLRequest(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final FbnsAIDLRequest[] newArray(int i) {
        return new FbnsAIDLRequest[i];
    }
}
