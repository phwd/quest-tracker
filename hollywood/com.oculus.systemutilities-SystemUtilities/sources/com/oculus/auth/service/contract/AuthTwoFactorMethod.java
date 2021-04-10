package com.oculus.auth.service.contract;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class AuthTwoFactorMethod implements Parcelable {
    public static final Parcelable.Creator<AuthTwoFactorMethod> CREATOR = new Parcelable.Creator<AuthTwoFactorMethod>() {
        /* class com.oculus.auth.service.contract.AuthTwoFactorMethod.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AuthTwoFactorMethod createFromParcel(Parcel in) {
            return new AuthTwoFactorMethod(in);
        }

        @Override // android.os.Parcelable.Creator
        public AuthTwoFactorMethod[] newArray(int size) {
            return new AuthTwoFactorMethod[size];
        }
    };
    public boolean enterCodeOption;
    public String id;
    public String instructions;
    public String label;
    public String resendText;
    public boolean sendOption;

    protected AuthTwoFactorMethod(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.id = in.readString();
        this.label = in.readString();
        this.instructions = in.readString();
        if (in.readByte() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.sendOption = z;
        this.resendText = in.readString();
        this.enterCodeOption = in.readByte() == 0 ? false : z2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2 = 1;
        dest.writeString(this.id);
        dest.writeString(this.label);
        dest.writeString(this.instructions);
        if (this.sendOption) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeString(this.resendText);
        if (!this.enterCodeOption) {
            i2 = 0;
        }
        dest.writeByte((byte) i2);
    }

    public int describeContents() {
        return 0;
    }

    public static <T> ArrayList<T> unmarshallParcelableList(byte[] blob, Parcelable.Creator<T> creator) {
        if (blob == null) {
            return null;
        }
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(blob, 0, blob.length);
        parcel.setDataPosition(0);
        ArrayList parcelableList = parcel.createTypedArrayList(creator);
        parcel.recycle();
        if (parcelableList == null) {
            return null;
        }
        return parcelableList;
    }
}
