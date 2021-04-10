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
        public LicenseCollection createFromParcel(Parcel parcel) {
            return new LicenseCollection(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LicenseCollection[] newArray(int i) {
            return new LicenseCollection[i];
        }
    };
    private final List<byte[]> blobs;

    public int describeContents() {
        return 0;
    }

    public LicenseCollection(Parcel parcel) {
        int readInt = parcel.readInt();
        this.blobs = new ArrayList(readInt);
        while (readInt > 0) {
            this.blobs.add(parcel.createByteArray());
            readInt--;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.blobs.size());
        this.blobs.stream().forEach(new Consumer(parcel) {
            /* class oculus.internal.license.$$Lambda$LicenseCollection$gfw1YWKx1rLq9mvQDzigOIPhgs */
            private final /* synthetic */ Parcel f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                LicenseCollection.lambda$writeToParcel$0(this.f$0, (byte[]) obj);
            }
        });
    }
}
