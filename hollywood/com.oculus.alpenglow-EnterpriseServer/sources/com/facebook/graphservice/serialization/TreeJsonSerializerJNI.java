package com.facebook.graphservice.serialization;

import X.C05400jG;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.interfaces.TreeJsonSerializer;
import com.facebook.graphservice.tree.TreeJNI;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.IOException;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
@ThreadSafe
public class TreeJsonSerializerJNI implements TreeJsonSerializer {
    @DoNotStrip
    public final HybridData mHybridData;

    private native <T extends TreeJNI> T deserializeFromJsonNative(String str, long j, Class<T> cls, int i, String str2) throws IOException;

    static {
        C05400jG.A00("graphservice-jni-serialization");
    }

    @DoNotStrip
    public TreeJsonSerializerJNI(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // com.facebook.graphservice.interfaces.TreeJsonSerializer
    public <T extends Tree> T deserializeFromJson(String str, long j, Class<T> cls, int i, String str2) throws IOException {
        return deserializeFromJsonNative(str, j, cls, i, str2);
    }
}
