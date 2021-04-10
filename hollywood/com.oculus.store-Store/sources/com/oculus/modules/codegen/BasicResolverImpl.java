package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class BasicResolverImpl<T> extends BaseResolverImpl<T> {
    public BasicResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    @Override // com.oculus.modules.codegen.Resolver
    public void resolve(T resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(resolution);
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
