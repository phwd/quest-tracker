package com.google.flatbuffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class FlatBufferBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Charset utf8charset = Charset.forName("UTF-8");
    ByteBuffer bb;
    boolean force_defaults;
    int minalign;
    boolean nested;
    int num_vtables;
    int space;
    int vector_num_elems;
    int[] vtable;
    int vtable_in_use;
    int[] vtables;

    public FlatBufferBuilder(int i) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_in_use = 0;
        this.nested = false;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        this.force_defaults = false;
        i = i <= 0 ? 1 : i;
        this.space = i;
        this.bb = newByteBuffer(i);
    }

    public FlatBufferBuilder() {
        this(1024);
    }

    static ByteBuffer newByteBuffer(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        return allocate;
    }
}
