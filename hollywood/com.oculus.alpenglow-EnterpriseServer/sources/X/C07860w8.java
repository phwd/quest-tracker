package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.push.service.FbnsService;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0w8  reason: invalid class name and case insensitive filesystem */
public final class C07860w8 implements AbstractC09550ze {
    public static final AbstractC09550ze A01 = new C07860w8(EnumC07690vn.ANALYTICS);
    public static final AbstractC09550ze A02 = new C07860w8(EnumC07690vn.MQTT_CONFIG);
    public static final AbstractC09550ze A03 = new C07860w8(EnumC07690vn.IDS);
    public final EnumC07690vn A00;

    public C07860w8(EnumC07690vn r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC09550ze
    public final Bundle A2A(FbnsService fbnsService, Bundle bundle) {
        SharedPreferences A002 = C07680vm.A00(fbnsService.getApplicationContext(), this.A00);
        Bundle bundle2 = new Bundle();
        for (String str : bundle.keySet()) {
            try {
                EnumC07870w9 r0 = (EnumC07870w9) Enum.valueOf(EnumC07870w9.class, str);
                AbstractC07890wB<?> wrapper = r0.getWrapper();
                try {
                    wrapper.A04(bundle2, r0.name(), wrapper.A01(A002, r0.getPrefKey(), null));
                } catch (ClassCastException e) {
                    AnonymousClass0NK.A04("KeyValueWrapper", "sharedPrefsToBundle got ClassCastException", e);
                }
            } catch (IllegalArgumentException e2) {
                AnonymousClass0NK.A0C("SharedPreferencesBasedStateHelper", e2, "aidlBundleKey: %s not exist in FbnsAIDLConstants", str);
            }
        }
        return bundle2;
    }

    @Override // X.AbstractC09550ze
    public final void A2B(FbnsService fbnsService, Bundle bundle) {
        SharedPreferences.Editor edit = C07680vm.A00(fbnsService.getApplicationContext(), this.A00).edit();
        for (String str : bundle.keySet()) {
            try {
                EnumC07870w9 r0 = (EnumC07870w9) Enum.valueOf(EnumC07870w9.class, str);
                AbstractC07890wB<?> wrapper = r0.getWrapper();
                String name = r0.name();
                try {
                    wrapper.A03(edit, r0.getPrefKey(), wrapper.A02(bundle, name, null));
                } catch (ClassCastException e) {
                    AnonymousClass0NK.A04("KeyValueWrapper", "bundleToSharedPrefs got ClassCastException", e);
                }
            } catch (IllegalArgumentException e2) {
                AnonymousClass0NK.A0C("SharedPreferencesBasedStateHelper", e2, "aidlBundleKey: %s not exist in FbnsAIDLConstants", str);
            }
        }
        edit.apply();
    }
}
