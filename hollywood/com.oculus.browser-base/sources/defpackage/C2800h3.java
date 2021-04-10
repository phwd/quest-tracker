package defpackage;

import J.N;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import com.oculus.browser.PanelApp;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.CompositorView;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: h3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2800h3 extends AbstractC0133Cd1 {

    /* renamed from: a  reason: collision with root package name */
    public final C4931ta f10045a = new C4931ta();
    public final Tab b;
    public ChromeActivity c;

    public C2800h3(Tab tab, ChromeActivity chromeActivity) {
        this.b = tab;
        this.c = chromeActivity;
        tab.A(new C2629g3(this));
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int a() {
        return 1;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void activateContents() {
        TabModel l;
        int i;
        ChromeActivity chromeActivity = this.c;
        if (chromeActivity == null) {
            AbstractC1220Ua0.a("ActivityTabWCDA", "Activity not set activateContents().  Bailing out.", new Object[0]);
        } else if (chromeActivity.v()) {
            AbstractC1220Ua0.a("ActivityTabWCDA", "Activity destroyed before calling activateContents().  Bailing out.", new Object[0]);
        } else if (!this.b.isInitialized()) {
            AbstractC1220Ua0.a("ActivityTabWCDA", "Tab not initialized before calling activateContents().  Bailing out.", new Object[0]);
        } else {
            Px1 a2 = Px1.a();
            if (!a2.d() && !a2.h) {
                a2.h = true;
                a2.b(a2.f ? "NewActivity" : "FocusActivity");
                a2.c("TimeToActivity");
            }
            if (!this.b.isUserInteractable() && (i = (l = ((AbstractC0246Ea1) this.c.P()).l(this.b.a())).i(this.b)) != -1) {
                l.x(i, 3);
                if (ApplicationStatus.e(this.c) == 5) {
                    b();
                }
            }
        }
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z) {
        A61 e = e();
        GURL gurl = (GURL) this.f10045a.remove(webContents2);
        boolean z2 = false;
        if (this.b.x()) {
            return false;
        }
        boolean c2 = e.c(this.b, webContents2, 4, gurl);
        if (e.d() || c2) {
            z2 = true;
        }
        if (z2) {
            if (i == 3) {
                AbstractC0124Ca1 ca1 = null;
                Tab tab = this.b;
                if (tab != null) {
                    ca1 = ((TabImpl) tab).X();
                }
                if (((AbstractC0246Ea1) ca1).c.d().N(this.b.getId()).size() == 2) {
                    AbstractC3535lK0.a("TabGroup.Created.DeveloperRequestedNewTab");
                }
            } else if (i == 5) {
                Objects.requireNonNull(AppHooks.get());
                ContextUtils.getApplicationContext();
                gurl.h();
            }
        }
        return z2;
    }

    public void b() {
        Intent a2 = AbstractC0409Gr.a(this.b.getId());
        a2.addFlags(268435456);
        ContextUtils.getApplicationContext().startActivity(a2);
    }

    public final AbstractC2400ek c() {
        ChromeActivity chromeActivity = this.c;
        if (chromeActivity == null || chromeActivity.v()) {
            return null;
        }
        return this.c.M0();
    }

    public final UT d() {
        PanelApp c2 = ((C5613xa1) ((TabImpl) this.b).i0).c();
        if (c2 == null) {
            return null;
        }
        return c2.Z;
    }

    public final A61 e() {
        Tab tab = this.b;
        return ((TabImpl) tab).i0.S(tab.a());
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void enterFullscreenModeForTab(boolean z) {
        UT d = d();
        if (d != null) {
            ((C4998tw0) d).F = true;
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void exitFullscreenModeForTab() {
        UT d = d();
        if (d != null) {
            ((C4998tw0) d).F = false;
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getBottomControlsHeight() {
        AbstractC2400ek c2 = c();
        if (c2 != null) {
            return ((C1551Zj) c2).O;
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getBottomControlsMinHeight() {
        AbstractC2400ek c2 = c();
        if (c2 != null) {
            return ((C1551Zj) c2).P;
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getTopControlsHeight() {
        AbstractC2400ek c2 = c();
        if (c2 != null) {
            return ((C1551Zj) c2).M;
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getTopControlsMinHeight() {
        AbstractC2400ek c2 = c();
        if (c2 != null) {
            return ((C1551Zj) c2).N;
        }
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void handleKeyboardEvent(KeyEvent keyEvent) {
        ChromeActivity chromeActivity;
        if (keyEvent.getAction() == 0 && (chromeActivity = this.c) != null) {
            if (!chromeActivity.onKeyDown(keyEvent.getKeyCode(), keyEvent)) {
                if (keyEvent.getKeyCode() == 111 && keyEvent.hasNoModifiers()) {
                    WebContents l = this.b.l();
                    if (l != null) {
                        l.stop();
                        return;
                    }
                    return;
                }
            } else {
                return;
            }
        }
        int keyCode = keyEvent.getKeyCode();
        if (!(keyCode == 79 || keyCode == 222)) {
            switch (keyCode) {
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                    break;
                default:
                    switch (keyCode) {
                        case 126:
                        case 127:
                        case 128:
                        case 129:
                        case 130:
                            break;
                        default:
                            return;
                    }
            }
        }
        ((AudioManager) ContextUtils.getApplicationContext().getSystemService("audio")).dispatchMediaKeyEvent(keyEvent);
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isCustomTab() {
        ChromeActivity chromeActivity = this.c;
        return chromeActivity != null && chromeActivity.d1();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean isFullscreenForTabOrPending() {
        UT d = d();
        if (d != null) {
            return ((C4998tw0) d).F;
        }
        return false;
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isNightModeEnabled() {
        ChromeActivity chromeActivity = this.c;
        if (chromeActivity != null) {
            return AbstractC1270Uv.e(chromeActivity);
        }
        return false;
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isPictureInPictureEnabled() {
        ChromeActivity chromeActivity = this.c;
        if (chromeActivity == null) {
            return false;
        }
        Context applicationContext = chromeActivity.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 26 && ((AppOpsManager) applicationContext.getSystemService("appops")).checkOpNoThrow("android:picture_in_picture", applicationContext.getApplicationInfo().uid, applicationContext.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    @Override // defpackage.AbstractC0133Cd1
    public void setOverlayMode(boolean z) {
        CompositorViewHolder compositorViewHolder;
        CompositorView compositorView;
        ChromeActivity chromeActivity = this.c;
        if (chromeActivity != null && (compositorViewHolder = chromeActivity.I0) != null && (compositorView = compositorViewHolder.M) != null) {
            N.M$Spxfoj(compositorView.K, compositorView, z);
            compositorView.H = z;
            ((SurfaceHolder$Callback2C0723Lw) compositorView.G).f(compositorView.b());
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldAnimateBrowserControlsHeightChanges() {
        AbstractC2400ek c2 = c();
        return c2 != null && ((C1551Zj) c2).Q;
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean shouldResumeRequestsForCreatedWindow() {
        return !e().d();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void showRepostFormWarningDialog() {
        C0887Om0 om0;
        C41 m = C41.m(this.b);
        if (m != null) {
            m.j();
            K41 k41 = m.H;
            if (k41 != null) {
                k41.e();
            }
            FX fx = m.O;
            if (!(fx == null || (om0 = fx.S) == null)) {
                om0.c();
            }
        }
        if (this.c == null || !this.b.isUserInteractable()) {
            this.b.l().f().u();
            return;
        }
        C2746gl0 l0 = this.c.l0();
        GW0 gw0 = new GW0(l0, new C2458f3(this));
        Resources resources = this.c.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, gw0);
        hh0.d(AbstractC3258jl0.c, resources, R.string.f52850_resource_name_obfuscated_RES_2131952602);
        hh0.d(AbstractC3258jl0.e, resources, R.string.f52830_resource_name_obfuscated_RES_2131952600);
        hh0.d(AbstractC3258jl0.g, resources, R.string.f52840_resource_name_obfuscated_RES_2131952601);
        hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
        hh0.b(AbstractC3258jl0.m, true);
        l0.i(hh0.a(), 1, true);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean takeFocus(boolean z) {
        ChromeActivity chromeActivity = this.c;
        if (chromeActivity == null) {
            return false;
        }
        if (z) {
            View findViewById = chromeActivity.findViewById(R.id.menu_button);
            if (findViewById != null && findViewById.isShown()) {
                return findViewById.requestFocus();
            }
            View findViewById2 = this.c.findViewById(R.id.tab_switcher_button);
            if (findViewById2 != null && findViewById2.isShown()) {
                return findViewById2.requestFocus();
            }
        } else {
            View findViewById3 = chromeActivity.findViewById(R.id.url_bar);
            if (findViewById3 != null) {
                return findViewById3.requestFocus();
            }
        }
        return false;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void webContentsCreated(WebContents webContents, long j, long j2, String str, GURL gurl, WebContents webContents2) {
        this.f10045a.put(webContents2, gurl);
        A61 e = e();
        if (e != null && e.d()) {
            if (EG.f7949a == null) {
                EG.f7949a = new EG();
            }
            EG eg = EG.f7949a;
            N.MY20dsUd(eg.b, eg, webContents2);
        }
    }
}
