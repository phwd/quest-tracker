package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.push.service.FbnsService;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0iE  reason: invalid class name and case insensitive filesystem */
public final class C04600iE implements AbstractC02430aW {
    public static final AbstractC02430aW A01 = new C04600iE(AnonymousClass0WE.ANALYTICS);
    public static final AbstractC02430aW A02 = new C04600iE(AnonymousClass0WE.MQTT_CONFIG);
    public static final AbstractC02430aW A03 = new C04600iE(AnonymousClass0WE.IDS);
    public final AnonymousClass0WE A00;

    public C04600iE(AnonymousClass0WE r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC02430aW
    public final Bundle A2G(FbnsService fbnsService, Bundle bundle) {
        SharedPreferences A002 = AnonymousClass0WJ.A00(fbnsService.getApplicationContext(), this.A00);
        Bundle bundle2 = new Bundle();
        for (String str : bundle.keySet()) {
            try {
                EnumC02400aQ r0 = (EnumC02400aQ) Enum.valueOf(EnumC02400aQ.class, str);
                AbstractC02390aP<?> wrapper = r0.getWrapper();
                try {
                    wrapper.A04(bundle2, r0.name(), wrapper.A01(A002, r0.getPrefKey(), null));
                } catch (ClassCastException e) {
                    AnonymousClass0NO.A0B("KeyValueWrapper", "sharedPrefsToBundle got ClassCastException", e);
                }
            } catch (IllegalArgumentException e2) {
                AnonymousClass0NO.A0K("SharedPreferencesBasedStateHelper", e2, "aidlBundleKey: %s not exist in FbnsAIDLConstants", str);
            }
        }
        return bundle2;
    }

    @Override // X.AbstractC02430aW
    public final void A2H(FbnsService fbnsService, Bundle bundle) {
        SharedPreferences.Editor edit = AnonymousClass0WJ.A00(fbnsService.getApplicationContext(), this.A00).edit();
        for (String str : bundle.keySet()) {
            try {
                EnumC02400aQ r0 = (EnumC02400aQ) Enum.valueOf(EnumC02400aQ.class, str);
                AbstractC02390aP<?> wrapper = r0.getWrapper();
                String name = r0.name();
                try {
                    wrapper.A03(edit, r0.getPrefKey(), wrapper.A02(bundle, name, null));
                } catch (ClassCastException e) {
                    AnonymousClass0NO.A0B("KeyValueWrapper", "bundleToSharedPrefs got ClassCastException", e);
                }
            } catch (IllegalArgumentException e2) {
                AnonymousClass0NO.A0K("SharedPreferencesBasedStateHelper", e2, "aidlBundleKey: %s not exist in FbnsAIDLConstants", str);
            }
        }
        edit.apply();
    }
}
