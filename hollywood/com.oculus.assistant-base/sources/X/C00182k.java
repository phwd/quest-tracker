package X;

/* renamed from: X.2k  reason: invalid class name and case insensitive filesystem */
public final class C00182k extends AbstractC0957pZ implements AbstractC0210Jy, K3 {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C00182k(K8 k8) {
        super(k8);
        C0514bB.A02(k8, "config");
    }

    public final AbstractC0211Jz A01(String str, String str2, String str3) {
        C0514bB.A02(str, "user_id_");
        C0514bB.A02(str2, "persona_id_");
        C0514bB.A02(str3, "locale_id_");
        return new C0620dK(new KA("UpsertSelectedTtsPersona", "INSERT OR REPLACE INTO selected_personas(manual_composite_key, user_id, persona_id, locale_id, manual_composite_foreign_key) VALUES(? || '_' || ?, ?, ?, ?, ? || '_' || ? || '_' || ?)", new String[]{"selected_personas"}, new Object[]{str, str2, str3}, new int[]{0, 2, 0, 1, 2, 0, 1, 2}, this.A00));
    }
}
