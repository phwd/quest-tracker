package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NullableResolverImpl<T> implements Resolver<T> {
    public RCTResolveCaller mCaller;
    public int mRejectID;
    public int mResolveID;
    public Resolver<T> mTypedResolver;

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

    @Override // com.oculus.modules.codegen.Resolver
    public void resolve(T t) {
        if (t == null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(JSONObject.NULL);
            this.mCaller.invokeJSON(this.mResolveID, jSONArray);
            return;
        }
        this.mTypedResolver.resolve(t);
    }

    public NullableResolverImpl(Resolver<T> resolver, RCTResolveCaller rCTResolveCaller, int i, int i2) {
        this.mCaller = rCTResolveCaller;
        this.mResolveID = i;
        this.mRejectID = i2;
        this.mTypedResolver = resolver;
    }
}
