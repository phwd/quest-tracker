package com.oculus.modules.codegen;

import com.oculus.modules.codegen.NativeModuleParcel;
import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class ParcelListResolverImpl<T extends NativeModuleParcel> extends BaseResolverImpl<List<T>> {
    @Override // com.oculus.modules.codegen.Resolver
    public /* bridge */ /* synthetic */ void resolve(Object obj) {
        resolve((List) ((List) obj));
    }

    public ParcelListResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    public void resolve(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(NativeModuleParcel.convertParcelListToJSONArray(list));
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
