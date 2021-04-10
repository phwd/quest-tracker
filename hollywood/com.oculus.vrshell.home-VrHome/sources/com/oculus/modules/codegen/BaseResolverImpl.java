package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ResolverFactory */
abstract class BaseResolverImpl<T> implements Resolver<T> {
    public RCTResolveCaller mCaller;
    public int mRejectID;
    public int mResolveID;

    public BaseResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        this.mCaller = caller;
        this.mResolveID = resolveID;
        this.mRejectID = rejectID;
    }

    @Override // com.oculus.modules.codegen.Resolver
    public void reject(Throwable error) {
        JSONObject promiseObject = new JSONObject();
        try {
            promiseObject.put("message", error.getMessage());
        } catch (JSONException e) {
        }
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(promiseObject);
        this.mCaller.invokeJSON(this.mRejectID, promiseArgs);
    }
}
