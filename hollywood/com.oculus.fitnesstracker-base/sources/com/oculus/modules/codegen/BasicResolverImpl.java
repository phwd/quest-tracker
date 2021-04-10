package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class BasicResolverImpl<T> extends BaseResolverImpl<T> {
    public BasicResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    @Override // com.oculus.modules.codegen.Resolver
    public void resolve(T t) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(t);
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
