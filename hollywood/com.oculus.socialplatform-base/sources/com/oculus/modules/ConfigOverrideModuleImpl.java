package com.oculus.modules;

import X.AnonymousClass006;
import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.ConfigOverrideModule;
import com.oculus.modules.codegen.Resolver;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigOverrideModuleImpl extends ConfigOverrideModule {
    public static final String SYS_PROP_GK_OVERRIDE = "persist.oculus.dbg_gk_override";
    public static final String SYS_PROP_QE_OVERRIDE = "persist.oculus.dbg_qe_override";
    public static final String TAG = "ConfigOverrideModuleImpl";
    public static Context mContext;

    private String getSystemProperty(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A09("Failed to fetch system property: '", str, "'."), e);
            return null;
        }
    }

    @Override // com.oculus.modules.codegen.ConfigOverrideModule
    public void fetchGKOverridesImpl(Resolver<JSONObject> resolver) {
        resolveWithSysProp(resolver, SYS_PROP_GK_OVERRIDE);
    }

    @Override // com.oculus.modules.codegen.ConfigOverrideModule
    public void fetchQEOverridesImpl(Resolver<JSONObject> resolver) {
        resolveWithSysProp(resolver, SYS_PROP_QE_OVERRIDE);
    }

    public ConfigOverrideModuleImpl(Context context) {
        mContext = context;
    }

    private void resolveWithSysProp(Resolver<JSONObject> resolver, String str) {
        JSONObject jSONObject;
        try {
            String systemProperty = getSystemProperty(str);
            if (systemProperty == null || systemProperty.isEmpty()) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(systemProperty);
            }
            resolver.resolve(jSONObject);
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }
}
