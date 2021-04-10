package com.google.flatbuffers;

import com.facebook.stetho.common.Utf8Charset;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

public class FlatBufferBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Charset utf8charset = Charset.forName(Utf8Charset.NAME);
    ByteBuffer bb;
    boolean force_defaults;
    int minalign;
    boolean nested;
    int num_vtables;
    int object_start;
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

    public FlatBufferBuilder(ByteBuffer byteBuffer) {
        this.minalign = 1;
        this.vtable = null;
        this.vtable_in_use = 0;
        this.nested = false;
        this.vtables = new int[16];
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        this.force_defaults = false;
        init(byteBuffer);
    }

    public FlatBufferBuilder init(ByteBuffer byteBuffer) {
        this.bb = byteBuffer;
        this.bb.clear();
        this.bb.order(ByteOrder.LITTLE_ENDIAN);
        this.minalign = 1;
        this.space = this.bb.capacity();
        this.vtable_in_use = 0;
        this.nested = false;
        this.object_start = 0;
        this.num_vtables = 0;
        this.vector_num_elems = 0;
        return this;
    }

    static ByteBuffer newByteBuffer(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        return allocate;
    }

    static ByteBuffer growByteBuffer(ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity();
        if ((-1073741824 & capacity) == 0) {
            int i = capacity << 1;
            byteBuffer.position(0);
            ByteBuffer newByteBuffer = newByteBuffer(i);
            newByteBuffer.position(i - capacity);
            newByteBuffer.put(byteBuffer);
            return newByteBuffer;
        }
        throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
    }

    public int offset() {
        return this.bb.capacity() - this.space;
    }

    public void pad(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.bb;
            int i3 = this.space - 1;
            this.space = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    public void prep(int i, int i2) {
        if (i > this.minalign) {
            this.minalign = i;
        }
        int capacity = ((((this.bb.capacity() - this.space) + i2) ^ -1) + 1) & (i - 1);
        while (this.space < capacity + i + i2) {
            int capacity2 = this.bb.capacity();
            this.bb = growByteBuffer(this.bb);
            this.space += this.bb.capacity() - capacity2;
        }
        pad(capacity);
    }

    public void putBoolean(boolean z) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 1;
        this.space = i;
        byteBuffer.put(i, z ? (byte) 1 : 0);
    }

    public void putByte(byte b) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 1;
        this.space = i;
        byteBuffer.put(i, b);
    }

    public void putShort(short s) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 2;
        this.space = i;
        byteBuffer.putShort(i, s);
    }

    public void putInt(int i) {
        ByteBuffer byteBuffer = this.bb;
        int i2 = this.space - 4;
        this.space = i2;
        byteBuffer.putInt(i2, i);
    }

    public void putLong(long j) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 8;
        this.space = i;
        byteBuffer.putLong(i, j);
    }

    public void putFloat(float f) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 4;
        this.space = i;
        byteBuffer.putFloat(i, f);
    }

    public void putDouble(double d) {
        ByteBuffer byteBuffer = this.bb;
        int i = this.space - 8;
        this.space = i;
        byteBuffer.putDouble(i, d);
    }

    public void addBoolean(boolean z) {
        prep(1, 0);
        putBoolean(z);
    }

    public void addByte(byte b) {
        prep(1, 0);
        putByte(b);
    }

    public void addShort(short s) {
        prep(2, 0);
        putShort(s);
    }

    public void addInt(int i) {
        prep(4, 0);
        putInt(i);
    }

    public void addLong(long j) {
        prep(8, 0);
        putLong(j);
    }

    public void addFloat(float f) {
        prep(4, 0);
        putFloat(f);
    }

    public void addDouble(double d) {
        prep(8, 0);
        putDouble(d);
    }

    public void addOffset(int i) {
        prep(4, 0);
        putInt((offset() - i) + 4);
    }

    public void startVector(int i, int i2, int i3) {
        notNested();
        this.vector_num_elems = i2;
        int i4 = i * i2;
        prep(4, i4);
        prep(i3, i4);
    }

    public int endVector() {
        putInt(this.vector_num_elems);
        return offset();
    }

    public int createString(String str) {
        byte[] bytes = str.getBytes(utf8charset);
        addByte((byte) 0);
        startVector(1, bytes.length, 1);
        ByteBuffer byteBuffer = this.bb;
        int length = this.space - bytes.length;
        this.space = length;
        byteBuffer.position(length);
        this.bb.put(bytes, 0, bytes.length);
        return endVector();
    }

    public int createString(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        addByte((byte) 0);
        startVector(1, remaining, 1);
        ByteBuffer byteBuffer2 = this.bb;
        int i = this.space - remaining;
        this.space = i;
        byteBuffer2.position(i);
        this.bb.put(byteBuffer);
        return endVector();
    }

    public void notNested() {
        if (this.nested) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    public void Nested(int i) {
        if (i != offset()) {
            throw new AssertionError("FlatBuffers: struct must be serialized inline.");
        }
    }

    public void startObject(int i) {
        notNested();
        int[] iArr = this.vtable;
        if (iArr == null || iArr.length < i) {
            this.vtable = new int[i];
        }
        this.vtable_in_use = i;
        Arrays.fill(this.vtable, 0, this.vtable_in_use, 0);
        this.nested = true;
        this.object_start = offset();
    }

    public void addBoolean(int i, boolean z, boolean z2) {
        if (this.force_defaults || z != z2) {
            addBoolean(z);
            slot(i);
        }
    }

    public void addByte(int i, byte b, int i2) {
        if (this.force_defaults || b != i2) {
            addByte(b);
            slot(i);
        }
    }

    public void addShort(int i, short s, int i2) {
        if (this.force_defaults || s != i2) {
            addShort(s);
            slot(i);
        }
    }

    public void addInt(int i, int i2, int i3) {
        if (this.force_defaults || i2 != i3) {
            addInt(i2);
            slot(i);
        }
    }

    public void addLong(int i, long j, long j2) {
        if (this.force_defaults || j != j2) {
            addLong(j);
            slot(i);
        }
    }

    public void addFloat(int i, float f, double d) {
        if (this.force_defaults || ((double) f) != d) {
            addFloat(f);
            slot(i);
        }
    }

    public void addDouble(int i, double d, double d2) {
        if (this.force_defaults || d != d2) {
            addDouble(d);
            slot(i);
        }
    }

    public void addOffset(int i, int i2, int i3) {
        if (this.force_defaults || i2 != i3) {
            addOffset(i2);
            slot(i);
        }
    }

    public void addStruct(int i, int i2, int i3) {
        if (i2 != i3) {
            Nested(i2);
            slot(i);
        }
    }

    public void slot(int i) {
        this.vtable[i] = offset();
    }

    public int endObject() {
        int i;
        if (this.vtable == null || !this.nested) {
            throw new AssertionError("FlatBuffers: endObject called without startObject");
        }
        addInt(0);
        int offset = offset();
        for (int i2 = this.vtable_in_use - 1; i2 >= 0; i2--) {
            int[] iArr = this.vtable;
            addShort((short) (iArr[i2] != 0 ? offset - iArr[i2] : 0));
        }
        addShort((short) (offset - this.object_start));
        addShort((short) ((this.vtable_in_use + 2) * 2));
        int i3 = 0;
        loop1:
        while (true) {
            if (i3 >= this.num_vtables) {
                i = 0;
                break;
            }
            int capacity = this.bb.capacity() - this.vtables[i3];
            int i4 = this.space;
            short s = this.bb.getShort(capacity);
            if (s == this.bb.getShort(i4)) {
                for (int i5 = 2; i5 < s; i5 += 2) {
                    if (this.bb.getShort(capacity + i5) == this.bb.getShort(i4 + i5)) {
                    }
                }
                i = this.vtables[i3];
                break loop1;
            }
            i3++;
        }
        if (i != 0) {
            this.space = this.bb.capacity() - offset;
            this.bb.putInt(this.space, i - offset);
        } else {
            int i6 = this.num_vtables;
            int[] iArr2 = this.vtables;
            if (i6 == iArr2.length) {
                this.vtables = Arrays.copyOf(iArr2, i6 * 2);
            }
            int[] iArr3 = this.vtables;
            int i7 = this.num_vtables;
            this.num_vtables = i7 + 1;
            iArr3[i7] = offset();
            ByteBuffer byteBuffer = this.bb;
            byteBuffer.putInt(byteBuffer.capacity() - offset, offset() - offset);
        }
        this.nested = false;
        return offset;
    }

    public void required(int i, int i2) {
        int capacity = this.bb.capacity() - i;
        if (!(this.bb.getShort((capacity - this.bb.getInt(capacity)) + i2) != 0)) {
            throw new AssertionError("FlatBuffers: field " + i2 + " must be set");
        }
    }

    public void finish(int i) {
        prep(this.minalign, 4);
        addOffset(i);
        this.bb.position(this.space);
    }

    public void finish(int i, String str) {
        prep(this.minalign, 8);
        if (str.length() == 4) {
            for (int i2 = 3; i2 >= 0; i2--) {
                addByte((byte) str.charAt(i2));
            }
            finish(i);
            return;
        }
        throw new AssertionError("FlatBuffers: file identifier must be length 4");
    }

    public FlatBufferBuilder forceDefaults(boolean z) {
        this.force_defaults = z;
        return this;
    }

    public ByteBuffer dataBuffer() {
        return this.bb;
    }

    @Deprecated
    private int dataStart() {
        return this.space;
    }

    public byte[] sizedByteArray(int i, int i2) {
        byte[] bArr = new byte[i2];
        this.bb.position(i);
        this.bb.get(bArr);
        return bArr;
    }

    public byte[] sizedByteArray() {
        return sizedByteArray(this.space, this.bb.capacity() - this.space);
    }
}
