package com.oculus.alpenglow.state;

import X.AbstractC03090bY;
import X.AnonymousClass0P9;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.graphql.calls.OculusDeviceStateInput;

@GeneratedGraphQL
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OculusDeviceStateUpdateMutation {

    @GeneratedGraphQL
    public interface Builder extends BuilderForInput, AnonymousClass0P9<AbstractC03090bY<OculusDeviceStateUpdateResponse>, OculusDeviceStateUpdateResponse> {
    }

    @GeneratedGraphQL
    public interface BuilderForInput {
        Builder A7y(OculusDeviceStateInput oculusDeviceStateInput);
    }
}
