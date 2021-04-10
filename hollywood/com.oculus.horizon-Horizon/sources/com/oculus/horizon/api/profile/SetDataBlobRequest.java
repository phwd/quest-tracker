package com.oculus.horizon.api.profile;

import X.C08780ya;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class SetDataBlobRequest extends ApiRequest<Void> {
    public String dataBlob;

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", UUID.randomUUID().toString());
        A01.put("data", this.dataBlob);
        return A01.build();
    }

    public SetDataBlobRequest(PublicProfile publicProfile) {
        this.dataBlob = new C08780ya().A06(publicProfile.value);
    }

    public SetDataBlobRequest(String str) {
        this.dataBlob = str;
    }
}
