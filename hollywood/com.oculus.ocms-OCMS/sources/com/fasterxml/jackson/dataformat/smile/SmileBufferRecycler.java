package com.fasterxml.jackson.dataformat.smile;

public class SmileBufferRecycler<T> {
    public static final int DEFAULT_NAME_BUFFER_LENGTH = 64;
    public static final int DEFAULT_STRING_VALUE_BUFFER_LENGTH = 64;
    protected T[] _seenNamesBuffer;
    protected T[] _seenStringValuesBuffer;

    public T[] allocSeenNamesBuffer() {
        T[] tArr = this._seenNamesBuffer;
        if (tArr != null) {
            this._seenNamesBuffer = null;
        }
        return tArr;
    }

    public T[] allocSeenStringValuesBuffer() {
        T[] tArr = this._seenStringValuesBuffer;
        if (tArr != null) {
            this._seenStringValuesBuffer = null;
        }
        return tArr;
    }

    public void releaseSeenNamesBuffer(T[] tArr) {
        this._seenNamesBuffer = tArr;
    }

    public void releaseSeenStringValuesBuffer(T[] tArr) {
        this._seenStringValuesBuffer = tArr;
    }
}
