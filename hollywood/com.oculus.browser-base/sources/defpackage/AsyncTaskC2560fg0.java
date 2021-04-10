package defpackage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.media.MediaDescriptionCompat;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/* renamed from: fg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AsyncTaskC2560fg0 extends AsyncTask {

    /* renamed from: a  reason: collision with root package name */
    public final Bitmap f9940a;
    public final Uri b;
    public int c;
    public final /* synthetic */ DialogC5460wg0 d;

    public AsyncTaskC2560fg0(DialogC5460wg0 wg0) {
        Bitmap bitmap;
        this.d = wg0;
        MediaDescriptionCompat mediaDescriptionCompat = wg0.o0;
        Uri uri = null;
        if (mediaDescriptionCompat == null) {
            bitmap = null;
        } else {
            bitmap = mediaDescriptionCompat.f9450J;
        }
        if (DialogC5460wg0.c(bitmap)) {
            Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
            bitmap = null;
        }
        this.f9940a = bitmap;
        MediaDescriptionCompat mediaDescriptionCompat2 = wg0.o0;
        this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.K : uri;
    }

    public final InputStream a(Uri uri) {
        InputStream inputStream;
        String lowerCase = uri.getScheme().toLowerCase();
        if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
            inputStream = this.d.Q.getContentResolver().openInputStream(uri);
        } else {
            URLConnection openConnection = new URL(uri.toString()).openConnection();
            openConnection.setConnectTimeout(30000);
            openConnection.setReadTimeout(30000);
            inputStream = openConnection.getInputStream();
        }
        if (inputStream == null) {
            return null;
        }
        return new BufferedInputStream(inputStream);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ba A[SYNTHETIC, Splitter:B:39:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c0 A[SYNTHETIC, Splitter:B:42:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e0 A[ADDED_TO_REGION] */
    @Override // android.os.AsyncTask
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object[] r9) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AsyncTaskC2560fg0.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        DialogC5460wg0 wg0 = this.d;
        wg0.p0 = null;
        if (!Objects.equals(wg0.q0, this.f9940a) || !Objects.equals(this.d.r0, this.b)) {
            DialogC5460wg0 wg02 = this.d;
            wg02.q0 = this.f9940a;
            wg02.t0 = bitmap;
            wg02.r0 = this.b;
            wg02.u0 = this.c;
            wg02.s0 = true;
            wg02.l();
        }
    }

    public void onPreExecute() {
        DialogC5460wg0 wg0 = this.d;
        wg0.s0 = false;
        wg0.t0 = null;
        wg0.u0 = 0;
    }
}
