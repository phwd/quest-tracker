package com.oculus.defaultapps.net;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
public class DefaultAppsConfigResponse {
    public final DefaultAppsConfig default_apps_nux_config;

    public static class DefaultAppsConfig {
        public final int hi_pri_apps_wait_timeout;
    }
}
