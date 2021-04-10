package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

public final class MediaSessionCompat$ResultReceiverWrapper implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper> CREATOR = new Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper>() {
        /* class android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaSessionCompat$ResultReceiverWrapper createFromParcel(Parcel parcel) {
            return new MediaSessionCompat$ResultReceiverWrapper(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MediaSessionCompat$ResultReceiverWrapper[] newArray(int i) {
            return new MediaSessionCompat$ResultReceiverWrapper[i];
        }
    };
    private ResultReceiver mResultReceiver;

    public int describeContents() {
        return 0;
    }

    MediaSessionCompat$ResultReceiverWrapper(Parcel parcel) {
        this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.mResultReceiver.writeToParcel(parcel, i);
    }
}
