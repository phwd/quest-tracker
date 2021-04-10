package android.support.v4.media.session;

import X.AnonymousClass10;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

@SuppressLint({"BanParcelableUsage"})
public final class MediaSessionCompat$ResultReceiverWrapper implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper> CREATOR = new AnonymousClass10();
    public ResultReceiver A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        this.A00.writeToParcel(parcel, i);
    }

    public MediaSessionCompat$ResultReceiverWrapper(Parcel parcel) {
        this.A00 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
    }
}
