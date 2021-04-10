package com.oculus.appmanager.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import java.util.HashMap;
import java.util.Map;

public class UpdateConfig implements Parcelable {
    public static final Parcelable.Creator<UpdateConfig> CREATOR = new Parcelable.Creator<UpdateConfig>() {
        /* class com.oculus.appmanager.model.UpdateConfig.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public UpdateConfig createFromParcel(Parcel parcel) {
            return new UpdateConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public UpdateConfig[] newArray(int i) {
            return new UpdateConfig[i];
        }
    };
    public final String accessToken;
    public final String downloadChecksum;
    public final String downloadChecksumType;
    public final Map<String, String> downloadHeaders;
    public final String downloadName;
    public final long downloadSize;
    public final String downloadUrl;
    public final String externalSignatures;
    public final Map<String, String> extras;
    public final String identifier;
    public final String oculusStoreId;
    public final String signature;
    public final ApkUpdateInfoContract.UpdateType updateType;
    public final long versionCode;

    public int describeContents() {
        return 0;
    }

    public UpdateConfig(Parcel parcel) {
        this.identifier = parcel.readString();
        this.versionCode = parcel.readLong();
        String readString = parcel.readString();
        this.oculusStoreId = TextUtils.isEmpty(readString) ? null : readString;
        this.updateType = ApkUpdateInfoContract.UpdateType.fromInt(parcel.readInt());
        this.downloadUrl = parcel.readString();
        this.downloadName = parcel.readString();
        this.downloadChecksum = parcel.readString();
        this.downloadChecksumType = parcel.readString();
        this.downloadSize = parcel.readLong();
        this.extras = fromBundle((Bundle) parcel.readParcelable(null));
        String readString2 = parcel.readString();
        this.signature = TextUtils.isEmpty(readString2) ? null : readString2;
        String readString3 = parcel.readString();
        this.externalSignatures = TextUtils.isEmpty(readString3) ? null : readString3;
        String readString4 = parcel.readString();
        this.accessToken = TextUtils.isEmpty(readString4) ? null : readString4;
        this.downloadHeaders = fromBundle((Bundle) parcel.readParcelable(null));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeLong(this.versionCode);
        parcel.writeString(this.oculusStoreId);
        parcel.writeInt(this.updateType.asInt());
        parcel.writeString(this.downloadUrl);
        parcel.writeString(this.downloadName);
        parcel.writeString(this.downloadChecksum);
        parcel.writeString(this.downloadChecksumType);
        parcel.writeLong(this.downloadSize);
        parcel.writeParcelable(toBundle(this.extras), 0);
        parcel.writeString(this.signature);
        parcel.writeString(this.externalSignatures);
        parcel.writeString(this.accessToken);
        parcel.writeParcelable(toBundle(this.downloadHeaders), 0);
    }

    public static Bundle toBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public static Map<String, String> fromBundle(Bundle bundle) {
        HashMap hashMap = new HashMap();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    hashMap.put(str, (String) obj);
                } else {
                    throw new ClassCastException("Extras key is not string value: " + str + " = " + obj);
                }
            }
        }
        return hashMap;
    }
}
