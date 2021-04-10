package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.http.common.Image;
import com.oculus.util.annotations.UsedByGson;
import java.util.Arrays;
import java.util.List;

public class App implements Parcelable {
    public static final Parcelable.Creator<App> CREATOR = new Parcelable.Creator<App>() {
        /* class com.oculus.horizon.api.common.App.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public App createFromParcel(Parcel parcel) {
            return new App(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public App[] newArray(int i) {
            return new App[i];
        }
    };
    public String display_name;
    public String id;
    public String livestreaming_status;
    public String platform;
    public List<Image> screenshots;

    public int describeContents() {
        return 0;
    }

    @UsedByGson
    public App() {
    }

    public App(Parcel parcel) {
        this.id = parcel.readString();
        this.display_name = parcel.readString();
        this.livestreaming_status = parcel.readString();
        this.screenshots = Arrays.asList((Image[]) parcel.createTypedArray(Image.CREATOR));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.display_name);
        parcel.writeString(this.livestreaming_status);
        List<Image> list = this.screenshots;
        parcel.writeTypedArray(list != null ? (Image[]) list.toArray(new Image[list.size()]) : new Image[0], i);
    }
}
