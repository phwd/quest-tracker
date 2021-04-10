package oculus.internal.enterprise;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Configuration implements Parcelable {
    public static final Parcelable.Creator<Configuration> CREATOR = new Parcelable.Creator<Configuration>() {
        /* class oculus.internal.enterprise.Configuration.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Configuration createFromParcel(Parcel parcel) {
            return new Configuration(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Configuration[] newArray(int i) {
            return new Configuration[i];
        }
    };
    private final List<String> mLauncherAppPackages = new ArrayList();
    private final String mName;
    private final String mPin;
    private final String mSingleAppPackage;

    public int describeContents() {
        return 0;
    }

    Configuration(Parcel parcel) {
        this.mName = parcel.readString();
        this.mSingleAppPackage = parcel.readString();
        parcel.readList(this.mLauncherAppPackages, Configuration.class.getClassLoader());
        this.mPin = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mSingleAppPackage);
        parcel.writeList(this.mLauncherAppPackages);
        parcel.writeString(this.mPin);
    }
}
