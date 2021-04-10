package defpackage;

/* JADX WARN: Init of enum F can be incorrect */
/* JADX WARN: Init of enum G can be incorrect */
/* JADX WARN: Init of enum H can be incorrect */
/* JADX WARN: Init of enum I can be incorrect */
/* JADX WARN: Init of enum J can be incorrect */
/* JADX WARN: Init of enum K can be incorrect */
/* JADX WARN: Init of enum L can be incorrect */
/* JADX WARN: Init of enum M can be incorrect */
/* JADX WARN: Init of enum N can be incorrect */
/* JADX WARN: Init of enum O can be incorrect */
/* JADX WARN: Init of enum P can be incorrect */
/* JADX WARN: Init of enum Q can be incorrect */
/* JADX WARN: Init of enum R can be incorrect */
/* JADX WARN: Init of enum S can be incorrect */
/* JADX WARN: Init of enum T can be incorrect */
/* JADX WARN: Init of enum U can be incorrect */
/* JADX WARN: Init of enum V can be incorrect */
/* JADX WARN: Init of enum W can be incorrect */
/* JADX WARN: Init of enum X can be incorrect */
/* JADX WARN: Init of enum Y can be incorrect */
/* JADX WARN: Init of enum Z can be incorrect */
/* JADX WARN: Init of enum a0 can be incorrect */
/* JADX WARN: Init of enum b0 can be incorrect */
/* JADX WARN: Init of enum c0 can be incorrect */
/* JADX WARN: Init of enum d0 can be incorrect */
/* JADX WARN: Init of enum e0 can be incorrect */
/* JADX WARN: Init of enum f0 can be incorrect */
/* JADX WARN: Init of enum g0 can be incorrect */
/* JADX WARN: Init of enum h0 can be incorrect */
/* JADX WARN: Init of enum i0 can be incorrect */
/* JADX WARN: Init of enum j0 can be incorrect */
/* JADX WARN: Init of enum k0 can be incorrect */
/* JADX WARN: Init of enum l0 can be incorrect */
/* JADX WARN: Init of enum m0 can be incorrect */
/* JADX WARN: Init of enum n0 can be incorrect */
/* JADX WARN: Init of enum o0 can be incorrect */
/* JADX WARN: Init of enum p0 can be incorrect */
/* JADX WARN: Init of enum q0 can be incorrect */
/* JADX WARN: Init of enum r0 can be incorrect */
/* JADX WARN: Init of enum s0 can be incorrect */
/* JADX WARN: Init of enum t0 can be incorrect */
/* JADX WARN: Init of enum u0 can be incorrect */
/* JADX WARN: Init of enum v0 can be incorrect */
/* JADX WARN: Init of enum w0 can be incorrect */
/* JADX WARN: Init of enum x0 can be incorrect */
/* JADX WARN: Init of enum y0 can be incorrect */
/* JADX WARN: Init of enum z0 can be incorrect */
/* JADX WARN: Init of enum A0 can be incorrect */
/* JADX WARN: Init of enum B0 can be incorrect */
/* JADX WARN: Init of enum C0 can be incorrect */
/* renamed from: DP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum DP {
    DOUBLE(0, 1, r7),
    FLOAT(1, 1, r14),
    INT64(2, 1, r8),
    UINT64(3, 1, r8),
    INT32(4, 1, r9),
    FIXED64(5, 1, r8),
    FIXED32(6, 1, r9),
    BOOL(7, 1, r10),
    STRING(8, 1, r11),
    MESSAGE(9, 1, r12),
    BYTES(10, 1, r13),
    UINT32(11, 1, r9),
    ENUM(12, 1, r21),
    SFIXED32(13, 1, r9),
    SFIXED64(14, 1, r8),
    SINT32(15, 1, r9),
    SINT64(16, 1, r8),
    GROUP(17, 1, r12),
    DOUBLE_LIST(18, 2, r7),
    FLOAT_LIST(19, 2, r14),
    INT64_LIST(20, 2, r8),
    UINT64_LIST(21, 2, r8),
    INT32_LIST(22, 2, r9),
    FIXED64_LIST(23, 2, r8),
    FIXED32_LIST(24, 2, r9),
    BOOL_LIST(25, 2, r10),
    STRING_LIST(26, 2, r11),
    MESSAGE_LIST(27, 2, r12),
    BYTES_LIST(28, 2, r13),
    UINT32_LIST(29, 2, r9),
    ENUM_LIST(30, 2, r21),
    SFIXED32_LIST(31, 2, r9),
    SFIXED64_LIST(32, 2, r8),
    SINT32_LIST(33, 2, r9),
    SINT64_LIST(34, 2, r8),
    DOUBLE_LIST_PACKED(35, 3, r7),
    FLOAT_LIST_PACKED(36, 3, r14),
    INT64_LIST_PACKED(37, 3, r8),
    UINT64_LIST_PACKED(38, 3, r8),
    INT32_LIST_PACKED(39, 3, r9),
    FIXED64_LIST_PACKED(40, 3, r8),
    FIXED32_LIST_PACKED(41, 3, r9),
    BOOL_LIST_PACKED(42, 3, r10),
    UINT32_LIST_PACKED(43, 3, r9),
    ENUM_LIST_PACKED(44, 3, r21),
    SFIXED32_LIST_PACKED(45, 3, r9),
    SFIXED64_LIST_PACKED(46, 3, r7),
    SINT32_LIST_PACKED(47, 3, r9),
    SINT64_LIST_PACKED(48, 3, r7),
    GROUP_LIST(49, 2, r12),
    MAP(50, 4, C40.VOID);
    
    public static final DP[] E0;
    public final int G0;
    public final int H0;

    /* access modifiers changed from: public */
    static {
        C40 c40 = C40.DOUBLE;
        C40 c402 = C40.FLOAT;
        C40 c403 = C40.LONG;
        C40 c404 = C40.INT;
        C40 c405 = C40.BOOLEAN;
        C40 c406 = C40.STRING;
        C40 c407 = C40.MESSAGE;
        C40 c408 = C40.BYTE_STRING;
        C40 c409 = C40.ENUM;
        C40 c4010 = C40.LONG;
        DP[] values = values();
        E0 = new DP[values.length];
        for (DP dp : values) {
            E0[dp.G0] = dp;
        }
    }

    /* access modifiers changed from: public */
    DP(int i, int i2, C40 c40) {
        this.G0 = i;
        this.H0 = i2;
        int a2 = AbstractC5580xK0.a(i2);
        if (a2 == 1) {
            Class cls = c40.Q;
        } else if (a2 == 3) {
            Class cls2 = c40.Q;
        }
        if (i2 == 1) {
            c40.ordinal();
        }
    }
}
