package java.nio;

public abstract class Buffer {
    final int _elementSizeShift;
    long address;
    private int capacity;
    private int limit;
    private int mark = -1;
    int position = 0;

    public abstract boolean isReadOnly();

    Buffer(int i, int i2, int i3, int i4, int i5) {
        if (i4 >= 0) {
            this.capacity = i4;
            limit(i3);
            position(i2);
            if (i >= 0) {
                if (i <= i2) {
                    this.mark = i;
                } else {
                    throw new IllegalArgumentException("mark > position: (" + i + " > " + i2 + ")");
                }
            }
            this._elementSizeShift = i5;
            return;
        }
        throw new IllegalArgumentException("Negative capacity: " + i4);
    }

    public final int capacity() {
        return this.capacity;
    }

    public final int position() {
        return this.position;
    }

    public Buffer position(int i) {
        if (i > this.limit || i < 0) {
            throw new IllegalArgumentException("Bad position " + i + "/" + this.limit);
        }
        this.position = i;
        if (this.mark > this.position) {
            this.mark = -1;
        }
        return this;
    }

    public final int limit() {
        return this.limit;
    }

    public Buffer limit(int i) {
        if (i > this.capacity || i < 0) {
            throw new IllegalArgumentException();
        }
        this.limit = i;
        int i2 = this.position;
        int i3 = this.limit;
        if (i2 > i3) {
            this.position = i3;
        }
        if (this.mark > this.limit) {
            this.mark = -1;
        }
        return this;
    }

    public Buffer mark() {
        this.mark = this.position;
        return this;
    }

    public Buffer reset() {
        int i = this.mark;
        if (i >= 0) {
            this.position = i;
            return this;
        }
        throw new InvalidMarkException();
    }

    public Buffer clear() {
        this.position = 0;
        this.limit = this.capacity;
        this.mark = -1;
        return this;
    }

    public Buffer flip() {
        this.limit = this.position;
        this.position = 0;
        this.mark = -1;
        return this;
    }

    public Buffer rewind() {
        this.position = 0;
        this.mark = -1;
        return this;
    }

    public final int remaining() {
        return this.limit - this.position;
    }

    public final boolean hasRemaining() {
        return this.position < this.limit;
    }

    /* access modifiers changed from: package-private */
    public final int nextGetIndex() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return i;
        }
        throw new BufferUnderflowException();
    }

    /* access modifiers changed from: package-private */
    public final int nextGetIndex(int i) {
        int i2 = this.limit;
        int i3 = this.position;
        if (i2 - i3 >= i) {
            this.position = i + i3;
            return i3;
        }
        throw new BufferUnderflowException();
    }

    /* access modifiers changed from: package-private */
    public final int nextPutIndex() {
        int i = this.position;
        if (i < this.limit) {
            this.position = i + 1;
            return i;
        }
        throw new BufferOverflowException();
    }

    /* access modifiers changed from: package-private */
    public final int checkIndex(int i) {
        if (i >= 0 && i < this.limit) {
            return i;
        }
        throw new IndexOutOfBoundsException("index=" + i + " out of bounds (limit=" + this.limit + ")");
    }

    /* access modifiers changed from: package-private */
    public final int checkIndex(int i, int i2) {
        if (i >= 0 && i2 <= this.limit - i) {
            return i;
        }
        throw new IndexOutOfBoundsException("index=" + i + " out of bounds (limit=" + this.limit + ", nb=" + i2 + ")");
    }

    /* access modifiers changed from: package-private */
    public final int markValue() {
        return this.mark;
    }

    /* access modifiers changed from: package-private */
    public final void discardMark() {
        this.mark = -1;
    }

    static void checkBounds(int i, int i2, int i3) {
        int i4 = i + i2;
        if ((i | i2 | i4 | (i3 - i4)) < 0) {
            throw new IndexOutOfBoundsException("off=" + i + ", len=" + i2 + " out of bounds (size=" + i3 + ")");
        }
    }
}
