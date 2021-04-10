package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum UserAction implements Parcelable {
    INSTALL("INSTALL"),
    LAUNCH("LAUNCH");
    
    public static final Parcelable.Creator<UserAction> CREATOR = new Parcelable.Creator<UserAction>() {
        /* class com.oculus.license.UserAction.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public UserAction createFromParcel(Parcel in) {
            return UserAction.from(in.readString());
        }

        @Override // android.os.Parcelable.Creator
        public UserAction[] newArray(int size) {
            return new UserAction[size];
        }
    };
    private final String mAction;

    private UserAction(String action) {
        this.mAction = action;
    }

    public static UserAction from(String action) {
        UserAction[] values = values();
        for (UserAction ua : values) {
            if (ua.mAction.equals(action)) {
                return ua;
            }
        }
        throw new IllegalArgumentException("Unrecognized user action: " + action);
    }

    public String getValue() {
        return this.mAction;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mAction);
    }
}
