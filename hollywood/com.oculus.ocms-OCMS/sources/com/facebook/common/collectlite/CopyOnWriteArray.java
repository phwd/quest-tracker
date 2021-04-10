package com.facebook.common.collectlite;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class CopyOnWriteArray<T> {
    private ArrayList<T> mActiveData;
    private int mDataInstanceCount;
    private final int mRequestedCapacity;
    private List<T> mUnmodifiableActiveData;

    public CopyOnWriteArray() {
        this.mActiveData = new ArrayList<>();
        this.mUnmodifiableActiveData = Collections.unmodifiableList(this.mActiveData);
        this.mRequestedCapacity = -1;
    }

    public CopyOnWriteArray(int i) {
        this.mActiveData = new ArrayList<>(i);
        this.mUnmodifiableActiveData = Collections.unmodifiableList(this.mActiveData);
        this.mRequestedCapacity = i;
    }

    public synchronized List<T> start() {
        List<T> list;
        list = this.mUnmodifiableActiveData;
        this.mDataInstanceCount++;
        return list;
    }

    public synchronized void stop() {
        this.mDataInstanceCount--;
    }

    public synchronized void add(T t) {
        if (this.mDataInstanceCount > 0) {
            ArrayList<T> arrayList = this.mActiveData;
            int size = arrayList.size();
            this.mActiveData = new ArrayList<>(size + 1);
            this.mUnmodifiableActiveData = Collections.unmodifiableList(this.mActiveData);
            for (int i = 0; i < size; i++) {
                this.mActiveData.add(arrayList.get(i));
            }
        }
        this.mActiveData.add(t);
    }

    public synchronized boolean addIfAbsent(T t) {
        if (this.mActiveData.contains(t)) {
            return false;
        }
        add(t);
        return true;
    }

    public synchronized boolean remove(T t) {
        if (this.mDataInstanceCount <= 0) {
            return this.mActiveData.remove(t);
        }
        int indexOf = this.mActiveData.indexOf(t);
        if (indexOf < 0) {
            return false;
        }
        ArrayList<T> arrayList = this.mActiveData;
        int size = arrayList.size();
        this.mActiveData = new ArrayList<>(size - 1);
        this.mUnmodifiableActiveData = Collections.unmodifiableList(this.mActiveData);
        for (int i = 0; i < size; i++) {
            if (i != indexOf) {
                this.mActiveData.add(arrayList.get(i));
            }
        }
        return true;
    }

    public synchronized void clear() {
        if (this.mDataInstanceCount > 0) {
            if (this.mRequestedCapacity >= 0) {
                this.mActiveData = new ArrayList<>(this.mRequestedCapacity);
            } else {
                this.mActiveData = new ArrayList<>();
            }
            this.mUnmodifiableActiveData = Collections.unmodifiableList(this.mActiveData);
        } else {
            this.mActiveData.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public int dataInstanceCount() {
        return this.mDataInstanceCount;
    }
}
