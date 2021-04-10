package com.oculus.alpenglow.config;

import X.AbstractC007808s;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.redex.annotations.ImmutableGetter;
import com.facebook.redex.annotations.ModelIdentity;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ModelIdentity(typeTag = LocalAppImpl.TYPE_TAG)
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class LocalAppImpl extends AbstractC007808s implements Tree, LocalApp {
    public static final String FRAGMENT_TYPENAME_FOR_TESTS = "HWMLocalApp";
    public static final long TREE_SHAPE_HASH_FOR_TESTS = 403028915;
    public static final int TYPE_TAG = 403028915;

    @Override // com.oculus.alpenglow.config.LocalApp
    @ImmutableGetter
    @Nullable
    public final String getId() {
        return A07(3355);
    }

    @Override // com.oculus.alpenglow.config.LocalApp
    @ImmutableGetter
    @Nullable
    public final String A3r() {
        return A07(-1228769423);
    }

    @DoNotStrip
    public LocalAppImpl(int i, @Nullable int[] iArr) {
        super(i, iArr);
    }
}
