package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.SystemClock;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.browser_ui.photo_picker.PhotoPickerToolbar;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: EC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EC0 extends RelativeLayout implements FP, QK0, AbstractC5559xD, View.OnClickListener, AbstractC3039iS0 {
    public DialogC4536rC0 F;
    public SelectableListLayout G;
    public WindowAndroid H;
    public ContentResolver I;

    /* renamed from: J  reason: collision with root package name */
    public List f7944J;
    public boolean K;
    public AbstractC4707sC0 L;
    public BinderC5899zD M;
    public RecyclerView N;
    public C5387wC0 O;
    public GridLayoutManager P;
    public GC0 Q;
    public C3209jS0 R;
    public OF S;
    public OF T;
    public OF U;
    public int V;
    public int W;
    public int a0;
    public boolean b0;
    public boolean c0;
    public int d0;
    public int e0;
    public int f0;
    public int g0;
    public GP h0;
    public long i0;
    public boolean j0;
    public List k0;
    public final PickerVideoPlayer l0;
    public ImageView m0;

    public EC0(WindowAndroid windowAndroid, ContentResolver contentResolver, boolean z, boolean z2, AbstractC4877tC0 tc0) {
        super((Context) windowAndroid.f11022J.get());
        this.H = windowAndroid;
        Context context = (Context) windowAndroid.f11022J.get();
        this.I = contentResolver;
        this.K = z;
        BinderC5899zD zDVar = new BinderC5899zD(this, context, z2);
        this.M = zDVar;
        Intent intent = (Intent) BinderC5899zD.c.get();
        intent.setAction(CY.class.getName());
        zDVar.d.bindService(intent, zDVar.s, 1);
        C3209jS0 js0 = new C3209jS0();
        this.R = js0;
        js0.d.b(this);
        if (!z) {
            this.R.f10206a = true;
        }
        this.G = (SelectableListLayout) LayoutInflater.from(context).inflate(R.layout.f40650_resource_name_obfuscated_RES_2131624374, this).findViewById(R.id.selectable_list);
        C5387wC0 wc0 = new C5387wC0(this);
        this.O = wc0;
        this.N = this.G.e(wc0, null);
        PhotoPickerToolbar photoPickerToolbar = (PhotoPickerToolbar) this.G.f(R.layout.f40660_resource_name_obfuscated_RES_2131624375, this.R, z ? R.string.f58970_resource_name_obfuscated_RES_2131953214 : R.string.f58960_resource_name_obfuscated_RES_2131953213, 0, 0, null, false, false);
        photoPickerToolbar.h();
        photoPickerToolbar.I.setOnClickListener(this);
        photoPickerToolbar.b1 = tc0;
        ((Button) photoPickerToolbar.findViewById(R.id.done)).setOnClickListener(this);
        this.l0 = (PickerVideoPlayer) findViewById(R.id.playback_container);
        this.m0 = (ImageView) findViewById(R.id.zoom);
        c();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, this.d0);
        this.P = gridLayoutManager;
        RecyclerView recyclerView = this.N;
        recyclerView.e0 = true;
        recyclerView.t0(gridLayoutManager);
        GC0 gc0 = new GC0(this, this.d0, this.e0);
        this.Q = gc0;
        this.N.g(gc0);
        this.N.V = this;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024;
        int i = (int) (maxMemory / 4);
        this.a0 = i;
        this.V = i;
        this.W = (int) (maxMemory / 8);
    }

    @Override // defpackage.QK0
    public void a(XK0 xk0) {
        String x = ((C5727yC0) xk0).x();
        if (x != null) {
            BinderC5899zD zDVar = this.M;
            Objects.requireNonNull(zDVar);
            Object obj = ThreadUtils.f10596a;
            Iterator it = zDVar.u.iterator();
            while (it.hasNext()) {
                if (((C5389wD) it.next()).f11528a.getPath().equals(x)) {
                    it.remove();
                }
            }
        }
    }

    @Override // defpackage.AbstractC3039iS0
    public void b(List list) {
        if (this.m0.getVisibility() != 0) {
            this.m0.setVisibility(0);
            this.m0.setOnClickListener(this);
        }
    }

    public final void c() {
        int i;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = (Context) this.H.f11022J.get();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f24100_resource_name_obfuscated_RES_2131166029);
        boolean z = false;
        if (this.b0) {
            i = 0;
        } else {
            i = context.getResources().getDimensionPixelSize(R.dimen.f24090_resource_name_obfuscated_RES_2131166028);
        }
        this.e0 = i;
        int max = this.b0 ? 1 : Math.max(1, (i2 - i) / (dimensionPixelSize + i));
        this.d0 = max;
        this.f0 = (i2 - ((max + 1) * this.e0)) / max;
        if (this.b0) {
            findViewById(R.id.action_bar_bg).getHeight();
        }
        boolean z2 = this.b0;
        if (!z2) {
            this.g0 = this.f0;
        }
        if (!z2) {
            boolean z3 = this.d0 % 2 == 0;
            int i3 = this.e0;
            if (i3 % 2 == 0) {
                z = true;
            }
            if (z3 != z) {
                this.e0 = i3 + 1;
            }
        }
    }

    public final void d(int i, Uri[] uriArr, int i2) {
        this.L.b(i, uriArr);
        DialogC4536rC0 rc0 = this.F;
        if (rc0 != null) {
            rc0.dismiss();
        }
        AbstractC3364kK0.g("Android.PhotoPicker.DialogAction", i2, 4);
        AbstractC3364kK0.d("Android.PhotoPicker.DecodeRequests", this.O.K);
        AbstractC3364kK0.d("Android.PhotoPicker.CacheHits", this.O.f11527J);
    }

    public void e(List list) {
        if (list != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.i0;
            AbstractC3364kK0.k("Android.PhotoPicker.EnumerationTime", elapsedRealtime);
            AbstractC3364kK0.e("Android.PhotoPicker.EnumeratedFiles", list.size(), 1, 10000, 50);
            AbstractC3364kK0.b("Android.PhotoPicker.EnumeratedRate", (int) (((long) (list.size() * 100)) / elapsedRealtime));
            this.f7944J = list;
            if (this.j0) {
                this.O.F.b();
            }
        }
    }

    public LruCache f() {
        OF of = this.U;
        if (of == null || of.f8611a == null) {
            PF pf = CV.f7814a;
            OF of2 = new OF(new LruCache(this.a0), null);
            pf.f8679a.add(of2);
            this.U = of2;
        }
        return (LruCache) this.U.f8611a;
    }

    public LruCache g() {
        OF of = this.T;
        if (of == null || of.f8611a == null) {
            PF pf = CV.f7814a;
            OF of2 = new OF(new LruCache(this.V), null);
            pf.f8679a.add(of2);
            this.T = of2;
        }
        return (LruCache) this.T.f8611a;
    }

    public LruCache h() {
        OF of = this.S;
        if (of == null || of.f8611a == null) {
            PF pf = CV.f7814a;
            OF of2 = new OF(new LruCache(this.W), null);
            pf.f8679a.add(of2);
            this.S = of2;
        }
        return (LruCache) this.S.f8611a;
    }

    public void onClick(View view) {
        int id = view.getId();
        int i = 0;
        if (id == R.id.done) {
            List b = this.R.b();
            Collections.sort(b);
            ArrayList arrayList = (ArrayList) b;
            Uri[] uriArr = new Uri[arrayList.size()];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                uriArr[i] = ((C5557xC0) it.next()).F;
                i++;
            }
            d(1, uriArr, 1);
        } else if (id != R.id.zoom) {
            d(0, null, 0);
        } else if (!this.c0) {
            HashSet hashSet = new HashSet(this.R.c);
            this.R.a();
            boolean z = !this.b0;
            this.b0 = z;
            if (z) {
                this.m0.setImageResource(R.drawable.f35610_resource_name_obfuscated_RES_2131231602);
            } else {
                this.m0.setImageResource(R.drawable.f35600_resource_name_obfuscated_RES_2131231601);
            }
            c();
            if (!this.b0) {
                f().evictAll();
            }
            this.c0 = true;
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.addListener(new CC0(this, hashSet));
            TransitionManager.beginDelayedTransition(this.N, changeBounds);
            this.P.Q1(this.d0);
            this.O.F.b();
            this.N.requestLayout();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c();
        this.P.Q1(this.d0);
        this.N.j0(this.Q);
        GC0 gc0 = new GC0(this, this.d0, this.e0);
        this.Q = gc0;
        this.N.g(gc0);
        if (this.f7944J != null) {
            this.O.F.b();
            this.N.requestLayout();
        }
    }
}
