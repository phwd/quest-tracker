package com.oculus.horizon.api.profile;

import X.AnonymousClass13N;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class SetDataBlobRequest extends ApiRequest<Void> {
    public String dataBlob;

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", UUID.randomUUID().toString());
        A04.put("data", this.dataBlob);
        return A04.build();
    }

    public SetDataBlobRequest(PublicProfile publicProfile) {
        this.dataBlob = new AnonymousClass13N().A06(publicProfile.value);
    }

    public SetDataBlobRequest(String str) {
        this.dataBlob = str;
    }
}
