package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.oculus.browser.PermissionsRequestActivity;
import java.util.Arrays;
import java.util.Objects;

/* renamed from: zw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6018zw0 implements AbstractC2809h6 {
    public final /* synthetic */ Context F;
    public final /* synthetic */ C0054Aw0 G;

    public C6018zw0(C0054Aw0 aw0, Context context) {
        this.G = aw0;
        this.F = context;
    }

    @Override // defpackage.AbstractC2809h6
    public boolean B(int i, String[] strArr, int[] iArr) {
        return false;
    }

    @Override // defpackage.AbstractC2809h6
    public boolean E(String str) {
        return false;
    }

    @Override // defpackage.AbstractC2809h6
    public boolean canRequestPermission(String str) {
        return true;
    }

    @Override // defpackage.AbstractC2809h6
    public boolean hasPermission(String str) {
        return AbstractC3153j7.a(this.F, str, Process.myPid(), Process.myUid()) == 0;
    }

    @Override // defpackage.AbstractC2809h6
    public void i(String[] strArr, HB0 hb0) {
        boolean z;
        Log.i("PanelWindowAndroid", "requestPermissions");
        this.G.b0 = hb0;
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No permissions");
        }
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (!hasPermission(strArr[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            Log.i("PanelWindowAndroid", "starting PermissionsRequestActivity");
            Intent intent = new Intent(this.F, PermissionsRequestActivity.class);
            intent.putExtra("request_permission", strArr);
            intent.setAction("com.oculus.vrshell.panel.vr_permission.GET");
            intent.setFlags(268435456);
            this.F.startActivity(intent);
            return;
        }
        int[] iArr = new int[strArr.length];
        Arrays.fill(iArr, 0);
        C0054Aw0 aw0 = this.G;
        Objects.requireNonNull(aw0);
        Log.i("PanelWindowAndroid", "onRequestPermissionResponse");
        HB0 hb02 = aw0.b0;
        if (hb02 != null) {
            hb02.b(strArr, iArr);
            aw0.b0 = null;
        }
    }
}
