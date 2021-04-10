package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigSchemaMeta {
    public final String paramHash;
    public final List<MobileConfigParamMeta> params;

    public MobileConfigSchemaMeta(String str, List<MobileConfigParamMeta> list) {
        this.paramHash = str;
        this.params = list;
    }
}
