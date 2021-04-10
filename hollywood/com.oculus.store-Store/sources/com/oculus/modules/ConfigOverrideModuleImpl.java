package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.ConfigOverrideModule;
import com.oculus.modules.codegen.Resolver;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigOverrideModuleImpl extends ConfigOverrideModule {
    private static final String SYS_PROP_GK_OVERRIDE = "persist.oculus.dbg_gk_override";
    private static final String SYS_PROP_QE_OVERRIDE = "persist.oculus.dbg_qe_override";
    private static final String TAG = ConfigOverrideModuleImpl.class.getSimpleName();
    private static Context mContext;

    public ConfigOverrideModuleImpl(Context context) {
        mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ConfigOverrideModule
    public void fetchGKOverridesImpl(Resolver<JSONObject> resolver) {
        resolveWithSysProp(resolver, SYS_PROP_GK_OVERRIDE);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ConfigOverrideModule
    public void fetchQEOverridesImpl(Resolver<JSONObject> resolver) {
        resolveWithSysProp(resolver, SYS_PROP_QE_OVERRIDE);
    }

    private void resolveWithSysProp(Resolver<JSONObject> resolver, String sysProp) {
        try {
            String overrides = getSystemProperty(sysProp);
            if (overrides == null || overrides.isEmpty()) {
                resolver.resolve(new JSONObject());
            } else {
                resolver.resolve(new JSONObject(overrides));
            }
        } catch (JSONException e) {
            resolver.reject(e);
        }
    }

    private String getSystemProperty(String key) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, key);
        } catch (Exception e) {
            Log.e(TAG, "Failed to fetch system property: '" + key + "'.", e);
            return null;
        }
    }
}
