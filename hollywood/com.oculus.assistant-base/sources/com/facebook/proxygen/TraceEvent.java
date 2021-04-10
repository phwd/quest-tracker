package com.facebook.proxygen;

import X.AnonymousClass08;
import java.util.Map;

public class TraceEvent extends NativeHandleImpl {
    public final long mEnd;
    public final int mID;
    public final String mName;
    public final int mParentID;
    public final long mStart;

    public native void close();

    public native Map getMetaData();

    public String toPrettyJson() {
        StringBuilder sb;
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb2 = new StringBuilder("{\n  \"name\":\"");
        sb2.append(this.mName);
        sb2.append("\",\n  \"id\":");
        sb2.append(this.mID);
        sb2.append(",\n  \"parentID\":");
        sb2.append(this.mParentID);
        sb2.append(",\n  \"start\":");
        sb2.append(this.mStart);
        sb2.append(",\n  \"end\":");
        sb2.append(this.mEnd);
        sb2.append(",\n  \"metaData\":{\n");
        stringBuffer.append(sb2.toString());
        boolean z = true;
        for (Map.Entry entry : getMetaData().entrySet()) {
            if (!z) {
                stringBuffer.append(",\n");
            }
            stringBuffer.append(AnonymousClass08.A05("    \"", (String) entry.getKey(), "\":\""));
            if (((String) entry.getValue()).length() > 100) {
                sb = new StringBuilder();
                sb.append(((String) entry.getValue()).substring(0, 97));
                str = "...\"";
            } else {
                sb = new StringBuilder();
                sb.append((String) entry.getValue());
                str = "\"";
            }
            sb.append(str);
            stringBuffer.append(sb.toString());
            z = false;
        }
        stringBuffer.append("\n  }\n}");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder("TraceEvent(name='");
        sb.append(this.mName);
        sb.append("', id='");
        sb.append(this.mID);
        sb.append("', parentID='");
        sb.append(this.mParentID);
        sb.append("', start='");
        sb.append(this.mStart);
        sb.append("', end='");
        sb.append(this.mEnd);
        sb.append("', metaData='{");
        stringBuffer.append(sb.toString());
        for (Map.Entry entry : getMetaData().entrySet()) {
            stringBuffer.append(AnonymousClass08.A06((String) entry.getKey(), ": ", (String) entry.getValue(), ", "));
        }
        stringBuffer.append("}')");
        return stringBuffer.toString();
    }

    public void finalize() {
        close();
        super.finalize();
    }

    public long getEnd() {
        return this.mEnd;
    }

    public int getId() {
        return this.mID;
    }

    public String getName() {
        return this.mName;
    }

    public int getParentID() {
        return this.mParentID;
    }

    public long getStart() {
        return this.mStart;
    }

    public TraceEvent(String str) {
        this.mName = str;
        this.mID = 0;
        this.mParentID = 0;
        this.mStart = 0;
        this.mEnd = 0;
    }

    public TraceEvent(String str, int i, int i2, long j, long j2) {
        this.mName = str;
        this.mID = i;
        this.mParentID = i2;
        this.mStart = j;
        this.mEnd = j2;
    }
}
