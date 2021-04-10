package defpackage;

import J.N;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.password_manager.settings.PasswordEntryViewer;
import org.chromium.chrome.browser.password_manager.settings.PasswordUIView;

/* renamed from: Yx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1519Yx0 implements AbstractC4323py0 {
    public final /* synthetic */ PasswordEntryViewer F;

    public C1519Yx0(PasswordEntryViewer passwordEntryViewer) {
        this.F = passwordEntryViewer;
    }

    @Override // defpackage.AbstractC4323py0
    public void j(int i) {
        if (!this.F.z0) {
            AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry", 1, 4);
            C4834sy0 sy0 = AbstractC4664ry0.f11238a;
            Objects.requireNonNull(sy0);
            Object obj = ThreadUtils.f10596a;
            PasswordUIView passwordUIView = sy0.F;
            N.M6hPIdj7(passwordUIView.f10742a, passwordUIView, this.F.y0);
            sy0.b(this);
            C1184Ti1.a(this.F.u().getApplicationContext(), R.string.f50790_resource_name_obfuscated_RES_2131952396, 0).b.show();
            this.F.u().finish();
        }
    }

    @Override // defpackage.AbstractC4323py0
    public void l(int i) {
        if (this.F.z0) {
            AbstractC3364kK0.g("PasswordManager.Android.PasswordExceptionEntry", 1, 4);
            C4834sy0 sy0 = AbstractC4664ry0.f11238a;
            Objects.requireNonNull(sy0);
            Object obj = ThreadUtils.f10596a;
            PasswordUIView passwordUIView = sy0.F;
            N.MnG4h9CU(passwordUIView.f10742a, passwordUIView, this.F.y0);
            sy0.b(this);
            C1184Ti1.a(this.F.u().getApplicationContext(), R.string.f50790_resource_name_obfuscated_RES_2131952396, 0).b.show();
            this.F.u().finish();
        }
    }
}
