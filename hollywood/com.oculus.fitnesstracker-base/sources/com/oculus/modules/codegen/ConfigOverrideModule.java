package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ConfigOverrideModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "ConfigOverrideModule";

    /* access modifiers changed from: protected */
    public abstract void fetchGKOverridesImpl(Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchQEOverridesImpl(Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("fetchGKOverrides", "+ii"));
        arrayList.add(new Pair("fetchQEOverrides", "+ii"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void fetchGKOverrides(int i, int i2) {
        fetchGKOverridesImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void fetchQEOverrides(int i, int i2) {
        fetchQEOverridesImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public static class QEOverride extends NativeModuleParcel {
        public boolean inExperiment;
        public JSONObject params;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("inExperiment", this.inExperiment);
                jSONObject.put("params", this.params);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.inExperiment = jSONObject.optBoolean("inExperiment");
            this.params = jSONObject.optJSONObject("params");
        }

        public static final QEOverride makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QEOverride qEOverride = new QEOverride();
            qEOverride.setFromJSONObject(jSONObject);
            return qEOverride;
        }
    }
}
