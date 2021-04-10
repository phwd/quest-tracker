package com.oculus.horizon.api.item;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.messenger.service.MessengerService;

public class GenerateDownloadUriRequest extends ApiRequest<GenerateDownloadUriResponse> {
    public final String installedVersionCode;
    public final String itemId;

    public ImmutableMap<String, ImmutableMap<String, String>> getParams() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put(MessengerService.InitParamKeys.APP_ID, this.itemId);
        String str = this.installedVersionCode;
        if (str != null) {
            builder.put("installed_version_code", str);
        }
        return ImmutableMap.A05("params", builder.build());
    }

    public GenerateDownloadUriRequest(String str) {
        this.itemId = str;
        this.installedVersionCode = null;
    }

    public GenerateDownloadUriRequest(String str, int i) {
        this.itemId = str;
        this.installedVersionCode = Integer.toString(i);
    }
}
