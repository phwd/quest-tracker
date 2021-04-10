package org.chromium.components.dom_distiller.core;

import J.N;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DomDistillerService {

    /* renamed from: a  reason: collision with root package name */
    public final C3864nG f10837a;

    public DomDistillerService(long j) {
        this.f10837a = new C3864nG(N.MzVEzhvu(j));
    }

    public static DomDistillerService create(long j) {
        Object obj = ThreadUtils.f10596a;
        return new DomDistillerService(j);
    }
}
