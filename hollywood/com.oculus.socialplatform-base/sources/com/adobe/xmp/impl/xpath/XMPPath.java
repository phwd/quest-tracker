package com.adobe.xmp.impl.xpath;

import java.util.ArrayList;
import java.util.List;

public class XMPPath {
    public static final int ARRAY_INDEX_STEP = 3;
    public static final int ARRAY_LAST_STEP = 4;
    public static final int FIELD_SELECTOR_STEP = 6;
    public static final int QUALIFIER_STEP = 2;
    public static final int QUAL_SELECTOR_STEP = 5;
    public static final int SCHEMA_NODE = Integer.MIN_VALUE;
    public static final int STEP_ROOT_PROP = 1;
    public static final int STEP_SCHEMA = 0;
    public static final int STRUCT_FIELD_STEP = 1;
    public List segments = new ArrayList(5);

    public void add(XMPPathSegment xMPPathSegment) {
        this.segments.add(xMPPathSegment);
    }

    public XMPPathSegment getSegment(int i) {
        return (XMPPathSegment) this.segments.get(i);
    }

    public int size() {
        return this.segments.size();
    }

    public String toString() {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 1; i2 < this.segments.size(); i2++) {
            stringBuffer.append(getSegment(i2));
            if (i2 < this.segments.size() - 1 && ((i = getSegment(i2 + 1).kind) == 1 || i == 2)) {
                stringBuffer.append('/');
            }
        }
        return stringBuffer.toString();
    }
}
