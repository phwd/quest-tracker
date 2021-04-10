package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;

/* renamed from: jf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC3240jf0 extends AbstractDialogC3498l8 {
    public final C3246jh0 H = C3246jh0.e(getContext());
    public final C2728gf0 I = new C2728gf0(this);

    /* renamed from: J  reason: collision with root package name */
    public TextView f10225J;
    public C0629Kg0 K = C0629Kg0.f8380a;
    public ArrayList L;
    public C2899hf0 M;
    public ListView N;
    public boolean O;
    public long P;
    public final Handler Q = new HandlerC2557ff0(this);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DialogC3240jf0(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r2 = defpackage.AbstractC4783sh0.a(r2, r3, r0)
            int r3 = defpackage.AbstractC4783sh0.b(r2)
            r1.<init>(r2, r3)
            Kg0 r2 = defpackage.C0629Kg0.f8380a
            r1.K = r2
            ff0 r2 = new ff0
            r2.<init>(r1)
            r1.Q = r2
            android.content.Context r2 = r1.getContext()
            jh0 r2 = defpackage.C3246jh0.e(r2)
            r1.H = r2
            gf0 r2 = new gf0
            r2.<init>(r1)
            r1.I = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DialogC3240jf0.<init>(android.content.Context, int):void");
    }

    public void c() {
        if (this.O) {
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
            Collections.sort(arrayList, C3069if0.F);
            if (SystemClock.uptimeMillis() - this.P >= 300) {
                this.P = SystemClock.uptimeMillis();
                this.L.clear();
                this.L.addAll(arrayList);
                this.M.notifyDataSetChanged();
                return;
            }
            this.Q.removeMessages(1);
            Handler handler = this.Q;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.P + 300);
        }
    }

    public void d(C0629Kg0 kg0) {
        if (kg0 == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.K.equals(kg0)) {
            this.K = kg0;
            if (this.O) {
                this.H.j(this.I);
                this.H.a(kg0, this.I, 1);
            }
            c();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.O = true;
        this.H.a(this.K, this.I, 1);
        c();
    }

    @Override // defpackage.AbstractDialogC3498l8
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.f39400_resource_name_obfuscated_RES_2131624249);
        this.L = new ArrayList();
        this.M = new C2899hf0(this, getContext(), this.L);
        ListView listView = (ListView) findViewById(R.id.mr_chooser_list);
        this.N = listView;
        listView.setAdapter((ListAdapter) this.M);
        this.N.setOnItemClickListener(this.M);
        this.N.setEmptyView(findViewById(16908292));
        this.f10225J = (TextView) findViewById(R.id.mr_chooser_title);
        getWindow().setLayout(AbstractC0991Qf0.a(getContext()), -2);
    }

    public void onDetachedFromWindow() {
        this.O = false;
        this.H.j(this.I);
        this.Q.removeMessages(1);
        super.onDetachedFromWindow();
    }

    @Override // android.app.Dialog, defpackage.AbstractDialogC3498l8
    public void setTitle(CharSequence charSequence) {
        this.f10225J.setText(charSequence);
    }

    @Override // android.app.Dialog, defpackage.AbstractDialogC3498l8
    public void setTitle(int i) {
        this.f10225J.setText(i);
    }
}
