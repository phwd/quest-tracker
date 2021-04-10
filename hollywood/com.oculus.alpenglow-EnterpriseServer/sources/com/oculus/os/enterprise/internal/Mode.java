package com.oculus.os.enterprise.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.os.enterprise.AUICapability;
import com.oculus.os.enterprise.HomeButtonBehaviour;
import java.util.Optional;

public class Mode implements Parcelable, com.oculus.os.enterprise.Mode {
    public static final Parcelable.Creator<Mode> CREATOR = new Parcelable.Creator<Mode>() {
        /* class com.oculus.os.enterprise.internal.Mode.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Mode createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String[] createStringArray = parcel.createStringArray();
            String readString2 = parcel.readString();
            Optional ofNullable = Optional.ofNullable(parcel.readString());
            String[] createStringArray2 = parcel.createStringArray();
            HomeButtonBehaviour valueOf = HomeButtonBehaviour.valueOf(parcel.readString());
            int length = createStringArray2.length;
            AUICapability[] aUICapabilityArr = new AUICapability[length];
            for (int i = 0; i < length; i++) {
                aUICapabilityArr[i] = AUICapability.valueOf(createStringArray2[i]);
            }
            return new Mode(Version.latest(), readString, createStringArray, readString2, aUICapabilityArr, valueOf, ofNullable);
        }

        @Override // android.os.Parcelable.Creator
        public Mode[] newArray(int i) {
            return new Mode[i];
        }
    };
    public final String[] applications;
    public final AUICapability[] auiCapabilities;
    public final String defaultApplication;
    public final String displayName;
    public final HomeButtonBehaviour homeButtonBehaviour;
    public final Optional<String> pin;
    public final Version version;

    public int describeContents() {
        return 0;
    }

    @Override // com.oculus.os.enterprise.Mode
    public String[] getApplications() {
        return this.applications;
    }

    @Override // com.oculus.os.enterprise.Mode
    public AUICapability[] getAuiCapabilities() {
        return this.auiCapabilities;
    }

    @Override // com.oculus.os.enterprise.Mode
    public String getDefaultApplication() {
        return this.defaultApplication;
    }

    @Override // com.oculus.os.enterprise.Mode
    public String getDisplayName() {
        return this.displayName;
    }

    @Override // com.oculus.os.enterprise.Mode
    public HomeButtonBehaviour getHomeButtonBehaviour() {
        return this.homeButtonBehaviour;
    }

    @Override // com.oculus.os.enterprise.Mode
    public Optional<String> getPin() {
        return this.pin;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayName);
        parcel.writeStringArray(this.applications);
        parcel.writeString(this.defaultApplication);
        parcel.writeString(this.pin.orElse(null));
        if (this.version.isAtLeast(Version.VERSION_1)) {
            AUICapability[] aUICapabilityArr = this.auiCapabilities;
            int length = aUICapabilityArr.length;
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = aUICapabilityArr[i2].toString();
            }
            parcel.writeStringArray(strArr);
        }
        if (this.version.isAtLeast(Version.VERSION_2)) {
            parcel.writeString(this.homeButtonBehaviour.toString());
        }
    }

    public Mode() {
        this.version = Version.latest();
        this.displayName = "Default";
        this.applications = new String[0];
        this.defaultApplication = "";
        this.auiCapabilities = new AUICapability[0];
        this.homeButtonBehaviour = HomeButtonBehaviour.UNRESTRICTED;
        this.pin = Optional.empty();
    }

    public Mode(Version version2, String str, String[] strArr, String str2, AUICapability[] aUICapabilityArr, HomeButtonBehaviour homeButtonBehaviour2, Optional<String> optional) {
        this.version = version2;
        this.displayName = str;
        this.applications = strArr;
        this.defaultApplication = str2;
        this.auiCapabilities = aUICapabilityArr;
        this.homeButtonBehaviour = homeButtonBehaviour2;
        this.pin = optional;
    }
}
