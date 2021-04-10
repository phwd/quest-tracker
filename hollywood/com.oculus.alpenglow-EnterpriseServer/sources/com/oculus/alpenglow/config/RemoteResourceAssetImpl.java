package com.oculus.alpenglow.config;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import com.google.common.collect.ImmutableList;
import com.oculus.alpenglow.config.RemoteResourceAsset;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = RemoteResourceAssetImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class RemoteResourceAssetImpl extends AbstractC007808s implements RemoteResourceAsset, Tree {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "HWMRemoteResource";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 529449778;
    public static final int TYPE_TAG = 529449778;

    @GeneratedGraphQL
    @ModelIdentity(typeTag = Headers.TYPE_TAG)
    @ThreadSafe
    public static final class Headers extends AbstractC007808s implements RemoteResourceAsset.Headers, Tree {
        public static final int TYPE_TAG = 528030145;

        @Override // com.oculus.alpenglow.config.RemoteResourceAsset.Headers
        @ImmutableGetter
        @Nullable
        public final String getKey() {
            return A07(106079);
        }

        @Override // com.oculus.alpenglow.config.RemoteResourceAsset.Headers
        @ImmutableGetter
        @Nullable
        public final String getValue() {
            return A07(111972721);
        }

        @DoNotStrip
        public Headers(int i, @Nullable int[] iArr) {
            super(i, iArr);
        }
    }

    @Override // com.oculus.alpenglow.config.RemoteResourceAsset
    @ImmutableGetter
    public final ImmutableList<? extends RemoteResourceAsset.Headers> A3d() {
        return A04(795307910, Headers.class, Headers.TYPE_TAG);
    }

    @Override // com.oculus.alpenglow.config.RemoteResourceAsset
    @ImmutableGetter
    @Nullable
    public final String getId() {
        return A07(3355);
    }

    @Override // com.oculus.alpenglow.config.RemoteResourceAsset
    @ImmutableGetter
    @Nullable
    public final String A3E() {
        return A07(353923240);
    }

    @Override // com.oculus.alpenglow.config.RemoteResourceAsset
    @ImmutableGetter
    @Nullable
    public final String A3T() {
        return A07(1109408053);
    }

    @Override // com.oculus.alpenglow.config.RemoteResourceAsset
    @ImmutableGetter
    @Nullable
    public final String A3Z() {
        return A07(-734768633);
    }

    @DoNotStrip
    public RemoteResourceAssetImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
