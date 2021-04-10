package org.chromium.ui.base;

import J.N;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SelectFileDialog implements Ky1, AbstractC4707sC0 {

    /* renamed from: a  reason: collision with root package name */
    public static final long f11020a = TimeUnit.HOURS.toMillis(1);
    public static final String[] b = {".apng", ".bmp", ".gif", ".jpeg", ".jpg", ".pdf", ".png", ".tif", ".tiff", ".xcf", ".webp"};
    public static final String[] c = {".asf", ".avhcd", ".avi", ".divx", ".flv", ".mov", ".mp4", ".mpeg", ".mpg", ".swf", ".wmv", ".webm", ".mkv"};
    public static CG0 d;
    public static AbstractC4195pC0 e;
    public final long f;
    public List g;
    public boolean h;
    public boolean i;
    public Uri j;
    public WindowAndroid k;
    public boolean l;
    public boolean m;
    public boolean n;

    public SelectFileDialog(long j2) {
        this.f = j2;
    }

    public static SelectFileDialog create(long j2) {
        return new SelectFileDialog(j2);
    }

    public static List g(List list) {
        if (list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.length() == 0) {
                str = "";
            } else {
                String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
                if (fileExtensionFromUrl.length() > 0 && (str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl)) == null) {
                    str = "application/octet-stream";
                }
            }
            if (!str.startsWith("image/")) {
                CG0 cg0 = d;
                boolean z = false;
                if (cg0 != null) {
                    Objects.requireNonNull(cg0);
                    z = N.M09VlOh_("PhotoPickerVideoSupport");
                }
                if (!z || !str.startsWith("video/")) {
                    return null;
                }
            }
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i2, Intent intent) {
        AbstractC4195pC0 pc0 = e;
        if (pc0 != null) {
            DialogC4536rC0 rc0 = (DialogC4536rC0) pc0;
            rc0.K = true;
            rc0.dismiss();
        }
        if (i2 != -1) {
            m();
        } else if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
            String path = "file".equals(this.j.getScheme()) ? this.j.getPath() : this.j.toString();
            long j2 = this.f;
            String lastPathSegment = this.j.getLastPathSegment();
            if (i()) {
                AbstractC3364kK0.c("Android.SelectFileDialogImgCount", 1);
            }
            N.MBeWYy2V(j2, this, path, lastPathSegment);
            Intent intent2 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.j);
            Objects.requireNonNull(windowAndroid);
            ContextUtils.getApplicationContext().sendBroadcast(intent2);
        } else {
            if (intent.getData() != null || intent.getClipData() == null) {
                if ("file".equals(intent.getData().getScheme())) {
                    String path2 = intent.getData().getPath();
                    if (!TextUtils.isEmpty(path2)) {
                        QR0 qr0 = new QR0(this, ContextUtils.getApplicationContext(), path2, windowAndroid);
                        Executor executor = AbstractC2032cb.f9616a;
                        qr0.f();
                        ((ExecutorC1463Ya) executor).execute(qr0.e);
                        return;
                    }
                }
                if ("content".equals(intent.getScheme())) {
                    SR0 sr0 = new SR0(this, ContextUtils.getApplicationContext(), false, new Uri[]{intent.getData()});
                    Executor executor2 = AbstractC2032cb.f9616a;
                    sr0.f();
                    ((ExecutorC1463Ya) executor2).execute(sr0.e);
                    return;
                }
                m();
                windowAndroid.G0(R.string.f56770_resource_name_obfuscated_RES_2131952994);
                return;
            }
            ClipData clipData = intent.getClipData();
            int itemCount = clipData.getItemCount();
            if (itemCount == 0) {
                m();
                return;
            }
            Uri[] uriArr = new Uri[itemCount];
            for (int i3 = 0; i3 < itemCount; i3++) {
                uriArr[i3] = clipData.getItemAt(i3).getUri();
            }
            SR0 sr02 = new SR0(this, ContextUtils.getApplicationContext(), true, uriArr);
            Executor executor3 = AbstractC2032cb.f9616a;
            sr02.f();
            ((ExecutorC1463Ya) executor3).execute(sr02.e);
        }
    }

    @Override // defpackage.AbstractC4707sC0
    public void b(int i2, Uri[] uriArr) {
        if (i2 != 0) {
            boolean z = true;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        if (this.i) {
                            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                        }
                        intent.setAction("android.intent.action.GET_CONTENT");
                        this.k.F0(intent, this, Integer.valueOf((int) R.string.f54270_resource_name_obfuscated_RES_2131952744));
                    }
                } else if (!this.k.hasPermission("android.permission.CAMERA")) {
                    this.k.i(new String[]{"android.permission.CAMERA"}, new OR0(this));
                } else {
                    RR0 rr0 = new RR0(this, Boolean.TRUE, this.k, this);
                    Executor executor = AbstractC2032cb.f9616a;
                    rr0.f();
                    ((ExecutorC1463Ya) executor).execute(rr0.e);
                }
            } else if (uriArr.length == 0) {
                m();
            } else {
                Context applicationContext = ContextUtils.getApplicationContext();
                if (uriArr.length <= 1) {
                    z = false;
                }
                SR0 sr0 = new SR0(this, applicationContext, z, uriArr);
                Executor executor2 = AbstractC2032cb.f9616a;
                sr0.f();
                ((ExecutorC1463Ya) executor2).execute(sr0.e);
            }
        } else {
            m();
        }
    }

    @Override // defpackage.AbstractC4707sC0
    public void c() {
        e = null;
    }

    public final boolean d(String str) {
        return h(str) == this.g.size();
    }

    public final boolean e(String str) {
        return this.g.isEmpty() || this.g.contains("*/*") || h(str) > 0;
    }

    public final boolean f() {
        return this.h && d("image");
    }

    public final int h(String str) {
        int i2 = 0;
        for (String str2 : this.g) {
            if (str2.startsWith(str)) {
                i2++;
            }
        }
        return i2;
    }

    public final boolean i() {
        return g(this.g) != null;
    }

    public final void j(int[] iArr) {
        if (iArr[0] == -1) {
            m();
            return;
        }
        RR0 rr0 = new RR0(this, Boolean.TRUE, this.k, this);
        Executor executor = AbstractC2032cb.f9616a;
        rr0.f();
        ((ExecutorC1463Ya) executor).execute(rr0.e);
    }

    public final void k() {
        boolean hasPermission = this.k.hasPermission("android.permission.CAMERA");
        if (!this.l || !hasPermission) {
            l(null);
            return;
        }
        RR0 rr0 = new RR0(this, Boolean.FALSE, this.k, this);
        Executor executor = AbstractC2032cb.f9616a;
        rr0.f();
        ((ExecutorC1463Ya) executor).execute(rr0.e);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01c4, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x023c  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0113 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0189  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l(android.content.Intent r21) {
        /*
        // Method dump skipped, instructions count: 599
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.SelectFileDialog.l(android.content.Intent):void");
    }

    public final void m() {
        long j2 = this.f;
        if (i()) {
            AbstractC3364kK0.c("Android.SelectFileDialogImgCount", 0);
        }
        N.MGVJOCWv(j2, this);
    }

    public final boolean n() {
        List g2 = g(this.g);
        if (!f() && g2 != null) {
            if ((d != null) && this.k.s0().get() != null) {
                return true;
            }
        }
        return false;
    }

    public final void selectFile(String[] strArr, boolean z, boolean z2, WindowAndroid windowAndroid) {
        this.g = new ArrayList(Arrays.asList(strArr));
        this.h = z;
        this.i = z2;
        this.k = windowAndroid;
        this.l = windowAndroid.r0(new Intent("android.media.action.IMAGE_CAPTURE"));
        this.m = this.k.r0(new Intent("android.media.action.VIDEO_CAPTURE"));
        this.n = this.k.r0(new Intent("android.provider.MediaStore.RECORD_SOUND"));
        ArrayList arrayList = new ArrayList();
        boolean n2 = n();
        if (!n2) {
            if (((this.l && e("image")) || (this.m && e("video"))) && !windowAndroid.hasPermission("android.permission.CAMERA")) {
                arrayList.add("android.permission.CAMERA");
            }
            if (this.n && e("audio") && !windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
                arrayList.add("android.permission.RECORD_AUDIO");
            }
        } else if (!windowAndroid.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            k();
            return;
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        windowAndroid.i(strArr2, new NR0(this, n2, strArr2, "android.permission.READ_EXTERNAL_STORAGE"));
    }
}
