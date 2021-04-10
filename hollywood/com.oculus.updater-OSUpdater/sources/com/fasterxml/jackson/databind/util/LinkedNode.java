package com.fasterxml.jackson.databind.util;

public final class LinkedNode<T> {
    final LinkedNode<T> _next;
    final T _value;

    public LinkedNode<T> next() {
        return this._next;
    }

    public T value() {
        return this._value;
    }
}
