package com.oculus.alpenglow.remotewipe;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.remotewipe.RemoteWipeStatusQueryResponse;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = RemoteWipeStatusQueryResponseImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class RemoteWipeStatusQueryResponseImpl extends AbstractC007808s implements Tree, RemoteWipeStatusQueryResponse {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "Query";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 347694595;
    public static final int TYPE_TAG = -1403263382;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = Me.TYPE_TAG)
    @ThreadSafe
    public static final class Me extends AbstractC007808s implements Tree, RemoteWipeStatusQueryResponse.Me {
        public static final int TYPE_TAG = -565023624;

        @GeneratedGraphQL
        @ModelIdentity(typeTag = HwmDevice.TYPE_TAG)
        @ThreadSafe
        public static final class HwmDevice extends AbstractC007808s implements Tree, RemoteWipeStatusQueryResponse.Me.HwmDevice {
            public static final int TYPE_TAG = 1789974456;
            @Nullable
            public CurrentDevice _cachedCurrentDevice;

            @Override // com.oculus.alpenglow.remotewipe.RemoteWipeStatusQueryResponse.Me.HwmDevice
            @ImmutableGetter
            @Nullable
            public final CurrentDevice A15() {
                CurrentDevice currentDevice = this._cachedCurrentDevice;
                if (currentDevice != null) {
                    return currentDevice;
                }
                if (getTypeName().hashCode() != -1554565315) {
                    return null;
                }
                CurrentDevice currentDevice2 = (CurrentDevice) reinterpret(CurrentDeviceImpl.class, CurrentDeviceImpl.TYPE_TAG);
                this._cachedCurrentDevice = currentDevice2;
                return currentDevice2;
            }

            @DoNotStrip
            public HwmDevice(int i, @Nullable int[] iArr) {
                super(i, iArr);
            }
        }

        @Override // com.oculus.alpenglow.remotewipe.RemoteWipeStatusQueryResponse.Me
        @ImmutableGetter
        @Nullable
        public final RemoteWipeStatusQueryResponse.Me.HwmDevice A3j() {
            return (RemoteWipeStatusQueryResponse.Me.HwmDevice) A03(-1235172233, HwmDevice.class, HwmDevice.TYPE_TAG);
        }

        @DoNotStrip
        public Me(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.remotewipe.RemoteWipeStatusQueryResponse
    @ImmutableGetter
    @Nullable
    public final RemoteWipeStatusQueryResponse.Me A40() {
        return (RemoteWipeStatusQueryResponse.Me) A03(3480, Me.class, Me.TYPE_TAG);
    }

    @DoNotStrip
    public RemoteWipeStatusQueryResponseImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
