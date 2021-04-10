package com.oculus.alpenglow.state;

import X.AbstractC03090bY;
import X.AnonymousClass0NK;
import X.C01790Lq;
import X.C01800Ls;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphql.query.GraphQlQueryParamSet;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import com.oculus.alpenglow.graphql.calls.OculusDeviceStateInput;
import com.oculus.alpenglow.state.OculusDeviceStateUpdateMutation;

@GeneratedGraphQL
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OculusDeviceStateUpdateMutationImpl {

    @GeneratedGraphQL
    public static final class Builder implements OculusDeviceStateUpdateMutation.Builder {
        public static final String INPUT = "input";
        public static final String OCULUS_DEVICE_STATE_UPDATE = "OculusDeviceStateUpdate";
        public boolean isInputSet = false;
        public GraphQlQueryParamSet mParams = new GraphQlQueryParamSet();
        public C01800Ls mRequest;

        /* Return type fixed from 'X.0bY' to match base method */
        @Override // X.AnonymousClass0P9
        public final AbstractC03090bY<OculusDeviceStateUpdateResponse> A1a() {
            Preconditions.checkArgument(this.isInputSet);
            if (this.mRequest != null) {
                AnonymousClass0NK.A03("OculusDeviceStateUpdateResponse", "Incorrect usage of query builder. Query should only be built once.");
                return this.mRequest;
            }
            C01790Lq r1 = new C01790Lq(OculusDeviceStateUpdateResponseImpl.class, OculusDeviceStateUpdateResponseImpl.TYPE_TAG, OculusDeviceStateUpdateResponseImpl.TREE_SHAPE_HASH_FOR_TESTS, OculusDeviceStateUpdateResponseImpl.TREE_SHAPE_HASH_FOR_TESTS);
            r1.A00(this.mParams);
            C01800Ls r0 = new C01800Ls(r1);
            this.mRequest = r0;
            return r0;
        }

        @Override // com.oculus.alpenglow.state.OculusDeviceStateUpdateMutation.BuilderForInput
        public final OculusDeviceStateUpdateMutation.Builder A7y(OculusDeviceStateInput oculusDeviceStateInput) {
            this.mParams.A00(INPUT, oculusDeviceStateInput);
            boolean z = false;
            if (oculusDeviceStateInput != null) {
                z = true;
            }
            this.isInputSet = z;
            return this;
        }
    }
}
