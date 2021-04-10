package com.oculus.mediaupload.io;

import X.AnonymousClass006;
import android.content.Intent;
import android.net.Uri;
import com.oculus.common.vrshell.Constants;
import com.oculus.common.vrshell.SystemUXRoute;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FacebookGamingProfileGetTokenIntentHelper {
    public static final String FB_OAUTH_URL = new Uri.Builder().scheme("https").authority("www.facebook.com").appendPath("v3.2/dialog/oauth").query("client_id=1089784724699195&response_type=token&scope=user_friends&redirect_uri=https://yanhick.github.io/index.html").build().toString();
    public static final String OCULUS_GAMING_APP_ID = "1089784724699195";
    public static final String REDIRECT_URL = "https://yanhick.github.io/index.html";
    public static final String URI = "uri";
    public static final String UTF8_ENCODING = "UTF-8";
    public static final String WEBTASK_URI = "ovrweb://webtask?uri=";

    public static Intent A00() {
        String str;
        try {
            str = AnonymousClass006.A05(WEBTASK_URI, URLEncoder.encode(FB_OAUTH_URL, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            str = null;
        }
        Intent intent = new Intent(Constants.ACTION_LAUNCH);
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("uri", str);
        intent.putExtra("intent_data", SystemUXRoute.DEFAULT_BROWSER.path());
        return intent;
    }
}
