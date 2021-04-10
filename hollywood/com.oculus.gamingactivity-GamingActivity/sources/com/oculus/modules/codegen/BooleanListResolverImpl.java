package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ResolverFactory */
class BooleanListResolverImpl extends BaseResolverImpl<List<Boolean>> {
    public BooleanListResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(List<Boolean> resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(NativeModuleParcel.convertBooleanListToJSONArray(resolution));
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
