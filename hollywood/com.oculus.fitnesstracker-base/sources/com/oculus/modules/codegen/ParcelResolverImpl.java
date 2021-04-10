package com.oculus.modules.codegen;

import com.oculus.modules.codegen.NativeModuleParcel;
import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class ParcelResolverImpl<T extends NativeModuleParcel> extends BaseResolverImpl<T> {
    public ParcelResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    public void resolve(T t) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(t.convertToJSONObject());
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
