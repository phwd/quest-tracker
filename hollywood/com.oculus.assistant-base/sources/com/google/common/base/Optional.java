package com.google.common.base;

import java.io.Serializable;

public abstract class Optional implements Serializable {
    public static final long serialVersionUID = 0;

    public abstract Object get();

    public abstract boolean isPresent();
}
