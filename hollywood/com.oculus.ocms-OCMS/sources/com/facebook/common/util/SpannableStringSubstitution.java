package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SpannableStringSubstitution {
    @Nullable
    public final Object span;
    public final int spanFlags;
    @Nullable
    public final Object substitutionText;
    public final int substitutionTextResId;

    public SpannableStringSubstitution(Object obj) {
        this(obj, 0, null, 0);
    }

    public SpannableStringSubstitution(int i) {
        this(null, i, null, 0);
    }

    public SpannableStringSubstitution(Object obj, Object obj2, int i) {
        this(obj, 0, obj2, i);
    }

    public SpannableStringSubstitution(int i, Object obj, int i2) {
        this(null, i, obj, i2);
    }

    private SpannableStringSubstitution(@Nullable Object obj, int i, @Nullable Object obj2, int i2) {
        Preconditions.checkArgument((obj == null && i == 0) ? false : true);
        this.substitutionText = obj;
        this.substitutionTextResId = i;
        this.span = obj2;
        this.spanFlags = i2;
    }
}
