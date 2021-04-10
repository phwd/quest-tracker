package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServiceModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = ServiceModule.class.getSimpleName();

    public enum ServiceType {
        AVATAR_EDITOR,
        CONTROLLER_PAIRING,
        EXPLORE,
        FIRST_TIME_NUX,
        HAND_TRACKING_NUX,
        IAP,
        LOGIN,
        SEARCH,
        STORE,
        SYSTEM_OVERLAYS,
        SYSTEM_UTILITIES
    }

    /* access modifiers changed from: protected */
    public abstract ServiceType marshallJSConstantServiceType();

    /* access modifiers changed from: protected */
    public abstract void onAccessTokenImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("onAccessToken", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("SERVICE_TYPE", marshallJSConstantServiceType().name());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final void onAccessToken(String accessToken, int resolveID, int rejectID) {
        onAccessTokenImpl(accessToken, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
