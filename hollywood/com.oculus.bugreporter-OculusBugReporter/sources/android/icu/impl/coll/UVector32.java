package android.icu.impl.coll;

public final class UVector32 {
    private int[] buffer = new int[32];
    private int length = 0;

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int size() {
        return this.length;
    }

    public int elementAti(int i) {
        return this.buffer[i];
    }

    public int[] getBuffer() {
        return this.buffer;
    }

    public void addElement(int e) {
        ensureAppendCapacity();
        int[] iArr = this.buffer;
        int i = this.length;
        this.length = i + 1;
        iArr[i] = e;
    }

    public void setElementAt(int elem, int index) {
        this.buffer[index] = elem;
    }

    public void insertElementAt(int elem, int index) {
        ensureAppendCapacity();
        int[] iArr = this.buffer;
        System.arraycopy((Object) iArr, index, (Object) iArr, index + 1, this.length - index);
        this.buffer[index] = elem;
        this.length++;
    }

    public void removeAllElements() {
        this.length = 0;
    }

    private void ensureAppendCapacity() {
        int i = this.length;
        int[] iArr = this.buffer;
        if (i >= iArr.length) {
            int[] newBuffer = new int[(iArr.length <= 65535 ? iArr.length * 4 : iArr.length * 2)];
            System.arraycopy((Object) this.buffer, 0, (Object) newBuffer, 0, this.length);
            this.buffer = newBuffer;
        }
    }
}
