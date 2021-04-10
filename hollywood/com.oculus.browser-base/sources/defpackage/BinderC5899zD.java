package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemClock;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;
import org.chromium.components.browser_ui.photo_picker.PickerBitmapView;

/* renamed from: zD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BinderC5899zD extends EY implements AbstractC4197pD {
    public static int b = 0;
    public static Q31 c;
    public final Context d;
    public ContentResolver e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public final boolean n;
    public C4368qD o;
    public C5389wD p;
    public final List q;
    public CY r;
    public ServiceConnection s = new ServiceConnectionC5219vD(this);
    public Comparator t = new C4879tD();
    public PriorityQueue u = new PriorityQueue(1, this.t);

    public BinderC5899zD(AbstractC5559xD xDVar, Context context, boolean z) {
        ArrayList arrayList = new ArrayList();
        this.q = arrayList;
        arrayList.add(xDVar);
        this.n = z;
        this.d = context;
        this.e = context.getContentResolver();
    }

    public final void c(String str, boolean z, boolean z2, List list, String str2, long j2, float f2) {
        int i2;
        HC0 hc0;
        List list2;
        Bitmap bitmap;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!z || list == null) {
            AbstractC3364kK0.k("Android.PhotoPicker.RequestProcessTime", elapsedRealtime - this.p.h);
        } else if (list.size() > 1) {
            AbstractC3364kK0.k("Android.PhotoPicker.RequestProcessTimeAnimation", elapsedRealtime - this.p.h);
        } else {
            AbstractC3364kK0.k("Android.PhotoPicker.RequestProcessTimeThumbnail", elapsedRealtime - this.p.h);
        }
        C5727yC0 yc0 = (C5727yC0) this.p.g;
        Objects.requireNonNull(yc0);
        if (list == null || list.size() == 0 || (!z && ((bitmap = (Bitmap) list.get(0)) == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0))) {
            i2 = 0;
        } else {
            if (z2) {
                hc0 = (HC0) yc0.Z.f().get(str);
            } else {
                hc0 = (HC0) yc0.Z.g().get(str);
            }
            if (hc0 == null || ((list2 = hc0.f8143a) != null && list2.size() < list.size())) {
                if (z2) {
                    yc0.Z.f().put(str, new HC0(list, str2, Boolean.valueOf(z2), f2));
                } else {
                    yc0.Z.g().put(str, new HC0(list, str2, Boolean.valueOf(z2), f2));
                }
            }
            if (yc0.Z.h().get(str) == null) {
                i2 = 0;
                C0631Kh kh = new C0631Kh(yc0.Z.h(), (Bitmap) list.get(0), str, str2, yc0.a0.getContext().getResources().getDimensionPixelSize(R.dimen.f24030_resource_name_obfuscated_RES_2131166022), f2);
                Executor executor = AbstractC2032cb.f9616a;
                kh.f();
                ((ExecutorC1463Ya) executor).execute(kh.e);
            } else {
                i2 = 0;
            }
            if (TextUtils.equals(yc0.x(), str) && yc0.a0.o(list, str2, f2)) {
                PickerBitmapView pickerBitmapView = yc0.a0;
                pickerBitmapView.S.setAlpha(0.0f);
                pickerBitmapView.S.animate().alpha(1.0f).setDuration(200).start();
            }
        }
        if (!(j2 == -1 || list == null || list.get(i2) == null)) {
            int byteCount = ((Bitmap) list.get(i2)).getByteCount() / 1024;
            if (!z) {
                AbstractC3364kK0.k("Android.PhotoPicker.ImageDecodeTime", j2);
                AbstractC3364kK0.e("Android.PhotoPicker.ImageByteCount", byteCount, 1, 100000, 50);
            } else if (list.size() > 1) {
                AbstractC3364kK0.k("Android.PhotoPicker.VideoDecodeTimeAnimation", j2);
            } else {
                AbstractC3364kK0.k("Android.PhotoPicker.VideoDecodeTimeThumbnail", j2);
                AbstractC3364kK0.e("Android.PhotoPicker.VideoByteCount", byteCount, 1, 100000, 50);
            }
        }
        this.p = null;
        f();
    }

    public final void d(String str) {
        c(str, false, false, null, null, -1, 1.0f);
    }

    public void e0(Uri uri, List list, String str, boolean z, int i2, float f2) {
        if (i2 != 0) {
            if (i2 == 1) {
                this.j++;
            } else if (i2 == 2) {
                this.k++;
            } else if (i2 == 3) {
                this.l++;
            }
        } else if (list == null || list.size() == 0) {
            this.m++;
        } else {
            this.i++;
        }
        c(uri.getPath(), true, z, list, str, -1, f2);
    }

    public final void f() {
        C5389wD wDVar;
        if (this.u.isEmpty()) {
            wDVar = null;
        } else {
            wDVar = (C5389wD) this.u.remove();
        }
        this.p = wDVar;
        if (wDVar != null) {
            wDVar.h = SystemClock.elapsedRealtime();
            C5389wD wDVar2 = this.p;
            if (wDVar2.d == 3) {
                C4368qD qDVar = new C4368qD(this, this.e, wDVar2.f11528a, wDVar2.b, wDVar2.c, wDVar2.e ? 1 : 10, (long) 2000);
                this.o = qDVar;
                Executor executor = AbstractC2032cb.f9616a;
                qDVar.f();
                ((ExecutorC1463Ya) executor).execute(qDVar.e);
                return;
            } else if (this.r == null) {
                AbstractC1220Ua0.a("ImageDecoderHost", "Connection to decoder service unexpectedly terminated.", new Object[0]);
                d(this.p.f11528a.getPath());
                return;
            } else {
                Bundle bundle = new Bundle();
                StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
                try {
                    try {
                        ParcelFileDescriptor parcelFileDescriptor = this.e.openAssetFileDescriptor(wDVar2.f11528a, "r").getParcelFileDescriptor();
                        if (parcelFileDescriptor == null) {
                            d(wDVar2.f11528a.getPath());
                            if (threadPolicy == null) {
                                return;
                            }
                            StrictMode.setThreadPolicy(threadPolicy);
                            return;
                        }
                        if (threadPolicy != null) {
                            StrictMode.setThreadPolicy(threadPolicy);
                        }
                        bundle.putString("file_path", wDVar2.f11528a.getPath());
                        bundle.putParcelable("file_descriptor", parcelFileDescriptor);
                        bundle.putInt("width", wDVar2.b);
                        bundle.putBoolean("full_width", wDVar2.c);
                        try {
                            this.r.g(bundle, this);
                            parcelFileDescriptor.close();
                            return;
                        } catch (RemoteException e2) {
                            AbstractC1220Ua0.a("ImageDecoderHost", "Communications failed (Remote): " + e2, new Object[0]);
                            d(wDVar2.f11528a.getPath());
                            return;
                        } catch (IOException e3) {
                            AbstractC1220Ua0.a("ImageDecoderHost", "Communications failed (IO): " + e3, new Object[0]);
                            d(wDVar2.f11528a.getPath());
                            return;
                        }
                    } catch (Throwable th) {
                        AbstractC0754Mh1.f8495a.a(th, th);
                    }
                } catch (FileNotFoundException e4) {
                    AbstractC1220Ua0.a("ImageDecoderHost", "Unable to obtain FileDescriptor: " + e4, new Object[0]);
                    d(wDVar2.f11528a.getPath());
                    if (threadPolicy == null) {
                        return;
                    }
                } catch (IllegalStateException e5) {
                    AbstractC1220Ua0.a("ImageDecoderHost", "Invalid ContentResolver state: " + e5, new Object[0]);
                    d(wDVar2.f11528a.getPath());
                    if (threadPolicy == null) {
                        return;
                    }
                }
            }
        } else {
            int i2 = this.f;
            int i3 = this.g;
            int i4 = i2 + i3 + this.h;
            if (i4 > 0) {
                AbstractC3364kK0.g("Android.PhotoPicker.DecoderHostFailureRuntime", (i3 * 100) / i4, 101);
                AbstractC3364kK0.g("Android.PhotoPicker.DecoderHostFailureOutOfMemory", (this.h * 100) / i4, 101);
                this.f = 0;
                this.g = 0;
                this.h = 0;
            }
            int i5 = this.i;
            int i6 = this.j;
            int i7 = i5 + i6 + this.k + this.l + this.m;
            if (i7 > 0) {
                AbstractC3364kK0.g("Android.PhotoPicker.DecoderHostVideoFileError", (i6 * 100) / i7, 101);
                AbstractC3364kK0.g("Android.PhotoPicker.DecoderHostVideoRuntimeError", (this.k * 100) / i7, 101);
                AbstractC3364kK0.g("Android.PhotoPicker.DecoderHostVideoIoError", (this.l * 100) / i7, 101);
                AbstractC3364kK0.g("Android.PhotoPicker.DecoderHostVideoUnknownError", (this.m * 100) / i7, 101);
                this.i = 0;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = 0;
            }
            for (AbstractC5559xD xDVar : this.q) {
                Objects.requireNonNull((EC0) xDVar);
            }
            return;
        }
        throw th;
    }

    @Override // defpackage.FY
    public void p(Bundle bundle) {
        PostTask.c(Zo1.f9374a, new RunnableC5049uD(this, bundle));
    }
}
