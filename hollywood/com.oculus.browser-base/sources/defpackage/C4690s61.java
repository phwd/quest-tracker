package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* renamed from: s61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4690s61 implements ContextMenuPopulatorFactory {

    /* renamed from: a  reason: collision with root package name */
    public final ContextMenuPopulatorFactory f11252a;
    public final Tab b;

    public C4690s61(ContextMenuPopulatorFactory contextMenuPopulatorFactory, Tab tab) {
        this.f11252a = contextMenuPopulatorFactory;
        this.b = tab;
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public void a() {
        ContextMenuPopulatorFactory contextMenuPopulatorFactory = this.f11252a;
        if (contextMenuPopulatorFactory != null) {
            contextMenuPopulatorFactory.a();
        }
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public AbstractC3128iz b(Context context, ContextMenuParams contextMenuParams, ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl) {
        return new C4519r61(this.f11252a.b(context, contextMenuParams, contextMenuNativeDelegateImpl), this.b);
    }
}
