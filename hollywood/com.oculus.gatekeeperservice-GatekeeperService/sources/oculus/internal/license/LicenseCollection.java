package oculus.internal.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LicenseCollection implements Parcelable {
    public static final Parcelable.Creator<LicenseCollection> CREATOR = new Parcelable.Creator<LicenseCollection>() {
        /* class oculus.internal.license.LicenseCollection.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public LicenseCollection createFromParcel(Parcel in) {
            return new LicenseCollection(in);
        }

        @Override // android.os.Parcelable.Creator
        public LicenseCollection[] newArray(int size) {
            return new LicenseCollection[size];
        }
    };
    private final List<byte[]> blobs;

    public LicenseCollection(List<byte[]> blobs2) {
        this.blobs = blobs2;
    }

    public LicenseCollection(Parcel in) {
        int size = in.readInt();
        this.blobs = new ArrayList(size);
        while (size > 0) {
            this.blobs.add(in.createByteArray());
            size--;
        }
    }

    public List<byte[]> getBlobs() {
        return this.blobs;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.blobs.size());
        this.blobs.stream().forEach(new Consumer(out) {
            /* class oculus.internal.license.$$Lambda$LicenseCollection$gfw1YWKx1rLq9mvQDzigOIPhgs */
            private final /* synthetic */ Parcel f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.writeByteArray((byte[]) obj);
            }
        });
    }
}
