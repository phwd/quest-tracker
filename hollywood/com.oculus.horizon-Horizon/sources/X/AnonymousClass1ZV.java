package X;

import android.content.Context;
import android.net.Uri;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfigservice_mobileconfigaccessor_MobileConfigAccessorDecorator_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1ZV  reason: invalid class name */
public final class AnonymousClass1ZV {
    public AnonymousClass0QC A00;

    public final void A01(String str, @Nullable String str2, @Nullable String str3, int i) {
        if (str2 == null) {
            AnonymousClass0NO.A08("MobileConfigServiceImpl", "Could not find package name for subscribe()");
            A00(this, AnonymousClass1aU.A08, "unknown_app_identity");
            return;
        }
        String str4 = str2;
        if (str3 != null) {
            str4 = str3;
        }
        new Thread(new AnonymousClass1ZW(this, str2, i, str, str3, str4)).start();
    }

    @Inject
    public AnonymousClass1ZV(AbstractC06640p5 r3) {
        this.A00 = new AnonymousClass0QC(2, r3);
    }

    public static void A00(AnonymousClass1ZV r1, Uri uri, String str) {
        ((Context) AnonymousClass0J2.A03(1, 294, r1.A00)).getContentResolver().notifyChange(Uri.withAppendedPath(uri, str), null);
    }
}
