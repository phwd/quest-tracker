package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.cY  reason: case insensitive filesystem */
public final class C0310cY implements Serializable, Cloneable {
    public static final long serialVersionUID = 1;
    public String argName;
    public String description;
    public String longOpt;
    public int numberOfArgs = -1;
    public final String opt;
    public boolean optionalArg;
    public final boolean required;
    public Class<?> type = String.class;
    public List<String> values = new ArrayList();
    public char valuesep;

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r1.equals(r5.opt) == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r3 = 1
            if (r4 == r5) goto L_0x0034
            r2 = 0
            if (r5 == 0) goto L_0x001e
            java.lang.Class r1 = r4.getClass()
            java.lang.Class r0 = r5.getClass()
            if (r1 != r0) goto L_0x001e
            X.cY r5 = (X.C0310cY) r5
            java.lang.String r1 = r4.opt
            if (r1 == 0) goto L_0x001f
            java.lang.String r0 = r5.opt
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0024
        L_0x001e:
            return r2
        L_0x001f:
            java.lang.String r0 = r5.opt
            if (r0 == 0) goto L_0x0024
            return r2
        L_0x0024:
            java.lang.String r1 = r4.longOpt
            java.lang.String r0 = r5.longOpt
            if (r1 == 0) goto L_0x0031
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0034
            return r2
        L_0x0031:
            if (r0 == 0) goto L_0x0034
            return r2
        L_0x0034:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0310cY.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        String str = this.opt;
        int i2 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        String str2 = this.longOpt;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return i3 + i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
            r3 = this;
            java.lang.String r0 = "[ option: "
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            java.lang.String r0 = r3.opt
            r2.append(r0)
            java.lang.String r1 = r3.longOpt
            java.lang.String r0 = " "
            if (r1 == 0) goto L_0x0018
            r2.append(r0)
            r2.append(r1)
        L_0x0018:
            r2.append(r0)
            int r1 = r3.numberOfArgs
            r0 = 1
            if (r1 > r0) goto L_0x004a
            r0 = -2
            if (r1 == r0) goto L_0x004a
            if (r1 > 0) goto L_0x0027
            if (r1 != r0) goto L_0x002c
        L_0x0027:
            java.lang.String r0 = " [ARG]"
        L_0x0029:
            r2.append(r0)
        L_0x002c:
            java.lang.String r1 = " :: "
            r2.append(r1)
            java.lang.String r0 = r3.description
            r2.append(r0)
            java.lang.Class<?> r0 = r3.type
            if (r0 == 0) goto L_0x0040
            r2.append(r1)
            r2.append(r0)
        L_0x0040:
            java.lang.String r0 = " ]"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            return r0
        L_0x004a:
            java.lang.String r0 = "[ARG...]"
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0310cY.toString():java.lang.String");
    }

    public C0310cY(String str, String str2, boolean z, String str3) throws IllegalArgumentException {
        char c;
        StringBuilder sb;
        if (str != null) {
            if (str.length() == 1) {
                c = str.charAt(0);
                sb = (Character.isJavaIdentifierPart(c) || c == '?' || c == '@') ? sb : new StringBuilder("Illegal option name '");
            } else {
                char[] charArray = str.toCharArray();
                int length = charArray.length;
                for (int i = 0; i < length; i++) {
                    c = charArray[i];
                    if (!Character.isJavaIdentifierPart(c)) {
                        sb = new StringBuilder("The option '");
                        sb.append(str);
                        sb.append("' contains an illegal ");
                        sb.append("character : '");
                    }
                }
            }
            sb.append(c);
            sb.append("'");
            throw new IllegalArgumentException(sb.toString());
        }
        this.opt = str;
        this.longOpt = str2;
        if (z) {
            this.numberOfArgs = 1;
        }
        this.description = str3;
    }

    @Override // java.lang.Object
    public final Object clone() {
        try {
            C0310cY cYVar = (C0310cY) super.clone();
            cYVar.values = new ArrayList(this.values);
            return cYVar;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(AnonymousClass06.A04("A CloneNotSupportedException was thrown: ", e.getMessage()));
        }
    }
}
