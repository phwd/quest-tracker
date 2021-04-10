package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Kt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0658Kt0 extends AbstractC6022zx1 {
    public final /* synthetic */ OverlayPanelContent G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0658Kt0(OverlayPanelContent overlayPanelContent, WebContents webContents) {
        super(webContents);
        this.G = overlayPanelContent;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f && navigationHandle.f10940a) {
            OverlayPanelContent overlayPanelContent = this.G;
            boolean z = false;
            overlayPanelContent.j = false;
            AbstractC6009zt0 zt0 = overlayPanelContent.l;
            String str = navigationHandle.e.f11029a;
            boolean z2 = !TextUtils.equals(str, overlayPanelContent.g);
            int i = navigationHandle.k;
            int i2 = (i <= 0 || i >= 400) ? 1 : 0;
            C1461Xz xz = (C1461Xz) zt0;
            Objects.requireNonNull(xz);
            if (!z2) {
                ContextualSearchManager contextualSearchManager = xz.f9246a;
                C4188pA pAVar = contextualSearchManager.n0;
                if (pAVar != null) {
                    if (pAVar.d) {
                        Pattern pattern = AA.f7657a;
                        AbstractC3364kK0.g("Search.ContextualSearchLowPrioritySearchRequestStatus", i2, 2);
                    } else {
                        Pattern pattern2 = AA.f7657a;
                        AbstractC3364kK0.g("Search.ContextualSearchNormalPrioritySearchRequestStatus", i2, 2);
                        if (contextualSearchManager.n0.e) {
                            AbstractC3364kK0.g("Search.ContextualSearchFallbackSearchRequestStatus", i2, 2);
                        }
                    }
                    if (i2 != 0 && contextualSearchManager.n0.d) {
                        if (contextualSearchManager.g() != null) {
                            ContextualSearchManager contextualSearchManager2 = (ContextualSearchManager) contextualSearchManager.Q;
                            if (contextualSearchManager2.g() != null) {
                                contextualSearchManager2.g().stop();
                            }
                        }
                        C4188pA pAVar2 = contextualSearchManager.n0;
                        pAVar2.e = true;
                        pAVar2.d = false;
                        C1796bA bAVar = contextualSearchManager.T;
                        if (bAVar == null || !bAVar.e0()) {
                            contextualSearchManager.Z = false;
                        } else {
                            OverlayPanelContent overlayPanelContent2 = contextualSearchManager.T.y0;
                            if (overlayPanelContent2 != null) {
                                overlayPanelContent2.i = true;
                            }
                            contextualSearchManager.o();
                        }
                    }
                }
                ContextualSearchManager contextualSearchManager3 = xz.f9246a;
                C4188pA pAVar3 = contextualSearchManager3.n0;
                if (pAVar3 != null && pAVar3.f11054a) {
                    boolean n = contextualSearchManager3.R.n();
                    C1967cA cAVar = xz.f9246a.T.F0;
                    Objects.requireNonNull(cAVar);
                    long nanoTime = (System.nanoTime() - cAVar.u) / 1000000;
                    Pattern pattern3 = AA.f7657a;
                    AbstractC3364kK0.j(n ? "Search.ContextualSearchResolvedSearchDuration" : "Search.ContextualSearchLiteralSearchDuration", nanoTime);
                }
            } else if (!AbstractC5686xz.c(14)) {
                Objects.requireNonNull(xz.f9246a.R);
                Uri parse = Uri.parse(str);
                if (!(parse == null || parse.getHost() == null || parse.getPath() == null || !parse.getHost().contains("google") || !parse.getPath().startsWith("/amp/"))) {
                    z = true;
                }
                if (z) {
                    ContextualSearchManager contextualSearchManager4 = xz.f9246a;
                    if (contextualSearchManager4.T.J0) {
                        contextualSearchManager4.p(str);
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartLoading(GURL gurl) {
        ((C1461Xz) this.G.l).f9246a.d0 = false;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !navigationHandle.c) {
            String str = navigationHandle.e.f11029a;
            OverlayPanelContent overlayPanelContent = this.G;
            AbstractC6009zt0 zt0 = overlayPanelContent.l;
            boolean z = !TextUtils.equals(str, overlayPanelContent.g);
            C1461Xz xz = (C1461Xz) zt0;
            C1796bA bAVar = xz.f9246a.T;
            OverlayPanelContent overlayPanelContent2 = bAVar.y0;
            if (overlayPanelContent2 != null) {
                N.Msf6mgl3(overlayPanelContent2.c, overlayPanelContent2, bAVar.M());
            }
            if (z) {
                xz.f9246a.p(str);
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void loadProgressChanged(float f) {
        AbstractC0292Et0 et0 = this.G.m.f7920a;
        et0.j0 = f;
        et0.R();
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        Objects.requireNonNull(this.G.l);
    }

    @Override // defpackage.AbstractC6022zx1
    public void titleWasSet(String str) {
        Objects.requireNonNull(this.G.l);
    }
}
