package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

public class VoidResolverImpl extends BaseResolverImpl<Void> {
    public VoidResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    @Override // com.oculus.modules.codegen.Resolver
    public /* bridge */ /* synthetic */ void resolve(Object obj) {
        resolve((Void) null);
    }

    public void resolve(Void r4) {
        this.mCaller.invokeJSON(this.mResolveID, new JSONArray());
    }
}
