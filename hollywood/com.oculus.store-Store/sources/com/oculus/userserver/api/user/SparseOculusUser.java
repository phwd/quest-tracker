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
        String repr = "SparseOculusUser: {";
        if (this.mUserId != null) {
            repr = repr + "id: " + this.mUserId + ", ";
        }
        if (this.mUserName != null) {
            repr = repr + "username: " + this.mUserName + ", ";
        }
        if (this.mPictureUri != null) {
            repr = repr + "pictureUri: " + this.mPictureUri + ", ";
        }
        return repr + "}";
    }

    public static final class Builder {
        @Nullable
        private String mPictureUri;
        @Nullable
        private Integer mUserId;
        @Nullable
        private String mUserName;

        public Builder setUserId(int userId) {
            this.mUserId = Integer.valueOf(userId);
            return this;
        }

        public Builder setUserName(String userName) {
            this.mUserName = userName;
            return this;
        }

        public Builder setPictureUri(String pictureUri) {
            this.mPictureUri = pictureUri;
            return this;
        }

        public SparseOculusUser build() {
            return new SparseOculusUser(this);
        }
    }
}
