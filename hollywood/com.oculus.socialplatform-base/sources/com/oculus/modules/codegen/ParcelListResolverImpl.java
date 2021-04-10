package com.oculus.modules.codegen;

import com.oculus.modules.codegen.NativeModuleParcel;
import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

public class ParcelListResolverImpl<T extends NativeModuleParcel> extends BaseResolverImpl<List<T>> {
    public ParcelListResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    @Override // com.oculus.modules.codegen.Resolver
    public /* bridge */ /* synthetic */ void resolve(Object obj) {
        resolve((List) ((List) obj));
    }

    public void resolve(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(NativeModuleParcel.convertParcelListToJSONArray(list));
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
