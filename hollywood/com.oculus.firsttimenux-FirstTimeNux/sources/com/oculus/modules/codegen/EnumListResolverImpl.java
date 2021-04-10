package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.lang.Enum;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ResolverFactory */
class EnumListResolverImpl<T extends Enum<T>> extends BaseResolverImpl<List<T>> {
    @Override // com.oculus.modules.codegen.Resolver
    public /* bridge */ /* synthetic */ void resolve(Object obj) {
        resolve((List) ((List) obj));
    }

    public EnumListResolverImpl(RCTResolveCaller caller, int resolveID, int rejectID) {
        super(caller, resolveID, rejectID);
    }

    public void resolve(List<T> resolution) {
        JSONArray promiseArgs = new JSONArray();
        promiseArgs.put(NativeModuleParcel.convertEnumListToJSONArray(resolution));
        this.mCaller.invokeJSON(this.mResolveID, promiseArgs);
    }
}
