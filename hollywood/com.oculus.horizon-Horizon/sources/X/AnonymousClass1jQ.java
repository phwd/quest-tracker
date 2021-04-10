package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.share.model.SharePhoto;

/* renamed from: X.1jQ  reason: invalid class name */
public class AnonymousClass1jQ implements Parcelable.Creator<SharePhoto> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final SharePhoto createFromParcel(Parcel parcel) {
        return new SharePhoto(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final SharePhoto[] newArray(int i) {
        return new SharePhoto[i];
    }
}
