package com.facebook.quicklog;

import X.AnonymousClass0T2;
import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStripAny;

@DoNotStripAny
public interface PointEditor {
    PointEditor addPointData(String str, double d);

    PointEditor addPointData(String str, float f);

    PointEditor addPointData(String str, int i);

    PointEditor addPointData(String str, long j);

    PointEditor addPointData(String str, @Nullable String str2);

    PointEditor addPointData(String str, boolean z);

    PointEditor addPointData(String str, double[] dArr);

    PointEditor addPointData(String str, float[] fArr);

    PointEditor addPointData(String str, int[] iArr);

    PointEditor addPointData(String str, long[] jArr);

    PointEditor addPointData(String str, @Nullable String[] strArr);

    PointEditor addPointData(String str, boolean[] zArr);

    void markerEditingCompleted();

    PointEditor pointCustomTimestamp(long j);

    AnonymousClass0T2 pointEditingCompleted();

    PointEditor pointShouldIncludeMetadata(boolean z);
}
