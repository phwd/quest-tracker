package com.oculus.horizon.mediaupload;

import X.AbstractC06600ny;
import X.AbstractC06640p5;
import X.AnonymousClass03W;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.C03030bw;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.oculus.horizon.mediaupload.MC;
import com.oculus.mediaupload.io.FacebookGamingProfileTokenManager;
import com.oculus.mediaupload.io.MediaUploaderDB;

public class MediaUploaderStoreFBGamingTokenService extends AnonymousClass03W {
    public static final String ACCESS_TOKEN_URI = "access_token_uri";
    public static final String TAG = "MediaUploaderStoreFBGamingTokenService";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, MediaUploaderStoreFBGamingTokenService mediaUploaderStoreFBGamingTokenService) {
        mediaUploaderStoreFBGamingTokenService._UL_mInjectionContext = new AnonymousClass0QC(2, r2);
    }

    @Override // X.AnonymousClass03W
    public void onHandleWork(Intent intent) {
        AnonymousClass0QC r3 = this._UL_mInjectionContext;
        handleIntent(intent, this, (AbstractC06600ny) AnonymousClass0J2.A03(1, 399, r3), (FacebookGamingProfileTokenManager) AnonymousClass0J2.A03(0, 460, r3));
    }

    public static final void _UL_injectMe(Context context, MediaUploaderStoreFBGamingTokenService mediaUploaderStoreFBGamingTokenService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), mediaUploaderStoreFBGamingTokenService);
    }

    @Override // X.AnonymousClass03W
    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    @VisibleForTesting
    public static void handleIntent(Intent intent, Context context, AbstractC06600ny r4, FacebookGamingProfileTokenManager facebookGamingProfileTokenManager) {
        String str;
        String str2;
        if (r4.A36(MC.oculus_mobile_save_to_fb_gaming_profile_horizon.enabled)) {
            if (intent == null) {
                str = TAG;
                str2 = "intent is null";
            } else {
                String stringExtra = intent.getStringExtra(ACCESS_TOKEN_URI);
                if (stringExtra == null) {
                    str = TAG;
                    str2 = "access token param is null";
                } else {
                    try {
                        Uri A00 = C03030bw.A00(stringExtra);
                        String scheme = A00.getScheme();
                        if (scheme == null) {
                            throw new FacebookGamingProfileTokenManager.TokenValidationException(FacebookGamingProfileTokenManager.TokenValidationError.WRONG_SCHEME);
                        } else if (scheme.equals(FacebookGamingProfileTokenManager.SCHEME)) {
                            String host = A00.getHost();
                            if (host == null) {
                                throw new FacebookGamingProfileTokenManager.TokenValidationException(FacebookGamingProfileTokenManager.TokenValidationError.WRONG_HOST);
                            } else if (host.equals("mediaupload")) {
                                String queryParameter = A00.getQueryParameter("access_token");
                                if (queryParameter != null) {
                                    SharedPreferences.Editor edit = context.getSharedPreferences(MediaUploaderDB.FB_GAMING_PROFILE_PREFERENCES, 0).edit();
                                    edit.putString(MediaUploaderDB.FB_GAMING_PROFILE_ACCESS_TOKEN_KEY, queryParameter);
                                    edit.apply();
                                    return;
                                }
                                throw new FacebookGamingProfileTokenManager.TokenValidationException(FacebookGamingProfileTokenManager.TokenValidationError.MISSING_ACCESS_TOKEN);
                            } else {
                                throw new FacebookGamingProfileTokenManager.TokenValidationException(FacebookGamingProfileTokenManager.TokenValidationError.WRONG_HOST);
                            }
                        } else {
                            throw new FacebookGamingProfileTokenManager.TokenValidationException(FacebookGamingProfileTokenManager.TokenValidationError.WRONG_SCHEME);
                        }
                    } catch (FacebookGamingProfileTokenManager.TokenValidationException e) {
                        AnonymousClass0NO.A0B(TAG, "access token can't be parsed", e);
                        return;
                    }
                }
            }
            AnonymousClass0NO.A08(str, str2);
        }
    }
}
