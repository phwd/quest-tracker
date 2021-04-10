package com.facebook.debug.tracer;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class LongStack {
    private int top = -1;
    private long[] vector;

    public LongStack(int i) {
        this.vector = new long[i];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public int size() {
        return this.top + 1;
    }

    public long pop() {
        long[] jArr = this.vector;
        int i = this.top;
        this.top = i - 1;
        return jArr[i];
    }

    public void push(long j) {
        if (this.vector.length == this.top + 1) {
            grow();
        }
        long[] jArr = this.vector;
        int i = this.top + 1;
        this.top = i;
        jArr[i] = j;
    }

    private void grow() {
        long[] jArr = this.vector;
        long[] jArr2 = new long[(jArr.length * 2)];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        this.vector = jArr2;
    }

    public long peek() {
        return this.vector[this.top];
    }

    public void clear() {
        this.top = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<LongStack vector:[");
        for (int i = 0; i < this.vector.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.top) {
                sb.append(">>");
            }
            sb.append(this.vector[i]);
            if (i == this.top) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}
