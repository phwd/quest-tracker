package com.facebook.quicklog;

import X.IY;

public interface PointEditor {
    PointEditor addPointData(String str, double d);

    PointEditor addPointData(String str, float f);

    PointEditor addPointData(String str, int i);

    PointEditor addPointData(String str, long j);

    PointEditor addPointData(String str, String str2);

    PointEditor addPointData(String str, boolean z);

    PointEditor addPointData(String str, double[] dArr);

    PointEditor addPointData(String str, float[] fArr);

    PointEditor addPointData(String str, int[] iArr);

    PointEditor addPointData(String str, long[] jArr);

    PointEditor addPointData(String str, String[] strArr);

    PointEditor addPointData(String str, boolean[] zArr);

    void markerEditingCompleted();

    PointEditor pointCustomTimestamp(long j);

    IY pointEditingCompleted();

    PointEditor pointShouldIncludeMetadata(boolean z);
}
