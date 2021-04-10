package android.icu.impl.coll;

public final class UVector64 {
    private long[] buffer = new long[32];
    private int length = 0;

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int size() {
        return this.length;
    }

    public long elementAti(int i) {
        return this.buffer[i];
    }

    public long[] getBuffer() {
        return this.buffer;
    }

    public void addElement(long e) {
        ensureAppendCapacity();
        long[] jArr = this.buffer;
        int i = this.length;
        this.length = i + 1;
        jArr[i] = e;
    }

    public void setElementAt(long elem, int index) {
        this.buffer[index] = elem;
    }

    public void insertElementAt(long elem, int index) {
        ensureAppendCapacity();
        long[] jArr = this.buffer;
        System.arraycopy((Object) jArr, index, (Object) jArr, index + 1, this.length - index);
        this.buffer[index] = elem;
        this.length++;
    }

    public void removeAllElements() {
        this.length = 0;
    }

    private void ensureAppendCapacity() {
        int i = this.length;
        long[] jArr = this.buffer;
        if (i >= jArr.length) {
            long[] newBuffer = new long[(jArr.length <= 65535 ? jArr.length * 4 : jArr.length * 2)];
            System.arraycopy((Object) this.buffer, 0, (Object) newBuffer, 0, this.length);
            this.buffer = newBuffer;
        }
    }
}
