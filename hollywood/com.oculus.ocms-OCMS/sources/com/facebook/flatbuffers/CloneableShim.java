package com.facebook.flatbuffers;

public interface CloneableShim extends Cloneable {
    Object shallowCopy();
}
