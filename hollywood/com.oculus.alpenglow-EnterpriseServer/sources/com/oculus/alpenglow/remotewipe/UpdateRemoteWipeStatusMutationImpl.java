package com.oculus.alpenglow.remotewipe;

import X.AbstractC03090bY;
import X.AnonymousClass0NK;
import X.C01790Lq;
import X.C01800Ls;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphql.query.GraphQlQueryParamSet;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import com.oculus.alpenglow.graphql.calls.HWMEntPersistedConfigMutationRequest;
import com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusMutation;

@GeneratedGraphQL
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class UpdateRemoteWipeStatusMutationImpl {

    @GeneratedGraphQL
    public static final class Builder implements UpdateRemoteWipeStatusMutation.Builder {
        public static final String REQUEST = "request";
        public static final String UPDATE_REMOTE_WIPE_STATUS = "UpdateRemoteWipeStatus";
        public boolean isRequestSet = false;
        public GraphQlQueryParamSet mParams = new GraphQlQueryParamSet();
        public C01800Ls mRequest;

        /* Return type fixed from 'X.0bY' to match base method */
        @Override // X.AnonymousClass0P9
        public final AbstractC03090bY<UpdateRemoteWipeStatusResponse> A1a() {
            Preconditions.checkArgument(this.isRequestSet);
            if (this.mRequest != null) {
                AnonymousClass0NK.A03("UpdateRemoteWipeStatusResponse", "Incorrect usage of query builder. Query should only be built once.");
                return this.mRequest;
            }
            C01790Lq r1 = new C01790Lq(UpdateRemoteWipeStatusResponseImpl.class, UpdateRemoteWipeStatusResponseImpl.TYPE_TAG, UpdateRemoteWipeStatusResponseImpl.TREE_SHAPE_HASH_FOR_TESTS, UpdateRemoteWipeStatusResponseImpl.TREE_SHAPE_HASH_FOR_TESTS);
            r1.A00(this.mParams);
            C01800Ls r0 = new C01800Ls(r1);
            this.mRequest = r0;
            return r0;
        }

        @Override // com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusMutation.BuilderForRequest
        public final UpdateRemoteWipeStatusMutation.Builder A87(HWMEntPersistedConfigMutationRequest hWMEntPersistedConfigMutationRequest) {
            this.mParams.A00("request", hWMEntPersistedConfigMutationRequest);
            boolean z = false;
            if (hWMEntPersistedConfigMutationRequest != null) {
                z = true;
            }
            this.isRequestSet = z;
            return this;
        }
    }
}
