package com.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder<T> {
    protected Node<T> _bufferHead;
    protected Node<T> _bufferTail;
    protected int _bufferedEntryCount;
    protected T _freeBuffer;

    /* access modifiers changed from: protected */
    public abstract T _constructArray(int i);

    protected PrimitiveArrayBuilder() {
    }

    public T resetAndStart() {
        _reset();
        T t = this._freeBuffer;
        return t == null ? _constructArray(12) : t;
    }

    public final T appendCompletedChunk(T t, int i) {
        Node<T> node = new Node<>(t, i);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        this._bufferedEntryCount += i;
        return _constructArray(i < 16384 ? i + i : i + (i >> 2));
    }

    public T completeAndClearBuffer(T t, int i) {
        int i2 = this._bufferedEntryCount + i;
        T _constructArray = _constructArray(i2);
        int i3 = 0;
        for (Node<T> node = this._bufferHead; node != null; node = node.next()) {
            i3 = node.copyData(_constructArray, i3);
        }
        System.arraycopy(t, 0, _constructArray, i3, i);
        int i4 = i3 + i;
        if (i4 == i2) {
            return _constructArray;
        }
        throw new IllegalStateException("Should have gotten " + i2 + " entries, got " + i4);
    }

    /* access modifiers changed from: protected */
    public void _reset() {
        Node<T> node = this._bufferTail;
        if (node != null) {
            this._freeBuffer = node.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    /* access modifiers changed from: package-private */
    public static final class Node<T> {
        final T _data;
        final int _dataLength;
        Node<T> _next;

        public Node(T t, int i) {
            this._data = t;
            this._dataLength = i;
        }

        public T getData() {
            return this._data;
        }

        public int copyData(T t, int i) {
            System.arraycopy(this._data, 0, t, i, this._dataLength);
            return i + this._dataLength;
        }

        public Node<T> next() {
            return this._next;
        }

        public void linkNext(Node<T> node) {
            if (this._next == null) {
                this._next = node;
                return;
            }
            throw new IllegalStateException();
        }
    }
}
