package com.oculus.horizon.api.push;

import com.google.common.collect.ImmutableMap;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleParams;
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
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", UUID.randomUUID().toString());
        A04.put("token", this.token);
        A04.put(GraphQLVRLifecycleParams.ARGUMENT_DEVICE_ID, this.device_id);
        A04.put("os_version", this.os_version);
        A04.put(AssetContract.AssetTableColumns.APP_VERSION, this.app_version);
        A04.put("hmd_serial", this.hmd_serial);
        return A04.build();
    }
}
