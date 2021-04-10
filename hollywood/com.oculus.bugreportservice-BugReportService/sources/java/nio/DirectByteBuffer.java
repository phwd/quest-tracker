package java.nio;

import dalvik.system.VMRuntime;
import java.io.FileDescriptor;
import libcore.io.Memory;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

public class DirectByteBuffer extends MappedByteBuffer implements DirectBuffer {
    final Cleaner cleaner;
    final MemoryRef memoryRef;

    @Override // java.nio.ByteBuffer
    public final boolean isDirect() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public static final class MemoryRef {
        long allocatedAddress;
        byte[] buffer;
        boolean isAccessible;
        boolean isFreed;
        final int offset;
        final Object originalBufferObject;

        MemoryRef(int i) {
            VMRuntime runtime = VMRuntime.getRuntime();
            this.buffer = (byte[]) runtime.newNonMovableArray(Byte.TYPE, i + 7);
            this.allocatedAddress = runtime.addressOf(this.buffer);
            long j = this.allocatedAddress;
            this.offset = (int) (((7 + j) & -8) - j);
            this.isAccessible = true;
            this.isFreed = false;
            this.originalBufferObject = null;
        }

        MemoryRef(long j, Object obj) {
            this.buffer = null;
            this.allocatedAddress = j;
            this.offset = 0;
            this.originalBufferObject = obj;
            this.isAccessible = true;
        }
    }

    DirectByteBuffer(int i, MemoryRef memoryRef2) {
        super(-1, 0, i, i, memoryRef2.buffer, memoryRef2.offset);
        this.memoryRef = memoryRef2;
        this.address = memoryRef2.allocatedAddress + ((long) memoryRef2.offset);
        this.cleaner = null;
        this.isReadOnly = false;
    }

    public DirectByteBuffer(int i, long j, FileDescriptor fileDescriptor, Runnable runnable, boolean z) {
        super(-1, 0, i, i, fileDescriptor);
        this.isReadOnly = z;
        this.memoryRef = new MemoryRef(j, null);
        this.address = j;
        this.cleaner = Cleaner.create(this.memoryRef, runnable);
    }

    DirectByteBuffer(MemoryRef memoryRef2, int i, int i2, int i3, int i4, int i5, boolean z) {
        super(i, i2, i3, i4, memoryRef2.buffer, i5);
        this.isReadOnly = z;
        this.memoryRef = memoryRef2;
        this.address = memoryRef2.allocatedAddress + ((long) i5);
        this.cleaner = null;
    }

    @Override // sun.nio.ch.DirectBuffer
    public final Cleaner cleaner() {
        return this.cleaner;
    }

