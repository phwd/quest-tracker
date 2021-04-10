package X;

/* renamed from: X.9z  reason: invalid class name and case insensitive filesystem */
public final class C00959z extends AbstractC0400Wb {
    public static final C0407Wj A01 = new C0407Wj();
    public final boolean A00;

    public C00959z() {
    }

    public C00959z(String str) {
        W0 A002 = W0.A00();
        C0514bB.A01(A002, "AssistantConfig.get()");
        this.A00 = A002.A00.getBoolean("enable_assistant_wakeword", false);
    }
}
