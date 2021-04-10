package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseResolverImpl<T> implements Resolver<T> {
    public RCTResolveCaller mCaller;
    public int mRejectID;
    public int mResolveID;

    @Override // com.oculus.modules.codegen.Resolver
    public void reject(Throwable th) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", th.getMessage());
        } catch (JSONException unused) {
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        this.mCaller.invokeJSON(this.mRejectID, jSONArray);
    }

    public BaseResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        this.mCaller = rCTResolveCaller;
        this.mResolveID = i;
        this.mRejectID = i2;
    }
}
