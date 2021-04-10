package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ResolverFactory */
class VoidListResolverImpl extends BaseResolverImpl<List<Void>> {
    public VoidListResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(List<Void> resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(NativeModuleParcel.convertVoidListToJSONArray(resolution));
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
