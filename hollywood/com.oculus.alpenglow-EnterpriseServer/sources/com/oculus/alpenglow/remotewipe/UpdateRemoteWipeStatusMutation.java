package com.oculus.alpenglow.remotewipe;

import X.AbstractC03090bY;
import X.AnonymousClass0P9;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.graphql.calls.HWMEntPersistedConfigMutationRequest;

@GeneratedGraphQL
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class UpdateRemoteWipeStatusMutation {

    @GeneratedGraphQL
    public interface Builder extends BuilderForRequest, AnonymousClass0P9<AbstractC03090bY<UpdateRemoteWipeStatusResponse>, UpdateRemoteWipeStatusResponse> {
    }

    @GeneratedGraphQL
    public interface BuilderForRequest {
        Builder A87(HWMEntPersistedConfigMutationRequest hWMEntPersistedConfigMutationRequest);
    }
}
