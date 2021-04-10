package com.oculus.util.collection;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class CollectionUtil {

    public interface Filter<I> {
    }

    public interface Folder<I, O> {
    }

    public interface Predicate<I, O> {
        O function(I i);
    }

    public static <T, U> List<U> A00(@Nullable List<T> list, Predicate<T, U> predicate) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (T t : list) {
            U function = predicate.function(t);
            if (function != null) {
                arrayList.add(function);
            }
        }
        return arrayList;
    }
}
