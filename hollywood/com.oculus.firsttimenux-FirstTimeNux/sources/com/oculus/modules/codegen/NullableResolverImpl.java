package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class NullableResolverImpl<T> implements Resolver<T> {
    public RCTResolveCaller mCaller;
    public int mRejectID;
    public int mResolveID;
    public Resolver<T> mTypedResolver;

    public NullableResolverImpl(Resolver<T> typedResolver, RCTResolveCaller caller, int resolveID, int rejectID) {
        this.mCaller = caller;
        this.mResolveID = resolveID;
        this.mRejectID = rejectID;
        this.mTypedResolver = typedResolver;
    }

    @Override // com.oculus.modules.codegen.Resolver
    public void resolve(T resolution) {
        if (resolution == null) {
            JSONArray promiseArgs = new JSONArray();
            promiseArgs.put(JSONObject.NULL);
            this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
            return;
        }
        this.mTypedResolver.resolve(resolution);
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
