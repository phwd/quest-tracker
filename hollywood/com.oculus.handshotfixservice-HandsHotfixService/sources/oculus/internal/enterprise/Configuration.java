package oculus.internal.enterprise;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Configuration implements Parcelable {
    public static final Parcelable.Creator<Configuration> CREATOR = new Parcelable.Creator<Configuration>() {
        /* class oculus.internal.enterprise.Configuration.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Configuration createFromParcel(Parcel in) {
            return new Configuration(in);
        }

        @Override // android.os.Parcelable.Creator
        public Configuration[] newArray(int size) {
            return new Configuration[size];
        }
    };
    private final List<String> mLauncherAppPackages;
    private final String mName;
    private final String mPin;
    private final String mSingleAppPackage;

    public Configuration(String name, String singleAppPackage, List<String> launcherAppPackages, String pin) {
        this.mName = name;
        this.mSingleAppPackage = singleAppPackage;
        this.mLauncherAppPackages = new ArrayList(launcherAppPackages);
        this.mPin = pin;
    }

    Configuration(Parcel source) {
        this.mName = source.readString();
        this.mSingleAppPackage = source.readString();
        this.mLauncherAppPackages = new ArrayList();
        source.readList(this.mLauncherAppPackages, Configuration.class.getClassLoader());
        this.mPin = source.readString();
    }

    public String getName() {
        return this.mName;
    }

    public String getSingleAppPackage() {
        return this.mSingleAppPackage;
    }

    public List<String> getLauncherAppPackages() {
        return this.mLauncherAppPackages;
    }

    public String getPin() {
        return this.mPin;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel sink, int flags) {
        sink.writeString(this.mName);
        sink.writeString(this.mSingleAppPackage);
        sink.writeList(this.mLauncherAppPackages);
        sink.writeString(this.mPin);
    }
}
