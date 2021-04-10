package com.facebook.quicklog;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.privacy.datacollection.DisallowSensitive;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class MarkerEditor {
    public static final int COLLECT_METADATA = 1;
    public static final int NO_METADATA = 0;

    public abstract MarkerEditor annotate(String str, double d);

    public abstract MarkerEditor annotate(String str, int i);

    public abstract MarkerEditor annotate(String str, long j);

    @DisallowSensitive
    public abstract MarkerEditor annotate(String str, @Nullable String str2);

    public abstract MarkerEditor annotate(String str, boolean z);

    public abstract MarkerEditor annotate(String str, double[] dArr);

    public abstract MarkerEditor annotate(String str, int[] iArr);

    public abstract MarkerEditor annotate(String str, long[] jArr);

    @DisallowSensitive
    public abstract MarkerEditor annotate(String str, String[] strArr);

    public abstract MarkerEditor annotate(String str, boolean[] zArr);

    public abstract void markerEditingCompleted();

    /* access modifiers changed from: protected */
    @DisallowSensitive
    public abstract MarkerEditor point(String str, @Nullable String str2, long j, int i);

    public abstract PointEditor pointEditor(String str);

    public abstract MarkerEditor withLevel(@EventLevel int i);

    public MarkerEditor point(String str) {
        return point(str, (String) null);
    }

    public MarkerEditor pointWithMetadata(String str) {
        return pointWithMetadata(str, (String) null);
    }

    @DisallowSensitive
    public MarkerEditor point(String str, @Nullable String str2) {
        return point(str, str2, -1);
    }

    @DisallowSensitive
    public MarkerEditor pointWithMetadata(String str, @Nullable String str2) {
        return pointWithMetadata(str, str2, -1);
    }

    public MarkerEditor point(String str, long j) {
        return point(str, null, j);
    }

    public MarkerEditor pointWithMetadata(String str, long j) {
        return pointWithMetadata(str, null, j);
    }

    @DisallowSensitive
    public final MarkerEditor point(String str, @Nullable String str2, long j) {
        return point(str, str2, j, 0);
    }

    public final MarkerEditor pointWithMetadata(String str, @Nullable String str2, long j) {
        return point(str, str2, j, 1);
    }
}
