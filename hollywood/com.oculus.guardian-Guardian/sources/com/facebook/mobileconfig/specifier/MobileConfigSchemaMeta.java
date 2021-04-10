package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigSchemaMeta {
    public final String paramHash;
    public final List<MobileConfigParamMeta> params;

    public MobileConfigSchemaMeta(String paramHash2, List<MobileConfigParamMeta> params2) {
        this.paramHash = paramHash2;
        this.params = params2;
    }
}
