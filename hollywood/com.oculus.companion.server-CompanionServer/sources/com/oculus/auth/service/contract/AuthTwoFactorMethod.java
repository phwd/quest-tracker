package com.oculus.auth.service.contract;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class AuthTwoFactorMethod implements Parcelable {
    public static final Parcelable.Creator<AuthTwoFactorMethod> CREATOR = new Parcelable.Creator<AuthTwoFactorMethod>() {
        /* class com.oculus.auth.service.contract.AuthTwoFactorMethod.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public AuthTwoFactorMethod createFromParcel(Parcel parcel) {
            return new AuthTwoFactorMethod(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AuthTwoFactorMethod[] newArray(int i) {
            return new AuthTwoFactorMethod[i];
        }
    };
    public boolean enterCodeOption;
    public String id;
    public String instructions;
    public String label;
    public String resendText;
    public boolean sendOption;

    public int describeContents() {
        return 0;
    }

    protected AuthTwoFactorMethod(Parcel parcel) {
        this.id = parcel.readString();
        this.label = parcel.readString();
        this.instructions = parcel.readString();
        boolean z = true;
        this.sendOption = parcel.readByte() != 0;
        this.resendText = parcel.readString();
        this.enterCodeOption = parcel.readByte() == 0 ? false : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.label);
        parcel.writeString(this.instructions);
        parcel.writeByte(this.sendOption ? (byte) 1 : 0);
        parcel.writeString(this.resendText);
        parcel.writeByte(this.enterCodeOption ? (byte) 1 : 0);
    }

    public static <T> ArrayList<T> unmarshallParcelableList(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        ArrayList<T> createTypedArrayList = obtain.createTypedArrayList(creator);
        obtain.recycle();
        if (createTypedArrayList == null) {
            return null;
        }
        return createTypedArrayList;
    }
}
