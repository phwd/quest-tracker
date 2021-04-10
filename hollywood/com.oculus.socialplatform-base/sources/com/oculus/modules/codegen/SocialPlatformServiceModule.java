package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SocialPlatformServiceModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "SocialPlatformServiceModule";

    public enum ServiceType {
        LIVESTREAM_PANEL,
        SHARE_SHEET,
        SOCIAL,
        SOCIAL_BACK_PANEL,
        SOCIAL_DIALOGS
    }

    public abstract ServiceType marshallJSConstantServiceType();

    public abstract void onAccessTokenImpl(String str, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("onAccessToken", "+sii"));
        return arrayList;
    }

    public final String marshallJSConstants() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SERVICE_TYPE", marshallJSConstantServiceType().name());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void onAccessToken(String str, int i, int i2) {
        onAccessTokenImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
