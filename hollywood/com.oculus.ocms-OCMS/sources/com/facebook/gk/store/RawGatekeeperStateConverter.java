package com.facebook.gk.store;

import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
final class RawGatekeeperStateConverter {
    private static final byte INITIALIZED = 3;
    private static final byte OVERRIDDEN = 12;
    static final byte SET = 2;
    static final byte SET_OVERRIDE = 8;
    static final byte UNKNOWN = 0;
    static final byte UNSET = 1;
    static final byte UNSET_OVERRIDE = 4;

    static boolean isGatekeeperInitialized(byte b) {
        return (b & 3) != 0;
    }

    static boolean isGatekeeperOverridden(byte b) {
        return (b & 12) != 0;
    }

    static boolean isGatekeeperOverriddenSet(byte b) {
        return (b & 8) != 0;
    }

    static boolean isGatekeeperSet(byte b) {
        return (b & 2) != 0;
    }

    static byte[] toRawStates(GatekeeperCache gatekeeperCache) {
        int size = gatekeeperCache.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = toRawValue(gatekeeperCache.getActual(i), gatekeeperCache.getOverridden(i));
        }
        return bArr;
    }

    private static byte toRawValue(TriState triState, TriState triState2) {
        return (byte) (toRawActualValue(triState) | toRawOverriddenValue(triState2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.facebook.gk.store.RawGatekeeperStateConverter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$util$TriState = new int[TriState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.facebook.common.util.TriState[] r0 = com.facebook.common.util.TriState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.gk.store.RawGatekeeperStateConverter.AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState = r0
                int[] r0 = com.facebook.gk.store.RawGatekeeperStateConverter.AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.facebook.common.util.TriState r1 = com.facebook.common.util.TriState.YES     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.facebook.gk.store.RawGatekeeperStateConverter.AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.facebook.common.util.TriState r1 = com.facebook.common.util.TriState.NO     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.facebook.gk.store.RawGatekeeperStateConverter.AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.facebook.common.util.TriState r1 = com.facebook.common.util.TriState.UNSET     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.gk.store.RawGatekeeperStateConverter.AnonymousClass1.<clinit>():void");
        }
    }

    private static byte toRawActualValue(TriState triState) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[triState.ordinal()];
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 0;
        }
        throw new IllegalArgumentException();
    }

    private static byte toRawOverriddenValue(TriState triState) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[triState.ordinal()];
        if (i == 1) {
            return 8;
        }
        if (i == 2) {
            return 4;
        }
        if (i == 3) {
            return 0;
        }
        throw new IllegalArgumentException();
    }

    private RawGatekeeperStateConverter() {
    }
}
