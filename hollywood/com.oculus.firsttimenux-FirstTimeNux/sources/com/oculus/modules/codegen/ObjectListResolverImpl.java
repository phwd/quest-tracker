package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ResolverFactory */
class ObjectListResolverImpl extends BaseResolverImpl<List<JSONObject>> {
    public ObjectListResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(List<JSONObject> resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(NativeModuleParcel.convertObjectListToJSONArray(resolution));
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
