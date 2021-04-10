package com.oculus.horizon.api.fbconnect;

import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class FBConnectAccountResponseWithToken {
    public final FBConnectAccountWithToken facebook_account;
}
