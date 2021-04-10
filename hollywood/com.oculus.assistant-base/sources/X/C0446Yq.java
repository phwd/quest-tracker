package X;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import com.facebook.auth.viewercontext.ViewerContext;
import com.facebook.proxygen.TraceFieldType;
import java.util.regex.Pattern;

/* renamed from: X.Yq  reason: case insensitive filesystem */
public final class C0446Yq {
    public static final C0445Yp A05 = new C0445Yp();
    public static final Pattern A06 = Pattern.compile("audio/raw; sampleRate=(\\d+); encoding=INT16; channels=1");
    public C0239Mb A00;
    public C1146tm A01;
    public final Handler A02;
    public final boolean A03;
    public final w1 A04;

    public C0446Yq(w1 w1Var, Handler handler, boolean z) {
        C0514bB.A02(w1Var, "accessToken");
        C0514bB.A02(handler, "handler");
        this.A04 = w1Var;
        this.A02 = handler;
        this.A03 = z;
    }

    public final void A00() {
        C0239Mb mb = this.A00;
        if (mb != null) {
            mb.A08 = true;
            mb.A05.interrupt();
        }
        this.A00 = null;
        C1146tm tmVar = this.A01;
        if (tmVar != null) {
            tmVar.A01();
        }
        this.A01 = null;
    }

    public final void A01(Uri uri, AbstractC0444Yo yo) {
        C0514bB.A02(uri, TraceFieldType.Uri);
        A00();
        C0547bk bkVar = new C0547bk();
        bkVar.A0E = C0175Gg.A00(Build.TIME);
        C0548bl blVar = new C0548bl(bkVar);
        C0550bn bnVar = new C0550bn();
        bnVar.A01(uri.toString());
        ViewerContext A002 = this.A04.A00();
        C0514bB.A00(A002);
        C0514bB.A01(A002, "accessToken.viewerContext!!");
        String A042 = AnonymousClass08.A04("OAuth ", A002.mAuthToken);
        C0541be beVar = bnVar.A03;
        C0541be.A00("Authorization", A042);
        beVar.A02("Authorization", A042);
        C0541be beVar2 = bnVar.A03;
        C0541be.A00("GraphDomain", "oculus");
        beVar2.A02("GraphDomain", "oculus");
        C1146tm tmVar = new C1146tm(blVar, bnVar.A00(), false);
        this.A01 = tmVar;
        tmVar.A02(new C1411yx(this, yo));
    }

    public final void A02(String str, AbstractC0444Yo yo) {
        C0514bB.A02(str, "text");
        Uri build = Uri.parse("https://shortwave.facebook.com/v2/speak").buildUpon().appendQueryParameter("text", str).appendQueryParameter("format", "pcm16").appendQueryParameter("source", "Wearable").appendQueryParameter("id", "facebook-nicole").build();
        C0514bB.A01(build, TraceFieldType.Uri);
        A01(build, yo);
    }
}
