package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class FirstTimeNuxServiceModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = FirstTimeNuxServiceModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract String getPairingCodeImpl();

    /* access modifiers changed from: protected */
    public abstract boolean isLocalAccountModeEnabledImpl();

    /* access modifiers changed from: protected */
    public abstract String marshallJSConstantServiceType();

    /* access modifiers changed from: protected */
    public abstract void onAccessTokenImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getPairingCode", "-"));
        list.add(new Pair<>("isLocalAccountModeEnabled", "-"));
        list.add(new Pair<>("onAccessToken", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("SERVICE_TYPE", marshallJSConstantServiceType());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final Object getPairingCode() {
        return getPairingCodeImpl();
    }

    /* access modifiers changed from: protected */
    public final Object isLocalAccountModeEnabled() {
        return Boolean.valueOf(isLocalAccountModeEnabledImpl());
    }

    /* access modifiers changed from: protected */
    public final void onAccessToken(String accessToken, int resolveID, int rejectID) {
        onAccessTokenImpl(accessToken, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
