package com.oculus.tablet.utils;

import android.view.View;
import java.util.function.Predicate;

public final class ViewBindingUtil {
    public static final Predicate<Integer> NEGATIVE = $$Lambda$ViewBindingUtil$6NHgdfDecs87CNv5P0rNgjlb5Y.INSTANCE;
    public static final Predicate<Integer> NON_NEGATIVE = NEGATIVE.negate();
    public static final Predicate<String> NOT_NULL_OR_EMPTY = NULL_OR_EMPTY.negate();
    public static final Predicate<String> NULL_OR_EMPTY = $$Lambda$ViewBindingUtil$xDUL6eLoCVQQBisNQvjGHsjIVw.INSTANCE;

    static /* synthetic */ boolean lambda$static$8(String str) {
        return str == null || "".equals(str);
    }

    static /* synthetic */ boolean lambda$static$9(Integer num) {
        return num == null || num.intValue() < 0;
    }

    public static void toggleViewInvisible(View view, boolean z) {
        setViewVisibility(view, 4, z);
    }

    public static void toggleViewGone(View view, boolean z) {
        setViewVisibility(view, 8, z);
    }

    private static void setViewVisibility(View view, int i, boolean z) {
        if (z) {
            i = 0;
        }
        if (i != view.getVisibility()) {
            view.setVisibility(i);
        }
    }

    @SafeVarargs
    public static <T> boolean all(Predicate<T> predicate, T... tArr) {
        for (T t : tArr) {
            if (!predicate.test(t)) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <T> boolean any(Predicate<T> predicate, T... tArr) {
        for (T t : tArr) {
            if (predicate.test(t)) {
                return true;
            }
        }
        return false;
    }

    @SafeVarargs
    public static <T> boolean none(Predicate<T> predicate, T... tArr) {
        return !any(predicate, tArr);
    }
}
