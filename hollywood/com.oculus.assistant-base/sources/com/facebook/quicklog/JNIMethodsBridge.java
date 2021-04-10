package com.facebook.quicklog;

import X.AnonymousClass08;
import X.C0917os;
import X.C0918ot;
import X.IY;

public class JNIMethodsBridge {
    public static void markerPoint(int i, int i2, String str, int i3, long j) {
        QuickPerformanceLogger qPLInstance = QuickPerformanceLoggerProvider.getQPLInstance();
        if (qPLInstance != null) {
            IY withMarker = qPLInstance.withMarker(i, i2);
            if (!(withMarker instanceof C0918ot)) {
                ((C0917os) withMarker).A00 = i3;
            }
            withMarker.A02(str).pointCustomTimestamp(j).pointEditingCompleted().markerEditingCompleted();
        }
    }

    public static void markerPoint(int i, int i2, String str, String str2, int i3, long j) {
        QuickPerformanceLogger qPLInstance = QuickPerformanceLoggerProvider.getQPLInstance();
        if (qPLInstance != null) {
            IY withMarker = qPLInstance.withMarker(i, i2);
            if (!(withMarker instanceof C0918ot)) {
                ((C0917os) withMarker).A00 = i3;
            }
            withMarker.A02(str).addPointData("__key", str2).pointCustomTimestamp(j).pointEditingCompleted().markerEditingCompleted();
        }
    }

    public static void markerPoint(int i, int i2, String str, String[] strArr, int i3, long j, boolean z) {
        QuickPerformanceLogger qPLInstance = QuickPerformanceLoggerProvider.getQPLInstance();
        if (qPLInstance != null) {
            int length = strArr.length;
            if (length % 3 == 0) {
                IY withMarker = qPLInstance.withMarker(i, i2);
                if (!(withMarker instanceof C0918ot)) {
                    ((C0917os) withMarker).A00 = i3;
                }
                PointEditor pointShouldIncludeMetadata = withMarker.A02(str).pointCustomTimestamp(j).pointShouldIncludeMetadata(z);
                for (int i4 = 0; i4 < length; i4 += 3) {
                    String str2 = strArr[i4];
                    String str3 = strArr[i4 + 1];
                    String str4 = strArr[i4 + 2];
                    switch (str4.hashCode()) {
                        case 49:
                            if (str4.equals("1")) {
                                pointShouldIncludeMetadata.addPointData(str2, str3);
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 50:
                            if (str4.equals("2")) {
                                pointShouldIncludeMetadata.addPointData(str2, Integer.parseInt(str3));
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 51:
                            if (str4.equals("3")) {
                                pointShouldIncludeMetadata.addPointData(str2, Double.parseDouble(str3));
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 52:
                            if (str4.equals("4")) {
                                pointShouldIncludeMetadata.addPointData(str2, !"0".equals(str3));
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 53:
                            if (str4.equals("5")) {
                                pointShouldIncludeMetadata.addPointData(str2, str3.split(",,,"));
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 54:
                            if (str4.equals("6")) {
                                String[] split = str3.split(",,,");
                                int length2 = split.length;
                                int[] iArr = new int[length2];
                                for (int i5 = 0; i5 < length2; i5++) {
                                    iArr[i5] = Integer.parseInt(split[i5]);
                                }
                                pointShouldIncludeMetadata.addPointData(str2, iArr);
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 55:
                            if (str4.equals("7")) {
                                String[] split2 = str3.split(",,,");
                                int length3 = split2.length;
                                double[] dArr = new double[length3];
                                for (int i6 = 0; i6 < length3; i6++) {
                                    dArr[i6] = Double.parseDouble(split2[i6]);
                                }
                                pointShouldIncludeMetadata.addPointData(str2, dArr);
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        case 56:
                            if (str4.equals("8")) {
                                String[] split3 = str3.split(",,,");
                                int length4 = split3.length;
                                boolean[] zArr = new boolean[length4];
                                for (int i7 = 0; i7 < length4; i7++) {
                                    boolean z2 = false;
                                    if (split3[i7] == "1") {
                                        z2 = true;
                                    }
                                    zArr[i7] = z2;
                                }
                                pointShouldIncludeMetadata.addPointData(str2, zArr);
                                break;
                            } else {
                                throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                            }
                        default:
                            throw new IllegalArgumentException(AnonymousClass08.A04("Type entry is not supported: ", str4));
                    }
                }
                pointShouldIncludeMetadata.pointEditingCompleted().markerEditingCompleted();
            }
        }
    }
}
