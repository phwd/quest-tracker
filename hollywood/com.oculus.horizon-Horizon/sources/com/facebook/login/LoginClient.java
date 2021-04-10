package com.facebook.login;

import X.AnonymousClass006;
import X.AnonymousClass1Zb;
import X.AnonymousClass1f1;
import X.AnonymousClass1f9;
import X.AnonymousClass1fD;
import X.AnonymousClass1gC;
import X.AnonymousClass1gR;
import X.AnonymousClass1gx;
import X.AnonymousClass1gy;
import X.AnonymousClass1gz;
import X.AnonymousClass1h1;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.oculus.horizon.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public final class LoginClient implements Parcelable {
    public static final Parcelable.Creator<LoginClient> CREATOR = new AnonymousClass1gx();
    public Map<String, String> A00;
    public boolean A01;
    public int A02 = -1;
    public Fragment A03;
    public Request A04;
    public AnonymousClass1h1 A05;
    public AnonymousClass1gR A06;
    public LoginMethodHandler[] A07;
    public AnonymousClass1f9 A08;

    public static class Request implements Parcelable {
        public static final Parcelable.Creator<Request> CREATOR = new AnonymousClass1gy();
        public Set<String> A00;
        public boolean A01;
        public final AnonymousClass1Zb A02;
        public final AnonymousClass1gC A03;
        public final String A04;
        public final String A05;

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            String str;
            AnonymousClass1gC r0 = this.A03;
            String str2 = null;
            if (r0 != null) {
                str = r0.name();
            } else {
                str = null;
            }
            parcel.writeString(str);
            parcel.writeStringList(new ArrayList(this.A00));
            AnonymousClass1Zb r02 = this.A02;
            if (r02 != null) {
                str2 = r02.name();
            }
            parcel.writeString(str2);
            parcel.writeString(this.A04);
            parcel.writeString(this.A05);
            parcel.writeByte(this.A01 ? (byte) 1 : 0);
        }

        public Request(Parcel parcel) {
            AnonymousClass1gC r0;
            boolean z = false;
            String readString = parcel.readString();
            AnonymousClass1Zb r2 = null;
            if (readString != null) {
                r0 = AnonymousClass1gC.valueOf(readString);
            } else {
                r0 = null;
            }
            this.A03 = r0;
            ArrayList arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.A00 = new HashSet(arrayList);
            String readString2 = parcel.readString();
            this.A02 = readString2 != null ? AnonymousClass1Zb.valueOf(readString2) : r2;
            this.A04 = parcel.readString();
            this.A05 = parcel.readString();
            this.A01 = parcel.readByte() != 0 ? true : z;
        }
    }

    public static class Result implements Parcelable {
        public static final Parcelable.Creator<Result> CREATOR = new AnonymousClass1gz();
        public Map<String, String> A00;
        public final Request A01;
        public final AccessToken A02;
        public final AnonymousClass1fD A03;
        public final String A04;
        public final String A05;

        public static Result A02(Request request, String str, String str2, String str3) {
            return new Result(request, AnonymousClass1fD.ERROR, null, TextUtils.join(": ", Utility.asListNoNulls(str, str2)), str3);
        }

        public final int describeContents() {
            return 0;
        }

        public static Result A00(Request request, AccessToken accessToken) {
            return new Result(request, AnonymousClass1fD.SUCCESS, accessToken, null, null);
        }

        public static Result A01(Request request, String str) {
            return new Result(request, AnonymousClass1fD.CANCEL, null, str, null);
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.A03.name());
            parcel.writeParcelable(this.A02, i);
            parcel.writeString(this.A05);
            parcel.writeString(this.A04);
            parcel.writeParcelable(this.A01, i);
            Utility.writeStringMapToParcel(parcel, this.A00);
        }

        public Result(Parcel parcel) {
            this.A03 = AnonymousClass1fD.valueOf(parcel.readString());
            this.A02 = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.A05 = parcel.readString();
            this.A04 = parcel.readString();
            this.A01 = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.A00 = Utility.readStringMapFromParcel(parcel);
        }

        public Result(Request request, AnonymousClass1fD r3, AccessToken accessToken, String str, String str2) {
            Validate.notNull(r3, "code");
            this.A01 = request;
            this.A02 = accessToken;
            this.A05 = str;
            this.A03 = r3;
            this.A04 = str2;
        }
    }

    public final void A05(Result result) {
        LoginMethodHandler loginMethodHandler;
        int i = this.A02;
        if (i >= 0 && (loginMethodHandler = this.A07[i]) != null) {
            A01(loginMethodHandler.A01(), result.A03.getLoggingValue(), result.A05, result.A04, loginMethodHandler.A00);
        }
        Map<String, String> map = this.A00;
        if (map != null) {
            result.A00 = map;
        }
        this.A07 = null;
        this.A02 = -1;
        this.A04 = null;
        this.A00 = null;
        AnonymousClass1h1 r0 = this.A05;
        if (r0 != null) {
            AnonymousClass1f1 r4 = r0.A00;
            r4.A00 = null;
            int i2 = -1;
            if (result.A03 == AnonymousClass1fD.CANCEL) {
                i2 = 0;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("com.facebook.LoginFragment:Result", result);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            if (r4.isAdded()) {
                r4.getActivity().setResult(i2, intent);
                r4.getActivity().finish();
            }
        }
    }

    public final int describeContents() {
        return 0;
    }

    private AnonymousClass1f9 A00() {
        AnonymousClass1f9 r2 = this.A08;
        if (r2 != null && r2.A00.equals(this.A04.A04)) {
            return r2;
        }
        AnonymousClass1f9 r22 = new AnonymousClass1f9(this.A03.getActivity(), this.A04.A04);
        this.A08 = r22;
        return r22;
    }

    private void A01(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.A04 == null) {
            AnonymousClass1f9 A002 = A00();
            Bundle A003 = AnonymousClass1f9.A00("");
            A003.putString("2_result", AnonymousClass1fD.ERROR.getLoggingValue());
            A003.putString("5_error_message", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.");
            A003.putString("3_method", str);
            AppEventsLogger.A02(A002.A01, "fb_mobile_login_method_complete", A003, true);
            return;
        }
        AnonymousClass1f9 A004 = A00();
        Bundle A005 = AnonymousClass1f9.A00(this.A04.A05);
        if (str2 != null) {
            A005.putString("2_result", str2);
        }
        if (str3 != null) {
            A005.putString("5_error_message", str3);
        }
        if (str4 != null) {
            A005.putString("4_error_code", str4);
        }
        if (map != null && !map.isEmpty()) {
            A005.putString("6_extras", new JSONObject(map).toString());
        }
        A005.putString("3_method", str);
        AppEventsLogger.A02(A004.A01, "fb_mobile_login_method_complete", A005, true);
    }

    private void A02(String str, String str2, boolean z) {
        Map map = this.A00;
        if (map == null) {
            map = new HashMap();
            this.A00 = map;
        }
        if (map.containsKey(str) && z) {
            str2 = AnonymousClass006.A07(this.A00.get(str), ",", str2);
        }
        this.A00.put(str, str2);
    }

    public static final boolean A03(LoginClient loginClient) {
        if (!loginClient.A01) {
            if (loginClient.A03.getActivity().checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
                FragmentActivity activity = loginClient.A03.getActivity();
                loginClient.A05(Result.A02(loginClient.A04, activity.getString(R.string.com_facebook_internet_permission_error_title), activity.getString(R.string.com_facebook_internet_permission_error_message), null));
                return false;
            }
            loginClient.A01 = true;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        A05(com.facebook.login.LoginClient.Result.A02(r2, "Login attempt failed.", null, null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04() {
        /*
        // Method dump skipped, instructions count: 486
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.LoginClient.A04():void");
    }

    public final void A06(Result result) {
        Result result2;
        AccessToken accessToken = result.A02;
        if (accessToken == null || AccessTokenManager.getInstance().currentAccessToken == null) {
            A05(result);
            return;
        }
        AccessToken accessToken2 = AccessTokenManager.getInstance().currentAccessToken;
        if (accessToken2 != null) {
            try {
                if (accessToken2.userId.equals(accessToken.userId)) {
                    result2 = Result.A00(this.A04, accessToken);
                    A05(result2);
                }
            } catch (Exception e) {
                A05(Result.A02(this.A04, "Caught exception", e.getMessage(), null));
                return;
            }
        }
        result2 = Result.A02(this.A04, "User logged in as different Facebook user.", null, null);
        A05(result2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.A07, i);
        parcel.writeInt(this.A02);
        parcel.writeParcelable(this.A04, i);
        Utility.writeStringMapToParcel(parcel, this.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.facebook.login.LoginMethodHandler[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.facebook.login.WebViewLoginMethodHandler */
    /* JADX WARN: Multi-variable type inference failed */
    public LoginClient(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        int length = readParcelableArray.length;
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[length];
        this.A07 = loginMethodHandlerArr;
        for (int i = 0; i < length; i++) {
            loginMethodHandlerArr[i] = readParcelableArray[i];
            WebViewLoginMethodHandler webViewLoginMethodHandler = loginMethodHandlerArr[i];
            if (((LoginMethodHandler) webViewLoginMethodHandler).A01 == null) {
                ((LoginMethodHandler) webViewLoginMethodHandler).A01 = this;
            } else {
                throw new FacebookException("Can't set LoginClient if it is already set.");
            }
        }
        this.A02 = parcel.readInt();
        this.A04 = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.A00 = Utility.readStringMapFromParcel(parcel);
    }

    public LoginClient(Fragment fragment) {
        this.A03 = fragment;
    }
}
