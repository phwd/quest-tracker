package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TriStateUtil {
    public static Combiner<TriState> AND_COMBINER = new Combiner<TriState>() {
        /* class com.facebook.common.util.TriStateUtil.AnonymousClass1 */

        public TriState combine(TriState triState, TriState triState2) {
            return (!triState.isSet() || !triState2.isSet()) ? triState.isSet() ? triState : triState2 : (!triState.asBoolean() || !triState2.asBoolean()) ? TriState.NO : TriState.YES;
        }
    };
    public static Combiner<TriState> OR_COMBINER = new Combiner<TriState>() {
        /* class com.facebook.common.util.TriStateUtil.AnonymousClass2 */

        public TriState combine(TriState triState, TriState triState2) {
            return (!triState.isSet() || !triState2.isSet()) ? triState.isSet() ? triState : triState2 : (triState.asBoolean() || triState2.asBoolean()) ? TriState.YES : TriState.NO;
        }
    };

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.common.util.TriState combine(java.util.List<com.facebook.common.util.TriState> r2, com.facebook.common.util.Combiner<com.facebook.common.util.TriState> r3, @javax.annotation.Nullable com.facebook.common.util.TriState r4) {
        /*
            boolean r0 = r2.isEmpty()
            if (r0 == 0) goto L_0x0009
            com.facebook.common.util.TriState r2 = com.facebook.common.util.TriState.UNSET
            return r2
        L_0x0009:
            java.util.Iterator r2 = r2.iterator()
            java.lang.Object r0 = r2.next()
            com.facebook.common.util.TriState r0 = (com.facebook.common.util.TriState) r0
        L_0x0013:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x0025
            java.lang.Object r1 = r2.next()
            java.lang.Object r0 = r3.combine(r0, r1)
            com.facebook.common.util.TriState r0 = (com.facebook.common.util.TriState) r0
            if (r0 != r4) goto L_0x0013
        L_0x0025:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.util.TriStateUtil.combine(java.util.List, com.facebook.common.util.Combiner, com.facebook.common.util.TriState):com.facebook.common.util.TriState");
    }
}
