package com.fasterxml.jackson.databind;

import X.VF;

public abstract class JsonSerializer<T> implements VF {

    public static abstract class None extends JsonSerializer<Object> {
    }
}