    @Override // java.nio.ByteBuffer
    public final ByteBuffer slice() {
        if (this.memoryRef.isAccessible) {
            int position = position();
            int limit = limit();
            int i = position <= limit ? limit - position : 0;
            return new DirectByteBuffer(this.memoryRef, -1, 0, i, i, position + this.offset, this.isReadOnly);
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final ByteBuffer duplicate() {
        MemoryRef memoryRef2 = this.memoryRef;
        if (!memoryRef2.isFreed) {
            return new DirectByteBuffer(memoryRef2, markValue(), position(), limit(), capacity(), this.offset, this.isReadOnly);
        }
        throw new IllegalStateException("buffer has been freed");
    }

    @Override // java.nio.ByteBuffer
    public final ByteBuffer asReadOnlyBuffer() {
        MemoryRef memoryRef2 = this.memoryRef;
        if (!memoryRef2.isFreed) {
            return new DirectByteBuffer(memoryRef2, markValue(), position(), limit(), capacity(), this.offset, true);
        }
        throw new IllegalStateException("buffer has been freed");
    }

    @Override // sun.nio.ch.DirectBuffer
    public final long address() {
        return this.address;
    }

    private long ix(int i) {
        return this.address + ((long) i);
    }

    private byte get(long j) {
        return Memory.peekByte(j);
    }

    @Override // java.nio.ByteBuffer
    public final byte get() {
        if (this.memoryRef.isAccessible) {
            return get(ix(nextGetIndex()));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final byte get(int i) {
        if (this.memoryRef.isAccessible) {
            checkIndex(i);
            return get(ix(i));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer get(byte[] bArr, int i, int i2) {
        if (this.memoryRef.isAccessible) {
            Buffer.checkBounds(i, i2, bArr.length);
            int position = position();
            int limit = limit();
            if (i2 <= (position <= limit ? limit - position : 0)) {
                Memory.peekByteArray(ix(position), bArr, i, i2);
                this.position = position + i2;
                return this;
            }
            throw new BufferUnderflowException();
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    private ByteBuffer put(long j, byte b) {
        Memory.pokeByte(j, b);
        return this;
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(ByteBuffer byteBuffer) {
        if (this.memoryRef.isAccessible) {
            super.put(byteBuffer);
            return this;
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final ByteBuffer put(byte b) {
        if (!this.memoryRef.isAccessible) {
            throw new IllegalStateException("buffer is inaccessible");
        } else if (!this.isReadOnly) {
            put(ix(nextPutIndex()), b);
            return this;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    public final ByteBuffer put(int i, byte b) {
        if (!this.memoryRef.isAccessible) {
            throw new IllegalStateException("buffer is inaccessible");
        } else if (!this.isReadOnly) {
            checkIndex(i);
            put(ix(i), b);
            return this;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    @Override // java.nio.ByteBuffer
    public ByteBuffer put(byte[] bArr, int i, int i2) {
        if (!this.memoryRef.isAccessible) {
            throw new IllegalStateException("buffer is inaccessible");
        } else if (!this.isReadOnly) {
            Buffer.checkBounds(i, i2, bArr.length);
            int position = position();
            int limit = limit();
            if (i2 <= (position <= limit ? limit - position : 0)) {
                Memory.pokeByteArray(ix(position), bArr, i, i2);
                this.position = position + i2;
                return this;
            }
            throw new BufferOverflowException();
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    @Override // java.nio.ByteBuffer
    public final ByteBuffer compact() {
        if (!this.memoryRef.isAccessible) {
            throw new IllegalStateException("buffer is inaccessible");
        } else if (!this.isReadOnly) {
            int position = position();
            int limit = limit();
            int i = position <= limit ? limit - position : 0;
            byte[] bArr = this.hb;
            int i2 = this.position;
            int i3 = this.offset;
            System.arraycopy(bArr, i2 + i3, bArr, i3, remaining());
            position(i);
            limit(capacity());
            discardMark();
            return this;
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    @Override // java.nio.Buffer
    public final boolean isReadOnly() {
        return this.isReadOnly;
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public final byte _get(int i) {
        return get(i);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public final void _put(int i, byte b) {
        put(i, b);
    }

    @Override // java.nio.ByteBuffer
    public final char getChar() {
        if (this.memoryRef.isAccessible) {
            int i = this.position + 2;
            if (i <= limit()) {
                char peekShort = (char) Memory.peekShort(ix(this.position), !this.nativeByteOrder);
                this.position = i;
                return peekShort;
            }
            throw new BufferUnderflowException();
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final char getChar(int i) {
        if (this.memoryRef.isAccessible) {
            checkIndex(i, 2);
            return (char) Memory.peekShort(ix(i), !this.nativeByteOrder);
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public char getCharUnchecked(int i) {
        if (this.memoryRef.isAccessible) {
            return (char) Memory.peekShort(ix(i), !this.nativeByteOrder);
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int i, char[] cArr, int i2, int i3) {
        if (this.memoryRef.isAccessible) {
            Memory.peekCharArray(ix(i), cArr, i2, i3, !this.nativeByteOrder);
            return;
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    private ByteBuffer putChar(long j, char c) {
        Memory.pokeShort(j, (short) c, !this.nativeByteOrder);
        return this;
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void putCharUnchecked(int i, char c) {
        if (this.memoryRef.isAccessible) {
            putChar(ix(i), c);
            return;
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final CharBuffer asCharBuffer() {
        if (!this.memoryRef.isFreed) {
            int position = position();
            int limit = limit();
            int i = (position <= limit ? limit - position : 0) >> 1;
            return new ByteBufferAsCharBuffer(this, -1, 0, i, i, position, order());
        }
        throw new IllegalStateException("buffer has been freed");
    }

    private short getShort(long j) {
        return Memory.peekShort(j, !this.nativeByteOrder);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public short getShortUnchecked(int i) {
        if (this.memoryRef.isAccessible) {
            return getShort(ix(i));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public void getUnchecked(int i, short[] sArr, int i2, int i3) {
        if (this.memoryRef.isAccessible) {
            Memory.peekShortArray(ix(i), sArr, i2, i3, !this.nativeByteOrder);
            return;
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final ShortBuffer asShortBuffer() {
        if (!this.memoryRef.isFreed) {
            int position = position();
            int limit = limit();
            int i = (position <= limit ? limit - position : 0) >> 1;
            return new ByteBufferAsShortBuffer(this, -1, 0, i, i, position, order());
        }
        throw new IllegalStateException("buffer has been freed");
    }

    private int getInt(long j) {
        return Memory.peekInt(j, !this.nativeByteOrder);
    }

    @Override // java.nio.ByteBuffer
    public int getInt() {
        if (this.memoryRef.isAccessible) {
            return getInt(ix(nextGetIndex(4)));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public int getInt(int i) {
        if (this.memoryRef.isAccessible) {
            checkIndex(i, 4);
            return getInt(ix(i));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public final int getIntUnchecked(int i) {
        if (this.memoryRef.isAccessible) {
            return getInt(ix(i));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public final void getUnchecked(int i, int[] iArr, int i2, int i3) {
        if (this.memoryRef.isAccessible) {
            Memory.peekIntArray(ix(i), iArr, i2, i3, !this.nativeByteOrder);
            return;
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final IntBuffer asIntBuffer() {
        if (!this.memoryRef.isFreed) {
            int position = position();
            int limit = limit();
            int i = (position <= limit ? limit - position : 0) >> 2;
            return new ByteBufferAsIntBuffer(this, -1, 0, i, i, position, order());
        }
        throw new IllegalStateException("buffer has been freed");
    }

    private long getLong(long j) {
        return Memory.peekLong(j, !this.nativeByteOrder);
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public final long getLongUnchecked(int i) {
        if (this.memoryRef.isAccessible) {
            return getLong(ix(i));
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    /* access modifiers changed from: package-private */
    @Override // java.nio.ByteBuffer
    public final void getUnchecked(int i, long[] jArr, int i2, int i3) {
        if (this.memoryRef.isAccessible) {
            Memory.peekLongArray(ix(i), jArr, i2, i3, !this.nativeByteOrder);
            return;
        }
        throw new IllegalStateException("buffer is inaccessible");
    }

    @Override // java.nio.ByteBuffer
    public final LongBuffer asLongBuffer() {
        if (!this.memoryRef.isFreed) {
            int position = position();
            int limit = limit();
            int i = (position <= limit ? limit - position : 0) >> 3;
            return new ByteBufferAsLongBuffer(this, -1, 0, i, i, position, order());
        }
        throw new IllegalStateException("buffer has been freed");
    }
}
