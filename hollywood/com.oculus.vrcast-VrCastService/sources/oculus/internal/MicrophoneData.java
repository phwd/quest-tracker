package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.nio.ByteBuffer;

public final class MicrophoneData implements Parcelable {
    public static final Parcelable.Creator<MicrophoneData> CREATOR = new Parcelable.Creator<MicrophoneData>() {
        /* class oculus.internal.MicrophoneData.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MicrophoneData createFromParcel(Parcel parcel) {
            return new MicrophoneData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MicrophoneData[] newArray(int i) {
            return new MicrophoneData[i];
        }
    };
    private static final String TAG = "MicrophoneData";
    private byte[] data;
    private int size;

    public int describeContents() {
        return 0;
    }

    public MicrophoneData() {
        this.data = null;
    }

    private MicrophoneData(Parcel parcel) {
        this.data = null;
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        Log.e(TAG, "Not implemented");
    }

    public void readFromParcel(Parcel parcel) {
        this.size = parcel.readInt();
        int i = this.size;
        if (i != 0) {
            int[] iArr = new int[i];
            parcel.readIntArray(iArr);
            ByteBuffer allocate = ByteBuffer.allocate(this.size * 4);
            allocate.asIntBuffer().put(iArr);
            this.data = allocate.array();
        }
    }
}
