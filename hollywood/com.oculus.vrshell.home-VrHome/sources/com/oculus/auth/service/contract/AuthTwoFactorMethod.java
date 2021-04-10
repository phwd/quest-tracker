package com.oculus.auth.service.contract;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

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
    @Nullable
    public String resendText;
    public boolean sendOption;

    public AuthTwoFactorMethod(String id2, String label2, String instructions2, boolean sendOption2, String resend_text, boolean enterCodeOption2) {
        this.id = id2;
        this.label = label2;
        this.instructions = instructions2;
        this.sendOption = sendOption2;
        this.resendText = resend_text;
        this.enterCodeOption = enterCodeOption2;
    }

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

    public static byte[] marshallParcelableList(List<? extends Parcelable> parcelableList) {
        if (parcelableList == null || parcelableList.isEmpty()) {
            return new byte[0];
        }
        Parcel parcel = Parcel.obtain();
        parcel.writeTypedList(parcelableList);
        byte[] marshall = parcel.marshall();
        parcel.recycle();
        return marshall;
    }

    @Nullable
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
