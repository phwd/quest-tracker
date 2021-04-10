package defpackage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/* renamed from: Cf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AsyncTaskC0138Cf0 extends AsyncTask {

    /* renamed from: a  reason: collision with root package name */
    public final Bitmap f7826a;
    public final Uri b;
    public int c;
    public long d;
    public final /* synthetic */ DialogC0504If0 e;

    public AsyncTaskC0138Cf0(DialogC0504If0 if0) {
        Bitmap bitmap;
        this.e = if0;
        MediaDescriptionCompat mediaDescriptionCompat = if0.z0;
        Uri uri = null;
        if (mediaDescriptionCompat == null) {
            bitmap = null;
        } else {
            bitmap = mediaDescriptionCompat.f9450J;
        }
        if (DialogC0504If0.m(bitmap)) {
            Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
            bitmap = null;
        }
        this.f7826a = bitmap;
        MediaDescriptionCompat mediaDescriptionCompat2 = if0.z0;
        this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.K : uri;
    }

    public final InputStream a(Uri uri) {
        InputStream inputStream;
        String lowerCase = uri.getScheme().toLowerCase();
        if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
            inputStream = this.e.M.getContentResolver().openInputStream(uri);
        } else {
            URLConnection openConnection = new URL(uri.toString()).openConnection();
            int i = DialogC0504If0.I;
            openConnection.setConnectTimeout(i);
            openConnection.setReadTimeout(i);
            inputStream = openConnection.getInputStream();
        }
        if (inputStream == null) {
            return null;
        }
        return new BufferedInputStream(inputStream);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b5 A[SYNTHETIC, Splitter:B:39:0x00b5] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bb A[SYNTHETIC, Splitter:B:42:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00db A[ADDED_TO_REGION] */
    @Override // android.os.AsyncTask
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object[] r10) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AsyncTaskC0138Cf0.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        DialogC0504If0 if0 = this.e;
        if0.A0 = null;
        if (!Objects.equals(if0.B0, this.f7826a) || !Objects.equals(this.e.C0, this.b)) {
            DialogC0504If0 if02 = this.e;
            if02.B0 = this.f7826a;
            if02.E0 = bitmap;
            if02.C0 = this.b;
            if02.F0 = this.c;
            boolean z = true;
            if02.D0 = true;
            long uptimeMillis = SystemClock.uptimeMillis() - this.d;
            DialogC0504If0 if03 = this.e;
            if (uptimeMillis <= 120) {
                z = false;
            }
            if03.s(z);
        }
    }

    public void onPreExecute() {
        this.d = SystemClock.uptimeMillis();
        DialogC0504If0 if0 = this.e;
        if0.D0 = false;
        if0.E0 = null;
        if0.F0 = 0;
    }
}
