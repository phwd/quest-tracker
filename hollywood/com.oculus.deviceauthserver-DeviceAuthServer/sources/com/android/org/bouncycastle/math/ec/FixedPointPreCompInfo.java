package com.android.org.bouncycastle.math.ec;

public class FixedPointPreCompInfo implements PreCompInfo {
    protected ECLookupTable lookupTable = null;
    protected ECPoint offset = null;
    protected int width = -1;

    public ECLookupTable getLookupTable() {
        return this.lookupTable;
    }

    public void setLookupTable(ECLookupTable lookupTable2) {
        this.lookupTable = lookupTable2;
    }

    public ECPoint getOffset() {
        return this.offset;
    }

    public void setOffset(ECPoint offset2) {
        this.offset = offset2;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width2) {
        this.width = width2;
    }
}
