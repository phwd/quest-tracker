package com.oculus.os.enterprise.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.os.enterprise.ControllerMode;
import com.oculus.os.enterprise.GuardianMode;
import com.oculus.os.enterprise.HandTracking;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class Configuration implements com.oculus.os.enterprise.Configuration, Parcelable {
    public static final Parcelable.Creator<Configuration> CREATOR = new Parcelable.Creator<Configuration>() {
        /* class com.oculus.os.enterprise.internal.Configuration.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Configuration createFromParcel(Parcel parcel) {
            boolean z;
            HandTracking handTracking;
            String[] strArr;
            Date date;
            Date date2;
            GuardianMode guardianMode;
            ControllerMode controllerMode;
            Date date3;
            int i;
            Mode[] modeArr;
            String str;
            ControllerMode controllerMode2;
            boolean z2 = false;
            Mode[] modeArr2 = {new Mode() {
                /* class com.oculus.os.enterprise.internal.Configuration.AnonymousClass1.AnonymousClass1 */
            }, new Mode() {
                /* class com.oculus.os.enterprise.internal.Configuration.AnonymousClass1.AnonymousClass2 */
            }};
            Date date4 = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
            ControllerMode controllerMode3 = ControllerMode.GAZE_MODE_DISABLED;
            GuardianMode guardianMode2 = GuardianMode.ENABLED;
            Date date5 = new Date(0);
            Date date6 = new Date(0);
            String[] strArr2 = new String[0];
            HandTracking handTracking2 = HandTracking.DISABLED;
            if (parcel.dataSize() > 0) {
                Mode[] modeArr3 = (Mode[]) parcel.createTypedArray(Mode.CREATOR);
                int readInt = parcel.readInt();
                Date date7 = new Date(parcel.readLong());
                if (Version.latest().isAtLeast(Version.VERSION_10)) {
                    controllerMode2 = ControllerMode.GAZE_MODE_DISABLED;
                } else {
                    controllerMode2 = ControllerMode.valueOf(parcel.readString());
                }
                GuardianMode valueOf = GuardianMode.valueOf(parcel.readString());
                Date date8 = new Date(parcel.readLong());
                Date date9 = new Date(parcel.readLong());
                String[] createStringArray = parcel.createStringArray();
                HandTracking valueOf2 = HandTracking.valueOf(parcel.readString());
                str = parcel.readString();
                if (parcel.readInt() != 0) {
                    z2 = true;
                }
                modeArr = modeArr3;
                z = z2;
                i = readInt;
                date3 = date7;
                controllerMode = controllerMode2;
                guardianMode = valueOf;
                date2 = date8;
                date = date9;
                strArr = createStringArray;
                handTracking = valueOf2;
            } else {
                str = null;
                modeArr = modeArr2;
                date3 = date4;
                i = 0;
                z = false;
                controllerMode = controllerMode3;
                guardianMode = guardianMode2;
                date2 = date5;
                strArr = strArr2;
                handTracking = handTracking2;
                date = date6;
            }
            return new Configuration(Version.latest(), modeArr, i, date3, controllerMode, guardianMode, date2, date, strArr, handTracking, str, z);
        }

        @Override // android.os.Parcelable.Creator
        public Configuration[] newArray(int i) {
            return new Configuration[i];
        }
    };
    public final ControllerMode controllerMode;
    public final int defaultModeIndex;
    public final String[] dynamicConfig;
    public final GuardianMode guardianMode;
    public final HandTracking handTracking;
    public final Date lastFetchTime;
    public final Date licenseExpirationDate;
    public final Mode[] modes;
    @Nullable
    public final String ownerName;
    public final boolean showUnknownSources;
    public final Date timestamp;
    final Version version;

    public int describeContents() {
        return 0;
    }

    public Configuration(Version version2, Mode[] modeArr, int i, Date date, ControllerMode controllerMode2, GuardianMode guardianMode2, Date date2, Date date3, String[] strArr, HandTracking handTracking2, @Nullable String str, boolean z) {
        this.version = version2;
        this.modes = modeArr;
        this.defaultModeIndex = i;
        this.licenseExpirationDate = date;
        this.controllerMode = controllerMode2;
        this.guardianMode = guardianMode2;
        this.timestamp = date2;
        this.lastFetchTime = date3;
        this.dynamicConfig = strArr;
        this.handTracking = handTracking2;
        this.ownerName = str;
        this.showUnknownSources = z;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public Date getLastFetchTime() {
        return this.lastFetchTime;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public Date getTimestamp() {
        return this.timestamp;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public Mode[] getModes() {
        return this.modes;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public int getDefaultModeIndex() {
        return this.defaultModeIndex;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public Date getLicenseExpirationDate() {
        return this.licenseExpirationDate;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public ControllerMode getControllerMode() {
        return this.controllerMode;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public GuardianMode getGuardianMode() {
        return this.guardianMode;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public String[] getDynamicConfig() {
        return this.dynamicConfig;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public HandTracking getHandTracking() {
        return this.handTracking;
    }

    @Override // com.oculus.os.enterprise.Configuration
    @Nullable
    public String getOwnerName() {
        return this.ownerName;
    }

    @Override // com.oculus.os.enterprise.Configuration
    public boolean getShowUnknownSources() {
        return this.showUnknownSources;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.modes, 0);
        parcel.writeInt(this.defaultModeIndex);
        parcel.writeLong(this.licenseExpirationDate.getTime());
        if (this.version.isAtLeast(Version.VERSION_3)) {
            if (!this.version.isAtLeast(Version.VERSION_10)) {
                parcel.writeString(ControllerMode.GAZE_MODE_DISABLED.toString());
            }
            parcel.writeString(this.guardianMode.toString());
        }
        if (this.version.isAtLeast(Version.VERSION_4)) {
            parcel.writeLong(this.timestamp.getTime());
        }
        if (this.version.isAtLeast(Version.VERSION_5)) {
            parcel.writeLong(this.lastFetchTime.getTime());
        }
        if (this.version.isAtLeast(Version.VERSION_6)) {
            parcel.writeStringArray(this.dynamicConfig);
        }
        if (this.version.isAtLeast(Version.VERSION_7)) {
            parcel.writeString(this.handTracking.toString());
        }
        if (this.version.isAtLeast(Version.VERSION_8)) {
            parcel.writeString(this.ownerName);
        }
        if (this.version.isAtLeast(Version.VERSION_9)) {
            parcel.writeInt(this.showUnknownSources ? 1 : 0);
        }
    }

    @Override // com.oculus.os.enterprise.Configuration
    public String toMarshalledString() throws UnsupportedEncodingException {
        Parcel obtain = Parcel.obtain();
        try {
            writeToParcel(obtain, 0);
            return new String(obtain.marshall(), StandardCharsets.ISO_8859_1);
        } finally {
            obtain.recycle();
        }
    }

    public static Configuration fromMarshalledString(String str) {
        Parcel obtain = Parcel.obtain();
        try {
            byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
            obtain.unmarshall(bytes, 0, bytes.length);
            obtain.setDataPosition(0);
            return CREATOR.createFromParcel(obtain);
        } finally {
            obtain.recycle();
        }
    }
}
