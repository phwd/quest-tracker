package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
    private Node _bufferHead;
    private Node _bufferTail;
    private int _bufferedEntryCount;
    private Object[] _freeBuffer;

    public Object[] resetAndStart() {
        _reset();
        Object[] objArr = this._freeBuffer;
        return objArr == null ? new Object[12] : objArr;
    }

    public Object[] appendCompletedChunk(Object[] objArr) {
        Node node = new Node(objArr);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        int length = objArr.length;
        this._bufferedEntryCount += length;
        return new Object[(length < 16384 ? length + length : length + (length >> 2))];
    }

    public Object[] completeAndClearBuffer(Object[] objArr, int i) {
        int i2 = this._bufferedEntryCount + i;
        Object[] objArr2 = new Object[i2];
        _copyTo(objArr2, i2, objArr, i);
        return objArr2;
    }

    public <T> T[] completeAndClearBuffer(Object[] objArr, int i, Class<T> cls) {
        int i2 = this._bufferedEntryCount + i;
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        _copyTo(tArr, i2, objArr, i);
        _reset();
        return tArr;
    }

    public void completeAndClearBuffer(Object[] objArr, int i, List<Object> list) {
        int i2;
        Node node = this._bufferHead;
        while (true) {
            i2 = 0;
            if (node == null) {
                break;
            }
            Object[] data = node.getData();
            int length = data.length;
            while (i2 < length) {
                list.add(data[i2]);
                i2++;
            }
            node = node.next();
        }
        while (i2 < i) {
            list.add(objArr[i2]);
            i2++;
        }
    }

    public int initialCapacity() {
        Object[] objArr = this._freeBuffer;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    /* access modifiers changed from: protected */
    public void _reset() {
        Node node = this._bufferTail;
        if (node != null) {
            this._freeBuffer = node.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    /* access modifiers changed from: protected */
    public final void _copyTo(Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        for (Node node = this._bufferHead; node != null; node = node.next()) {
            Object[] data = node.getData();
            int length = data.length;
            System.arraycopy(data, 0, obj, i3, length);
            i3 += length;
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            throw new IllegalStateException("Should have gotten " + i + " entries, got " + i4);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Node {
        final Object[] _data;
        Node _next;

        public Node(Object[] objArr) {
            this._data = objArr;
        }

        public Object[] getData() {
            return this._data;
        }

        public Node next() {
            return this._next;
        }

        public void linkNext(Node node) {
            if (this._next == null) {
                this._next = node;
                return;
            }
            throw new IllegalStateException();
        }
    }
}
