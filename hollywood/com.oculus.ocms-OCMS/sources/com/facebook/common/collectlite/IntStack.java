package com.facebook.common.collectlite;

import com.facebook.infer.annotation.Nullsafe;
import java.util.EmptyStackException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class IntStack {
    private int mCount = 0;
    private final ManagedIntArray mDelegate;

    public IntStack(int i) {
        this.mDelegate = ManagedIntArray.createWithInitialCapacity(i);
    }

    public void push(int i) {
        if (this.mCount < this.mDelegate.mLength) {
            this.mDelegate.mArray[this.mCount] = i;
        } else {
            this.mDelegate.add(i);
        }
        this.mCount++;
    }

    public int pop() {
        int i = this.mCount;
        if (i != 0) {
            this.mCount--;
            return this.mDelegate.get(i - 1);
        }
        throw new EmptyStackException();
    }

    public int peek() {
        int i = this.mCount;
        if (i != 0) {
            return this.mDelegate.get(i - 1);
        }
        throw new EmptyStackException();
    }

    public int size() {
        return this.mCount;
    }

    public int getAt(int i) {
        if (i >= 0 && i < this.mCount) {
            return this.mDelegate.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public void reset() {
        this.mCount = 0;
    }
}
