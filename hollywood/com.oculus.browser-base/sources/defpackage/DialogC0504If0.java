package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.mediarouter.app.MediaRouteExpandCollapseButton;
import androidx.mediarouter.app.MediaRouteVolumeSlider;
import androidx.mediarouter.app.OverlayListView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: If0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC0504If0 extends DialogC2461f4 {
    public static final int I = ((int) TimeUnit.SECONDS.toMillis(30));
    public AsyncTaskC0138Cf0 A0;
    public Bitmap B0;
    public Uri C0;
    public boolean D0;
    public Bitmap E0;
    public int F0;
    public boolean G0;
    public boolean H0;
    public boolean I0;

    /* renamed from: J  reason: collision with root package name */
    public final C3246jh0 f8239J;
    public boolean J0;
    public final C0260Ef0 K;
    public boolean K0;
    public final C2392eh0 L;
    public int L0;
    public Context M = getContext();
    public int M0;
    public boolean N;
    public int N0;
    public boolean O;
    public Interpolator O0;
    public int P;
    public Interpolator P0;
    public Button Q;
    public Interpolator Q0;
    public Button R;
    public Interpolator R0;
    public ImageButton S;
    public final AccessibilityManager S0;
    public ImageButton T;
    public Runnable T0 = new RunnableC4777sf0(this);
    public MediaRouteExpandCollapseButton U;
    public FrameLayout V;
    public LinearLayout W;
    public FrameLayout X;
    public FrameLayout Y;
    public ImageView Z;
    public TextView a0;
    public TextView b0;
    public TextView c0;
    public boolean d0 = true;
    public LinearLayout e0;
    public RelativeLayout f0;
    public LinearLayout g0;
    public View h0;
    public OverlayListView i0;
    public C0443Hf0 j0;
    public List k0;
    public Set l0;
    public Set m0;
    public Set n0;
    public SeekBar o0;
    public C0382Gf0 p0;
    public C2392eh0 q0;
    public int r0;
    public int s0;
    public int t0;
    public final int u0;
    public Map v0;
    public C0985Qd0 w0;
    public C0199Df0 x0 = new C0199Df0(this);
    public PlaybackStateCompat y0;
    public MediaDescriptionCompat z0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DialogC0504If0(android.content.Context r3) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DialogC0504If0.<init>(android.content.Context):void");
    }

    public static int k(View view) {
        return view.getLayoutParams().height;
    }

    public static boolean m(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    public static void q(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    public final void e(View view, int i) {
        C5797yf0 yf0 = new C5797yf0(this, view.getLayoutParams().height, i, view);
        yf0.setDuration((long) this.L0);
        yf0.setInterpolator(this.O0);
        view.startAnimation(yf0);
    }

    public final boolean f() {
        return (this.z0 == null && this.y0 == null) ? false : true;
    }

    public void g(boolean z) {
        Set set;
        int firstVisiblePosition = this.i0.getFirstVisiblePosition();
        for (int i = 0; i < this.i0.getChildCount(); i++) {
            View childAt = this.i0.getChildAt(i);
            C2392eh0 eh0 = (C2392eh0) this.j0.getItem(firstVisiblePosition + i);
            if (!z || (set = this.l0) == null || !set.contains(eh0)) {
                ((LinearLayout) childAt.findViewById(R.id.volume_item_container)).setVisibility(0);
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
                alphaAnimation.setDuration(0);
                animationSet.addAnimation(alphaAnimation);
                new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
            }
        }
        for (C0048At0 at0 : this.i0.F) {
            at0.k = true;
            at0.l = true;
            C4266pf0 pf0 = at0.m;
            if (pf0 != null) {
                pf0.b.n0.remove(pf0.f11080a);
                pf0.b.j0.notifyDataSetChanged();
            }
        }
        if (!z) {
            h(false);
        }
    }

    public void h(boolean z) {
        this.l0 = null;
        this.m0 = null;
        this.J0 = false;
        if (this.K0) {
            this.K0 = false;
            w(z);
        }
        this.i0.setEnabled(true);
    }

    public int j(int i, int i2) {
        if (i >= i2) {
            return (int) (((((float) this.P) * ((float) i2)) / ((float) i)) + 0.5f);
        }
        return (int) (((((float) this.P) * 9.0f) / 16.0f) + 0.5f);
    }

    public final int l(boolean z) {
        if (!z && this.g0.getVisibility() != 0) {
            return 0;
        }
        int paddingBottom = this.e0.getPaddingBottom() + this.e0.getPaddingTop() + 0;
        if (z) {
            paddingBottom += this.f0.getMeasuredHeight();
        }
        int measuredHeight = this.g0.getVisibility() == 0 ? this.g0.getMeasuredHeight() + paddingBottom : paddingBottom;
        return (!z || this.g0.getVisibility() != 0) ? measuredHeight : measuredHeight + this.h0.getMeasuredHeight();
    }

    public boolean n() {
        return (this.y0.f9453J & 514) != 0;
    }

    public boolean o() {
        return (this.y0.f9453J & 516) != 0;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.O = true;
        this.f8239J.a(C0629Kg0.f8380a, this.K, 2);
        r(this.f8239J.f());
    }

    @Override // defpackage.AbstractDialogC3498l8, defpackage.DialogC2461f4
    public void onCreate(Bundle bundle) {
        Interpolator interpolator;
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
        setContentView(R.layout.f39420_resource_name_obfuscated_RES_2131624251);
        findViewById(16908315).setVisibility(8);
        View$OnClickListenerC0077Bf0 bf0 = new View$OnClickListenerC0077Bf0(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mr_expandable_area);
        this.V = frameLayout;
        frameLayout.setOnClickListener(new View$OnClickListenerC4947tf0(this));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mr_dialog_area);
        this.W = linearLayout;
        linearLayout.setOnClickListener(new View$OnClickListenerC5117uf0(this));
        Context context = this.M;
        int h = AbstractC4783sh0.h(context, 0, R.attr.f3160_resource_name_obfuscated_RES_2130968762);
        if (AbstractC1331Vv.b(h, AbstractC4783sh0.h(context, 0, 16842801)) < 3.0d) {
            h = AbstractC4783sh0.h(context, 0, R.attr.f3020_resource_name_obfuscated_RES_2130968748);
        }
        Button button = (Button) findViewById(16908314);
        this.Q = button;
        button.setText(R.string.f55260_resource_name_obfuscated_RES_2131952843);
        this.Q.setTextColor(h);
        this.Q.setOnClickListener(bf0);
        Button button2 = (Button) findViewById(16908313);
        this.R = button2;
        button2.setText(R.string.f55330_resource_name_obfuscated_RES_2131952850);
        this.R.setTextColor(h);
        this.R.setOnClickListener(bf0);
        this.c0 = (TextView) findViewById(R.id.mr_name);
        ImageButton imageButton = (ImageButton) findViewById(R.id.mr_close);
        this.T = imageButton;
        imageButton.setOnClickListener(bf0);
        this.Y = (FrameLayout) findViewById(R.id.mr_custom_control);
        this.X = (FrameLayout) findViewById(R.id.mr_default_control);
        View$OnClickListenerC5287vf0 vf0 = new View$OnClickListenerC5287vf0(this);
        ImageView imageView = (ImageView) findViewById(R.id.mr_art);
        this.Z = imageView;
        imageView.setOnClickListener(vf0);
        findViewById(R.id.mr_control_title_container).setOnClickListener(vf0);
        this.e0 = (LinearLayout) findViewById(R.id.mr_media_main_control);
        this.h0 = findViewById(R.id.mr_control_divider);
        this.f0 = (RelativeLayout) findViewById(R.id.mr_playback_control);
        this.a0 = (TextView) findViewById(R.id.mr_control_title);
        this.b0 = (TextView) findViewById(R.id.mr_control_subtitle);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.mr_control_playback_ctrl);
        this.S = imageButton2;
        imageButton2.setOnClickListener(bf0);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.mr_volume_control);
        this.g0 = linearLayout2;
        linearLayout2.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R.id.mr_volume_slider);
        this.o0 = seekBar;
        seekBar.setTag(this.L);
        C0382Gf0 gf0 = new C0382Gf0(this);
        this.p0 = gf0;
        this.o0.setOnSeekBarChangeListener(gf0);
        this.i0 = (OverlayListView) findViewById(R.id.mr_volume_group_list);
        this.k0 = new ArrayList();
        C0443Hf0 hf0 = new C0443Hf0(this, this.i0.getContext(), this.k0);
        this.j0 = hf0;
        this.i0.setAdapter((ListAdapter) hf0);
        this.n0 = new HashSet();
        Context context2 = this.M;
        LinearLayout linearLayout3 = this.e0;
        OverlayListView overlayListView = this.i0;
        boolean f = this.L.f();
        int h2 = AbstractC4783sh0.h(context2, 0, R.attr.f3160_resource_name_obfuscated_RES_2130968762);
        int h3 = AbstractC4783sh0.h(context2, 0, R.attr.f3170_resource_name_obfuscated_RES_2130968763);
        if (f && AbstractC4783sh0.c(context2, 0) == -570425344) {
            h3 = h2;
            h2 = -1;
        }
        linearLayout3.setBackgroundColor(h2);
        overlayListView.setBackgroundColor(h3);
        linearLayout3.setTag(Integer.valueOf(h2));
        overlayListView.setTag(Integer.valueOf(h3));
        AbstractC4783sh0.m(this.M, (MediaRouteVolumeSlider) this.o0, this.e0);
        HashMap hashMap = new HashMap();
        this.v0 = hashMap;
        hashMap.put(this.L, this.o0);
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(R.id.mr_group_expand_collapse);
        this.U = mediaRouteExpandCollapseButton;
        mediaRouteExpandCollapseButton.setOnClickListener(new View$OnClickListenerC5457wf0(this));
        if (this.I0) {
            interpolator = this.P0;
        } else {
            interpolator = this.Q0;
        }
        this.O0 = interpolator;
        this.L0 = this.M.getResources().getInteger(R.integer.f35950_resource_name_obfuscated_RES_2131492892);
        this.M0 = this.M.getResources().getInteger(R.integer.f35960_resource_name_obfuscated_RES_2131492893);
        this.N0 = this.M.getResources().getInteger(R.integer.f35970_resource_name_obfuscated_RES_2131492894);
        this.N = true;
        v();
    }

    public void onDetachedFromWindow() {
        this.f8239J.j(this.K);
        r(null);
        this.O = false;
        super.onDetachedFromWindow();
    }

    @Override // defpackage.DialogC2461f4
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 25 && i != 24) {
            return super.onKeyDown(i, keyEvent);
        }
        this.L.l(i == 25 ? -1 : 1);
        return true;
    }

    @Override // defpackage.DialogC2461f4
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 25 || i == 24) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public boolean p() {
        return (this.y0.f9453J & 1) != 0;
    }

    public final void r(MediaSessionCompat$Token mediaSessionCompat$Token) {
        MediaDescriptionCompat mediaDescriptionCompat;
        C0985Qd0 qd0 = this.w0;
        PlaybackStateCompat playbackStateCompat = null;
        if (qd0 != null) {
            qd0.d(this.x0);
            this.w0 = null;
        }
        if (mediaSessionCompat$Token != null && this.O) {
            C0985Qd0 qd02 = new C0985Qd0(this.M, mediaSessionCompat$Token);
            this.w0 = qd02;
            qd02.c(this.x0);
            MediaMetadataCompat a2 = this.w0.a();
            if (a2 == null) {
                mediaDescriptionCompat = null;
            } else {
                mediaDescriptionCompat = a2.c();
            }
            this.z0 = mediaDescriptionCompat;
            C0741Md0 md0 = (C0741Md0) this.w0.f8773a;
            if (md0.e.b() != null) {
                try {
                    playbackStateCompat = md0.e.b().b();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
                this.y0 = playbackStateCompat;
                u();
                s(false);
            }
            PlaybackState playbackState = md0.f8489a.getPlaybackState();
            if (playbackState != null) {
                playbackStateCompat = PlaybackStateCompat.b(playbackState);
            }
            this.y0 = playbackStateCompat;
            u();
            s(false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0136  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void s(boolean r10) {
        /*
        // Method dump skipped, instructions count: 417
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DialogC0504If0.s(boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        if (((r0 != null && r0.equals(r1)) || (r0 == null && r1 == null)) == false) goto L_0x0037;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void u() {
        /*
            r6 = this;
            android.support.v4.media.MediaDescriptionCompat r0 = r6.z0
            r1 = 0
            if (r0 != 0) goto L_0x0007
            r2 = r1
            goto L_0x0009
        L_0x0007:
            android.graphics.Bitmap r2 = r0.f9450J
        L_0x0009:
            if (r0 != 0) goto L_0x000c
            goto L_0x000e
        L_0x000c:
            android.net.Uri r1 = r0.K
        L_0x000e:
            Cf0 r0 = r6.A0
            if (r0 != 0) goto L_0x0015
            android.graphics.Bitmap r3 = r6.B0
            goto L_0x0017
        L_0x0015:
            android.graphics.Bitmap r3 = r0.f7826a
        L_0x0017:
            if (r0 != 0) goto L_0x001c
            android.net.Uri r0 = r6.C0
            goto L_0x001e
        L_0x001c:
            android.net.Uri r0 = r0.b
        L_0x001e:
            r4 = 0
            r5 = 1
            if (r3 == r2) goto L_0x0023
            goto L_0x0037
        L_0x0023:
            if (r3 != 0) goto L_0x0039
            if (r0 == 0) goto L_0x002e
            boolean r2 = r0.equals(r1)
            if (r2 == 0) goto L_0x002e
            goto L_0x0032
        L_0x002e:
            if (r0 != 0) goto L_0x0034
            if (r1 != 0) goto L_0x0034
        L_0x0032:
            r0 = r5
            goto L_0x0035
        L_0x0034:
            r0 = r4
        L_0x0035:
            if (r0 != 0) goto L_0x0039
        L_0x0037:
            r0 = r5
            goto L_0x003a
        L_0x0039:
            r0 = r4
        L_0x003a:
            if (r0 != 0) goto L_0x003d
            goto L_0x0050
        L_0x003d:
            Cf0 r0 = r6.A0
            if (r0 == 0) goto L_0x0044
            r0.cancel(r5)
        L_0x0044:
            Cf0 r0 = new Cf0
            r0.<init>(r6)
            r6.A0 = r0
            java.lang.Void[] r1 = new java.lang.Void[r4]
            r0.execute(r1)
        L_0x0050:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.DialogC0504If0.u():void");
    }

    public void v() {
        int a2 = AbstractC0991Qf0.a(this.M);
        getWindow().setLayout(a2, -2);
        View decorView = getWindow().getDecorView();
        this.P = (a2 - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = this.M.getResources();
        this.r0 = resources.getDimensionPixelSize(R.dimen.f21000_resource_name_obfuscated_RES_2131165719);
        this.s0 = resources.getDimensionPixelSize(R.dimen.f20990_resource_name_obfuscated_RES_2131165718);
        this.t0 = resources.getDimensionPixelSize(R.dimen.f21010_resource_name_obfuscated_RES_2131165720);
        this.B0 = null;
        this.C0 = null;
        u();
        s(false);
    }

    public void w(boolean z) {
        this.X.requestLayout();
        this.X.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC5627xf0(this, z));
    }

    public final void y(boolean z) {
        int i = 0;
        this.h0.setVisibility((this.g0.getVisibility() != 0 || !z) ? 8 : 0);
        LinearLayout linearLayout = this.e0;
        if (this.g0.getVisibility() == 8 && !z) {
            i = 8;
        }
        linearLayout.setVisibility(i);
    }
}
