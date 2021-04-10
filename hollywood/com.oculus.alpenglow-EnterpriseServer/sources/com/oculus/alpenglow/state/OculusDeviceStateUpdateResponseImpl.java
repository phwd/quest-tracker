package com.oculus.alpenglow.state;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.state.OculusDeviceStateUpdateResponse;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = OculusDeviceStateUpdateResponseImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OculusDeviceStateUpdateResponseImpl extends AbstractC007808s implements Tree, OculusDeviceStateUpdateResponse {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "Mutation";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 136846081;
    public static final int TYPE_TAG = -1020742214;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = OculusDeviceStateUpdate.TYPE_TAG)
    @ThreadSafe
    public static final class OculusDeviceStateUpdate extends AbstractC007808s implements Tree, OculusDeviceStateUpdateResponse.OculusDeviceStateUpdate {
        public static final int TYPE_TAG = -2140790533;

        @DoNotStrip
        public OculusDeviceStateUpdate(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @DoNotStrip
    public OculusDeviceStateUpdateResponseImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
