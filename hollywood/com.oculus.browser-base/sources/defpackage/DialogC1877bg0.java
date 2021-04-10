package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: bg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC1877bg0 extends AbstractDialogC3498l8 {
    public final C3246jh0 H;
    public final C1235Uf0 I;

    /* renamed from: J  reason: collision with root package name */
    public Context f9555J;
    public C0629Kg0 K = C0629Kg0.f8380a;
    public List L;
    public ImageButton M;
    public C1540Zf0 N;
    public RecyclerView O;
    public boolean P;
    public long Q;
    public long R;
    public final Handler S = new HandlerC1113Sf0(this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DialogC1877bg0(android.content.Context r3) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r3 = defpackage.AbstractC4783sh0.a(r3, r0, r0)
            int r0 = defpackage.AbstractC4783sh0.b(r3)
            r2.<init>(r3, r0)
            Kg0 r3 = defpackage.C0629Kg0.f8380a
            r2.K = r3
            Sf0 r3 = new Sf0
            r3.<init>(r2)
            r2.S = r3
            android.content.Context r3 = r2.getContext()
            jh0 r0 = defpackage.C3246jh0.e(r3)
            r2.H = r0
            Uf0 r0 = new Uf0
            r0.<init>(r2)
            r2.I = r0
            r2.f9555J = r3
            android.content.res.Resources r3 = r3.getResources()
            r0 = 2131492895(0x7f0c001f, float:1.8609255E38)
            int r3 = r3.getInteger(r0)
            long r0 = (long) r3
            r2.Q = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DialogC1877bg0.<init>(android.content.Context):void");
    }

    public void c() {
        if (this.P) {
            ArrayList arrayList = new ArrayList(this.H.g());
            int size = arrayList.size();
            while (true) {
                int i = size - 1;
                boolean z = true;
                if (size <= 0) {
                    break;
                }
                C2392eh0 eh0 = (C2392eh0) arrayList.get(i);
                if (eh0.e() || !eh0.g || !eh0.i(this.K)) {
                    z = false;
                }
                if (!z) {
                    arrayList.remove(i);
                }
                size = i;
            }
            Collections.sort(arrayList, C1697ag0.F);
            if (SystemClock.uptimeMillis() - this.R >= this.Q) {
                this.R = SystemClock.uptimeMillis();
                this.L.clear();
                this.L.addAll(arrayList);
                this.N.s();
                return;
            }
            this.S.removeMessages(1);
            Handler handler = this.S;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.R + this.Q);
        }
    }

    public void d(C0629Kg0 kg0) {
        if (kg0 == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.K.equals(kg0)) {
            this.K = kg0;
            if (this.P) {
                this.H.j(this.I);
                this.H.a(kg0, this.I, 1);
            }
            c();
        }
    }

    public void e() {
        getWindow().setLayout(AbstractC0991Qf0.b(this.f9555J), !this.f9555J.getResources().getBoolean(R.bool.f9540_resource_name_obfuscated_RES_2131034117) ? -1 : -2);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = true;
        this.H.a(this.K, this.I, 1);
        c();
    }

    @Override // defpackage.AbstractDialogC3498l8
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.f39440_resource_name_obfuscated_RES_2131624253);
        AbstractC4783sh0.k(this.f9555J, this);
        this.L = new ArrayList();
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_picker_close_button);
        this.M = imageButton;
        imageButton.setOnClickListener(new View$OnClickListenerC1174Tf0(this));
        this.N = new C1540Zf0(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mr_picker_list);
        this.O = recyclerView;
        recyclerView.q0(this.N);
        this.O.t0(new LinearLayoutManager(this.f9555J));
        e();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.P = false;
        this.H.j(this.I);
        this.S.removeMessages(1);
    }
}
