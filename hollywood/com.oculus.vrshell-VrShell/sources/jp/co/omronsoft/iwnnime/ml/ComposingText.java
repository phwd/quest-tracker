package jp.co.omronsoft.iwnnime.ml;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class ComposingText {
    public static final int LAYER0 = 0;
    public static final int LAYER1 = 1;
    public static final int LAYER2 = 2;
    public static final int MAX_LAYER = 3;
    protected int[] mCursor = new int[3];
    protected ArrayList<StrSegment>[] mStringLayer = new ArrayList[3];

    public ComposingText() {
        for (int i = 0; i < 3; i++) {
            this.mStringLayer[i] = new ArrayList<>();
            this.mCursor[i] = 0;
        }
    }

    public void debugout() {
        for (int i = 0; i < 3; i++) {
            Log.d("IWnnIME", "ComposingText[" + i + "]");
            StringBuilder sb = new StringBuilder();
            sb.append("  cur = ");
            sb.append(this.mCursor[i]);
            Log.d("IWnnIME", sb.toString());
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<StrSegment> it = this.mStringLayer[i].iterator();
            while (it.hasNext()) {
                StrSegment next = it.next();
                stringBuffer.append("(");
                stringBuffer.append(next.string);
                stringBuffer.append(",");
                stringBuffer.append(next.from);
                stringBuffer.append(",");
                stringBuffer.append(next.to);
                stringBuffer.append(")");
            }
            Log.d("IWnnIME", "  str = " + stringBuffer.toString());
        }
    }

    public StrSegment getStrSegment(int i, int i2) {
        try {
            ArrayList<StrSegment> arrayList = this.mStringLayer[i];
            if (i2 < 0) {
                i2 = arrayList.size() - 1;
            }
            if (i2 < arrayList.size()) {
                if (i2 >= 0) {
                    return arrayList.get(i2);
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public String toString(int i, int i2, int i3) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<StrSegment> arrayList = this.mStringLayer[i];
            while (i2 <= i3) {
                stringBuffer.append(arrayList.get(i2).string);
                i2++;
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString(int i) {
        return toString(i, 0, this.mStringLayer[i].size() - 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        r6 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void modifyUpper(int r12, int r13, int r14, int r15) {
        /*
        // Method dump skipped, instructions count: 232
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.co.omronsoft.iwnnime.ml.ComposingText.modifyUpper(int, int, int, int):void");
    }

    public void insertStrSegment(int i, StrSegment strSegment) {
        int i2 = this.mCursor[i];
        this.mStringLayer[i].add(i2, strSegment);
        modifyUpper(i, i2, 1, 0);
        setCursor(i, i2 + 1);
    }

    public void insertStrSegment(int i, int i2, StrSegment strSegment) {
        this.mStringLayer[i].add(this.mCursor[i], strSegment);
        int[] iArr = this.mCursor;
        iArr[i] = iArr[i] + 1;
        for (int i3 = i + 1; i3 <= i2; i3++) {
            int i4 = this.mCursor[i3 - 1] - 1;
            StrSegment strSegment2 = new StrSegment(strSegment.string, i4, i4);
            ArrayList<StrSegment> arrayList = this.mStringLayer[i3];
            arrayList.add(this.mCursor[i3], strSegment2);
            int[] iArr2 = this.mCursor;
            iArr2[i3] = iArr2[i3] + 1;
            for (int i5 = iArr2[i3]; i5 < arrayList.size(); i5++) {
                StrSegment strSegment3 = arrayList.get(i5);
                strSegment3.from++;
                strSegment3.to++;
            }
        }
        int i6 = this.mCursor[i2];
        modifyUpper(i2, i6 - 1, 1, 0);
        setCursor(i2, i6);
    }

    /* access modifiers changed from: protected */
    public void replaceStrSegment0(int i, StrSegment[] strSegmentArr, int i2, int i3) {
        ArrayList<StrSegment> arrayList = this.mStringLayer[i];
        if (i2 < 0 || i2 > arrayList.size()) {
            i2 = arrayList.size();
        }
        if (i3 < 0 || i3 > arrayList.size()) {
            i3 = arrayList.size();
        }
        for (int i4 = i2; i4 <= i3; i4++) {
            arrayList.remove(i2);
        }
        for (int length = strSegmentArr.length - 1; length >= 0; length--) {
            arrayList.add(i2, strSegmentArr[length]);
        }
        modifyUpper(i, i2, strSegmentArr.length, (i3 - i2) + 1);
    }

    public void replaceStrSegmentCurrentCursor(int i, StrSegment[] strSegmentArr) {
        replaceStrSegment0(i, strSegmentArr, 0, this.mStringLayer[i].size() - 1);
    }

    public void replaceStrSegment(int i, StrSegment[] strSegmentArr, int i2) {
        int i3 = this.mCursor[i];
        replaceStrSegment0(i, strSegmentArr, i3 - i2, i3 - 1);
        setCursor(i, (i3 + strSegmentArr.length) - i2);
    }

    public void replaceStrSegment(int i, StrSegment[] strSegmentArr) {
        int i2 = this.mCursor[i];
        int i3 = i2 - 1;
        replaceStrSegment0(i, strSegmentArr, i3, i3);
        setCursor(i, (i2 + strSegmentArr.length) - 1);
    }

    public void deleteStrSegment(int i, int i2, int i3) {
        int[] iArr = {-1, -1, -1};
        int[] iArr2 = {-1, -1, -1};
        ArrayList<StrSegment>[] arrayListArr = this.mStringLayer;
        ArrayList<StrSegment> arrayList = arrayListArr[2];
        ArrayList<StrSegment> arrayList2 = arrayListArr[1];
        if (i == 2) {
            iArr[2] = i2;
            iArr2[2] = i3;
            iArr[1] = arrayList.get(i2).from;
            iArr2[1] = arrayList.get(i3).to;
            iArr[0] = arrayList2.get(iArr[1]).from;
            iArr2[0] = arrayList2.get(iArr2[1]).to;
        } else if (i == 1) {
            iArr[1] = i2;
            iArr2[1] = i3;
            iArr[0] = arrayList2.get(i2).from;
            iArr2[0] = arrayList2.get(i3).to;
        } else {
            iArr[0] = i2;
            iArr2[0] = i3;
        }
        int i4 = (i3 - i2) + 1;
        for (int i5 = 0; i5 < 3; i5++) {
            if (iArr[i5] >= 0) {
                deleteStrSegment0(i5, iArr[i5], iArr2[i5], i4);
            } else {
                ArrayList<StrSegment> arrayList3 = this.mStringLayer[i5];
                int i6 = -1;
                int i7 = -1;
                int i8 = 0;
                while (true) {
                    if (i8 >= arrayList3.size()) {
                        break;
                    }
                    StrSegment strSegment = arrayList3.get(i8);
                    int i9 = i5 - 1;
                    if ((strSegment.from >= iArr[i9] && strSegment.from <= iArr2[i9]) || (strSegment.to >= iArr[i9] && strSegment.to <= iArr2[i9])) {
                        if (iArr[i5] < 0) {
                            iArr[i5] = i8;
                            i6 = strSegment.from;
                        }
                        iArr2[i5] = i8;
                        i7 = strSegment.to;
                    } else if (strSegment.from <= iArr[i9] && strSegment.to >= iArr2[i9]) {
                        i6 = strSegment.from;
                        i7 = strSegment.to;
                        iArr[i5] = i8;
                        iArr2[i5] = i8;
                        break;
                    } else if (strSegment.from > iArr2[i9]) {
                        break;
                    }
                    i8++;
                }
                int i10 = i5 - 1;
                if (i6 == iArr[i10] && i7 == iArr2[i10]) {
                    deleteStrSegment0(i5, iArr[i5], iArr2[i5], i4);
                } else {
                    deleteStrSegment0(i5, iArr[i5] + 1, iArr2[i5], i4);
                    replaceStrSegment0(i5, new StrSegment[]{new StrSegment(toString(i10), i6, i7 - i4)}, iArr[i5], iArr[i5]);
                    return;
                }
            }
            i4 = (iArr2[i5] - iArr[i5]) + 1;
        }
    }

    private void deleteStrSegment0(int i, int i2, int i3, int i4) {
        ArrayList<StrSegment> arrayList = this.mStringLayer[i];
        if (i4 != 0) {
            for (int i5 = i3 + 1; i5 < arrayList.size(); i5++) {
                StrSegment strSegment = arrayList.get(i5);
                strSegment.from -= i4;
                strSegment.to -= i4;
            }
        }
        for (int i6 = i2; i6 <= i3; i6++) {
            arrayList.remove(i2);
        }
    }

    public int delete(int i, boolean z) {
        int i2 = this.mCursor[i];
        ArrayList<StrSegment> arrayList = this.mStringLayer[i];
        if (!z && i2 > 0) {
            int i3 = i2 - 1;
            deleteStrSegment(i, i3, i3);
            setCursor(i, i3);
        } else if (z && i2 < arrayList.size()) {
            deleteStrSegment(i, i2, i2);
            setCursor(i, i2);
        }
        return arrayList.size();
    }

    public int deleteForward(int i) {
        int i2 = this.mCursor[i];
        ArrayList<StrSegment> arrayList = this.mStringLayer[i];
        if (arrayList.size() > i2) {
            deleteStrSegment(i, i2, i2);
            setCursor(i, i2);
        }
        return arrayList.size();
    }

    private int included(int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        ArrayList<StrSegment> arrayList = this.mStringLayer[i + 1];
        while (i3 < arrayList.size()) {
            StrSegment strSegment = arrayList.get(i3);
            if (strSegment.from <= i2 && i2 <= strSegment.to) {
                break;
            }
            i3++;
        }
        return i3;
    }

    public int setCursor(int i, int i2) {
        if (i2 > this.mStringLayer[i].size()) {
            i2 = this.mStringLayer[i].size();
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i == 0) {
            int[] iArr = this.mCursor;
            iArr[0] = i2;
            iArr[1] = included(0, i2);
            int[] iArr2 = this.mCursor;
            iArr2[2] = included(1, iArr2[1]);
        } else if (i == 1) {
            this.mCursor[2] = included(1, i2);
            int[] iArr3 = this.mCursor;
            iArr3[1] = i2;
            iArr3[0] = i2 > 0 ? this.mStringLayer[1].get(i2 - 1).to + 1 : 0;
        } else {
            int[] iArr4 = this.mCursor;
            iArr4[2] = i2;
            iArr4[1] = i2 > 0 ? this.mStringLayer[2].get(i2 - 1).to + 1 : 0;
            int[] iArr5 = this.mCursor;
            iArr5[0] = iArr5[1] > 0 ? this.mStringLayer[1].get(iArr5[1] - 1).to + 1 : 0;
        }
        return i2;
    }

    public int moveCursor(int i, int i2) {
        return setCursor(i, this.mCursor[i] + i2);
    }

    public int getCursor(int i) {
        return this.mCursor[i];
    }

    public int size(int i) {
        return this.mStringLayer[i].size();
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            this.mStringLayer[i].clear();
            this.mCursor[i] = 0;
        }
    }
}
