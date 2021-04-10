package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum UserAction implements Parcelable {
    INSTALL("INSTALL"),
    LAUNCH("LAUNCH");
    
    public static final Parcelable.Creator<UserAction> CREATOR = new Parcelable.Creator<UserAction>() {
        /* class com.oculus.license.UserAction.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public UserAction createFromParcel(Parcel parcel) {
            return UserAction.from(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public UserAction[] newArray(int i) {
            return new UserAction[i];
        }
    };
    private final String mAction;

    public int describeContents() {
        return 0;
    }

    private UserAction(String str) {
        this.mAction = str;
    }

    public static UserAction from(String str) {
        UserAction[] values = values();
        for (UserAction userAction : values) {
            if (userAction.mAction.equals(str)) {
                return userAction;
            }
        }
        throw new IllegalArgumentException("Unrecognized user action: " + str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAction);
    }
}
