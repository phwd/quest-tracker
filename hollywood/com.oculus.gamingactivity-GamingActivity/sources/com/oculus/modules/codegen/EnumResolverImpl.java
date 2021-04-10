package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.lang.Enum;
import org.json.JSONArray;

/* compiled from: ResolverFactory */
class EnumResolverImpl<T extends Enum<T>> extends BaseResolverImpl<T> {
    public EnumResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(T resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(resolution.name());
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
