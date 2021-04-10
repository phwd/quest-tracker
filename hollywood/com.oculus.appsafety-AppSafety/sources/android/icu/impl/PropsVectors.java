package android.icu.impl;

import android.icu.impl.Trie;
import android.icu.impl.TrieBuilder;
import java.util.Arrays;
import java.util.Comparator;

public class PropsVectors {
    public static final int ERROR_VALUE_CP = 1114113;
    public static final int FIRST_SPECIAL_CP = 1114112;
    public static final int INITIAL_ROWS = 4096;
    public static final int INITIAL_VALUE_CP = 1114112;
    public static final int MAX_CP = 1114113;
    public static final int MAX_ROWS = 1114114;
    public static final int MEDIUM_ROWS = 65536;
    private int columns;
    private boolean isCompacted;
    private int maxRows;
    private int prevRow;
    private int rows;
    private int[] v;

    public interface CompactHandler {
        void setRowIndexForErrorValue(int i);

        void setRowIndexForInitialValue(int i);

        void setRowIndexForRange(int i, int i2, int i3);

        void startRealValues(int i);
    }

    private boolean areElementsSame(int index1, int[] target, int index2, int length) {
        for (int i = 0; i < length; i++) {
            if (this.v[index1 + i] != target[index2 + i]) {
                return false;
            }
        }
        return true;
    }

    private int findRow(int rangeStart) {
        int i = this.prevRow;
        int i2 = this.columns;
        int index = i * i2;
        int[] iArr = this.v;
        if (rangeStart >= iArr[index]) {
            if (rangeStart < iArr[index + 1]) {
                return index;
            }
            int index2 = index + i2;
            if (rangeStart < iArr[index2 + 1]) {
                this.prevRow = i + 1;
                return index2;
            }
            int index3 = index2 + i2;
            if (rangeStart < iArr[index3 + 1]) {
                this.prevRow = i + 2;
                return index3;
            } else if (rangeStart - iArr[index3 + 1] < 10) {
                this.prevRow = i + 2;
                do {
                    this.prevRow++;
                    index3 += this.columns;
                } while (rangeStart >= this.v[index3 + 1]);
                return index3;
            }
        } else if (rangeStart < iArr[1]) {
            this.prevRow = 0;
            return 0;
        }
        int start = 0;
        int limit = this.rows;
        while (start < limit - 1) {
            int mid = (start + limit) / 2;
            int index4 = this.columns * mid;
            int[] iArr2 = this.v;
            if (rangeStart < iArr2[index4]) {
                limit = mid;
            } else if (rangeStart < iArr2[index4 + 1]) {
                this.prevRow = mid;
                return index4;
            } else {
                start = mid;
            }
        }
        this.prevRow = start;
        return this.columns * start;
    }

    public PropsVectors(int numOfColumns) {
        if (numOfColumns >= 1) {
            this.columns = numOfColumns + 2;
            this.v = new int[(this.columns * 4096)];
            this.maxRows = 4096;
            this.rows = 3;
            this.prevRow = 0;
            this.isCompacted = false;
            int[] iArr = this.v;
            iArr[0] = 0;
            iArr[1] = 1114112;
            int index = this.columns;
            for (int cp = 1114112; cp <= 1114113; cp++) {
                int[] iArr2 = this.v;
                iArr2[index] = cp;
                iArr2[index + 1] = cp + 1;
                index += this.columns;
            }
            return;
        }
        throw new IllegalArgumentException("numOfColumns need to be no less than 1; but it is " + numOfColumns);
    }

