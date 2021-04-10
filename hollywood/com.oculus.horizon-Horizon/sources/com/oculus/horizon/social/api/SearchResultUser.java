package com.oculus.horizon.social.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.horizon.api.common.user.BasicUser;

public class SearchResultUser extends BasicUser implements Parcelable {
    public static final Parcelable.Creator<SearchResultUser> CREATOR = new Parcelable.Creator<SearchResultUser>() {
        /* class com.oculus.horizon.social.api.SearchResultUser.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public SearchResultUser createFromParcel(Parcel parcel) {
            return new SearchResultUser(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public SearchResultUser[] newArray(int i) {
            return new SearchResultUser[i];
        }
    };
    public String user_id;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SearchResultUser) || !((BasicUser) obj).id.contentEquals(this.id)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public SearchResultUser(Parcel parcel) {
        super(parcel);
        this.user_id = parcel.readString();
    }

    @Override // com.oculus.horizon.api.common.user.BasicUser
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.user_id);
    }
}
