package com.oculus.userserver.api.user;

import X.AnonymousClass006;
import javax.annotation.Nullable;

public final class SparseOculusUser {
    @Nullable
    public final String mPictureUri;
    @Nullable
    public final Integer mUserId;
    @Nullable
    public final String mUserName;

    public static final class Builder {
        @Nullable
        public String mPictureUri;
        @Nullable
        public final Integer mUserId;
        @Nullable
        public String mUserName;
    }

    public final String toString() {
        Integer num = this.mUserId;
        String str = "SparseOculusUser: {";
        if (num != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("id: ");
            sb.append(num);
            sb.append(", ");
            str = sb.toString();
        }
        String str2 = this.mUserName;
        if (str2 != null) {
            str = AnonymousClass006.A08(str, "username: ", str2, ", ");
        }
        String str3 = this.mPictureUri;
        if (str3 != null) {
            str = AnonymousClass006.A08(str, "pictureUri: ", str3, ", ");
        }
        return AnonymousClass006.A05(str, "}");
    }

    public SparseOculusUser(Builder builder) {
        this.mUserId = builder.mUserId;
        this.mUserName = builder.mUserName;
        this.mPictureUri = builder.mPictureUri;
    }
}
