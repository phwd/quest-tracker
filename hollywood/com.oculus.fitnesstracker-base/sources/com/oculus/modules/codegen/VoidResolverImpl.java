package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class VoidResolverImpl extends BaseResolverImpl<Void> {
    public VoidResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    public void resolve(Void r3) {
        this.mCaller.invokeJSON(this.mResolveID, new JSONArray());
    }
}
