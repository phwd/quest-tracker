package com.facebook.auth.viewercontext;

import X.BT;
import X.BV;
import android.os.Parcel;
import android.os.Parcelable;

public final class ViewerContext implements Parcelable {
    public static final Parcelable.Creator CREATOR = new BT();
    public final String A00;
    public volatile boolean A01;
    public final String mAuthToken;
    public final boolean mIsContextualProfileContext;
    public final boolean mIsDittoContext;
    public final boolean mIsPageContext;
    public final boolean mIsRoomGuestContext;
    public final boolean mIsTimelineViewAsContext;
    public final String mSessionCookiesString;
    public final String mSessionKey;
    public final String mSessionSecret;
    public final String mUserId;
    public final String mUsername;

    public final int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        if (r1.equals(r5.mUserId) == false) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.auth.viewercontext.ViewerContext.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        String str = this.mUserId;
        int i7 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i8 = i * 31;
        String str2 = this.mAuthToken;
        if (str2 != null) {
            i2 = str2.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        String str3 = this.mSessionCookiesString;
        if (str3 != null) {
            i3 = str3.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((((((((i9 + i3) * 31) + (this.mIsPageContext ? 1 : 0)) * 31) + (this.mIsDittoContext ? 1 : 0)) * 31) + (this.mIsTimelineViewAsContext ? 1 : 0)) * 31) + (this.mIsContextualProfileContext ? 1 : 0)) * 31;
        String str4 = this.mSessionSecret;
        if (str4 != null) {
            i4 = str4.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        String str5 = this.mSessionKey;
        if (str5 != null) {
            i5 = str5.hashCode();
        } else {
            i5 = 0;
        }
        int i12 = (i11 + i5) * 31;
        String str6 = this.mUsername;
        if (str6 != null) {
            i6 = str6.hashCode();
        } else {
            i6 = 0;
        }
        int i13 = (i12 + i6) * 31;
        String str7 = this.A00;
        if (str7 != null) {
            i7 = str7.hashCode();
        }
        return i13 + i7;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mAuthToken);
        parcel.writeString(this.mSessionCookiesString);
        parcel.writeInt(this.mIsPageContext ? 1 : 0);
        parcel.writeInt(this.mIsDittoContext ? 1 : 0);
        parcel.writeInt(this.mIsTimelineViewAsContext ? 1 : 0);
        parcel.writeInt(this.mIsContextualProfileContext ? 1 : 0);
        parcel.writeString(this.mSessionSecret);
        parcel.writeString(this.mSessionKey);
        parcel.writeString(this.mUsername);
        parcel.writeString(this.A00);
        parcel.writeInt(this.mIsRoomGuestContext ? 1 : 0);
    }

    public ViewerContext() {
        this.mUserId = null;
        this.mAuthToken = null;
        this.mSessionCookiesString = null;
        this.mIsPageContext = false;
        this.mIsDittoContext = false;
        this.mIsTimelineViewAsContext = false;
        this.mIsContextualProfileContext = false;
        this.mSessionSecret = null;
        this.mSessionKey = null;
        this.mUsername = null;
        this.A00 = null;
        this.mIsRoomGuestContext = false;
    }

    public ViewerContext(Parcel parcel) {
        boolean z = true;
        this.mUserId = parcel.readString();
        this.mAuthToken = parcel.readString();
        this.mSessionCookiesString = parcel.readString();
        this.mIsPageContext = parcel.readInt() == 1;
        this.mIsDittoContext = parcel.readInt() == 1;
        this.mIsTimelineViewAsContext = parcel.readInt() == 1;
        this.mIsContextualProfileContext = parcel.readInt() == 1;
        this.mSessionSecret = parcel.readString();
        this.mSessionKey = parcel.readString();
        this.mUsername = parcel.readString();
        this.A00 = parcel.readString();
        this.mIsRoomGuestContext = parcel.readInt() != 1 ? false : z;
    }

    public ViewerContext(BV bv) {
        String str = bv.A01;
        if (str != null) {
            this.mUserId = str;
            String str2 = bv.A00;
            if (str2 != null) {
                this.mAuthToken = str2;
                this.mSessionCookiesString = null;
                this.mIsPageContext = false;
                this.mIsDittoContext = false;
                this.mIsTimelineViewAsContext = false;
                this.mIsContextualProfileContext = false;
                this.mSessionSecret = null;
                this.mSessionKey = null;
                this.mUsername = null;
                this.A00 = null;
                this.mIsRoomGuestContext = false;
                return;
            }
            throw null;
        }
        throw null;
    }
}
