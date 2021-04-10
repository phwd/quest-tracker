package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ResolverFactory */
class DoubleListResolverImpl extends BaseResolverImpl<List<Double>> {
    public DoubleListResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(List<Double> resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(NativeModuleParcel.convertDoubleListToJSONArray(resolution));
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
