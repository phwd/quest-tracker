package com.facebook.graphservice.interfaces;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Expensive;
import com.facebook.infer.annotation.ReturnsOwnership;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.IOException;
import java.nio.ByteBuffer;

@DoNotStrip
@ThreadSafe
public interface TreeSerializer {
    @Expensive
    @Nullable
    @ReturnsOwnership
    <T extends Tree> T deserializeTree(String str, Class<T> cls, int i) throws IOException, RuntimeException;

    @Expensive
    @Nullable
    @ReturnsOwnership
    <T extends Tree> T deserializeTree(String str, Class<T> cls, int i, int i2, int i3) throws IOException;

    @Expensive
    @Nullable
    @ReturnsOwnership
    <T extends Tree> T deserializeTreeFromByteBuffer(ByteBuffer byteBuffer, Class<T> cls, int i) throws IOException;

    @Expensive
    int serializeTree(Tree tree, String str) throws IOException, RuntimeException;

    @Expensive
    int serializeTree(Tree tree, String str, boolean z) throws IOException, RuntimeException;

    @Expensive
    @ReturnsOwnership
    ByteBuffer serializeTreeToByteBuffer(Tree tree) throws IOException;
}
