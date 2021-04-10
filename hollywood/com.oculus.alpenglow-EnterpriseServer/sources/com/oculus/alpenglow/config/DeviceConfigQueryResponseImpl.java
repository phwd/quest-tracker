package com.oculus.alpenglow.config;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.config.DeviceConfigQueryResponse;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = DeviceConfigQueryResponseImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class DeviceConfigQueryResponseImpl extends AbstractC007808s implements Tree, DeviceConfigQueryResponse {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "Query";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 2945653107L;
    public static final int TYPE_TAG = 1339216884;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = Me.TYPE_TAG)
    @ThreadSafe
    public static final class Me extends AbstractC007808s implements Tree, DeviceConfigQueryResponse.Me {
        public static final int TYPE_TAG = -281808790;

        @GeneratedGraphQL
        @ModelIdentity(typeTag = HwmDevice.TYPE_TAG)
        @ThreadSafe
        public static final class HwmDevice extends AbstractC007808s implements Tree, DeviceConfigQueryResponse.Me.HwmDevice {
            public static final int TYPE_TAG = 110039941;
            @Nullable
            public Device _cachedDevice;

            @Override // com.oculus.alpenglow.config.DeviceConfigQueryResponse.Me.HwmDevice
            @ImmutableGetter
            @Nullable
            public final Device A16() {
                Device device = this._cachedDevice;
                if (device != null) {
                    return device;
                }
                if (getTypeName().hashCode() != -1554565315) {
                    return null;
                }
                Device device2 = (Device) reinterpret(DeviceImpl.class, DeviceImpl.TYPE_TAG);
                this._cachedDevice = device2;
                return device2;
            }

            @DoNotStrip
            public HwmDevice(int i, @Nullable int[] iArr) {
                super(i, iArr);
            }
        }

        @Override // com.oculus.alpenglow.config.DeviceConfigQueryResponse.Me
        @ImmutableGetter
        @Nullable
        public final DeviceConfigQueryResponse.Me.HwmDevice A3i() {
            return (DeviceConfigQueryResponse.Me.HwmDevice) A03(-1235172233, HwmDevice.class, HwmDevice.TYPE_TAG);
        }

        @DoNotStrip
        public Me(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.config.DeviceConfigQueryResponse
    @ImmutableGetter
    @Nullable
    public final DeviceConfigQueryResponse.Me A3z() {
        return (DeviceConfigQueryResponse.Me) A03(3480, Me.class, Me.TYPE_TAG);
    }

    @DoNotStrip
    public DeviceConfigQueryResponseImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
