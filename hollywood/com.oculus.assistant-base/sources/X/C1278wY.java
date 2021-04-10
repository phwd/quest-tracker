package X;

/* renamed from: X.wY  reason: case insensitive filesystem */
public final class C1278wY extends X7 {
    public final /* synthetic */ C0403Wf A00;

    public C1278wY(C0403Wf wf) {
        this.A00 = wf;
    }

    @Override // X.X7
    public final void onLayerEvent(String str, String str2) {
        String str3;
        super.onLayerEvent(str, str2);
        if (str != null && str.hashCode() == -1478243142 && str.equals("SYSTEM_BACK_PRESSED")) {
            this.A00.A00();
        }
        if (str2 != null) {
            int hashCode = str2.hashCode();
            if (hashCode == -2146094112) {
                str3 = "home-button";
            } else if (hashCode == -617292458) {
                str3 = "task-bar-navigation";
            } else {
                return;
            }
            if (str2.equals(str3)) {
                this.A00.A00();
            }
        }
    }
}
