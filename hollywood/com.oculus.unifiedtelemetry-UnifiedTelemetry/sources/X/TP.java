package X;

import com.google.gson.annotations.JsonAdapter;

public final class TP implements AbstractC0132Os {
    public final TW A00;

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        JsonAdapter jsonAdapter = (JsonAdapter) lQVar.rawType.getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (AbstractC0131Ob<T>) A00(this.A00, hy, lQVar, jsonAdapter);
    }

    public TP(TW tw) {
        this.A00 = tw;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r2 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.AbstractC0131Ob<?> A00(X.TW r5, X.HY r6, X.lQ<?> r7, com.google.gson.annotations.JsonAdapter r8) {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: X.TP.A00(X.TW, X.HY, X.lQ, com.google.gson.annotations.JsonAdapter):X.Ob");
    }
}
