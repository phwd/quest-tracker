package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.share.model.ShareOpenGraphObject;

/* renamed from: X.1jV  reason: invalid class name */
public class AnonymousClass1jV implements Parcelable.Creator<ShareOpenGraphObject> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ShareOpenGraphObject createFromParcel(Parcel parcel) {
        return new ShareOpenGraphObject(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final ShareOpenGraphObject[] newArray(int i) {
        return new ShareOpenGraphObject[i];
    }
}
