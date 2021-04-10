package com.oculus.horizon.api.profile;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class SetDataBlobRequest extends ApiRequest<Void> {
    public String dataBlob;

    public SetDataBlobRequest(PublicProfile publicProfile) {
        this.dataBlob = new Gson().toJson(publicProfile.value);
    }

    public SetDataBlobRequest(String str) {
        this.dataBlob = str;
    }

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("client_mutation_id", UUID.randomUUID().toString()).put("data", this.dataBlob).build();
    }
}
