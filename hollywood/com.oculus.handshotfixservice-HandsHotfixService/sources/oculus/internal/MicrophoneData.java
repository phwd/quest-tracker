package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.nio.ByteBuffer;

public final class MicrophoneData implements Parcelable {
    public static final Parcelable.Creator<MicrophoneData> CREATOR = new Parcelable.Creator<MicrophoneData>() {
        /* class oculus.internal.MicrophoneData.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MicrophoneData createFromParcel(Parcel in) {
            return new MicrophoneData(in);
        }

        @Override // android.os.Parcelable.Creator
        public MicrophoneData[] newArray(int size) {
            return new MicrophoneData[size];
        }
    };
    private static final String TAG = MicrophoneData.class.getSimpleName();
    private byte[] data;
    private int size;

    public MicrophoneData() {
        this.data = null;
    }

    private MicrophoneData(Parcel in) {
        this.data = null;
        readFromParcel(in);
    }

    public void writeToParcel(Parcel out, int flags) {
        Log.e(TAG, "Not implemented");
    }

    public void readFromParcel(Parcel in) {
        this.size = in.readInt();
        int i = this.size;
        if (i != 0) {
            int[] tmpData = new int[i];
            in.readIntArray(tmpData);
            ByteBuffer bb = ByteBuffer.allocate(this.size * 4);
            bb.asIntBuffer().put(tmpData);
            this.data = bb.array();
        }
    }

    public int describeContents() {
        return 0;
    }

    public int getSize() {
        return this.size;
    }

    public byte[] getData() {
        return this.data;
    }
}
