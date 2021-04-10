package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaSessionCompat$ResultReceiverWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0449Hh0();
    public ResultReceiver F;

    public MediaSessionCompat$ResultReceiverWrapper(Parcel parcel) {
        this.F = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.F.writeToParcel(parcel, i);
    }
}
