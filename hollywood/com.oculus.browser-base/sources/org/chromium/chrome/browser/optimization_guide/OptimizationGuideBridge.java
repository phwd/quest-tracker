package org.chromium.chrome.browser.optimization_guide;

import J.N;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OptimizationGuideBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10727a = N.M9P8SBdL();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface OptimizationGuideCallback {
        void a(int i, OptimizationMetadata optimizationMetadata);
    }

    public OptimizationGuideBridge() {
        Object obj = ThreadUtils.f10596a;
    }

    public static OptimizationMetadata createOptimizationMetadataWithPerformanceHintsMetadata(byte[] bArr) {
        OptimizationMetadata optimizationMetadata = new OptimizationMetadata();
        try {
            EB0 eb0 = (EB0) AbstractC2360eV.k(EB0.e, bArr);
            return optimizationMetadata;
        } catch (L30 unused) {
            return null;
        }
    }

    public static void onOptimizationGuideDecision(OptimizationGuideCallback optimizationGuideCallback, int i, Object obj) {
        optimizationGuideCallback.a(i, (OptimizationMetadata) obj);
    }
}
