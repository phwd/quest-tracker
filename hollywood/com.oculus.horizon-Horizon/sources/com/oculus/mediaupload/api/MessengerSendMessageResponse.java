package com.oculus.mediaupload.api;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
public class MessengerSendMessageResponse {
    public final String id;
}
