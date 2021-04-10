package com.facebook.flatbuffers;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Extension {
    public final ByteBuffer contentBuffer;
    public final int length;
    private boolean mIsDeleted;
    public final int offset;
    public final int origin;
    public final int pivot;

    public static Extension create(ByteBuffer byteBuffer, int i) {
        return new Extension(i, i + FlatBuffer.getRootObjectPosition(byteBuffer), byteBuffer, 0, byteBuffer.capacity());
    }

    public static int writeToBuffer(Extension extension, FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.prep(8, extension.length);
        flatBufferBuilder.copyBytes(extension.contentBuffer.array(), extension.offset, extension.length);
        flatBufferBuilder.addInt(extension.length);
        flatBufferBuilder.addInt(extension.pivot);
        flatBufferBuilder.addInt(extension.origin);
        return flatBufferBuilder.offset();
    }

    public static Extension readFromBuffer(ByteBuffer byteBuffer, int i) {
        return new Extension(byteBuffer.getInt(i), byteBuffer.getInt(i + 4), byteBuffer, i + 12, byteBuffer.getInt(i + 8));
    }

    public static int getPivot(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getInt(i + 4);
    }

    @VisibleForTesting
    protected Extension(int i, int i2, ByteBuffer byteBuffer, int i3, int i4) {
        this.origin = i;
        this.pivot = i2;
        this.contentBuffer = byteBuffer;
        this.offset = i3;
        this.length = i4;
    }

    public boolean containsPosition(int i) {
        int i2 = this.origin;
        return i >= i2 && i < i2 + this.length;
    }

    public int convertToContentBufferPosition(int i) {
        return (i - this.origin) + this.offset;
    }

    public int convertToGlobalPosition(int i) {
        return (i + this.origin) - this.offset;
    }

    public int end() {
        return this.origin + this.length;
    }

    /* access modifiers changed from: package-private */
    public void markAsDeleted() {
        this.mIsDeleted = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isDeleted() {
        return this.mIsDeleted;
    }
}
