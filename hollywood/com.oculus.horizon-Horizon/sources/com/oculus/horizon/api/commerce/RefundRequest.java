package com.oculus.horizon.api.commerce;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.userserver.api.user.OculusUserBundler;
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
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", UUID.randomUUID().toString());
        A01.put("item_ids", this.itemId);
        A01.put("play_times", String.valueOf(this.itemActivitySeconds));
        A01.put(OculusUserBundler.KEY_CREATION_TIME, String.valueOf(this.libraryCreationTimestamp));
        A01.put("reason", this.reason);
        A01.put("comment", this.comment);
        A01.put("pin", this.pin);
        return A01.build();
    }
}
