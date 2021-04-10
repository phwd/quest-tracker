package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class VoidResolverImpl extends BaseResolverImpl<Void> {
    public VoidResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(Void resolution) {
        this.mCaller.invokeJSON(this.mResolveID, new JSONArray());
    }
}
