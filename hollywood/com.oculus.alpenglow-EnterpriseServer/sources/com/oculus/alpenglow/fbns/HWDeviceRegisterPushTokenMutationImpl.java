package com.oculus.alpenglow.fbns;

import X.AbstractC03090bY;
import X.AnonymousClass0NK;
import X.C01790Lq;
import X.C01800Ls;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphql.query.GraphQlQueryParamSet;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenMutation;
import com.oculus.alpenglow.graphql.calls.HWPushTokenRegisterRequest;

@GeneratedGraphQL
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HWDeviceRegisterPushTokenMutationImpl {

    @GeneratedGraphQL
    public static final class Builder implements HWDeviceRegisterPushTokenMutation.Builder {
        public static final String HWDEVICE_REGISTER_PUSH_TOKEN = "HWDeviceRegisterPushToken";
        public static final String REQUEST = "request";
        public boolean isRequestSet = false;
        public GraphQlQueryParamSet mParams = new GraphQlQueryParamSet();
        public C01800Ls mRequest;

        /* Return type fixed from 'X.0bY' to match base method */
        @Override // X.AnonymousClass0P9
        public final AbstractC03090bY<HWDeviceRegisterPushTokenResponse> A1a() {
            Preconditions.checkArgument(this.isRequestSet);
            if (this.mRequest != null) {
                AnonymousClass0NK.A03("HWDeviceRegisterPushTokenResponse", "Incorrect usage of query builder. Query should only be built once.");
                return this.mRequest;
            }
            C01790Lq r1 = new C01790Lq(HWDeviceRegisterPushTokenResponseImpl.class, HWDeviceRegisterPushTokenResponseImpl.TYPE_TAG, HWDeviceRegisterPushTokenResponseImpl.TREE_SHAPE_HASH_FOR_TESTS, HWDeviceRegisterPushTokenResponseImpl.TREE_SHAPE_HASH_FOR_TESTS);
            r1.A00(this.mParams);
            C01800Ls r0 = new C01800Ls(r1);
            this.mRequest = r0;
            return r0;
        }

        @Override // com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenMutation.BuilderForRequest
        public final HWDeviceRegisterPushTokenMutation.Builder A86(HWPushTokenRegisterRequest hWPushTokenRegisterRequest) {
            this.mParams.A00("request", hWPushTokenRegisterRequest);
            boolean z = false;
            if (hWPushTokenRegisterRequest != null) {
                z = true;
            }
            this.isRequestSet = z;
            return this;
        }
    }
}
