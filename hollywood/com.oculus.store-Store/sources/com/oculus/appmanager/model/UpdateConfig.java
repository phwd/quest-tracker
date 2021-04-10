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
        public UpdateConfig createFromParcel(Parcel in) {
            return new UpdateConfig(in);
        }

        @Override // android.os.Parcelable.Creator
        public UpdateConfig[] newArray(int size) {
            return new UpdateConfig[size];
        }
    };
    public static final long UNKNOWN_DOWNLOAD_SIZE = -1;
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

    public UpdateConfig(String identifier2, long versionCode2, ApkUpdateInfoContract.UpdateType updateType2, String downloadUrl2, long downloadSize2) {
        this(identifier2, versionCode2, updateType2, downloadUrl2, downloadSize2, null, null, null, null, null, null, null, null, null);
    }

    public UpdateConfig(String identifier2, long versionCode2, ApkUpdateInfoContract.UpdateType updateType2, String downloadUrl2, long downloadSize2, String oculusStoreId2, String accessToken2, String downloadName2, String checksum, String checksumType, String signature2, String externalSignatures2, Map<String, String> extras2, Map<String, String> headers) {
        this.identifier = identifier2;
        this.versionCode = versionCode2;
        this.updateType = updateType2;
        this.downloadUrl = downloadUrl2;
        this.downloadSize = downloadSize2;
        this.oculusStoreId = oculusStoreId2;
        this.accessToken = accessToken2;
        this.downloadName = downloadName2;
        this.downloadChecksum = checksum;
        this.downloadChecksumType = checksumType;
        this.signature = signature2;
        this.externalSignatures = externalSignatures2;
        this.extras = extras2 == null ? new HashMap<>() : extras2;
        this.downloadHeaders = headers == null ? new HashMap<>() : headers;
    }

    public UpdateConfig(Parcel in) {
        this.identifier = in.readString();
        this.versionCode = in.readLong();
        String tempString = in.readString();
        this.oculusStoreId = TextUtils.isEmpty(tempString) ? null : tempString;
        this.updateType = ApkUpdateInfoContract.UpdateType.fromInt(in.readInt());
        this.downloadUrl = in.readString();
        this.downloadName = in.readString();
        this.downloadChecksum = in.readString();
        this.downloadChecksumType = in.readString();
        this.downloadSize = in.readLong();
        this.extras = fromBundle((Bundle) in.readParcelable(null));
        String tempString2 = in.readString();
        this.signature = TextUtils.isEmpty(tempString2) ? null : tempString2;
        String tempString3 = in.readString();
        this.externalSignatures = TextUtils.isEmpty(tempString3) ? null : tempString3;
        String tempString4 = in.readString();
        this.accessToken = TextUtils.isEmpty(tempString4) ? null : tempString4;
        this.downloadHeaders = fromBundle((Bundle) in.readParcelable(null));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.identifier);
        dest.writeLong(this.versionCode);
        dest.writeString(this.oculusStoreId);
        dest.writeInt(this.updateType.asInt());
        dest.writeString(this.downloadUrl);
        dest.writeString(this.downloadName);
        dest.writeString(this.downloadChecksum);
        dest.writeString(this.downloadChecksumType);
        dest.writeLong(this.downloadSize);
        dest.writeParcelable(toBundle(this.extras), 0);
        dest.writeString(this.signature);
        dest.writeString(this.externalSignatures);
        dest.writeString(this.accessToken);
        dest.writeParcelable(toBundle(this.downloadHeaders), 0);
    }

    public int describeContents() {
        return 0;
    }

    public static Bundle toBundle(Map<String, String> mapToBundle) {
        Bundle bundle = new Bundle();
        if (mapToBundle != null) {
            for (Map.Entry<String, String> entry : mapToBundle.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        return bundle;
    }

    public static Map<String, String> fromBundle(Bundle bundle) {
        Map<String, String> data = new HashMap<>();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                if (value instanceof String) {
                    data.put(key, (String) value);
                } else {
                    throw new ClassCastException("Extras key is not string value: " + key + " = " + value);
                }
            }
        }
        return data;
    }
}
