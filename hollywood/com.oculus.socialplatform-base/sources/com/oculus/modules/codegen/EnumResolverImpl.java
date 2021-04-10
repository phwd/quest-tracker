package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.lang.Enum;
import org.json.JSONArray;

public class EnumResolverImpl<T extends Enum<T>> extends BaseResolverImpl<T> {
    public EnumResolverImpl(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        super(rCTResolveCaller, i, i2);
    }

    public void resolve(T t) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(t.name());
        this.mCaller.invokeJSON(this.mResolveID, jSONArray);
    }
}
