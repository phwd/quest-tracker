package com.oculus.restwrapper;

public interface ErrorCallback<E> {
    void call(String str, Exception exc, int i, E e);
}
