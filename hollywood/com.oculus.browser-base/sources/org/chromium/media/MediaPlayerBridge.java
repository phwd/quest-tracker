package org.chromium.media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.view.Surface;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaPlayerBridge {

    /* renamed from: a  reason: collision with root package name */
    public C0379Ge0 f10981a;
    public MediaPlayer b;
    public long c;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class AllowedOperations {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f10982a;
        public final boolean b;

        public AllowedOperations(boolean z, boolean z2) {
            this.f10982a = z;
            this.b = z2;
        }

        public final boolean canSeekBackward() {
            return this.b;
        }

        public final boolean canSeekForward() {
            return this.f10982a;
        }
    }

    public MediaPlayerBridge(long j) {
        this.c = j;
    }

    public static MediaPlayerBridge create(long j) {
        return new MediaPlayerBridge(j);
    }

    public final void a() {
        C0379Ge0 ge0 = this.f10981a;
        if (ge0 != null) {
            ge0.b(true);
            this.f10981a = null;
        }
    }

    public MediaPlayer b() {
        if (this.b == null) {
            this.b = new MediaPlayer();
        }
        return this.b;
    }

    public void destroy() {
        a();
        this.c = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00bf, code lost:
        if (((java.lang.Boolean) r6.invoke(r1, java.lang.Integer.valueOf(r4))).booleanValue() != false) goto L_0x0130;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x00ad A[Catch:{ NoSuchMethodException -> 0x00c9, InvocationTargetException -> 0x00c7, IllegalAccessException -> 0x00c5, NoSuchFieldException -> 0x00c3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.chromium.media.MediaPlayerBridge.AllowedOperations getAllowedOperations() {
        /*
        // Method dump skipped, instructions count: 312
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaPlayerBridge.getAllowedOperations():org.chromium.media.MediaPlayerBridge$AllowedOperations");
    }

    public int getCurrentPosition() {
        return b().getCurrentPosition();
    }

    public int getDuration() {
        return b().getDuration();
    }

    public boolean isPlaying() {
        return b().isPlaying();
    }

    public void pause() {
        b().pause();
    }

    public boolean prepareAsync() {
        try {
            b().prepareAsync();
            return true;
        } catch (IllegalStateException e) {
            AbstractC1220Ua0.a("media", "Unable to prepare MediaPlayer.", e);
            return false;
        } catch (Exception e2) {
            AbstractC1220Ua0.a("media", "Unable to prepare MediaPlayer.", e2);
            return false;
        }
    }

    public void release() {
        a();
        b().release();
    }

    public void seekTo(int i) {
        b().seekTo(i);
    }

    public boolean setDataSource(String str, String str2, String str3, boolean z) {
        Uri parse = Uri.parse(str);
        HashMap hashMap = new HashMap();
        if (z) {
            hashMap.put("x-hide-urls-from-log", "true");
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("Cookie", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("User-Agent", str3);
        }
        hashMap.put("android-allow-cross-domain-redirect", "0");
        try {
            b().setDataSource(ContextUtils.getApplicationContext(), parse, hashMap);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean setDataSourceFromFd(int i, long j, long j2) {
        try {
            ParcelFileDescriptor adoptFd = ParcelFileDescriptor.adoptFd(i);
            b().setDataSource(adoptFd.getFileDescriptor(), j, j2);
            adoptFd.close();
            return true;
        } catch (IOException e) {
            AbstractC1220Ua0.a("media", "Failed to set data source from file descriptor: " + e, new Object[0]);
            return false;
        }
    }

    public boolean setDataUriDataSource(String str) {
        int indexOf;
        a();
        if (!str.startsWith("data:") || (indexOf = str.indexOf(44)) == -1) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        String[] split = substring.substring(5).split(";");
        if (split.length != 2 || !"base64".equals(split[1])) {
            return false;
        }
        C0379Ge0 ge0 = new C0379Ge0(this, substring2);
        this.f10981a = ge0;
        Executor executor = AbstractC2032cb.f9616a;
        ge0.f();
        ((ExecutorC1463Ya) executor).execute(ge0.e);
        return true;
    }

    public void setSurface(Surface surface) {
        b().setSurface(surface);
    }

    public void setVolume(double d) {
        float f = (float) d;
        b().setVolume(f, f);
    }

    public void start() {
        b().start();
    }
}
