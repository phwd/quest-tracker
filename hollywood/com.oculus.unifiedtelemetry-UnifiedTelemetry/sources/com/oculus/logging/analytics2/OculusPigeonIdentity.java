package com.oculus.logging.analytics2;

import X.C0260Yj;
import X.MG;
import X.YE;
import android.annotation.SuppressLint;
import javax.annotation.Nullable;

@SuppressLint({"AvoidSubclassingIssue"})
public class OculusPigeonIdentity extends C0260Yj {
    public static final String DEFAULT_ACCESS_TOKEN = "";
    public final String mOculusAccessToken;

    public static OculusPigeonIdentity A00(@Nullable String str, @Nullable String str2) {
        if (str == null || str2 == null) {
            return new OculusPigeonIdentity("0", "0", "");
        }
        return new OculusPigeonIdentity(str, str, str2);
    }

    @Override // X.C0260Yj
    public final void A01(YE ye, MG mg) {
        YE.A00(ye, "uid", "0");
        YE.A00(ye, "oculus_userid", this.userId);
        YE.A00(ye, "app_uid", this.userId);
        YE.A00(ye, "oculus_access_token", this.mOculusAccessToken);
    }

    public OculusPigeonIdentity(String str, String str2, String str3) {
        super(str, str2);
        this.mOculusAccessToken = str3;
    }
}
