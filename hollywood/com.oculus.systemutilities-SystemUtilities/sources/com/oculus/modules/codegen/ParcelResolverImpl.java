package com.oculus.modules.codegen;

import com.oculus.modules.codegen.NativeModuleParcel;
import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class ParcelResolverImpl<T extends NativeModuleParcel> extends BaseResolverImpl<T> {
    public ParcelResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(T resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(resolution.convertToJSONObject());
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
