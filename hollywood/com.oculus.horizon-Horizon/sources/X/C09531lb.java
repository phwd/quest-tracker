package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.callercontext.CallerContext;

/* renamed from: X.1lb  reason: invalid class name and case insensitive filesystem */
public class C09531lb implements Parcelable.Creator<CallerContext> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final CallerContext createFromParcel(Parcel parcel) {
        return new CallerContext(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final CallerContext[] newArray(int i) {
        return new CallerContext[i];
    }
}
