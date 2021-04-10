package com.facebook.graphservice.factory;

import X.C05400jG;
import androidx.annotation.VisibleForTesting;
import com.facebook.graphservice.asset.GraphServiceAsset;
import com.facebook.graphservice.serialization.TreeJsonSerializerJNI;
import com.facebook.graphservice.serialization.TreeSerializerJNI;
import com.facebook.graphservice.tree.TreeBuilderJNI;
import com.facebook.graphservice.tree.TreeJNI;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GraphQLServiceFactory {
    @Nullable
    public final HybridData mHybridData;

    public static native HybridData initHybridData(GraphServiceAsset graphServiceAsset);

    private native long legacyPersistIdForQueryNameHash(long j);

    private native <T extends TreeBuilderJNI> T newTreeBuilderFromTree(TreeJNI treeJNI, Class<T> cls, int i);

    private native <T extends TreeBuilderJNI> T newTreeBuilderFromType(String str, Class<T> cls, int i);

    private native <T extends TreeBuilderJNI> T newUpdateBuilderFromTree(TreeJNI treeJNI, Class<T> cls, int i);

    private native long ossPersistIdForQueryNameHash(long j);

    private native String queryTextForQueryNameHash(long j);

    private native String[] transientParametersForQueryNameHash(long j);

    public native TreeJsonSerializerJNI newTreeJsonSerializer();

    public native TreeSerializerJNI newTreeSerializer();

    static {
        C05400jG.A00("graphservice-jni-factory");
    }

    public String getQueryTextForQueryNameHash(long j) {
        return queryTextForQueryNameHash(j);
    }

    @VisibleForTesting
    public GraphQLServiceFactory() {
        this.mHybridData = null;
    }

    @DoNotStrip
    public GraphQLServiceFactory(GraphServiceAsset graphServiceAsset) {
        this.mHybridData = initHybridData(graphServiceAsset);
    }
}
