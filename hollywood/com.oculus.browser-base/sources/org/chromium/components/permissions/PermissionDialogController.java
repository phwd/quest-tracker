package org.chromium.components.permissions;

import J.N;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionDialogController implements AbstractC3834n6, AbstractC3087il0 {
    public UH0 F;
    public UH0 G;
    public PermissionDialogDelegate H;
    public C2746gl0 I;

    /* renamed from: J  reason: collision with root package name */
    public List f10877J = new LinkedList();
    public int K = 0;

    public PermissionDialogController(LB0 lb0) {
    }

    public static void createDialog(PermissionDialogDelegate permissionDialogDelegate) {
        PermissionDialogController permissionDialogController = MB0.f8463a;
        permissionDialogController.f10877J.add(permissionDialogDelegate);
        permissionDialogDelegate.b = permissionDialogController;
        permissionDialogController.b();
    }

    public final void a(int i) {
        if (i != 0) {
            this.H.h.clone();
        }
        PermissionDialogDelegate permissionDialogDelegate = this.H;
        N.MLMIuACo(permissionDialogDelegate.f10878a, permissionDialogDelegate);
        permissionDialogDelegate.f10878a = 0;
        this.H = null;
        this.K = 0;
    }

    public final void b() {
        if (this.K == 0 && !this.f10877J.isEmpty()) {
            PermissionDialogDelegate permissionDialogDelegate = (PermissionDialogDelegate) this.f10877J.remove(0);
            this.H = permissionDialogDelegate;
            Context context = (Context) permissionDialogDelegate.c.f11022J.get();
            if (ContextUtils.a(context) == null) {
                PermissionDialogDelegate permissionDialogDelegate2 = this.H;
                N.My1ZBTOK(permissionDialogDelegate2.f10878a, permissionDialogDelegate2);
                a(0);
                return;
            }
            PermissionDialogDelegate permissionDialogDelegate3 = this.H;
            if (permissionDialogDelegate3 == null) {
                this.K = 0;
                b();
                return;
            }
            this.I = permissionDialogDelegate3.c.v0();
            PermissionDialogDelegate permissionDialogDelegate4 = this.H;
            JB0 jb0 = new JB0(this, context);
            View a2 = AbstractC2471f70.a((Context) permissionDialogDelegate4.c.f11022J.get(), R.layout.f40570_resource_name_obfuscated_RES_2131624366, null);
            String str = permissionDialogDelegate4.e;
            TextView textView = (TextView) a2.findViewById(R.id.text);
            textView.setText(str);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(permissionDialogDelegate4.d, 0, 0, 0);
            Map c = UH0.c(AbstractC3258jl0.r);
            OH0 oh0 = AbstractC3258jl0.f10235a;
            LH0 lh0 = new LH0(null);
            lh0.f8415a = this;
            HashMap hashMap = (HashMap) c;
            hashMap.put(oh0, lh0);
            TH0 th0 = AbstractC3258jl0.f;
            LH0 lh02 = new LH0(null);
            lh02.f8415a = a2;
            hashMap.put(th0, lh02);
            TH0 th02 = AbstractC3258jl0.g;
            String str2 = permissionDialogDelegate4.f;
            LH0 lh03 = new LH0(null);
            lh03.f8415a = str2;
            hashMap.put(th02, lh03);
            TH0 th03 = AbstractC3258jl0.j;
            String str3 = permissionDialogDelegate4.g;
            LH0 lh04 = new LH0(null);
            lh04.f8415a = str3;
            hashMap.put(th03, lh04);
            OH0 oh02 = AbstractC3258jl0.b;
            String str4 = permissionDialogDelegate4.e;
            LH0 lh05 = new LH0(null);
            lh05.f8415a = str4;
            hashMap.put(oh02, lh05);
            MH0 mh0 = AbstractC3258jl0.n;
            GH0 gh0 = new GH0(null);
            gh0.f8081a = true;
            hashMap.put(mh0, gh0);
            OH0 oh03 = AbstractC3258jl0.o;
            LH0 lh06 = new LH0(null);
            lh06.f8415a = jb0;
            UH0 o = AbstractC2531fV.o(hashMap, oh03, lh06, c, null);
            this.F = o;
            this.I.i(o, 1, false);
            this.K = 2;
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.K = 3;
            this.I.b(uh0, 1);
        } else if (i == 1) {
            this.K = 4;
            this.I.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3834n6
    public void e() {
        PermissionDialogDelegate permissionDialogDelegate = this.H;
        if (permissionDialogDelegate == null) {
            this.K = 0;
        } else {
            N.My1ZBTOK(permissionDialogDelegate.f10878a, permissionDialogDelegate);
            a(2);
        }
        b();
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        this.F = null;
        PermissionDialogDelegate permissionDialogDelegate = this.H;
        if (permissionDialogDelegate == null) {
            this.K = 0;
            return;
        }
        int i2 = this.K;
        if (i2 == 3) {
            this.K = 5;
            if (!AbstractC4005o6.a(permissionDialogDelegate.c, (int[]) permissionDialogDelegate.h.clone(), this)) {
                g();
                return;
            }
            return;
        }
        if (i2 == 4) {
            N.MG2fhXvZ(permissionDialogDelegate.f10878a, permissionDialogDelegate);
        } else {
            N.My1ZBTOK(permissionDialogDelegate.f10878a, permissionDialogDelegate);
        }
        a(2);
        b();
    }

    @Override // defpackage.AbstractC3834n6
    public void g() {
        PermissionDialogDelegate permissionDialogDelegate = this.H;
        if (permissionDialogDelegate == null) {
            this.K = 0;
        } else {
            N.MoC5mife(permissionDialogDelegate.f10878a, permissionDialogDelegate);
            a(1);
        }
        b();
    }
}
