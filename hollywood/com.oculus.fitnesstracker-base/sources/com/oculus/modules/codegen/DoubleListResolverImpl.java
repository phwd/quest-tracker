package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONArray;

/* access modifiers changed from: package-private */
/* compiled from: ResolverFactory */
public class DoubleListResolverImpl extends BaseResolverImpl<List<Double>> {
    public DoubleListResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    public void resolve(List<Double> list) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(NativeModuleParcel.convertDoubleListToJSONArray(list));
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
