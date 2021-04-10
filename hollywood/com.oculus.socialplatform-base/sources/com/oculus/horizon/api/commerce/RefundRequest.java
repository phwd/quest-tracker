package com.oculus.horizon.api.commerce;

import com.google.common.collect.ImmutableMap;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class RefundRequest extends ApiRequest<RefundResponse> {
    public final String comment;
    public final long itemActivitySeconds;
    public final String itemId;
    public final long libraryCreationTimestamp;
    public final String pin;
    public final String reason;

    public RefundRequest(String str, long j, long j2, String str2, String str3, String str4) {
        this.itemId = str;
        this.itemActivitySeconds = j;
        this.libraryCreationTimestamp = j2;
        this.reason = str2;
        this.comment = str3;
        this.pin = str4;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", UUID.randomUUID().toString());
        A04.put("item_ids", this.itemId);
        A04.put("play_times", String.valueOf(this.itemActivitySeconds));
        A04.put("creation_time", String.valueOf(this.libraryCreationTimestamp));
        A04.put("reason", this.reason);
        A04.put("comment", this.comment);
        A04.put(ServiceContract.EXTRA_PIN, this.pin);
        return A04.build();
    }
}
