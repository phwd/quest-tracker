package com.oculus.tablet.utils;

import android.view.View;
import java.util.function.Predicate;

public final class ViewBindingUtil {
    public static final Predicate<Integer> NEGATIVE;
    public static final Predicate<Integer> NON_NEGATIVE;
    public static final Predicate<String> NOT_NULL_OR_EMPTY;
    public static final Predicate<String> NULL_OR_EMPTY;

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

    public static void toggleViewInvisible(View view, boolean z) {
        setViewVisibility(view, 4, z);
    }

    static {
        $$Lambda$ViewBindingUtil$wDccbNwtBQ12D5Hzi9SqAK98V6c2 r0 = $$Lambda$ViewBindingUtil$wDccbNwtBQ12D5Hzi9SqAK98V6c2.INSTANCE;
        NULL_OR_EMPTY = r0;
        NOT_NULL_OR_EMPTY = r0.negate();
        $$Lambda$ViewBindingUtil$yUcWnQO2AyGnn1KtNNkm9ZU_jY2 r02 = $$Lambda$ViewBindingUtil$yUcWnQO2AyGnn1KtNNkm9ZU_jY2.INSTANCE;
        NEGATIVE = r02;
        NON_NEGATIVE = r02.negate();
    }

    public static /* synthetic */ boolean lambda$static$0(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean lambda$static$1(Integer num) {
        if (num == null || num.intValue() < 0) {
            return true;
        }
        return false;
    }

    public static void setViewVisibility(View view, int i, boolean z) {
        if (z) {
            i = 0;
        }
        if (i != view.getVisibility()) {
            view.setVisibility(i);
        }
    }

    public static void toggleViewGone(View view, boolean z) {
        setViewVisibility(view, 8, z);
    }

    @SafeVarargs
    public static <T> boolean none(Predicate<T> predicate, T... tArr) {
        return !any(predicate, tArr);
    }
}
