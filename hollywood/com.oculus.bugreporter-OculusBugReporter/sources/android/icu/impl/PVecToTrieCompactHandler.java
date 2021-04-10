package android.icu.impl;

import android.icu.impl.PropsVectors;

public class PVecToTrieCompactHandler implements PropsVectors.CompactHandler {
    public IntTrieBuilder builder;
    public int initialValue;

    @Override // android.icu.impl.PropsVectors.CompactHandler
    public void setRowIndexForErrorValue(int rowIndex) {
    }

    @Override // android.icu.impl.PropsVectors.CompactHandler
    public void setRowIndexForInitialValue(int rowIndex) {
        this.initialValue = rowIndex;
    }

    @Override // android.icu.impl.PropsVectors.CompactHandler
    public void setRowIndexForRange(int start, int end, int rowIndex) {
        this.builder.setRange(start, end + 1, rowIndex, true);
    }

    @Override // android.icu.impl.PropsVectors.CompactHandler
    public void startRealValues(int rowIndex) {
        if (rowIndex <= 65535) {
            int i = this.initialValue;
            this.builder = new IntTrieBuilder(null, 100000, i, i, false);
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
