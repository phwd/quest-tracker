package com.facebook.graphservice.serialization;

import X.C05400jG;
import com.facebook.graphservice.interfaces.TreeSerializer;
import com.facebook.graphservice.tree.TreeJNI;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ReturnsOwnership;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
@ThreadSafe
public class TreeSerializerJNI implements TreeSerializer {
    @DoNotStrip
    public final HybridData mHybridData;

    private native <T extends TreeJNI> T deserializeTreeFromByteBufferNative(ByteBuffer byteBuffer, Class<T> cls, int i) throws IOException;

    @Nullable
    @ReturnsOwnership
    private native <T extends TreeJNI> T deserializeTreeNative(String str, Class<T> cls, int i, int i2, int i3) throws IOException;

    private native int serializeTreeNative(TreeJNI treeJNI, String str, boolean z) throws IOException;

    @ReturnsOwnership
    private native ByteBuffer serializeTreeToByteBufferNative(TreeJNI treeJNI) throws IOException;

    static {
        C05400jG.A00("graphservice-jni-serialization");
    }

    @DoNotStrip
    public TreeSerializerJNI(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
