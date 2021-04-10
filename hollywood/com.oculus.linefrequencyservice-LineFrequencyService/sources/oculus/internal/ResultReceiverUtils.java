package oculus.internal;

import android.os.Parcel;
import android.os.ResultReceiver;

public class ResultReceiverUtils {
    public static ResultReceiver getCrossPackageReceiver(ResultReceiver receiver) {
        Parcel parcel = Parcel.obtain();
        receiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver receiverForSending = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return receiverForSending;
    }
}
