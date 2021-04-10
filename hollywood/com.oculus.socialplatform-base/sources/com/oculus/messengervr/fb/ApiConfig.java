package com.oculus.messengervr.fb;

import android.annotation.TargetApi;
import android.app.Application;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Initializer;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ApiConfig {
    public final String mAccessToken;
    public final String mAppId;
    public final String mAppName;
    public final String mClientToken;
    public final Application mContext;
    @Nullable
    public final String mDeviceId;
    public final String mUserId;

    public static final class Builder {
        @Nullable
        public String mAccessToken;
        @Nullable
        public String mAppId;
        @Nullable
        public String mAppName;
        @Nullable
        public String mClientToken;
        @Nullable
        public Application mContext;
        @Nullable
        public String mDeviceId;
        @Nullable
        public String mUserId;

        public ApiConfig build() {
            return new ApiConfig((Application) Objects.requireNonNull(this.mContext, "ApiConfig.Build: Must setContext."), (String) Objects.requireNonNull(this.mAppId, "ApiConfig.Build: Must setAppId."), (String) Objects.requireNonNull(this.mAppName, "ApiConfig.Build: Must setAppName."), (String) Objects.requireNonNull(this.mClientToken, "ApiConfig.Build: Must setClientToken."), (String) Objects.requireNonNull(this.mUserId, "ApiConfig.Build: Must setUserId."), (String) Objects.requireNonNull(this.mAccessToken, "ApiConfig.Build: Must setAccessToken."), this.mDeviceId);
        }

        @Initializer
        public Builder setAccessToken(String str) {
            this.mAccessToken = str;
            return this;
        }

        @Initializer
        public Builder setAppId(String str) {
            this.mAppId = str;
            return this;
        }

        @Initializer
        public Builder setAppName(String str) {
            this.mAppName = str;
            return this;
        }

        @Initializer
        public Builder setClientToken(String str) {
            this.mClientToken = str;
            return this;
        }

        @Initializer
        public Builder setContext(Application application) {
            this.mContext = application;
            return this;
        }

        @Initializer
        public Builder setDeviceId(String str) {
            this.mDeviceId = str;
            return this;
        }

        @Initializer
        public Builder setUserId(String str) {
            this.mUserId = str;
            return this;
        }

        public Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getClientToken() {
        return this.mClientToken;
    }

    public Application getContext() {
        return this.mContext;
    }

    public Optional<String> getDeviceId() {
        return Optional.ofNullable(this.mDeviceId);
    }

    public String getUserId() {
        return this.mUserId;
    }

    public ApiConfig(Application application, String str, String str2, String str3, String str4, String str5, @Nullable String str6) {
        this.mContext = application;
        this.mAppId = str;
        this.mAppName = str2;
        this.mClientToken = str3;
        this.mUserId = str4;
        this.mAccessToken = str5;
        this.mDeviceId = str6;
    }
}
