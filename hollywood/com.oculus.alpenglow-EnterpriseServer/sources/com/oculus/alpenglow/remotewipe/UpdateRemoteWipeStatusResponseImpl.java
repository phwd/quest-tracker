package com.oculus.alpenglow.remotewipe;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.oculus.alpenglow.graphql.enums.GraphQLRemoteWipeStatus;
import com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusResponse;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = UpdateRemoteWipeStatusResponseImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class UpdateRemoteWipeStatusResponseImpl extends AbstractC007808s implements Tree, UpdateRemoteWipeStatusResponse {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "Mutation";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 1417237430;
    public static final int TYPE_TAG = -825751617;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = UpdateRemoteWipeStatus.TYPE_TAG)
    @ThreadSafe
    public static final class UpdateRemoteWipeStatus extends AbstractC007808s implements Tree, UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus {
        public static final int TYPE_TAG = -1256607457;

        @GeneratedGraphQL
        @ModelIdentity(typeTag = PersistedConfig.TYPE_TAG)
        @ThreadSafe
        public static final class PersistedConfig extends AbstractC007808s implements Tree, UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus.PersistedConfig {
            public static final int TYPE_TAG = -965887321;

            @Override // com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus.PersistedConfig
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

        @Override // com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus
        @ImmutableGetter
        @Nullable
        public final UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus.PersistedConfig A4G() {
            return (UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus.PersistedConfig) A03(-58267762, PersistedConfig.class, PersistedConfig.TYPE_TAG);
        }

        @Override // com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus
        @ImmutableGetter
        @Nullable
        public final String A3V() {
            return A07(96784904);
        }

        @DoNotStrip
        public UpdateRemoteWipeStatus(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.remotewipe.UpdateRemoteWipeStatusResponse
    @ImmutableGetter
    @Nullable
    public final UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus A4n() {
        return (UpdateRemoteWipeStatusResponse.UpdateRemoteWipeStatus) A03(1643842471, UpdateRemoteWipeStatus.class, UpdateRemoteWipeStatus.TYPE_TAG);
    }

    @DoNotStrip
    public UpdateRemoteWipeStatusResponseImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
