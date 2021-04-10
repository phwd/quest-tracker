package com.facebook.common.internal;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.STRICT)
public class ImmutableSet<E> extends HashSet<E> {
    private ImmutableSet(Set<E> set) {
        super(set);
    }

    public static <E> ImmutableSet<E> copyOf(Set<E> set) {
        return new ImmutableSet<>(set);
    }

    public static <E> ImmutableSet<E> of(E... eArr) {
        HashSet hashSet = new HashSet(eArr.length);
        Collections.addAll(hashSet, eArr);
        return new ImmutableSet<>(hashSet);
    }
}
