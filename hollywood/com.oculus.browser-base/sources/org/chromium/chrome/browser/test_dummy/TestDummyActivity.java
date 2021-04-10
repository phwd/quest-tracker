package org.chromium.chrome.browser.test_dummy;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TestDummyActivity extends I7 {
    public final void f0(boolean z) {
        if (z) {
            ((AbstractC0688Lf1) AbstractC0627Kf1.f8379a.b()).a().a(getIntent(), this);
            return;
        }
        throw new RuntimeException("Failed to install module");
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onResume() {
        super.onResume();
        if (!AbstractC1575Zv.e().g("enable-test-dummy-module")) {
            finish();
            return;
        }
        C4455ql0 ql0 = AbstractC0627Kf1.f8379a;
        if (!ql0.f()) {
            ql0.d(new C0566Jf1(this));
        } else {
            f0(true);
        }
    }
}
