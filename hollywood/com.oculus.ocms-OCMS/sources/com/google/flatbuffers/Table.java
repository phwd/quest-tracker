package com.google.flatbuffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Table {
    protected ByteBuffer bb;
    protected int bb_pos;

    public ByteBuffer getByteBuffer() {
        return this.bb;
    }

    /* access modifiers changed from: protected */
    public int __offset(int i) {
        int i2 = this.bb_pos;
        int i3 = i2 - this.bb.getInt(i2);
        if (i < this.bb.getShort(i3)) {
            return this.bb.getShort(i3 + i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int __indirect(int i) {
        return i + this.bb.getInt(i);
    }

    /* access modifiers changed from: protected */
    public String __string(int i) {
        int i2 = i + this.bb.getInt(i);
        if (this.bb.hasArray()) {
            return new String(this.bb.array(), this.bb.arrayOffset() + i2 + 4, this.bb.getInt(i2), FlatBufferBuilder.utf8charset);
        }
        ByteBuffer order = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        byte[] bArr = new byte[order.getInt(i2)];
        order.position(i2 + 4);
        order.get(bArr);
        return new String(bArr, 0, bArr.length, FlatBufferBuilder.utf8charset);
    }

    /* access modifiers changed from: protected */
    public int __vector_len(int i) {
        int i2 = i + this.bb_pos;
        return this.bb.getInt(i2 + this.bb.getInt(i2));
    }

    /* access modifiers changed from: protected */
    public int __vector(int i) {
        int i2 = i + this.bb_pos;
        return i2 + this.bb.getInt(i2) + 4;
    }

    /* access modifiers changed from: protected */
    public ByteBuffer __vector_as_bytebuffer(int i, int i2) {
        int __offset = __offset(i);
        if (__offset == 0) {
            return null;
        }
        ByteBuffer order = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int __vector = __vector(__offset);
        order.position(__vector);
        order.limit(__vector + (__vector_len(__offset) * i2));
        return order;
    }

    /* access modifiers changed from: protected */
    public Table __union(Table table, int i) {
        int i2 = i + this.bb_pos;
        table.bb_pos = i2 + this.bb.getInt(i2);
        table.bb = this.bb;
        return table;
    }

    protected static boolean __has_identifier(ByteBuffer byteBuffer, String str) {
        if (str.length() == 4) {
            for (int i = 0; i < 4; i++) {
                if (str.charAt(i) != ((char) byteBuffer.get(byteBuffer.position() + 4 + i))) {
                    return false;
                }
            }
            return true;
        }
        throw new AssertionError("FlatBuffers: file identifier must be length 4");
    }
}
