package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* renamed from: wg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC5460wg0 extends AbstractDialogC3498l8 {
    public static final /* synthetic */ int H = 0;
    public final C3246jh0 I;

    /* renamed from: J  reason: collision with root package name */
    public final C3243jg0 f11560J;
    public C0629Kg0 K = C0629Kg0.f8380a;
    public C2392eh0 L;
    public final List M = new ArrayList();
    public final List N = new ArrayList();
    public final List O = new ArrayList();
    public final List P = new ArrayList();
    public Context Q;
    public boolean R;
    public boolean S;
    public long T;
    public final Handler U = new HandlerC2048cg0(this);
    public RecyclerView V;
    public C4950tg0 W;
    public C5290vg0 X;
    public Map Y;
    public C2392eh0 Z;
    public Map a0;
    public boolean b0;
    public boolean c0;
    public boolean d0;
    public ImageButton e0;
    public Button f0;
    public ImageView g0;
    public View h0;
    public ImageView i0;
    public TextView j0;
    public TextView k0;
    public String l0;
    public C0985Qd0 m0;
    public C2731gg0 n0;
    public MediaDescriptionCompat o0;
    public AsyncTaskC2560fg0 p0;
    public Bitmap q0;
    public Uri r0;
    public boolean s0;
    public Bitmap t0;
    public int u0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DialogC5460wg0(android.content.Context r2) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r2 = defpackage.AbstractC4783sh0.a(r2, r0, r0)
            int r0 = defpackage.AbstractC4783sh0.b(r2)
            r1.<init>(r2, r0)
            Kg0 r2 = defpackage.C0629Kg0.f8380a
            r1.K = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.M = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.N = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.O = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.P = r2
            cg0 r2 = new cg0
            r2.<init>(r1)
            r1.U = r2
            android.content.Context r2 = r1.getContext()
            r1.Q = r2
            jh0 r2 = defpackage.C3246jh0.e(r2)
            r1.I = r2
            jg0 r0 = new jg0
            r0.<init>(r1)
            r1.f11560J = r0
            eh0 r0 = r2.h()
            r1.L = r0
            gg0 r0 = new gg0
            r0.<init>(r1)
            r1.n0 = r0
            android.support.v4.media.session.MediaSessionCompat$Token r2 = r2.f()
            r1.g(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DialogC5460wg0.<init>(android.content.Context):void");
    }

    public static boolean c(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    public static void f(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    public void d(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            C2392eh0 eh0 = (C2392eh0) list.get(size);
            if (!(!eh0.e() && eh0.g && eh0.i(this.K) && this.L != eh0)) {
                list.remove(size);
            }
        }
    }

    public void e() {
        Bitmap bitmap;
        Bitmap bitmap2;
        Uri uri;
        MediaDescriptionCompat mediaDescriptionCompat = this.o0;
        Uri uri2 = null;
        if (mediaDescriptionCompat == null) {
            bitmap = null;
        } else {
            bitmap = mediaDescriptionCompat.f9450J;
        }
        if (mediaDescriptionCompat != null) {
            uri2 = mediaDescriptionCompat.K;
        }
        AsyncTaskC2560fg0 fg0 = this.p0;
        if (fg0 == null) {
            bitmap2 = this.q0;
        } else {
            bitmap2 = fg0.f9940a;
        }
        if (fg0 == null) {
            uri = this.r0;
        } else {
            uri = fg0.b;
        }
        if (bitmap2 != bitmap || (bitmap2 == null && !Objects.equals(uri, uri2))) {
            AsyncTaskC2560fg0 fg02 = this.p0;
            if (fg02 != null) {
                fg02.cancel(true);
            }
            AsyncTaskC2560fg0 fg03 = new AsyncTaskC2560fg0(this);
            this.p0 = fg03;
            fg03.execute(new Void[0]);
        }
    }

    public final void g(MediaSessionCompat$Token mediaSessionCompat$Token) {
        C0985Qd0 qd0 = this.m0;
        MediaDescriptionCompat mediaDescriptionCompat = null;
        if (qd0 != null) {
            qd0.d(this.n0);
            this.m0 = null;
        }
        if (mediaSessionCompat$Token != null && this.S) {
            C0985Qd0 qd02 = new C0985Qd0(this.Q, mediaSessionCompat$Token);
            this.m0 = qd02;
            qd02.c(this.n0);
            MediaMetadataCompat a2 = this.m0.a();
            if (a2 != null) {
                mediaDescriptionCompat = a2.c();
            }
            this.o0 = mediaDescriptionCompat;
            e();
            l();
        }
    }

    public void h(C0629Kg0 kg0) {
        if (kg0 == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.K.equals(kg0)) {
            this.K = kg0;
            if (this.S) {
                this.I.j(this.f11560J);
                this.I.a(kg0, this.f11560J, 1);
                m();
            }
        }
    }

    public final boolean j() {
        if (this.Z != null || this.b0) {
            return true;
        }
        return !this.R;
    }

    public void k() {
        getWindow().setLayout(AbstractC0991Qf0.b(this.Q), !this.Q.getResources().getBoolean(R.bool.f9540_resource_name_obfuscated_RES_2131034117) ? -1 : -2);
        this.q0 = null;
        this.r0 = null;
        e();
        l();
        n();
    }

    public void l() {
        CharSequence charSequence;
        if (j()) {
            this.d0 = true;
            return;
        }
        this.d0 = false;
        if (!this.L.h() || this.L.e()) {
            dismiss();
        }
        CharSequence charSequence2 = null;
        if (!this.s0 || c(this.t0) || this.t0 == null) {
            if (c(this.t0)) {
                StringBuilder i = AbstractC2531fV.i("Can't set artwork image with recycled bitmap: ");
                i.append(this.t0);
                Log.w("MediaRouteCtrlDialog", i.toString());
            }
            this.i0.setVisibility(8);
            this.h0.setVisibility(8);
            this.g0.setImageBitmap(null);
        } else {
            this.i0.setVisibility(0);
            this.i0.setImageBitmap(this.t0);
            this.i0.setBackgroundColor(this.u0);
            this.h0.setVisibility(0);
            Bitmap bitmap = this.t0;
            RenderScript create = RenderScript.create(this.Q);
            Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
            Allocation createTyped = Allocation.createTyped(create, createFromBitmap.getType());
            ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
            create2.setRadius(10.0f);
            create2.setInput(createFromBitmap);
            create2.forEach(createTyped);
            Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
            createTyped.copyTo(copy);
            createFromBitmap.destroy();
            createTyped.destroy();
            create2.destroy();
            create.destroy();
            this.g0.setImageBitmap(copy);
        }
        this.s0 = false;
        this.t0 = null;
        this.u0 = 0;
        MediaDescriptionCompat mediaDescriptionCompat = this.o0;
        if (mediaDescriptionCompat == null) {
            charSequence = null;
        } else {
            charSequence = mediaDescriptionCompat.G;
        }
        boolean z = !TextUtils.isEmpty(charSequence);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.o0;
        if (mediaDescriptionCompat2 != null) {
            charSequence2 = mediaDescriptionCompat2.H;
        }
        boolean isEmpty = true ^ TextUtils.isEmpty(charSequence2);
        if (z) {
            this.j0.setText(charSequence);
        } else {
            this.j0.setText(this.l0);
        }
        if (isEmpty) {
            this.k0.setText(charSequence2);
            this.k0.setVisibility(0);
            return;
        }
        this.k0.setVisibility(8);
    }

    public void m() {
        this.M.clear();
        this.N.clear();
        this.O.clear();
        this.M.addAll(this.L.c());
        for (C2392eh0 eh0 : this.L.f9872a.b()) {
            C2222dh0 b = this.L.b(eh0);
            if (b != null) {
                if (b.a()) {
                    this.N.add(eh0);
                }
                C0141Cg0 cg0 = b.f9800a;
                if (cg0 != null && cg0.e) {
                    this.O.add(eh0);
                }
            }
        }
        d(this.N);
        d(this.O);
        List list = this.M;
        C5120ug0 ug0 = C5120ug0.F;
        Collections.sort(list, ug0);
        Collections.sort(this.N, ug0);
        Collections.sort(this.O, ug0);
        this.W.w();
    }

    public void n() {
        if (!this.S) {
            return;
        }
        if (SystemClock.uptimeMillis() - this.T < 300) {
            this.U.removeMessages(1);
            this.U.sendEmptyMessageAtTime(1, this.T + 300);
        } else if (j()) {
            this.c0 = true;
        } else {
            this.c0 = false;
            if (!this.L.h() || this.L.e()) {
                dismiss();
            }
            this.T = SystemClock.uptimeMillis();
            this.W.v();
        }
    }

    public void o() {
        if (this.c0) {
            n();
        }
        if (this.d0) {
            l();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.S = true;
        this.I.a(this.K, this.f11560J, 1);
        m();
        g(this.I.f());
    }

    @Override // defpackage.AbstractDialogC3498l8
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.f39340_resource_name_obfuscated_RES_2131624243);
        AbstractC4783sh0.k(this.Q, this);
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_cast_close_button);
        this.e0 = imageButton;
        imageButton.setColorFilter(-1);
        this.e0.setOnClickListener(new View$OnClickListenerC2219dg0(this));
        Button button = (Button) findViewById(R.id.mr_cast_stop_button);
        this.f0 = button;
        button.setTextColor(-1);
        this.f0.setOnClickListener(new View$OnClickListenerC2389eg0(this));
        this.W = new C4950tg0(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mr_cast_list);
        this.V = recyclerView;
        recyclerView.q0(this.W);
        this.V.t0(new LinearLayoutManager(this.Q));
        this.X = new C5290vg0(this);
        this.Y = new HashMap();
        this.a0 = new HashMap();
        this.g0 = (ImageView) findViewById(R.id.mr_cast_meta_background);
        this.h0 = findViewById(R.id.mr_cast_meta_black_scrim);
        this.i0 = (ImageView) findViewById(R.id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R.id.mr_cast_meta_title);
        this.j0 = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R.id.mr_cast_meta_subtitle);
        this.k0 = textView2;
        textView2.setTextColor(-1);
        this.l0 = this.Q.getResources().getString(R.string.f55190_resource_name_obfuscated_RES_2131952836);
        this.R = true;
        k();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.S = false;
        this.I.j(this.f11560J);
        this.U.removeCallbacksAndMessages(null);
        g(null);
    }
}
