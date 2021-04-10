package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ResolverFactory */
class IntListResolverImpl extends BaseResolverImpl<List<Integer>> {
    public IntListResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    public void resolve(List<Integer> list) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(NativeModuleParcel.convertIntListToJSONArray(list));
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
