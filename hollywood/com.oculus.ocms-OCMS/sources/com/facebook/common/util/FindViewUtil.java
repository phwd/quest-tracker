package com.facebook.common.util;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.common.string.StringUtil;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FindViewUtil {
    private FindViewUtil() {
    }

    public static <T extends View> Optional<T> getOptionalView(View view, int i) {
        Preconditions.checkNotNull(view);
        return Optional.fromNullable(view.findViewById(i));
    }

    public static <T extends View> Optional<T> getOptionalViewWithTagKey(View view, int i) {
        Preconditions.checkNotNull(view);
        return Optional.fromNullable(findViewWithTagKey(view, i));
    }

    public static <T extends View> Optional<T> getOptionalView(Activity activity, int i) {
        Preconditions.checkNotNull(activity);
        return Optional.fromNullable(activity.findViewById(i));
    }

    public static <T extends View> T getViewOrThrow(View view, int i) {
        Preconditions.checkNotNull(view);
        return (T) assertViewExists(view.findViewById(i), view.getResources(), i);
    }

    public static <T extends View> T getViewWithTagKeyOrThrow(View view, int i) {
        Preconditions.checkNotNull(view);
        return (T) assertViewExists(findViewWithTagKey(view, i), view.getResources(), i);
    }

    public static <T extends View> T getViewWithTagKeyAndValueOrThrow(View view, int i, Object obj) {
        Preconditions.checkNotNull(view);
        return (T) assertViewExists(findViewWithTagKeyAndValue(view, i, obj), view.getResources(), i);
    }

    public static <T extends View> T getViewOrThrow(Activity activity, int i) {
        Preconditions.checkNotNull(activity);
        return (T) assertViewExists(activity.findViewById(i), activity.getResources(), i);
    }

    public static <T extends View> Optional<T> getOptionalParentOfType(View view, Class<? extends T> cls) {
        ViewParent parent = view.getParent();
        while (parent != null && !cls.isInstance(parent)) {
            parent = parent.getParent();
        }
        return Optional.fromNullable((View) parent);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: android.view.View */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T extends View> T assertViewExists(@Nullable View view, Resources resources, int i) {
        Preconditions.checkNotNull(resources);
        if (view != 0) {
            return view;
        }
        throw new IllegalStateException(StringUtil.formatStrLocaleSafe("Required view with ID %s not found. Either your layout is missing the ID you requested, or you want to use getOptionalView. Only use getOptionalView if you're sure that you need logic that depends on whether a particular child view exists.", resources.getResourceEntryName(i)));
    }

    @Nullable
    private static View findViewWithTagKey(View view, int i) {
        if (view.getTag(i) != null) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View findViewWithTagKey = findViewWithTagKey(viewGroup.getChildAt(i2), i);
            if (findViewWithTagKey != null) {
                return findViewWithTagKey;
            }
        }
        return null;
    }

    @Nullable
    private static View findViewWithTagKeyAndValue(View view, int i, Object obj) {
        Object tag = view.getTag(i);
        if (tag == obj) {
            return view;
        }
        if (tag != null && tag.equals(obj)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View findViewWithTagKeyAndValue = findViewWithTagKeyAndValue(viewGroup.getChildAt(i2), i, obj);
                if (findViewWithTagKeyAndValue != null) {
                    return findViewWithTagKeyAndValue;
                }
            }
        }
        return null;
    }
}
