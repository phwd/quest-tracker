package defpackage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.browser_ui.photo_picker.PickerBitmapView;

/* renamed from: wC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5387wC0 extends AbstractC5750yK0 {
    public EC0 I;

    /* renamed from: J  reason: collision with root package name */
    public int f11527J;
    public int K;

    public C5387wC0(EC0 ec0) {
        this.I = ec0;
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.f7944J.size();
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        HC0 hc0;
        if (xk0 instanceof C5727yC0) {
            C5727yC0 yc0 = (C5727yC0) xk0;
            EC0 ec0 = this.I;
            yc0.Z = ec0;
            C5557xC0 xc0 = (C5557xC0) ec0.f7944J.get(i);
            yc0.b0 = xc0;
            int i2 = xc0.H;
            boolean z = false;
            if (i2 == 1 || i2 == 2) {
                yc0.a0.k(xc0, null, null, false, -1.0f);
            } else {
                String path = xc0.F.getPath();
                EC0 ec02 = yc0.Z;
                if (ec02.b0) {
                    hc0 = (HC0) ec02.f().get(path);
                } else {
                    hc0 = (HC0) ec02.g().get(path);
                }
                if (hc0 != null) {
                    yc0.a0.k(yc0.b0, hc0.f8143a, hc0.b, false, hc0.c);
                    z = true;
                } else {
                    EC0 ec03 = yc0.Z;
                    int i3 = ec03.f0;
                    HC0 hc02 = null;
                    if (ec03.b0) {
                        hc02 = (HC0) ec03.g().get(path);
                    }
                    if (hc02 == null) {
                        hc02 = (HC0) yc0.Z.h().get(path);
                    }
                    if (hc02 != null) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        Bitmap d = AbstractC0752Mh.d((Bitmap) hc02.f8143a.get(0), (float) i3, false);
                        AbstractC3364kK0.k("Android.PhotoPicker.UpscaleLowResBitmap", SystemClock.elapsedRealtime() - elapsedRealtime);
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(d);
                        yc0.a0.k(yc0.b0, arrayList, hc02.b, true, hc02.c);
                    } else {
                        yc0.a0.k(yc0.b0, null, null, true, -1.0f);
                    }
                    EC0 ec04 = yc0.Z;
                    BinderC5899zD zDVar = ec04.M;
                    C5557xC0 xc02 = yc0.b0;
                    Uri uri = xc02.F;
                    int i4 = xc02.H;
                    boolean z2 = ec04.b0;
                    Objects.requireNonNull(zDVar);
                    Object obj = ThreadUtils.f10596a;
                    C5389wD wDVar = new C5389wD(uri, i3, z2, i4, true, yc0);
                    zDVar.u.add(wDVar);
                    if (i4 == 3 && zDVar.n) {
                        zDVar.u.add(new C5389wD(uri, i3, z2, i4, false, wDVar.g));
                    }
                    if (zDVar.p == null) {
                        zDVar.f();
                    }
                    z = true;
                }
            }
            if (z) {
                this.f11527J++;
            } else if (z) {
                this.K++;
            }
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        PickerBitmapView pickerBitmapView = (PickerBitmapView) AbstractC2531fV.r(viewGroup, R.layout.f40640_resource_name_obfuscated_RES_2131624373, viewGroup, false);
        EC0 ec0 = this.I;
        pickerBitmapView.P = ec0;
        C3209jS0 js0 = ec0.R;
        pickerBitmapView.Q = js0;
        pickerBitmapView.h(js0);
        return new C5727yC0(pickerBitmapView);
    }
}
