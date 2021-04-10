package android.support.graphics.drawable;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    PathParser() {
    }

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int length = fArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(createNodesFromPathData, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + str, e);
        }
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int nextStart = nextStart(str, i);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i = nextStart + 1;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            if (!(pathDataNodeArr[i].type == pathDataNodeArr2[i].type && pathDataNodeArr[i].params.length == pathDataNodeArr2[i].params.length)) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].type = pathDataNodeArr2[i].type;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].params.length; i2++) {
                pathDataNodeArr[i].params[i2] = pathDataNodeArr2[i].params[i2];
            }
        }
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i;
            }
            i++;
        }
        return i;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    /* access modifiers changed from: private */
    public static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        int i = 1;
        if ((str.charAt(0) == 'z') || (str.charAt(0) == 'Z')) {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i2 = 0;
            while (i < length) {
                extract(str, i, extractFloatResult);
                int i3 = extractFloatResult.mEndPosition;
                if (i < i3) {
                    fArr[i2] = Float.parseFloat(str.substring(i, i3));
                    i2++;
                }
                i = extractFloatResult.mEndWithNegOrDot ? i3 : i3 + 1;
            }
            return copyOfRange(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e A[LOOP:0: B:1:0x0007->B:20:0x003e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extract(java.lang.String r8, int r9, android.support.graphics.drawable.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = r0
            r3 = r2
            r4 = r3
        L_0x0007:
            int r5 = r8.length()
            if (r1 >= r5) goto L_0x0041
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L_0x0039
            r6 = 69
            if (r5 == r6) goto L_0x0037
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x0037
            switch(r5) {
                case 44: goto L_0x0039;
                case 45: goto L_0x002c;
                case 46: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0035
        L_0x0022:
            if (r3 != 0) goto L_0x0027
            r2 = r0
            r3 = r7
            goto L_0x003b
        L_0x0027:
            r10.mEndWithNegOrDot = r7
            r2 = r0
            r4 = r7
            goto L_0x003b
        L_0x002c:
            if (r1 == r9) goto L_0x0035
            if (r2 != 0) goto L_0x0035
            r10.mEndWithNegOrDot = r7
            r2 = r0
            r4 = r7
            goto L_0x003b
        L_0x0035:
            r2 = r0
            goto L_0x003b
        L_0x0037:
            r2 = r7
            goto L_0x003b
        L_0x0039:
            r2 = r0
            r4 = r7
        L_0x003b:
            if (r4 == 0) goto L_0x003e
            goto L_0x0041
        L_0x003e:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0041:
            r10.mEndPosition = r1
            return
            switch-data {44->0x0039, 45->0x002c, 46->0x0022, }
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.PathParser.extract(java.lang.String, int, android.support.graphics.drawable.PathParser$ExtractFloatResult):void");
    }

    public static class PathDataNode {
        float[] params;
        char type;

        PathDataNode(char c, float[] fArr) {
            this.type = c;
            this.params = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.type = pathDataNode.type;
            float[] fArr = pathDataNode.params;
            this.params = PathParser.copyOfRange(fArr, 0, fArr.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                addCommand(path, fArr, c, pathDataNodeArr[i].type, pathDataNodeArr[i].params);
                c = pathDataNodeArr[i].type;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            int i = 0;
            while (true) {
                float[] fArr = pathDataNode.params;
                if (i < fArr.length) {
                    this.params[i] = (fArr[i] * (1.0f - f)) + (pathDataNode2.params[i] * f);
                    i++;
                } else {
                    return;
                }
            }
        }

        private static void addCommand(Path path, float[] fArr, char c, char c2, float[] fArr2) {
            int i;
            int i2;
            float f;
            float f2;
            boolean z;
            float f3;
            float f4;
            float f5;
            Path path2 = path;
            boolean z2 = false;
            float f6 = fArr[0];
            float f7 = fArr[1];
            float f8 = fArr[2];
            float f9 = fArr[3];
            float f10 = fArr[4];
            float f11 = fArr[5];
            switch (c2) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                    i = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path2.moveTo(f10, f11);
                    f6 = f10;
                    f8 = f6;
                    f7 = f11;
                    f9 = f7;
                    i = 2;
                    break;
                default:
                    i = 2;
                    break;
            }
            float f12 = f6;
            float f13 = f7;
            float f14 = f10;
            float f15 = f11;
            int i3 = 0;
            char c3 = c;
            while (i3 < fArr2.length) {
                float f16 = 0.0f;
                switch (c2) {
                    case 'A':
                        i2 = i3;
                        int i4 = i2 + 5;
                        int i5 = i2 + 6;
                        drawArc(path, f12, f13, fArr2[i4], fArr2[i5], fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                        f12 = fArr2[i4];
                        f13 = fArr2[i5];
                        f9 = f13;
                        f8 = f12;
                        break;
                    case 'C':
                        i2 = i3;
                        int i6 = i2 + 2;
                        int i7 = i2 + 3;
                        int i8 = i2 + 4;
                        int i9 = i2 + 5;
                        path.cubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i6], fArr2[i7], fArr2[i8], fArr2[i9]);
                        f12 = fArr2[i8];
                        float f17 = fArr2[i9];
                        float f18 = fArr2[i6];
                        float f19 = fArr2[i7];
                        f13 = f17;
                        f9 = f19;
                        f8 = f18;
                        break;
                    case 'H':
                        i2 = i3;
                        int i10 = i2 + 0;
                        path2.lineTo(fArr2[i10], f13);
                        f12 = fArr2[i10];
                        break;
                    case 'L':
                        i2 = i3;
                        int i11 = i2 + 0;
                        int i12 = i2 + 1;
                        path2.lineTo(fArr2[i11], fArr2[i12]);
                        f12 = fArr2[i11];
                        f13 = fArr2[i12];
                        break;
                    case 'M':
                        i2 = i3;
                        int i13 = i2 + 0;
                        f12 = fArr2[i13];
                        int i14 = i2 + 1;
                        f13 = fArr2[i14];
                        if (i2 <= 0) {
                            path2.moveTo(fArr2[i13], fArr2[i14]);
                            f15 = f13;
                            f14 = f12;
                            break;
                        } else {
                            path2.lineTo(fArr2[i13], fArr2[i14]);
                            break;
                        }
                    case 'Q':
                        i2 = i3;
                        int i15 = i2 + 0;
                        int i16 = i2 + 1;
                        int i17 = i2 + 2;
                        int i18 = i2 + 3;
                        path2.quadTo(fArr2[i15], fArr2[i16], fArr2[i17], fArr2[i18]);
                        float f20 = fArr2[i15];
                        float f21 = fArr2[i16];
                        f12 = fArr2[i17];
                        f13 = fArr2[i18];
                        f8 = f20;
                        f9 = f21;
                        break;
                    case 'S':
                        i2 = i3;
                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                            float f22 = (f12 * 2.0f) - f8;
                            f = (f13 * 2.0f) - f9;
                            f2 = f22;
                        } else {
                            f2 = f12;
                            f = f13;
                        }
                        int i19 = i2 + 0;
                        int i20 = i2 + 1;
                        int i21 = i2 + 2;
                        int i22 = i2 + 3;
                        path.cubicTo(f2, f, fArr2[i19], fArr2[i20], fArr2[i21], fArr2[i22]);
                        float f23 = fArr2[i19];
                        float f24 = fArr2[i20];
                        f12 = fArr2[i21];
                        f13 = fArr2[i22];
                        f8 = f23;
                        f9 = f24;
                        break;
                    case 'T':
                        float f25 = f13;
                        float f26 = f12;
                        i2 = i3;
                        if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                            f25 = (f25 * 2.0f) - f9;
                            f26 = (f26 * 2.0f) - f8;
                        }
                        int i23 = i2 + 0;
                        int i24 = i2 + 1;
                        path2.quadTo(f26, f25, fArr2[i23], fArr2[i24]);
                        f12 = fArr2[i23];
                        f8 = f26;
                        f9 = f25;
                        f13 = fArr2[i24];
                        break;
                    case 'V':
                        i2 = i3;
                        int i25 = i2 + 0;
                        path2 = path;
                        path2.lineTo(f12, fArr2[i25]);
                        f12 = f12;
                        f13 = fArr2[i25];
                        break;
                    case 'a':
                        int i26 = i3 + 5;
                        float f27 = fArr2[i26] + f12;
                        int i27 = i3 + 6;
                        float f28 = fArr2[i27] + f13;
                        float f29 = fArr2[i3 + 0];
                        float f30 = fArr2[i3 + 1];
                        float f31 = fArr2[i3 + 2];
                        boolean z3 = fArr2[i3 + 3] != 0.0f ? true : z2;
                        if (fArr2[i3 + 4] != 0.0f) {
                            z = true;
                        } else {
                            boolean z4 = z2 ? 1 : 0;
                            Object[] objArr = z2 ? 1 : 0;
                            Object[] objArr2 = z2 ? 1 : 0;
                            z = z4;
                        }
                        i2 = i3;
                        drawArc(path, f12, f13, f27, f28, f29, f30, f31, z3, z);
                        f12 += fArr2[i26];
                        f13 += fArr2[i27];
                        f9 = f13;
                        f8 = f12;
                        path2 = path;
                        break;
                    case 'c':
                        int i28 = i3 + 2;
                        int i29 = i3 + 3;
                        int i30 = i3 + 4;
                        int i31 = i3 + 5;
                        path.rCubicTo(fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i28], fArr2[i29], fArr2[i30], fArr2[i31]);
                        float f32 = fArr2[i28] + f12;
                        float f33 = fArr2[i29] + f13;
                        f12 += fArr2[i30];
                        f13 += fArr2[i31];
                        f8 = f32;
                        f9 = f33;
                        i2 = i3;
                        break;
                    case 'h':
                        int i32 = i3 + 0;
                        path2.rLineTo(fArr2[i32], 0.0f);
                        f12 += fArr2[i32];
                        i2 = i3;
                        break;
                    case 'l':
                        int i33 = i3 + 0;
                        int i34 = i3 + 1;
                        path2.rLineTo(fArr2[i33], fArr2[i34]);
                        f12 += fArr2[i33];
                        f13 += fArr2[i34];
                        i2 = i3;
                        break;
                    case 'm':
                        int i35 = i3 + 0;
                        f12 += fArr2[i35];
                        int i36 = i3 + 1;
                        f13 += fArr2[i36];
                        if (i3 <= 0) {
                            path2.rMoveTo(fArr2[i35], fArr2[i36]);
                            f15 = f13;
                            f14 = f12;
                            i2 = i3;
                            break;
                        } else {
                            path2.rLineTo(fArr2[i35], fArr2[i36]);
                            i2 = i3;
                            break;
                        }
                    case 'q':
                        int i37 = i3 + 0;
                        int i38 = i3 + 1;
                        int i39 = i3 + 2;
                        int i40 = i3 + 3;
                        path2.rQuadTo(fArr2[i37], fArr2[i38], fArr2[i39], fArr2[i40]);
                        float f34 = fArr2[i37] + f12;
                        float f35 = fArr2[i38] + f13;
                        f12 += fArr2[i39];
                        f13 += fArr2[i40];
                        f8 = f34;
                        f9 = f35;
                        i2 = i3;
                        break;
                    case 's':
                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                            float f36 = f12 - f8;
                            f3 = f13 - f9;
                            f4 = f36;
                        } else {
                            f4 = 0.0f;
                            f3 = 0.0f;
                        }
                        int i41 = i3 + 0;
                        int i42 = i3 + 1;
                        int i43 = i3 + 2;
                        int i44 = i3 + 3;
                        path.rCubicTo(f4, f3, fArr2[i41], fArr2[i42], fArr2[i43], fArr2[i44]);
                        float f37 = fArr2[i41] + f12;
                        float f38 = fArr2[i42] + f13;
                        f12 += fArr2[i43];
                        f13 += fArr2[i44];
                        f8 = f37;
                        f9 = f38;
                        i2 = i3;
                        break;
                    case 't':
                        if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                            f16 = f12 - f8;
                            f5 = f13 - f9;
                        } else {
                            f5 = 0.0f;
                        }
                        int i45 = i3 + 0;
                        int i46 = i3 + 1;
                        path2.rQuadTo(f16, f5, fArr2[i45], fArr2[i46]);
                        float f39 = f16 + f12;
                        float f40 = f5 + f13;
                        f12 += fArr2[i45];
                        f13 += fArr2[i46];
                        f9 = f40;
                        i2 = i3;
                        f8 = f39;
                        break;
                    case 'v':
                        int i47 = i3 + 0;
                        path2.rLineTo(0.0f, fArr2[i47]);
                        f13 += fArr2[i47];
                        i2 = i3;
                        break;
                    default:
                        i2 = i3;
                        f13 = f13;
                        break;
                }
                i3 = i2 + i;
                c3 = c2;
                z2 = false;
            }
            char c4 = z2 ? 1 : 0;
            char c5 = z2 ? 1 : 0;
            char c6 = z2 ? 1 : 0;
            fArr[c4] = f12;
            fArr[1] = f13;
            fArr[2] = f8;
            fArr[3] = f9;
            fArr[4] = f14;
            fArr[5] = f15;
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            double radians = Math.toRadians((double) f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = (double) f;
            double d4 = d3 * cos;
            double d5 = (double) f2;
            double d6 = (double) f5;
            double d7 = (d4 + (d5 * sin)) / d6;
            double d8 = (double) f6;
            double d9 = ((((double) (-f)) * sin) + (d5 * cos)) / d8;
            double d10 = (double) f4;
            double d11 = ((((double) f3) * cos) + (d10 * sin)) / d6;
            double d12 = ((((double) (-f3)) * sin) + (d10 * cos)) / d8;
            double d13 = d7 - d11;
            double d14 = d9 - d12;
            double d15 = (d7 + d11) / 2.0d;
            double d16 = (d9 + d12) / 2.0d;
            double d17 = (d13 * d13) + (d14 * d14);
            if (d17 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d18 = (1.0d / d17) - 0.25d;
            if (d18 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d17);
                float sqrt = (float) (Math.sqrt(d17) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d18);
            double d19 = d13 * sqrt2;
            double d20 = sqrt2 * d14;
            if (z == z2) {
                d2 = d15 - d20;
                d = d16 + d19;
            } else {
                d2 = d15 + d20;
                d = d16 - d19;
            }
            double atan2 = Math.atan2(d9 - d, d7 - d2);
            double atan22 = Math.atan2(d12 - d, d11 - d2) - atan2;
            int i = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
            if (z2 != (i >= 0)) {
                atan22 = i > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d21 = d2 * d6;
            double d22 = d * d8;
            arcToBezier(path, (d21 * cos) - (d22 * sin), (d21 * sin) + (d22 * cos), d6, d8, d3, d5, radians, atan2, atan22);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            double d14 = (d12 * sin2) - (d13 * cos2);
            double d15 = d11 * sin;
            double d16 = d4 * cos;
            double d17 = (sin2 * d15) + (cos2 * d16);
            double d18 = d9 / ((double) ceil);
            double d19 = d5;
            double d20 = d6;
            double d21 = d17;
            double d22 = d14;
            int i = 0;
            double d23 = d8;
            while (i < ceil) {
                double d24 = d23 + d18;
                double sin3 = Math.sin(d24);
                double cos3 = Math.cos(d24);
                double d25 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                double d26 = d2 + (d10 * sin * cos3) + (d16 * sin3);
                double d27 = (d12 * sin3) - (d13 * cos3);
                double d28 = (sin3 * d15) + (cos3 * d16);
                double d29 = d24 - d23;
                double tan = Math.tan(d29 / 2.0d);
                double sin4 = (Math.sin(d29) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                float f = (float) d19;
                float f2 = (float) d20;
                path.rCubicTo(((float) (d19 + (d22 * sin4))) - f, ((float) (d20 + (d21 * sin4))) - f2, ((float) (d25 - (sin4 * d27))) - f, ((float) (d26 - (sin4 * d28))) - f2, ((float) d25) - f, ((float) d26) - f2);
                i++;
                d18 = d18;
                ceil = ceil;
                sin = sin;
                d15 = d15;
                d12 = d12;
                d23 = d24;
                d21 = d28;
                d22 = d27;
                cos = cos;
                d20 = d26;
                d19 = d25;
                d10 = d3;
            }
        }
    }
}
