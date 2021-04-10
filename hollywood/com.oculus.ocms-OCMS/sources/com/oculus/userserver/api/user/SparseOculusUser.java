package com.oculus.userserver.api.user;

import javax.annotation.Nullable;

public final class SparseOculusUser {
    @Nullable
    private final String mPictureUri;
    @Nullable
    private final Integer mUserId;
    @Nullable
    private final String mUserName;

    private SparseOculusUser(Builder builder) {
        this.mUserId = builder.mUserId;
        this.mUserName = builder.mUserName;
        this.mPictureUri = builder.mPictureUri;
    }

    @Nullable
    public Integer getUserId() {
        return this.mUserId;
    }

    @Nullable
    public String getUserName() {
        return this.mUserName;
    }

    @Nullable
    public String getPictureUri() {
        return this.mPictureUri;
    }

    public String toString() {
        String str = "SparseOculusUser: {";
        if (this.mUserId != null) {
            str = str + "id: " + this.mUserId + ", ";
        }
        if (this.mUserName != null) {
            str = str + "username: " + this.mUserName + ", ";
        }
        if (this.mPictureUri != null) {
            str = str + "pictureUri: " + this.mPictureUri + ", ";
        }
        return str + "}";
    }

    public static final class Builder {
        @Nullable
        private String mPictureUri;
        @Nullable
        private Integer mUserId;
        @Nullable
        private String mUserName;

        public Builder setUserId(int i) {
            this.mUserId = Integer.valueOf(i);
            return this;
        }

        public Builder setUserName(String str) {
            this.mUserName = str;
            return this;
        }

        public Builder setPictureUri(String str) {
            this.mPictureUri = str;
            return this;
        }

        public SparseOculusUser build() {
            return new SparseOculusUser(this);
        }
    }
}
