package org.chromium.content.browser.selection;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import android.view.ActionMode;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.List;
import java.util.Objects;
import org.chromium.base.PackageManagerUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SelectionPopupControllerImpl extends B2 implements WZ, AbstractC3551lS0, Wy1, BE0, Qr1 {
    public static boolean G;
    public final Handler H = new Handler();
    public Context I;

    /* renamed from: J  reason: collision with root package name */
    public WindowAndroid f10931J;
    public WebContentsImpl K;
    public ActionMode.Callback L;
    public long M;
    public C4406qS0 N;
    public final Rect O = new Rect();
    public Runnable P;
    public View Q;
    public ActionMode R;
    public int S;
    public boolean T;
    public boolean U;
    public boolean V;
    public boolean W;
    public boolean X;
    public boolean Y;
    public String Z;
    public int a0;
    public boolean b0;
    public boolean c0;
    public AbstractC0424Gy0 d0;
    public boolean e0;
    public AbstractC2185dS0 f0;
    public ZX0 g0;
    public EE0 h0;
    public C2355eS0 i0;
    public boolean j0;
    public C5445wb0 k0;
    public F3 l0;

    public SelectionPopupControllerImpl(WebContents webContents, EE0 ee0, boolean z) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.K = webContentsImpl;
        H3 h3 = null;
        this.h0 = null;
        this.I = webContentsImpl.u0();
        this.f10931J = this.K.I();
        ViewAndroidDelegate F = this.K.F();
        if (F != null) {
            this.Q = F.getContainerView();
            F.c.b(this);
        }
        this.S = 7;
        this.P = new RunnableC3893nS0(this);
        Zy1 t0 = Zy1.t0(this.K);
        if (t0 != null) {
            t0.F.b(this);
            if (t0.I) {
                I(true);
            }
        }
        if (z) {
            this.M = N.MJHXNa8U(this, this.K);
            ImeAdapterImpl s0 = ImeAdapterImpl.s0(this.K);
            if (s0 != null) {
                s0.N.add(this);
            }
        }
        this.N = new C4406qS0(this, null);
        this.Z = "";
        w();
        Object obj = ThreadUtils.f10596a;
        if (C0727Ly.f8451a == null) {
            C0727Ly.f8451a = new C0727Ly();
        }
        Objects.requireNonNull(C0727Ly.f8451a);
        this.l0 = Build.VERSION.SDK_INT >= 28 ? new H3() : h3;
        t().F.add(this);
    }

    public static String D(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() < i) {
            return str;
        }
        StringBuilder i2 = AbstractC2531fV.i("Truncating oversized query (");
        i2.append(str.length());
        i2.append(").");
        AbstractC1220Ua0.f("SelectionPopupCtlr", i2.toString(), new Object[0]);
        return str.substring(0, i) + "…";
    }

    public static SelectionPopupControllerImpl r(WebContents webContents) {
        return (SelectionPopupControllerImpl) ((WebContentsImpl) webContents).v0(SelectionPopupControllerImpl.class, AbstractC4746sS0.f11275a);
    }

    @Override // defpackage.XF
    public void A(List list) {
    }

    public final boolean B(int i) {
        boolean z = (this.S & i) != 0;
        if (i != 1) {
            return z;
        }
        if (!z) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        return PackageManagerUtils.c(intent, 65536).isEmpty() ^ true;
    }

    @Override // defpackage.XF
    public void C(Display.Mode mode) {
    }

    public void E() {
        WebContentsImpl webContentsImpl = this.K;
        webContentsImpl.r0();
        N.MNvj1u1S(webContentsImpl.H, webContentsImpl);
        this.i0 = null;
        if (this.U) {
            AbstractC3535lK0.a("MobileActionMode.SelectAllWasEditable");
        } else {
            AbstractC3535lK0.a("MobileActionMode.SelectAllWasNonEditable");
        }
    }

    public void F(AbstractC2185dS0 ds0) {
        ZX0 zx0;
        this.f0 = ds0;
        if (ds0 == null) {
            zx0 = null;
        } else {
            zx0 = ds0.c();
        }
        this.g0 = zx0;
        this.i0 = null;
    }

    public void G() {
        if ((this.L != B2.F) && this.c0 && this.Q != null) {
            if (!e() || y()) {
                n();
                ActionMode startActionMode = this.Q.startActionMode(new C3890nR(this, this.L), 1);
                if (startActionMode != null) {
                    AbstractC4518r60.b(this.I, startActionMode);
                }
                this.R = startActionMode;
                this.Y = true;
                if (!e()) {
                    m();
                    return;
                }
                return;
            }
            try {
                this.R.invalidate();
            } catch (NullPointerException e) {
                AbstractC1220Ua0.f("SelectionPopupCtlr", "Ignoring NPE from ActionMode.invalidate() as workaround for L", e);
            }
            v(false);
        }
    }

    public final void H(int i, int i2) {
        if (this.K.s() != null) {
            RenderWidgetHostViewImpl w0 = this.K.s();
            long j = w0.f10916a;
            if (j != 0) {
                N.McU85DFE(j, w0, i, i2);
                return;
            }
            throw new IllegalStateException("Native RenderWidgetHostViewAndroid already destroyed", w0.b);
        }
    }

    public void I(boolean z) {
        boolean z2 = !z;
        long j = this.M;
        if (j != 0) {
            N.M01adZlM(j, this, z2);
        }
        if (z) {
            restoreSelectionPopupsIfNecessary();
            return;
        }
        n();
        t().e();
    }

    @Override // defpackage.Wy1
    public void S(boolean z, boolean z2) {
        if (z) {
            restoreSelectionPopupsIfNecessary();
            return;
        }
        ImeAdapterImpl.s0(this.K).U.setEmpty();
        if (this.j0) {
            this.j0 = false;
            hidePopupsAndPreserveSelection();
            return;
        }
        q();
    }

    @Override // defpackage.XF
    public void U(float f) {
    }

    @Override // defpackage.WZ
    public void a() {
    }

    @Override // defpackage.BE0
    public void b() {
        o();
    }

    @Override // defpackage.B2
    public void c() {
        this.T = false;
        this.H.removeCallbacks(this.P);
        if (e()) {
            this.R.finish();
            this.R = null;
        }
    }

    @Override // defpackage.WZ
    public void d(KeyEvent keyEvent) {
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    @Override // defpackage.B2
    public boolean e() {
        return this.R != null;
    }

    @Override // defpackage.B2
    public boolean f(ActionMode actionMode, MenuItem menuItem) {
        Context context;
        ZX0 zx0;
        if (!e()) {
            return true;
        }
        int itemId = menuItem.getItemId();
        int groupId = menuItem.getGroupId();
        if (this.c0 && (zx0 = this.g0) != null) {
            String str = this.Z;
            int i = this.a0;
            int i2 = 105;
            if (groupId != 16908353) {
                if (itemId == R.id.select_action_menu_select_all) {
                    i2 = 200;
                } else if (itemId == R.id.select_action_menu_cut) {
                    i2 = 103;
                } else if (itemId == R.id.select_action_menu_copy) {
                    i2 = 101;
                } else if (itemId == R.id.select_action_menu_paste || itemId == R.id.select_action_menu_paste_as_plain_text) {
                    i2 = 102;
                } else if (itemId == R.id.select_action_menu_share) {
                    i2 = 104;
                } else if (itemId != 16908353) {
                    i2 = 108;
                }
            }
            zx0.f(str, i, i2, this.i0);
        }
        if (groupId == R.id.select_action_menu_assist_items && itemId == 16908353) {
            C2355eS0 es0 = this.i0;
            if (es0 != null && es0.a()) {
                C2355eS0 es02 = this.i0;
                View.OnClickListener onClickListener = es02.f;
                if (onClickListener != null) {
                    onClickListener.onClick(this.Q);
                } else if (!(es02.e == null || (context = (Context) this.f10931J.f11022J.get()) == null)) {
                    context.startActivity(this.i0.e);
                }
            }
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_select_all) {
            E();
        } else if (itemId == R.id.select_action_menu_cut) {
            this.K.t0();
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_copy) {
            this.K.s0();
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_paste) {
            this.K.x0();
            actionMode.finish();
        } else if (Build.VERSION.SDK_INT >= 26 && itemId == R.id.select_action_menu_paste_as_plain_text) {
            WebContentsImpl webContentsImpl = this.K;
            webContentsImpl.r0();
            N.MdSkKRWg(webContentsImpl.H, webContentsImpl);
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_share) {
            AbstractC3535lK0.a("MobileActionMode.Share");
            String D = D(this.Z, 100000);
            if (!TextUtils.isEmpty(D)) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", D);
                try {
                    Intent createChooser = Intent.createChooser(intent, this.I.getString(R.string.f46500_resource_name_obfuscated_RES_2131951967));
                    createChooser.setFlags(268435456);
                    this.I.startActivity(createChooser);
                } catch (ActivityNotFoundException unused) {
                }
            }
            actionMode.finish();
        } else if (itemId == R.id.select_action_menu_web_search) {
            AbstractC3535lK0.a("MobileActionMode.WebSearch");
            String D2 = D(this.Z, 1000);
            if (!TextUtils.isEmpty(D2)) {
                Intent intent2 = new Intent("android.intent.action.WEB_SEARCH");
                intent2.putExtra("new_search", true);
                intent2.putExtra("query", D2);
                intent2.putExtra("com.android.browser.application_id", this.I.getPackageName());
                intent2.addFlags(268435456);
                try {
                    this.I.startActivity(intent2);
                } catch (ActivityNotFoundException unused2) {
                }
            }
            actionMode.finish();
        } else if (groupId == R.id.select_action_menu_text_processing_menus) {
            Intent intent3 = menuItem.getIntent();
            AbstractC3535lK0.a("MobileActionMode.ProcessTextIntent");
            String D3 = D(this.Z, 100000);
            if (!TextUtils.isEmpty(D3)) {
                intent3.putExtra("android.intent.extra.PROCESS_TEXT", D3);
                try {
                    this.f10931J.F0(intent3, new C4235pS0(this), null);
                } catch (ActivityNotFoundException unused3) {
                }
            }
        } else if (groupId != 16908353) {
            return false;
        } else {
            F3 f3 = this.l0;
            if (f3 != null) {
                ((H3) f3).f(menuItem, this.Q);
                actionMode.finish();
            }
        }
        return true;
    }

    @Override // defpackage.B2
    public void g(ActionMode actionMode, Menu menu) {
        actionMode.setTitle(DeviceFormFactor.b(this.f10931J) ? this.I.getString(R.string.f46510_resource_name_obfuscated_RES_2131951968) : null);
        actionMode.setSubtitle((CharSequence) null);
    }

    public final Context getContext() {
        return this.I;
    }

    @Override // defpackage.XF
    public void h(float f) {
    }

    @Override // defpackage.XF
    public void h0(int i) {
        if (e()) {
            hidePopupsAndPreserveSelection();
            G();
        }
    }

    public void hidePopupsAndPreserveSelection() {
        n();
        t().e();
    }

    @Override // defpackage.WZ
    public void i(boolean z, boolean z2) {
        if (!z) {
            o();
        }
        if (z != this.U || z2 != this.V) {
            this.U = z;
            this.V = z2;
            if (e()) {
                this.R.invalidate();
            }
        }
    }

    @Override // defpackage.B2
    public boolean j(ActionMode actionMode, Menu menu) {
        F3 f3;
        C2355eS0 es0;
        F3 f32 = this.l0;
        if (f32 != null) {
            ((H3) f32).b();
        }
        menu.removeGroup(R.id.select_action_menu_default_items);
        menu.removeGroup(R.id.select_action_menu_assist_items);
        menu.removeGroup(R.id.select_action_menu_text_processing_menus);
        menu.removeGroup(16908353);
        Context context = this.I;
        try {
            actionMode.getMenuInflater().inflate(R.menu.f42500_resource_name_obfuscated_RES_2131689484, menu);
        } catch (Resources.NotFoundException unused) {
            new MenuInflater(context).inflate(R.menu.f42500_resource_name_obfuscated_RES_2131689484, menu);
        }
        if (Build.VERSION.SDK_INT >= 26 && (es0 = this.i0) != null && es0.a()) {
            menu.add(R.id.select_action_menu_assist_items, 16908353, 1, this.i0.c).setIcon(this.i0.d);
        }
        if (!this.U || !k()) {
            menu.removeItem(R.id.select_action_menu_paste);
            menu.removeItem(R.id.select_action_menu_paste_as_plain_text);
        }
        if (!l()) {
            menu.removeItem(R.id.select_action_menu_paste_as_plain_text);
        }
        if (!this.c0) {
            menu.removeItem(R.id.select_action_menu_select_all);
            menu.removeItem(R.id.select_action_menu_cut);
            menu.removeItem(R.id.select_action_menu_copy);
            menu.removeItem(R.id.select_action_menu_share);
            menu.removeItem(R.id.select_action_menu_web_search);
        } else {
            if (!this.U) {
                menu.removeItem(R.id.select_action_menu_cut);
            }
            if (this.U || !B(1)) {
                menu.removeItem(R.id.select_action_menu_share);
            }
            if (this.U || this.K.a() || !B(2)) {
                menu.removeItem(R.id.select_action_menu_web_search);
            }
            if (this.V) {
                menu.removeItem(R.id.select_action_menu_copy);
                menu.removeItem(R.id.select_action_menu_cut);
            }
        }
        MenuItem findItem = menu.findItem(R.id.select_action_menu_paste_as_plain_text);
        if (findItem != null) {
            findItem.setTitle(17039385);
        }
        Context context2 = (Context) this.f10931J.f11022J.get();
        C2355eS0 es02 = this.i0;
        if (!(es02 == null || (f3 = this.l0) == null || context2 == null)) {
            ((H3) f3).a(context2, menu, es02.g, es02.i);
        }
        if (this.c0 && !this.V && B(4)) {
            List c = PackageManagerUtils.c(new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain"), 0);
            for (int i = 0; i < c.size(); i++) {
                ResolveInfo resolveInfo = (ResolveInfo) c.get(i);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.exported) {
                    MenuItem add = menu.add(R.id.select_action_menu_text_processing_menus, 0, i + 100, resolveInfo.loadLabel(this.I.getPackageManager()));
                    Intent putExtra = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain").putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !this.U);
                    ActivityInfo activityInfo2 = resolveInfo.activityInfo;
                    add.setIntent(putExtra.setClassName(activityInfo2.packageName, activityInfo2.name)).setShowAsAction(1);
                }
            }
        }
        return true;
    }

    public final boolean k() {
        return ((ClipboardManager) this.I.getSystemService("clipboard")).hasPrimaryClip();
    }

    public boolean l() {
        boolean z = false;
        if (Build.VERSION.SDK_INT < 26 || !this.X) {
            return false;
        }
        ClipboardManager clipboardManager = (ClipboardManager) this.I.getSystemService("clipboard");
        if (!clipboardManager.hasPrimaryClip()) {
            return false;
        }
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        ClipDescription description = primaryClip.getDescription();
        CharSequence text = primaryClip.getItemAt(0).getText();
        if (description.hasMimeType("text/plain") && (text instanceof Spanned)) {
            Spanned spanned = (Spanned) text;
            Class[] clsArr = {CharacterStyle.class, ParagraphStyle.class, UpdateAppearance.class};
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                if (spanned.nextSpanTransition(-1, spanned.length(), clsArr[i]) < spanned.length()) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                return true;
            }
        }
        return description.hasMimeType("text/html");
    }

    public void m() {
        WebContentsImpl webContentsImpl = this.K;
        if (webContentsImpl != null) {
            if (this.L != B2.F) {
                if (!webContentsImpl.g()) {
                    N.MDK_KK0z(webContentsImpl.H, webContentsImpl);
                }
                this.i0 = null;
            }
        }
    }

    public void n() {
        this.Y = false;
        c();
    }

    public final void nativeSelectionPopupControllerDestroyed() {
        this.M = 0;
    }

    public void o() {
        if (z()) {
            this.d0.b();
            this.d0 = null;
        }
    }

    @Override // defpackage.Wy1
    public void onAttachedToWindow() {
        I(true);
    }

    @Override // defpackage.Wy1
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // defpackage.Wy1
    public void onDetachedFromWindow() {
        I(false);
    }

    public void onDragUpdate(int i, float f, float f2) {
        if ((i != 2 || Build.VERSION.CODENAME.equals("S")) && this.k0 != null) {
            float s = s();
            float f3 = f * s;
            float f4 = (f2 * s) + this.K.M.k;
            C5445wb0 wb0 = this.k0;
            if (wb0.f11552a.b.a() != null) {
                if (wb0.c && f4 != wb0.i) {
                    if (wb0.b.isRunning()) {
                        wb0.b.cancel();
                        wb0.a();
                        wb0.f = wb0.d;
                        wb0.g = wb0.e;
                    } else {
                        wb0.f = wb0.h;
                        wb0.g = wb0.i;
                    }
                    wb0.b.start();
                } else if (!wb0.b.isRunning()) {
                    wb0.f11552a.a(f3, f4);
                }
                wb0.h = f3;
                wb0.i = f4;
                wb0.c = true;
            }
        }
    }

    public final void onSelectWordAroundCaretAck(boolean z, int i, int i2) {
        AbstractC2185dS0 ds0 = this.f0;
        if (ds0 != null) {
            ds0.g(z, i, i2);
        }
    }

    public void onSelectionChanged(String str) {
        if (TextUtils.isEmpty(str) && this.c0) {
            ZX0 zx0 = this.g0;
            if (zx0 != null) {
                zx0.f(this.Z, this.a0, 107, null);
            }
            n();
        }
        this.Z = str;
        AbstractC2185dS0 ds0 = this.f0;
        if (ds0 != null) {
            ds0.d(str);
        }
    }

    public void onSelectionEvent(int i, int i2, int i3, int i4, int i5) {
        View view;
        View view2;
        if (i2 == i4) {
            i4++;
        }
        if (i3 == i5) {
            i5++;
        }
        switch (i) {
            case 0:
                this.O.set(i2, i3, i4, i5);
                break;
            case 1:
                this.O.set(i2, i3, i4, i5);
                if (e()) {
                    this.R.invalidateContentRect();
                }
                if (this.b0 && Build.VERSION.SDK_INT >= 29 && (view = this.Q) != null) {
                    view.performHapticFeedback(9);
                    break;
                }
            case 2:
                this.Z = "";
                this.a0 = 0;
                this.c0 = false;
                this.Y = false;
                this.O.setEmpty();
                AbstractC2185dS0 ds0 = this.f0;
                if (ds0 != null) {
                    ds0.a();
                }
                c();
                break;
            case 3:
                v(true);
                this.b0 = true;
                break;
            case 4:
                H(i2, i5);
                C5445wb0 wb0 = this.k0;
                if (wb0 != null) {
                    wb0.b();
                }
                this.b0 = false;
                break;
            case 5:
                this.O.set(i2, i3, i4, i5);
                break;
            case 6:
                this.O.set(i2, i3, i4, i5);
                if (GestureListenerManagerImpl.s0(this.K).isScrollInProgress() || !z()) {
                    o();
                } else {
                    try {
                        ((C4403qR) this.d0).a(u());
                    } catch (WindowManager.BadTokenException unused) {
                    }
                }
                if (this.b0 && Build.VERSION.SDK_INT >= 29 && (view2 = this.Q) != null) {
                    view2.performHapticFeedback(9);
                    break;
                }
                break;
            case Version.VERSION_7:
                if (this.e0) {
                    o();
                } else {
                    Rect rect = this.O;
                    H(rect.left, rect.bottom);
                }
                this.e0 = false;
                break;
            case Version.VERSION_8:
                o();
                if (!this.c0) {
                    this.O.setEmpty();
                    break;
                }
                break;
            case Version.VERSION_9:
                this.e0 = z();
                o();
                this.b0 = true;
                break;
            case Version.VERSION_10:
                if (this.e0) {
                    Rect rect2 = this.O;
                    H(rect2.left, rect2.bottom);
                }
                this.e0 = false;
                C5445wb0 wb02 = this.k0;
                if (wb02 != null) {
                    wb02.b();
                }
                this.b0 = false;
                break;
        }
        if (this.f0 != null) {
            float s = s();
            Rect rect3 = this.O;
            this.f0.e(i, (float) ((int) (((float) rect3.left) * s)), (float) ((int) (((float) rect3.bottom) * s)));
        }
    }

    @Override // defpackage.Wy1
    public void onWindowFocusChanged(boolean z) {
        if (e()) {
            this.R.onWindowFocusChanged(z);
        }
    }

    public final void p() {
        if (this.K.s() != null) {
            RenderWidgetHostViewImpl w0 = this.K.s();
            if (!w0.a()) {
                N.MQWja$xA(w0.f10916a, w0);
            }
        }
    }

    public final void q() {
        EE0 c;
        this.Y = true;
        c();
        p();
        WebContentsImpl webContentsImpl = this.K;
        if (!(webContentsImpl == null || (c = EE0.c(webContentsImpl)) == null)) {
            c.e();
        }
        m();
    }

    public void restoreSelectionPopupsIfNecessary() {
        if (this.c0 && !e()) {
            G();
        }
    }

    public final float s() {
        return this.K.M.j;
    }

    public void showSelectionMenu(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, String str, int i6, boolean z3, boolean z4, boolean z5, int i7) {
        this.O.set(i, i2, i3, i4 + i5);
        this.U = z;
        this.Z = str;
        this.a0 = i6;
        boolean z6 = str.length() != 0;
        this.c0 = z6;
        this.V = z2;
        this.W = z3;
        this.X = z4;
        this.Y = true;
        if (z6) {
            ZX0 zx0 = this.g0;
            if (!(zx0 == null || i7 == 7)) {
                if (i7 == 9) {
                    zx0.g(this.Z, this.a0, this.i0);
                } else if (i7 != 10) {
                    zx0.h(this.Z, this.a0, z);
                } else {
                    zx0.f(this.Z, this.a0, 201, null);
                }
            }
            if (i7 == 9) {
                G();
                return;
            }
            AbstractC2185dS0 ds0 = this.f0;
            if (ds0 == null || !ds0.f(z5)) {
                G();
                return;
            }
            return;
        }
        View view = this.Q;
        if (view != null && view.getParent() != null && this.Q.getVisibility() == 0) {
            o();
            C4064oS0 os0 = new C4064oS0(this);
            Context context = (Context) this.f10931J.f11022J.get();
            if (context != null) {
                C4403qR qRVar = new C4403qR(context, this.Q, os0, null);
                this.d0 = qRVar;
                try {
                    qRVar.a(u());
                } catch (WindowManager.BadTokenException unused) {
                }
            }
        }
    }

    public final EE0 t() {
        if (this.h0 == null) {
            this.h0 = EE0.c(this.K);
        }
        return this.h0;
    }

    public final Rect u() {
        float s = s();
        Rect rect = this.O;
        Rect rect2 = new Rect((int) (((float) rect.left) * s), (int) (((float) rect.top) * s), (int) (((float) rect.right) * s), (int) (((float) rect.bottom) * s));
        rect2.offset(0, (int) this.K.M.k);
        return rect2;
    }

    public final void v(boolean z) {
        if (y() && this.T != z) {
            this.T = z;
            if (z) {
                this.P.run();
                return;
            }
            this.H.removeCallbacks(this.P);
            if (e()) {
                this.R.hide(300);
            }
        }
    }

    public final void w() {
        C5445wb0 wb0;
        Object obj = ThreadUtils.f10596a;
        if (C0727Ly.f8451a == null) {
            C0727Ly.f8451a = new C0727Ly();
        }
        C0727Ly ly = C0727Ly.f8451a;
        C3722mS0 ms0 = new C3722mS0(this);
        Objects.requireNonNull(ly);
        if (Build.VERSION.SDK_INT < 28) {
            wb0 = null;
        } else {
            wb0 = new C5445wb0(new C5615xb0(ms0));
        }
        this.k0 = wb0;
    }

    @Override // defpackage.Wy1
    public void x(WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            q();
            this.I = null;
            this.f10931J = null;
            return;
        }
        this.f10931J = windowAndroid;
        this.I = this.K.u0();
        w();
        o();
    }

    public final boolean y() {
        if (!e() || this.R.getType() != 1) {
            return false;
        }
        return true;
    }

    public boolean z() {
        return this.d0 != null;
    }
}
