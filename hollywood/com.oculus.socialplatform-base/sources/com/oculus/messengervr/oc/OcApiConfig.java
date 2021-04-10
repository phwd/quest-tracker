package com.oculus.messengervr.oc;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.http.useragent.UserAgentBuilder;
import com.oculus.util.constants.OculusConstants;
import com.oculus.util.device.DeviceUtils;
import java.util.Locale;
import java.util.Objects;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcApiConfig {
    public final String mAccessToken;
    public final String mAppId;
    @Nullable
    public final String mAppName;
    public final Application mContext;
    @Nullable
    public final PackageInfo mPackageInfo;
    public final String mUserId;

    public static final class Builder {
        @Nullable
        public String mAccessToken;
        @Nullable
        public String mAppId;
        @Nullable
        public String mAppName;
        @Nullable
        public Application mContext;
        @Nullable
        public String mUserId;

        @Deprecated
        public Builder setUserAgent(String str) {
            return this;
        }

        public OcApiConfig build() {
            return new OcApiConfig((Application) Objects.requireNonNull(this.mContext, "OcApiConfig.Build: Must setContext."), (String) Objects.requireNonNull(this.mUserId, "OcApiConfig.Build: Must setUserId."), (String) Objects.requireNonNull(this.mAccessToken, "OcApiConfig.Build: Must setAccessToken."), (String) Objects.requireNonNull(this.mAppId, "OcApiConfig.Build: Must setAppId."), this.mAppName);
        }

        public Builder setAccessToken(String str) {
            this.mAccessToken = str;
            return this;
        }

        public Builder setAppId(String str) {
            this.mAppId = str;
            return this;
        }

        public Builder setAppName(String str) {
            this.mAppName = str;
            return this;
        }

        public Builder setContext(Application application) {
            this.mContext = application;
            return this;
        }

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

    public Application getContext() {
        return this.mContext;
    }

    public String getDbsPath() {
        return this.mContext.getFilesDir().toString();
    }

    public String getDeviceId() {
        String deviceId = DeviceUtils.getDeviceId(this.mContext);
        if (deviceId == null) {
            return "";
        }
        return deviceId;
    }

    @SuppressLint({"BadMethodUse-java.util.Locale.getDefault"})
    public String getUserAgent() {
        String str;
        String str2;
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String property = System.getProperty("http.agent");
        UserAgentBuilder userAgentBuilder = new UserAgentBuilder(this.mContext);
        if (property == null) {
            property = "";
        }
        userAgentBuilder.mHttpAgent = property;
        userAgentBuilder.setPackageName(this.mContext.getPackageName());
        String str3 = this.mAppName;
        if (str3 == null) {
            str3 = OculusConstants.SOCIAL_PLATFORM_USER_AGENT_APP_NAME;
        }
        userAgentBuilder.setAppName(str3);
        PackageInfo packageInfo = this.mPackageInfo;
        if (packageInfo != null) {
            str = packageInfo.versionName;
        } else {
            str = "";
        }
        userAgentBuilder.setAppVersion(str);
        PackageInfo packageInfo2 = this.mPackageInfo;
        if (packageInfo2 != null) {
            str2 = String.valueOf(packageInfo2.versionCode);
        } else {
            str2 = "";
        }
        userAgentBuilder.setBuildVersion(str2);
        userAgentBuilder.setLocale(locale.toString());
        userAgentBuilder.setUserAgentAppVersionMap("");
        return userAgentBuilder.build();
    }

    public String getUserId() {
        return this.mUserId;
    }

    public OcApiConfig(Application application, String str, String str2, String str3, @Nullable String str4) {
        this.mContext = application;
        this.mUserId = str;
        this.mAccessToken = str2;
        this.mAppId = str3;
        this.mAppName = str4;
        PackageInfo packageInfo = null;
        try {
            PackageManager packageManager = application.getPackageManager();
            if (packageManager != null) {
                packageInfo = packageManager.getPackageInfo(application.getPackageName(), 0);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.mPackageInfo = packageInfo;
    }
}
