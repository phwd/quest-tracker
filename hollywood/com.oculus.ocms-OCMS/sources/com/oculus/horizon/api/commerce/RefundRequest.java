package com.oculus.horizon.api.commerce;

import com.facebook.ipc.activity.BaseActivityConstants;
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
        return ImmutableMap.builder().put("client_mutation_id", UUID.randomUUID().toString()).put("item_ids", this.itemId).put("play_times", String.valueOf(this.itemActivitySeconds)).put("creation_time", String.valueOf(this.libraryCreationTimestamp)).put("reason", this.reason).put(BaseActivityConstants.Extras.COMMENT, this.comment).put(ServiceContract.EXTRA_PIN, this.pin).build();
    }
}
