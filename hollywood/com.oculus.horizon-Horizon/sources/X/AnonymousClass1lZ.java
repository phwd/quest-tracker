package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.callercontext.ContextChain;

/* renamed from: X.1lZ  reason: invalid class name */
public class AnonymousClass1lZ implements Parcelable.Creator<ContextChain> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ContextChain createFromParcel(Parcel parcel) {
        return new ContextChain(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ContextChain[] newArray(int i) {
        return new ContextChain[i];
    }
}
