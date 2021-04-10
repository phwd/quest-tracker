package defpackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

/* renamed from: Sx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1152Sx extends AbstractC2032cb {
    public final /* synthetic */ URL i;
    public final /* synthetic */ int j;
    public final /* synthetic */ C2101cy k;

    public C1152Sx(URL url, int i2, C2101cy cyVar) {
        this.i = url;
        this.j = i2;
        this.k = cyVar;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.i.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(false);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setConnectTimeout(this.j);
            httpURLConnection.setReadTimeout(this.j);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 204) {
                return 1;
            }
            return 2;
        } catch (SocketTimeoutException unused) {
            return 3;
        } catch (ProtocolException unused2) {
            return 4;
        } catch (IOException unused3) {
            return 2;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.k.a(((Integer) obj).intValue());
    }
}
