package com.oculus.browser;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FacebookLoginManager extends RN {

    /* renamed from: a  reason: collision with root package name */
    public static final Signature f9699a = (Build.TYPE.equals("user") ? null : new Signature("3082037b30820263a0030201020204232eae62300d06092a864886f70d0101050500306d310b30090603550406130255533110300e06035504081307556e6b6e6f776e3110300e06035504071307556e6b6e6f776e3110300e060355040a1307556e6b6e6f776e3110300e060355040b1307416e64726f6964311630140603550403130d416e64726f69642044656275673020170d3133313233313232333530345a180f32303532303433303232333530345a306d310b30090603550406130255533110300e06035504081307556e6b6e6f776e3110300e06035504071307556e6b6e6f776e3110300e060355040a1307556e6b6e6f776e3110300e060355040b1307416e64726f6964311630140603550403130d416e64726f696420446562756730820122300d06092a864886f70d01010105000382010f003082010a028201010092de6e2026c86cd5441e788c25f40d5bdcd177bad77a67c3027dcfe8ad163fd5155ea4536b58be1999369ec71667708185081d35cdecb97124c4eccacd76e1964286e08c058aad2fe7f834dad15884075a5bf5483787576bf55887fc9833fb3d219d2936f150b981b939ebad4fe7d9b561e2085711d38845492cd4ba9aab415fb21b82a475d805736987fb173ec0cb57f1e2e640d98f2de1dee9270015b0e863385ddbfbb69b87dc55014a8a2a355919d17d156305b6e6b1e7ee024a718c8907089262407fd443de47eff2da48d774792b20df93218043df995dcd42376980c62bfd2d23c34fb8be2e70ad968625780a0ee8a9454972d44db125f086dfe9d1610203010001a321301f301d0603551d0e041604140bf9fe3889d28a9c58f0c10ab70e4328d823f320300d06092a864886f70d010105050003820101005fd27615bfce63184d1250c1b91175f0bce2f9c44e7ae7777f8d85c9e1b2153d3d10066bb0019a2c0a6d7e3c02dbdff776d6b6ff272ebcf468259868a40c0e77d430455e7bcfc3b9ff2f4f7a8a931b75b052bbf4245f31e28e0f491018ab7e97d7fb8e1d73d312cd08dc96fb9dd3939ca2e1d6adf65cff4a58d9ec916aea06d43b5243fad59cf1146e3c71ab66d324f012935a314e96aff81baec183c83790f99a86dab56b5bc5b26d1694bc355e4290ec02edba115d57bbdd65a8b0e4693456b5ac2a3a07a4447e5d7ad4de9dfc8a99c53cd4ee6c2e65b9bfdec689ae8e2319d64cb83a6308a2187e30c03feef687718d4903e54003a9a0b57f78dbd9a8892a"));
    public static final Signature b = new Signature("3082038030820268a00302010202045390c873300d06092a864886f70d0101050500308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e301e170d3134303630353139343334375a170d3431313032313139343334375a308181310b3009060355040613024341311330110603550408130a43616c69666f726e6961310f300d06035504071306497276696e6531183016060355040a130f4f63756c75732056522c20496e632e31183016060355040b130f4f63756c75732056522c20496e632e311830160603550403130f4f63756c75732056522c20496e632e30820122300d06092a864886f70d01010105000382010f003082010a0282010100810913c89c710f0c1baf44cbd759cb193a9ceacc3ff64f6c710519cdc30f55f913285c74e8fd3e3220e55524cb881921a4de9b68a2f6a55eedd1da6c7f65f11adb983831e87797d70f411fd654dffbec956308bd7bbd8fb64215f15f315831a869b3ebc90a268b069e4b15e5861cbe1ef2b82c543f394410324891f161e0cecd1544c2b3e4be220e61312d1169950b3fc7c9c76c5eb4b723033caf65f5a9aadcb817c7923daea4266d0e874fd1f82480090f70b631d2b4ee4704c406c30c0d31cd75a9159ba37002ccfdff6752d61543252d5030ec0fff14e505b514027b702641b0aa6e65e5a3b80c847fc3f866d936234e7b91f8129749ac5383067bd74dcb0203010001300d06092a864886f70d01010505000382010100457e0ddb298616761934a9ff2a91048872af6f004855a0e866f4ffea0a4dfb1ff0aa2cf4ffac55f87d2d8e8273a867d4ef63d2fecb468d081ad405b3359fc576eeac839bacebb111d65f39a6930509b2986268c6a65554dfbd3eb40904b9a1d0b476c3a94b128bd975cd285635e2a225c4c3f7664eb8b98962d45416705805e4434bb826c7cb095c7ddb22cf99f92b6bc4e1b2a7e93d0ecb8d9b4543efed428589c2d52b16cde1c52dade04ffddcd3aa70aacef2a5c8dd89961f7e2b425ed1400f9673278bee714777d6b681eebd176de9345be39c800a4f78c41714d61018dbd5f3750fc8fe7ba35c2172b796fb5abb6c2b61528d1cddc84d2e16b685d02e66");
    public static String c = "FACEBOOK_LOGIN_MANAGER_PROMPT_";
    public Context d;
    public ArrayList e;
    public boolean f = false;
    public String g = null;
    public Boolean h = null;
    public Boolean i = null;
    public String j;
    public String k;
    public String l;
    public String m;
    public long n = nativeInit();

    public FacebookLoginManager(Context context) {
        this.d = context;
    }

    public static boolean h(Context context) {
        Signature signature = null;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo("com.oculus.horizon", 64).signatures;
            if (signatureArr.length > 0) {
                signature = signatureArr[0];
            }
        } catch (PackageManager.NameNotFoundException e2) {
            StringBuilder i2 = AbstractC2531fV.i("Horizon Package Missing: ");
            i2.append(e2.toString());
            AbstractC1220Ua0.a("FACEBOOK_LOGIN_MANAGER", i2.toString(), new Object[0]);
        }
        if (Build.TYPE.equals("user") || !f9699a.equals(signature)) {
            return b.equals(signature);
        }
        return true;
    }

    public void Decline() {
        Preferences instance = Preferences.getInstance();
        instance.setBoolean(c + this.j, false);
    }

    public void Login() {
        ArrayList arrayList = this.e;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                QS0 qs0 = (QS0) it.next();
                String str = qs0.c + "=" + qs0.e;
                if (qs0.f8762a != null) {
                    StringBuilder j2 = AbstractC2531fV.j(str, "; Domain=");
                    j2.append(qs0.f8762a);
                    str = j2.toString();
                    String str2 = qs0.b;
                    if (str2 != null && !str2.isEmpty()) {
                        StringBuilder j3 = AbstractC2531fV.j(str, "; Expires=");
                        j3.append(qs0.b);
                        str = j3.toString();
                    }
                }
                nativeInjectCookieAsync(this.n, AbstractC2531fV.f(str, ";SameSite=None;Secure"), qs0.d);
            }
            nativeUpdateSsoData(this.n, this.j, this.k, this.m, true, true, false);
        }
        Preferences.getInstance().setBoolean(c + this.j, true);
    }

    public void Update() {
        if (!e()) {
            LN ln = new LN(this);
            Executor executor = AbstractC2032cb.f9616a;
            ln.f();
            ((ExecutorC1463Ya) executor).execute(ln.e);
            return;
        }
        nativeProfileIsLoggedInAsync(this.n);
    }

    @Override // defpackage.RN
    public void b(String str) {
        this.m = AbstractC2531fV.f("data:image/png;base64, ", str);
        g();
    }

    @Override // defpackage.RN
    public void d(Exception exc) {
        StringBuilder i2 = AbstractC2531fV.i("Facebook Picture URL request call failed: ");
        i2.append(exc.toString());
        AbstractC1220Ua0.a("FACEBOOK_LOGIN_MANAGER", i2.toString(), new Object[0]);
    }

    public final boolean e() {
        Boolean bool;
        Boolean bool2 = this.h;
        return ((bool2 != null && bool2.booleanValue()) || ((bool = this.i) != null && bool.booleanValue())) && this.g != null;
    }

    public void f() {
        ArrayList arrayList;
        boolean z = !e() || Gatekeeper.g().h(TU.NEW_SSO_UX);
        if (!this.f || !z || !((arrayList = this.e) == null || arrayList.size() == 0)) {
            ThreadUtils.g(new NN(this));
            return;
        }
        MN mn = new MN(this);
        Executor executor = AbstractC2032cb.f9616a;
        mn.f();
        ((ExecutorC1463Ya) executor).execute(mn.e);
    }

    public final void g() {
        if (this.j != null && this.k != null && this.m != null) {
            ArrayList arrayList = this.e;
            boolean z = arrayList != null && arrayList.size() > 0;
            Preferences instance = Preferences.getInstance();
            nativeUpdateSsoData(this.n, this.j, this.k, this.m, z, e(), instance.getBoolean(c + this.j, true));
        }
    }

    public final void injectComplete(boolean z) {
    }

    public final native long nativeInit();

    public final native void nativeInjectCookieAsync(long j2, String str, boolean z);

    public final native void nativeProfileIsLoggedInAsync(long j2);

    public final native void nativeUpdateSsoData(long j2, String str, String str2, String str3, boolean z, boolean z2, boolean z3);

    public final void onCookieChanged(String str, String str2) {
        boolean e2 = e();
        if (str.equals("c_user")) {
            this.g = str2;
        } else {
            boolean z = true;
            if (str.equals("xs")) {
                if (str2 == null) {
                    z = false;
                }
                this.h = Boolean.valueOf(z);
            } else if (str.equals("datr")) {
                if (str2 == null) {
                    z = false;
                }
                this.i = Boolean.valueOf(z);
            }
        }
        if (this.f && e2 != e()) {
            if (e2) {
                this.e = null;
            }
            f();
        }
    }

    public final void onLoginChecked() {
        this.f = true;
        f();
    }
}
