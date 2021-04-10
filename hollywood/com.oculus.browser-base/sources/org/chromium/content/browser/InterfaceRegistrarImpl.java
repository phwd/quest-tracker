package org.chromium.content.browser;

import java.util.Objects;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.mojo.system.impl.CoreImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InterfaceRegistrarImpl {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10913a;

    public static void a() {
        if (!f10913a) {
            f10913a = true;
            C5700y30 y30 = new C5700y30(null);
            if (C5020u30.f11383a == null) {
                C5020u30.f11383a = new C5020u30();
            }
            C5020u30.f11383a.d.add(y30);
        }
    }

    public static void createInterfaceRegistry(int i) {
        a();
        CoreImpl coreImpl = (CoreImpl) VA.f9067a;
        Objects.requireNonNull(coreImpl);
        A30 f0 = A30.f0(new C1709ak0(new Rp1(coreImpl, i)));
        C5020u30 u30 = C5020u30.f11383a;
        if (u30 != null) {
            u30.a(f0, null);
        }
    }

    public static void createInterfaceRegistryForRenderFrameHost(int i, RenderFrameHost renderFrameHost) {
        a();
        CoreImpl coreImpl = (CoreImpl) VA.f9067a;
        Objects.requireNonNull(coreImpl);
        A30 f0 = A30.f0(new C1709ak0(new Rp1(coreImpl, i)));
        C5020u30 u30 = C5020u30.c;
        if (u30 != null) {
            u30.a(f0, renderFrameHost);
        }
    }

    public static void createInterfaceRegistryForWebContents(int i, WebContents webContents) {
        a();
        CoreImpl coreImpl = (CoreImpl) VA.f9067a;
        Objects.requireNonNull(coreImpl);
        A30 f0 = A30.f0(new C1709ak0(new Rp1(coreImpl, i)));
        C5020u30 u30 = C5020u30.b;
        if (u30 != null) {
            u30.a(f0, webContents);
        }
    }
}
