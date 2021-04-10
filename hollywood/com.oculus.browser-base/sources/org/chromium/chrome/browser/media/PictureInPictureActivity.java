package org.chromium.chrome.browser.media;

import J.N;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.util.Rational;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.thinwebview.internal.CompositorViewImpl;
import org.chromium.content.browser.MediaSessionImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PictureInPictureActivity extends AbstractActivityC0731Ma {
    public static long o0;
    public static Tab p0;
    public static int q0;
    public static ZC0 r0;
    public AbstractC0845Nw s0;
    public AbstractC1180Th0 t0;
    public BroadcastReceiver u0 = new WC0(this);

    public static void createActivity(long j, Object obj) {
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent(applicationContext, PictureInPictureActivity.class);
        long j2 = o0;
        if (j2 != 0) {
            N.MrWAWBMN(j2);
        }
        o0 = j;
        Tab tab = (Tab) obj;
        p0 = tab;
        q0 = AbstractC5112ud1.b(tab).getTaskId();
        ZC0 zc0 = new ZC0();
        r0 = zc0;
        p0.A(zc0);
        intent.addFlags(268435456);
        applicationContext.startActivity(intent);
    }

    public static void onWindowDestroyed(long j) {
        if (o0 == j) {
            o0 = 0;
        }
    }

    public final void close() {
        finish();
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public C2971i3 j0() {
        return new C2971i3(this);
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma, defpackage.AbstractActivityC5319vq
    public void onDestroy() {
        super.onDestroy();
        o0 = 0;
        p0.I(r0);
        p0 = null;
        r0 = null;
        AbstractC1180Th0 th0 = this.t0;
        if (th0 != null) {
            th0.g();
            this.t0 = null;
        }
        unregisterReceiver(this.u0);
    }

    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        if (!z) {
            finish();
        }
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onStart() {
        super.onStart();
        if (o0 != 0) {
            ZC0 zc0 = r0;
            if (zc0.b != 2) {
                zc0.f9328a = this;
                registerReceiver(this.u0, new IntentFilter("org.chromium.chrome.browser.media.PictureInPictureActivity.Play"));
                N.MjkqYLC6(o0, this, this.b0);
                this.t0 = new YC0(this, (MediaSessionImpl) N.Mtun$qW8(p0.l()));
                enterPictureInPictureMode(z0());
                return;
            }
        }
        finish();
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma
    public void onStop() {
        super.onStop();
        AbstractC0845Nw nw = this.s0;
        if (nw != null) {
            CompositorViewImpl compositorViewImpl = (CompositorViewImpl) nw;
            long j = compositorViewImpl.d;
            if (j != 0) {
                N.M_L66GG1(j, compositorViewImpl);
                compositorViewImpl.d = 0;
            }
        }
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma
    public void u() {
        super.u();
        CompositorViewImpl compositorViewImpl = new CompositorViewImpl(this, this.b0, new C1483Yg1());
        this.s0 = compositorViewImpl;
        addContentView(compositorViewImpl.b, new ViewGroup.LayoutParams(-1, -1));
        ((CompositorViewImpl) this.s0).b.addOnLayoutChangeListener(new XC0(this));
        N.MxJhtvhD(o0, this.s0);
    }

    public final void updateVideoSize(int i, int i2) {
        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        float f = (float) i2;
        builder.setAspectRatio(new Rational((int) (AbstractC4089od0.b(((float) i) / f, 0.41841003f, 2.39f) * f), i2));
        setPictureInPictureParams(builder.build());
    }

    @Override // defpackage.AbstractC3083ik
    public boolean w() {
        return true;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public void y0() {
        r0();
    }

    public final PictureInPictureParams z0() {
        ArrayList arrayList = new ArrayList();
        AbstractC1180Th0 th0 = this.t0;
        if (th0 != null && !th0.f8975a.d) {
            arrayList.add(new RemoteAction(Icon.createWithResource(getApplicationContext(), (int) R.drawable.f32500_resource_name_obfuscated_RES_2131231290), getApplicationContext().getResources().getText(R.string.f45710_resource_name_obfuscated_RES_2131951888), "", PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("org.chromium.chrome.browser.media.PictureInPictureActivity.Play"), 0)));
        }
        return new PictureInPictureParams.Builder().setActions(arrayList).build();
    }
}
