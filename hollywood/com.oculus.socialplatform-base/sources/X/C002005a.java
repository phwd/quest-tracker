package X;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/* renamed from: X.05a  reason: invalid class name and case insensitive filesystem */
public final class C002005a extends AnonymousClass0HX {
    public boolean A00 = false;
    public char[] A01;
    public AbstractC03650oF A02;
    public Reader A03;
    public final int A04;
    public final C03890ok A05;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:427:0x0089 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:83:0x0168 */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0429, code lost:
        if (r10 != false) goto L_0x042b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x050e, code lost:
        if (r1 != '.') goto L_0x052e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x0510, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0511, code lost:
        if (r14 >= r11) goto L_0x0429;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x0513, code lost:
        r0 = r14 + 1;
        r1 = r5[r14];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x0517, code lost:
        if (r1 < '0') goto L_0x051f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x0519, code lost:
        if (r1 > '9') goto L_0x051f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x051b, code lost:
        r4 = r4 + 1;
        r14 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x051f, code lost:
        if (r4 != 0) goto L_0x0530;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x0521, code lost:
        A17(r1, "Decimal point not followed by a digit");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x052d, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x052e, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x0530, code lost:
        r14 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x0533, code lost:
        if (r1 == 'e') goto L_0x0539;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x0537, code lost:
        if (r1 != 'E') goto L_0x063c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x0539, code lost:
        if (r14 >= r11) goto L_0x0429;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x053b, code lost:
        r2 = r14 + 1;
        r1 = r5[r14];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x053f, code lost:
        if (r1 == '-') goto L_0x0554;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x0543, code lost:
        if (r1 == '+') goto L_0x0554;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0545, code lost:
        r14 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x0546, code lost:
        if (r1 > '9') goto L_0x062d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x0548, code lost:
        if (r1 < '0') goto L_0x062d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x054a, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x054c, code lost:
        if (r14 >= r11) goto L_0x0429;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x054e, code lost:
        r0 = r14 + 1;
        r1 = r5[r14];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0552, code lost:
        r14 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x0554, code lost:
        if (r2 >= r11) goto L_0x0429;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x0556, code lost:
        r0 = r2 + 1;
        r1 = r5[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x062d, code lost:
        if (r8 != 0) goto L_0x063c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x062f, code lost:
        A17(r1, "Exponent indicator not followed by a digit");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x063b, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x063c, code lost:
        r1 = r14 - 1;
        r17.A04 = r1;
        r17.A0N.A09(r5, r6, r1 - r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:399:0x0646, code lost:
        if (r4 >= 1) goto L_0x0663;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x0648, code lost:
        if (r8 >= 1) goto L_0x0663;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x0656, code lost:
        if (r5 < 1) goto L_0x0658;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0658, code lost:
        r17.A0I = r10;
        r17.A05 = r7;
        r17.A06 = 0;
        r0 = X.EnumC03640oE.VALUE_NUMBER_INT;
     */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x028e  */
    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.EnumC03640oE A0j() throws java.io.IOException, X.C02290iR {
        /*
        // Method dump skipped, instructions count: 1740
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C002005a.A0j():X.0oE");
    }

    private final char A00(String str) throws IOException, C02290iR {
        if (super.A04 < super.A03 || A18()) {
            char[] cArr = this.A01;
            int i = super.A04;
            super.A04 = i + 1;
            return cArr[i];
        }
        A0y(str);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private int A01() throws IOException, C02290iR {
        while (true) {
            if (super.A04 < super.A03 || A18()) {
                char[] cArr = this.A01;
                int i = super.A04;
                int i2 = i + 1;
                super.A04 = i2;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    A04();
                } else if (c == ' ') {
                    continue;
                } else if (c == '\n') {
                    super.A01++;
                    super.A02 = i2;
                } else if (c == '\r') {
                    A06();
                } else if (c != '\t') {
                    A0u(c);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                throw new C02290iR(AnonymousClass006.A09("Unexpected end-of-input within/between ", this.A0D.A00(), " entries"), A0V());
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r8v0 ??, r8v1 ??, r8v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    private final X.EnumC03640oE A02(
/*
[142] Method generation error in method: X.05a.A02(int, boolean):X.0oE, file: classes.dex
    jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v0 ??
    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:228)
    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:190)
    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:145)
    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:337)
    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
    	at java.util.ArrayList.forEach(ArrayList.java:1259)
    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
    
*/

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String A03(int r7, int r8, int r9) throws java.io.IOException, X.C02290iR {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C002005a.A03(int, int, int):java.lang.String");
    }

    private void A04() throws IOException, C02290iR {
        if (!A0L(EnumC03610oA.ALLOW_COMMENTS)) {
            A0v(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (super.A04 < super.A03 || A18()) {
            char[] cArr = this.A01;
            int i = super.A04;
            int i2 = i + 1;
            super.A04 = i2;
            char c = cArr[i];
            if (c == '/') {
                while (true) {
                    if (i2 < super.A03 || A18()) {
                        char[] cArr2 = this.A01;
                        int i3 = super.A04;
                        i2 = i3 + 1;
                        super.A04 = i2;
                        char c2 = cArr2[i3];
                        if (c2 < ' ') {
                            if (c2 == '\n') {
                                super.A01++;
                                super.A02 = i2;
                                return;
                            } else if (c2 == '\r') {
                                A06();
                                return;
                            } else if (c2 != '\t') {
                                A0u(c2);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        }
                    } else {
                        return;
                    }
                }
            } else if (c != '*') {
                A0v(c, "was expecting either '*' or '/' for a comment");
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                while (true) {
                    if (super.A04 >= super.A03 && !A18()) {
                        break;
                    }
                    char[] cArr3 = this.A01;
                    int i4 = super.A04;
                    int i5 = i4 + 1;
                    super.A04 = i5;
                    char c3 = cArr3[i4];
                    if (c3 <= '*') {
                        if (c3 == '*') {
                            if (i5 >= super.A03 && !A18()) {
                                break;
                            }
                            char[] cArr4 = this.A01;
                            int i6 = super.A04;
                            if (cArr4[i6] == '/') {
                                super.A04 = i6 + 1;
                                return;
                            }
                        } else if (c3 >= ' ') {
                            continue;
                        } else if (c3 == '\n') {
                            super.A01++;
                            super.A02 = i5;
                        } else if (c3 == '\r') {
                            A06();
                        } else if (c3 != '\t') {
                            A0u(c3);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    }
                }
                A0y(" in a comment");
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            A0y(" in a comment");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private final void A05() throws IOException, C02290iR {
        int i = super.A04;
        int i2 = super.A03;
        if (i < i2) {
            int[] iArr = C03730oR.A04;
            int length = iArr.length;
            while (true) {
                char[] cArr = this.A01;
                char c = cArr[i];
                if (c >= length || iArr[c] == 0) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } else if (c == '\"') {
                    this.A0N.A09(cArr, i, i - i);
                    super.A04 = i + 1;
                    return;
                }
            }
        }
        C03970ou r3 = this.A0N;
        char[] cArr2 = this.A01;
        int i3 = i - i;
        r3.A08 = null;
        r3.A02 = -1;
        r3.A01 = 0;
        r3.A04 = null;
        r3.A09 = null;
        if (r3.A06) {
            C03970ou.A00(r3);
        } else if (r3.A07 == null) {
            r3.A07 = C03970ou.A03(r3, i3);
        }
        r3.A03 = 0;
        r3.A00 = 0;
        r3.A08(cArr2, i, i3);
        super.A04 = i;
        char[] A0D = r3.A0D();
        int i4 = r3.A00;
        while (true) {
            if (super.A04 < super.A03 || A18()) {
                char[] cArr3 = this.A01;
                int i5 = super.A04;
                super.A04 = i5 + 1;
                char c2 = cArr3[i5];
                if (c2 <= '\\') {
                    if (c2 == '\\') {
                        c2 = A10();
                    } else if (c2 <= '\"') {
                        if (c2 == '\"') {
                            r3.A00 = i4;
                            return;
                        } else if (c2 < ' ') {
                            A0w(c2, "string value");
                        }
                    }
                }
                if (i4 >= A0D.length) {
                    A0D = r3.A0C();
                    i4 = 0;
                }
                A0D[i4] = c2;
                i4++;
            } else {
                A0y(": was expecting closing quote for a string value");
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    private final void A06() throws IOException {
        if (super.A04 < super.A03 || A18()) {
            char[] cArr = this.A01;
            int i = super.A04;
            if (cArr[i] == '\n') {
                super.A04 = i + 1;
            }
        }
        super.A01++;
        super.A02 = super.A04;
    }

    private final void A07(String str) throws IOException, C02290iR {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (super.A04 >= super.A03 && !A18()) {
                break;
            }
            char c = this.A01[super.A04];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            super.A04++;
            sb.append(c);
        }
        A0x(AnonymousClass006.A09("Unrecognized token '", sb.toString(), "': was expecting "));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC02280iQ
    public final int A0M(Writer writer) throws IOException {
        int i = super.A03;
        int i2 = super.A04;
        int i3 = i - i2;
        if (i3 < 1) {
            return 0;
        }
        writer.write(this.A01, i2, i3);
        return i3;
    }

    @Override // X.AbstractC02280iQ
    public final String A0P() throws IOException, C02290iR {
        if (((AbstractC01190Sv) this).A00 != EnumC03640oE.VALUE_STRING) {
            return super.A0n(null);
        }
        if (this.A00) {
            this.A00 = false;
            A05();
        }
        return this.A0N.A05();
    }

    @Override // X.AbstractC02280iQ
    public final String A0Q() throws IOException, C02290iR {
        AnonymousClass0iO A022;
        if (((AbstractC01190Sv) this).A00 == EnumC03640oE.FIELD_NAME) {
            this.A0H = false;
            EnumC03640oE r1 = this.A0C;
            this.A0C = null;
            ((AbstractC01190Sv) this).A00 = r1;
            if (r1 == EnumC03640oE.VALUE_STRING) {
                if (this.A00) {
                    this.A00 = false;
                    A05();
                }
                return this.A0N.A05();
            }
            if (r1 == EnumC03640oE.START_ARRAY) {
                A022 = this.A0D.A01(this.A09, this.A08);
            } else if (r1 == EnumC03640oE.START_OBJECT) {
                A022 = this.A0D.A02(this.A09, this.A08);
            }
            this.A0D = A022;
        } else if (A0j() == EnumC03640oE.VALUE_STRING) {
            return A0m();
        }
        return null;
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final int A0d() throws IOException, C02290iR {
        EnumC03640oE r3 = ((AbstractC01190Sv) this).A00;
        if (r3 == null) {
            return 0;
        }
        int i = C03840oe.A00[r3.ordinal()];
        if (i == 1) {
            return this.A0D.A02.length();
        }
        if (i != 2) {
            if (!(i == 3 || i == 4)) {
                return r3.asCharArray().length;
            }
        } else if (this.A00) {
            this.A00 = false;
            A05();
        }
        return this.A0N.A04();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r1 != 4) goto L_0x0019;
     */
    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A0e() throws java.io.IOException, X.C02290iR {
        /*
            r3 = this;
            X.0oE r0 = r3.A00
            r2 = 0
            if (r0 == 0) goto L_0x0019
            int[] r1 = X.C03840oe.A00
            int r0 = r0.ordinal()
            r1 = r1[r0]
            r0 = 1
            if (r1 == r0) goto L_0x0019
            r0 = 2
            if (r1 == r0) goto L_0x001a
            r0 = 3
            if (r1 == r0) goto L_0x0023
            r0 = 4
            if (r1 == r0) goto L_0x0023
        L_0x0019:
            return r2
        L_0x001a:
            boolean r0 = r3.A00
            if (r0 == 0) goto L_0x0023
            r3.A00 = r2
            r3.A05()
        L_0x0023:
            X.0ou r0 = r3.A0N
            int r0 = r0.A02
            if (r0 >= 0) goto L_0x002a
            r0 = 0
        L_0x002a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C002005a.A0e():int");
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final String A0m() throws IOException, C02290iR {
        EnumC03640oE r2 = ((AbstractC01190Sv) this).A00;
        if (r2 == EnumC03640oE.VALUE_STRING) {
            if (this.A00) {
                this.A00 = false;
                A05();
            }
        } else if (r2 == null) {
            return null;
        } else {
            int i = C03840oe.A00[r2.ordinal()];
            if (i == 1) {
                return this.A0D.A02;
            }
            if (!(i == 2 || i == 3 || i == 4)) {
                return r2.asString();
            }
        }
        return this.A0N.A05();
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final String A0n(String str) throws IOException, C02290iR {
        if (((AbstractC01190Sv) this).A00 != EnumC03640oE.VALUE_STRING) {
            return super.A0n(str);
        }
        if (this.A00) {
            this.A00 = false;
            A05();
        }
        return this.A0N.A05();
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final byte[] A0r(AnonymousClass0o2 r10) throws IOException, C02290iR {
        byte[] bArr;
        EnumC03640oE r2 = ((AbstractC01190Sv) this).A00;
        if (r2 != EnumC03640oE.VALUE_STRING && (r2 != EnumC03640oE.VALUE_EMBEDDED_OBJECT || this.A0L == null)) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(r2);
            sb.append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
            A0x(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (this.A00) {
            try {
                C03940or r0 = this.A0E;
                if (r0 == null) {
                    this.A0E = new C03940or();
                } else {
                    r0.A01();
                }
                C03940or r4 = this.A0E;
                while (true) {
                    if (super.A04 >= super.A03) {
                        A14();
                    }
                    char[] cArr = this.A01;
                    int i = super.A04;
                    super.A04 = i + 1;
                    char c = cArr[i];
                    if (c > ' ') {
                        int A012 = r10.A01(c);
                        if (A012 < 0) {
                            if (c == '\"') {
                                bArr = r4.A05();
                                break;
                            }
                            A012 = A11(r10, c, 0);
                            if (A012 < 0) {
                            }
                        }
                        if (super.A04 >= super.A03) {
                            A14();
                        }
                        char[] cArr2 = this.A01;
                        int i2 = super.A04;
                        super.A04 = i2 + 1;
                        char c2 = cArr2[i2];
                        int A013 = r10.A01(c2);
                        if (A013 < 0) {
                            A013 = A11(r10, c2, 1);
                        }
                        int i3 = (A012 << 6) | A013;
                        if (super.A04 >= super.A03) {
                            A14();
                        }
                        char[] cArr3 = this.A01;
                        int i4 = super.A04;
                        super.A04 = i4 + 1;
                        char c3 = cArr3[i4];
                        int A014 = r10.A01(c3);
                        if (A014 < 0) {
                            if (A014 != -2) {
                                if (c3 == '\"' && !r10.A02) {
                                    r4.A02(i3 >> 4);
                                    bArr = r4.A05();
                                    break;
                                }
                                A014 = A11(r10, c3, 2);
                            }
                            if (A014 == -2) {
                                if (super.A04 >= super.A03) {
                                    A14();
                                }
                                char[] cArr4 = this.A01;
                                int i5 = super.A04;
                                super.A04 = i5 + 1;
                                char c4 = cArr4[i5];
                                char c5 = r10.A00;
                                boolean z = false;
                                if (c4 == c5) {
                                    z = true;
                                }
                                if (z) {
                                    r4.A02(i3 >> 4);
                                } else {
                                    throw AnonymousClass0HX.A09(r10, c4, 3, AnonymousClass006.A02("expected padding character '", c5, "'"));
                                }
                            }
                        }
                        int i6 = (i3 << 6) | A014;
                        if (super.A04 >= super.A03) {
                            A14();
                        }
                        char[] cArr5 = this.A01;
                        int i7 = super.A04;
                        super.A04 = i7 + 1;
                        char c6 = cArr5[i7];
                        int A015 = r10.A01(c6);
                        if (A015 < 0) {
                            if (A015 != -2) {
                                if (c6 == '\"' && !r10.A02) {
                                    r4.A04(i6 >> 2);
                                    bArr = r4.A05();
                                    break;
                                }
                                A015 = A11(r10, c6, 3);
                            }
                            if (A015 == -2) {
                                r4.A04(i6 >> 2);
                            }
                        }
                        r4.A03((i6 << 6) | A015);
                    }
                }
                this.A0L = bArr;
                this.A00 = false;
                return bArr;
            } catch (IllegalArgumentException e) {
                StringBuilder sb2 = new StringBuilder("Failed to decode VALUE_STRING as base64 (");
                sb2.append(r10);
                sb2.append("): ");
                sb2.append(e.getMessage());
                throw new C02290iR(sb2.toString(), A0V());
            }
        } else {
            byte[] bArr2 = this.A0L;
            if (bArr2 != null) {
                return bArr2;
            }
            C03940or r02 = this.A0E;
            if (r02 == null) {
                this.A0E = new C03940or();
            } else {
                r02.A01();
            }
            C03940or r1 = this.A0E;
            A0z(A0m(), r1, r10);
            byte[] A052 = r1.A05();
            this.A0L = A052;
            return A052;
        }
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final char[] A0s() throws IOException, C02290iR {
        EnumC03640oE r2 = ((AbstractC01190Sv) this).A00;
        if (r2 == null) {
            return null;
        }
        int i = C03840oe.A00[r2.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (!(i == 3 || i == 4)) {
                    return r2.asCharArray();
                }
            } else if (this.A00) {
                this.A00 = false;
                A05();
            }
            return this.A0N.A0E();
        }
        if (!this.A0H) {
            String str = this.A0D.A02;
            int length = str.length();
            char[] cArr = this.A0M;
            if (cArr == null) {
                C03750oT r22 = this.A0O;
                if (r22.A01 == null) {
                    cArr = r22.A03.A00(AnonymousClass007.A04, length);
                    r22.A01 = cArr;
                } else {
                    throw new IllegalStateException("Trying to call same allocXxx() method second time");
                }
            } else {
                if (cArr.length < length) {
                    cArr = new char[length];
                }
                str.getChars(0, length, cArr, 0);
                this.A0H = true;
            }
            this.A0M = cArr;
            str.getChars(0, length, cArr, 0);
            this.A0H = true;
        }
        return this.A0M;
    }

    @Override // X.AnonymousClass0HX
    public final char A10() throws IOException, C02290iR {
        int i;
        if (super.A04 < super.A03 || A18()) {
            char[] cArr = this.A01;
            int i2 = super.A04;
            int i3 = i2 + 1;
            super.A04 = i3;
            char c = cArr[i2];
            if (!(c == '\"' || c == '/' || c == '\\')) {
                if (c == 'b') {
                    return '\b';
                }
                if (c == 'f') {
                    return '\f';
                }
                if (c == 'n') {
                    return '\n';
                }
                if (c == 'r') {
                    return '\r';
                }
                if (c == 't') {
                    return '\t';
                }
                if (c == 'u') {
                    int i4 = 0;
                    int i5 = 0;
                    do {
                        if (i3 < super.A03 || A18()) {
                            char[] cArr2 = this.A01;
                            int i6 = super.A04;
                            i3 = i6 + 1;
                            super.A04 = i3;
                            char c2 = cArr2[i6];
                            if (c2 > 127 || (i = C03730oR.A03[c2]) < 0) {
                                A0v(c2, "expected a hex-digit for character escape sequence");
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                            i5 = (i5 << 4) | i;
                            i4++;
                        } else {
                            A0y(" in character escape sequence");
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } while (i4 < 4);
                    return (char) i5;
                } else if (!A0L(EnumC03610oA.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) && (c != '\'' || !A0L(EnumC03610oA.ALLOW_SINGLE_QUOTES))) {
                    A0x(AnonymousClass006.A07("Unrecognized character escape ", AbstractC01190Sv.A0D(c)));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            return c;
        }
        A0y(" in character escape sequence");
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AnonymousClass0HX
    public final void A15() throws IOException {
        Reader reader = this.A03;
        if (reader != null) {
            if (this.A0O.A05 || A0L(EnumC03610oA.AUTO_CLOSE_SOURCE)) {
                reader.close();
            }
            this.A03 = null;
        }
    }

    @Override // X.AnonymousClass0HX
    public final boolean A18() throws IOException {
        long j = this.A0J;
        int i = super.A03;
        this.A0J = j + ((long) i);
        super.A02 -= i;
        Reader reader = this.A03;
        if (reader != null) {
            char[] cArr = this.A01;
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                super.A04 = 0;
                super.A03 = read;
                return true;
            }
            A15();
            if (read == 0) {
                throw new IOException(AnonymousClass006.A03("Reader returned 0 characters when trying to read ", super.A03));
            }
        }
        return false;
    }

    public C002005a(C03750oT r4, int i, Reader reader, AbstractC03650oF r7, C03890ok r8) {
        super(r4, i);
        this.A03 = reader;
        if (r4.A02 == null) {
            char[] A002 = r4.A03.A00(AnonymousClass007.A00, 0);
            r4.A02 = A002;
            this.A01 = A002;
            this.A02 = r7;
            this.A05 = r8;
            this.A04 = r8.A08;
            return;
        }
        throw new IllegalStateException("Trying to call same allocXxx() method second time");
    }

    private final void A08(String str, int i) throws IOException, C02290iR {
        int i2;
        char c;
        int length = str.length();
        do {
            if (super.A04 >= super.A03 && !A18()) {
                A07(str.substring(0, i));
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (this.A01[super.A04] != str.charAt(i)) {
                A07(str.substring(0, i));
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                i2 = super.A04 + 1;
                super.A04 = i2;
                i++;
            }
        } while (i < length);
        if ((i2 < super.A03 || A18()) && (c = this.A01[super.A04]) >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
            A07(str.substring(0, i));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC02280iQ
    public final AbstractC03650oF A0N() {
        return this.A02;
    }

    @Override // X.AbstractC02280iQ
    public final Object A0O() {
        return this.A03;
    }

    @Override // X.AnonymousClass0HX
    public final void A13() throws IOException {
        super.A13();
        char[] cArr = this.A01;
        if (cArr != null) {
            this.A01 = null;
            C03750oT r1 = this.A0O;
            if (cArr == r1.A02) {
                r1.A02 = null;
                r1.A03.A00[0] = cArr;
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    @Override // X.AbstractC01190Sv, X.AnonymousClass0HX, java.io.Closeable, java.lang.AutoCloseable, X.AbstractC02280iQ
    public final void close() throws IOException {
        C03890ok r1;
        super.close();
        C03890ok r3 = this.A05;
        if (r3.A05 && (r1 = r3.A04) != null) {
            int i = r3.A02;
            if (i > 12000 || r3.A01 > 63) {
                synchronized (r1) {
                    r1.A07 = new String[64];
                    r1.A06 = new C03880oj[32];
                    r1.A00 = 63;
                    r1.A02 = 0;
                    r1.A01 = 0;
                    r1.A03 = 48;
                    r1.A05 = false;
                }
            } else if (i > r1.A02) {
                synchronized (r1) {
                    r1.A07 = r3.A07;
                    r1.A06 = r3.A06;
                    r1.A02 = r3.A02;
                    r1.A03 = r3.A03;
                    r1.A00 = r3.A00;
                    r1.A01 = r3.A01;
                    r1.A05 = false;
                }
            }
            r3.A05 = false;
        }
    }
}
