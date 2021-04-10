package com.oculus.defaultapps.net;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.http.core.base.ApiRequest;

@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
public class DefaultAppsConfigRequest extends ApiRequest<DefaultAppsConfigResponse> {
}
