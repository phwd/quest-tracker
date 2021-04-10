package com.oculus.alpenglow.fbns;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenResponse;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = HWDeviceRegisterPushTokenResponseImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HWDeviceRegisterPushTokenResponseImpl extends AbstractC007808s implements Tree, HWDeviceRegisterPushTokenResponse {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "Mutation";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 3942224532L;
    public static final int TYPE_TAG = -787417510;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = HwDeviceRegisterPushToken.TYPE_TAG)
    @ThreadSafe
    public static final class HwDeviceRegisterPushToken extends AbstractC007808s implements Tree, HWDeviceRegisterPushTokenResponse.HwDeviceRegisterPushToken {
        public static final int TYPE_TAG = -153325646;

        @Override // com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenResponse.HwDeviceRegisterPushToken
        @ImmutableGetter
        @Nullable
        public final String A4I() {
            return A07(-1045419866);
        }

        @DoNotStrip
        public HwDeviceRegisterPushToken(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.fbns.HWDeviceRegisterPushTokenResponse
    @ImmutableGetter
    @Nullable
    public final HWDeviceRegisterPushTokenResponse.HwDeviceRegisterPushToken A3h() {
        return (HWDeviceRegisterPushTokenResponse.HwDeviceRegisterPushToken) A03(1995825527, HwDeviceRegisterPushToken.class, HwDeviceRegisterPushToken.TYPE_TAG);
    }

    @DoNotStrip
    public HWDeviceRegisterPushTokenResponseImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