    public void setValue(int start, int end, int column, int value, int mask) {
        int newMaxRows;
        if (start < 0 || start > end || end > 1114113 || column < 0 || column >= this.columns - 2) {
            throw new IllegalArgumentException();
        } else if (!this.isCompacted) {
            int limit = end + 1;
            int column2 = column + 2;
            int value2 = value & mask;
            int firstRow = findRow(start);
            int lastRow = findRow(end);
            int[] iArr = this.v;
            boolean splitLastRow = true;
            boolean splitFirstRow = (start == iArr[firstRow] || value2 == (iArr[firstRow + column2] & mask)) ? false : true;
            int[] iArr2 = this.v;
            if (limit == iArr2[lastRow + 1] || value2 == (iArr2[lastRow + column2] & mask)) {
                splitLastRow = false;
            }
            if (splitFirstRow || splitLastRow) {
                int rowsToExpand = 0;
                if (splitFirstRow) {
                    rowsToExpand = 0 + 1;
                }
                if (splitLastRow) {
                    rowsToExpand++;
                }
                int i = this.rows + rowsToExpand;
                int i2 = this.maxRows;
                if (i > i2) {
                    if (i2 < 65536) {
                        newMaxRows = 65536;
                    } else if (i2 < 1114114) {
                        newMaxRows = MAX_ROWS;
                    } else {
                        throw new IndexOutOfBoundsException("MAX_ROWS exceeded! Increase it to a higher valuein the implementation");
                    }
                    int i3 = this.columns;
                    int[] temp = new int[(newMaxRows * i3)];
                    System.arraycopy((Object) this.v, 0, (Object) temp, 0, this.rows * i3);
                    this.v = temp;
                    this.maxRows = newMaxRows;
                }
                int i4 = this.rows;
                int i5 = this.columns;
                int count = (i4 * i5) - (lastRow + i5);
                if (count > 0) {
                    int[] iArr3 = this.v;
                    System.arraycopy((Object) iArr3, lastRow + i5, (Object) iArr3, lastRow + ((rowsToExpand + 1) * i5), count);
                }
                this.rows += rowsToExpand;
                if (splitFirstRow) {
                    int i6 = this.columns;
                    int[] iArr4 = this.v;
                    System.arraycopy((Object) iArr4, firstRow, (Object) iArr4, i6 + firstRow, (lastRow - firstRow) + i6);
                    int i7 = this.columns;
                    lastRow += i7;
                    int[] iArr5 = this.v;
                    iArr5[firstRow + i7] = start;
                    iArr5[firstRow + 1] = start;
                    firstRow += i7;
                }
                if (splitLastRow) {
                    int[] iArr6 = this.v;
                    int i8 = this.columns;
                    System.arraycopy((Object) iArr6, lastRow, (Object) iArr6, lastRow + i8, i8);
                    int[] iArr7 = this.v;
                    iArr7[this.columns + lastRow] = limit;
                    iArr7[lastRow + 1] = limit;
                }
            }
            this.prevRow = lastRow / this.columns;
            int firstRow2 = firstRow + column2;
            int lastRow2 = lastRow + column2;
            int mask2 = ~mask;
            while (true) {
                int[] iArr8 = this.v;
                iArr8[firstRow2] = (iArr8[firstRow2] & mask2) | value2;
                if (firstRow2 != lastRow2) {
                    firstRow2 += this.columns;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Shouldn't be called aftercompact()!");
        }
    }

    public int getValue(int c, int column) {
        if (this.isCompacted || c < 0 || c > 1114113 || column < 0 || column >= this.columns - 2) {
            return 0;
        }
        return this.v[findRow(c) + 2 + column];
    }

    public int[] getRow(int rowIndex) {
        if (this.isCompacted) {
            throw new IllegalStateException("Illegal Invocation of the method after compact()");
        } else if (rowIndex < 0 || rowIndex > this.rows) {
            throw new IllegalArgumentException("rowIndex out of bound!");
        } else {
            int i = this.columns;
            int[] rowToReturn = new int[(i - 2)];
            System.arraycopy((Object) this.v, (rowIndex * i) + 2, (Object) rowToReturn, 0, i - 2);
            return rowToReturn;
        }
    }

    public int getRowStart(int rowIndex) {
        if (this.isCompacted) {
            throw new IllegalStateException("Illegal Invocation of the method after compact()");
        } else if (rowIndex >= 0 && rowIndex <= this.rows) {
            return this.v[this.columns * rowIndex];
        } else {
            throw new IllegalArgumentException("rowIndex out of bound!");
        }
    }

    public int getRowEnd(int rowIndex) {
        if (this.isCompacted) {
            throw new IllegalStateException("Illegal Invocation of the method after compact()");
        } else if (rowIndex >= 0 && rowIndex <= this.rows) {
            return this.v[(this.columns * rowIndex) + 1] - 1;
        } else {
            throw new IllegalArgumentException("rowIndex out of bound!");
        }
    }

    public void compact(CompactHandler compactor) {
        if (!this.isCompacted) {
            this.isCompacted = true;
            int valueColumns = this.columns - 2;
            Integer[] indexArray = new Integer[this.rows];
            for (int i = 0; i < this.rows; i++) {
                indexArray[i] = Integer.valueOf(this.columns * i);
            }
            Arrays.sort(indexArray, new Comparator<Integer>() {
                /* class android.icu.impl.PropsVectors.AnonymousClass1 */

                public int compare(Integer o1, Integer o2) {
                    int indexOfRow1 = o1.intValue();
                    int indexOfRow2 = o2.intValue();
                    int count = PropsVectors.this.columns;
                    int index = 2;
                    while (PropsVectors.this.v[indexOfRow1 + index] == PropsVectors.this.v[indexOfRow2 + index]) {
                        index++;
                        if (index == PropsVectors.this.columns) {
                            index = 0;
                        }
                        count--;
                        if (count <= 0) {
                            return 0;
                        }
                    }
                    if (PropsVectors.this.v[indexOfRow1 + index] < PropsVectors.this.v[indexOfRow2 + index]) {
                        return -1;
                    }
                    return 1;
                }
            });
            int count = -valueColumns;
            for (int i2 = 0; i2 < this.rows; i2++) {
                int start = this.v[indexArray[i2].intValue()];
                if (count < 0 || !areElementsSame(indexArray[i2].intValue() + 2, this.v, indexArray[i2 - 1].intValue() + 2, valueColumns)) {
                    count += valueColumns;
                }
                if (start == 1114112) {
                    compactor.setRowIndexForInitialValue(count);
                } else if (start == 1114113) {
                    compactor.setRowIndexForErrorValue(count);
                }
            }
            int count2 = count + valueColumns;
            compactor.startRealValues(count2);
            int[] temp = new int[count2];
            int count3 = -valueColumns;
            for (int i3 = 0; i3 < this.rows; i3++) {
                int start2 = this.v[indexArray[i3].intValue()];
                int limit = this.v[indexArray[i3].intValue() + 1];
                if (count3 < 0 || !areElementsSame(indexArray[i3].intValue() + 2, temp, count3, valueColumns)) {
                    count3 += valueColumns;
                    System.arraycopy((Object) this.v, indexArray[i3].intValue() + 2, (Object) temp, count3, valueColumns);
                }
                if (start2 < 1114112) {
                    compactor.setRowIndexForRange(start2, limit - 1, count3);
                }
            }
            this.v = temp;
            this.rows = (count3 / valueColumns) + 1;
        }
    }

    public int[] getCompactedArray() {
        if (this.isCompacted) {
            return this.v;
        }
        throw new IllegalStateException("Illegal Invocation of the method before compact()");
    }

    public int getCompactedRows() {
        if (this.isCompacted) {
            return this.rows;
        }
        throw new IllegalStateException("Illegal Invocation of the method before compact()");
    }

    public int getCompactedColumns() {
        if (this.isCompacted) {
            return this.columns - 2;
        }
        throw new IllegalStateException("Illegal Invocation of the method before compact()");
    }

    public IntTrie compactToTrieWithRowIndexes() {
        PVecToTrieCompactHandler compactor = new PVecToTrieCompactHandler();
        compact(compactor);
        return compactor.builder.serialize(new DefaultGetFoldedValue(compactor.builder), new DefaultGetFoldingOffset());
    }

    private static class DefaultGetFoldingOffset implements Trie.DataManipulate {
        private DefaultGetFoldingOffset() {
        }

        @Override // android.icu.impl.Trie.DataManipulate
        public int getFoldingOffset(int value) {
            return value;
        }
    }

    private static class DefaultGetFoldedValue implements TrieBuilder.DataManipulate {
        private IntTrieBuilder builder;

        public DefaultGetFoldedValue(IntTrieBuilder inBuilder) {
            this.builder = inBuilder;
        }

        @Override // android.icu.impl.TrieBuilder.DataManipulate
        public int getFoldedValue(int start, int offset) {
            int initialValue = this.builder.m_initialValue_;
            int limit = start + 1024;
            while (start < limit) {
                boolean[] inBlockZero = new boolean[1];
                int value = this.builder.getValue(start, inBlockZero);
                if (inBlockZero[0]) {
                    start += 32;
                } else if (value != initialValue) {
                    return offset;
                } else {
                    start++;
                }
            }
            return 0;
        }
    }
}
