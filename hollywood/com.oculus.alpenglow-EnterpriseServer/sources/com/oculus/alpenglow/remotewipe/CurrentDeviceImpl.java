package com.oculus.alpenglow.remotewipe;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.graphql.enums.GraphQLRemoteWipeStatus;
import com.oculus.alpenglow.remotewipe.CurrentDevice;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = CurrentDeviceImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class CurrentDeviceImpl extends AbstractC007808s implements Tree, CurrentDevice {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "HWMOculusDevice";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 1623222355;
    public static final int TYPE_TAG = 1623222355;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = PersistedConfig.TYPE_TAG)
    @ThreadSafe
    public static final class PersistedConfig extends AbstractC007808s implements Tree, CurrentDevice.PersistedConfig {
        public static final int TYPE_TAG = -411299426;

        @Override // com.oculus.alpenglow.remotewipe.CurrentDevice.PersistedConfig
        @ImmutableGetter
        @Nullable
        public final GraphQLRemoteWipeStatus A4O() {
            return (GraphQLRemoteWipeStatus) A06(1489677425, GraphQLRemoteWipeStatus.UNSET_OR_UNRECOGNIZED_ENUM_VALUE);
        }

        @DoNotStrip
        public PersistedConfig(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.remotewipe.CurrentDevice
    @ImmutableGetter
    @Nullable
    public final CurrentDevice.PersistedConfig A4F() {
        return (CurrentDevice.PersistedConfig) A03(-58267762, PersistedConfig.class, PersistedConfig.TYPE_TAG);
    }

    @DoNotStrip
    public CurrentDeviceImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
