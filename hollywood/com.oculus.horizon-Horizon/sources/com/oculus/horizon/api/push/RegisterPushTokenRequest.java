package com.oculus.horizon.api.push;

import com.google.common.collect.ImmutableMap;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class RegisterPushTokenRequest extends ApiRequest<PushTokenResponse> {
    public String app_version;
    public String device_id;
    public String hmd_serial;
    public String os_version;
    public String token;

    public RegisterPushTokenRequest(String str, String str2, String str3, String str4, String str5) {
        this.token = str;
        this.device_id = str2;
        this.os_version = str3;
        this.app_version = str4;
        this.hmd_serial = str5;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", UUID.randomUUID().toString());
        A01.put("token", this.token);
        A01.put("device_id", this.device_id);
        A01.put("os_version", this.os_version);
        A01.put(AssetContract.AssetTableColumns.APP_VERSION, this.app_version);
        A01.put("hmd_serial", this.hmd_serial);
        return A01.build();
    }
}
