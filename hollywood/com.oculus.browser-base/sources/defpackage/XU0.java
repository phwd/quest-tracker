package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.optimization_guide.OptimizationMetadata;

/* renamed from: XU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XU0 implements OptimizationGuideBridge.OptimizationGuideCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9209a;

    public XU0(Callback callback) {
        this.f9209a = callback;
    }

    @Override // org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge.OptimizationGuideCallback
    public void a(int i, OptimizationMetadata optimizationMetadata) {
        Callback callback = this.f9209a;
        boolean z = true;
        if (!(i == 1 || i == 0)) {
            z = false;
        }
        callback.onResult(Boolean.valueOf(z));
    }
}
