package org.chromium.components.permissions;

import J.N;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionDialogDelegate {

    /* renamed from: a  reason: collision with root package name */
    public long f10878a;
    public PermissionDialogController b;
    public WindowAndroid c;
    public int d;
    public String e;
    public String f;
    public String g;
    public int[] h;

    public PermissionDialogDelegate(long j, WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3) {
        this.f10878a = j;
        this.c = windowAndroid;
        this.h = iArr;
        this.d = i;
        this.e = str;
        this.f = str2;
        this.g = str3;
    }

    public static PermissionDialogDelegate create(long j, WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3) {
        return new PermissionDialogDelegate(j, windowAndroid, iArr, i, str, str2, str3);
    }

    public final void dismissFromNative() {
        PermissionDialogController permissionDialogController = this.b;
        if (permissionDialogController.H == this) {
            permissionDialogController.H = null;
            if (permissionDialogController.K == 2) {
                permissionDialogController.I.b(permissionDialogController.F, 4);
            }
        } else {
            permissionDialogController.f10877J.remove(this);
        }
        N.MLMIuACo(this.f10878a, this);
        this.f10878a = 0;
    }
}
