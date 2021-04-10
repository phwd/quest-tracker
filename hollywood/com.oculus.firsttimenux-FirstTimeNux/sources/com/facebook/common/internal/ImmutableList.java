package com.facebook.common.internal;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Nullsafe(Nullsafe.Mode.STRICT)
public class ImmutableList<E> extends ArrayList<E> {
    private ImmutableList(int capacity) {
        super(capacity);
    }

    private ImmutableList(List<E> list) {
        super(list);
    }

    public static <E> ImmutableList<E> copyOf(List<E> list) {
        return new ImmutableList<>(list);
    }

    public static <E> ImmutableList<E> of(E... elements) {
        ImmutableList<E> list = new ImmutableList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }
}
