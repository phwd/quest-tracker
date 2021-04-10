package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.components.permissions.PermissionDialogController;

/* renamed from: JB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JB0 implements Runnable {
    public final PermissionDialogController F;
    public final Context G;

    public JB0(PermissionDialogController permissionDialogController, Context context) {
        this.F = permissionDialogController;
        this.G = context;
    }

    public void run() {
        PermissionDialogController permissionDialogController = this.F;
        Context context = this.G;
        if (permissionDialogController.G == null) {
            GW0 gw0 = new GW0(permissionDialogController.I, new KB0(permissionDialogController));
            HH0 hh0 = new HH0(AbstractC3258jl0.r);
            hh0.e(AbstractC3258jl0.f10235a, gw0);
            hh0.e(AbstractC3258jl0.c, context.getString(R.string.f56860_resource_name_obfuscated_RES_2131953003, AbstractC0456Hk.f8178a.b));
            hh0.d(AbstractC3258jl0.e, context.getResources(), R.string.f56850_resource_name_obfuscated_RES_2131953002);
            hh0.d(AbstractC3258jl0.g, context.getResources(), R.string.f48470_resource_name_obfuscated_RES_2131952164);
            hh0.d(AbstractC3258jl0.j, context.getResources(), R.string.f63810_resource_name_obfuscated_RES_2131953698);
            hh0.b(AbstractC3258jl0.m, true);
            UH0 a2 = hh0.a();
            permissionDialogController.G = a2;
            permissionDialogController.I.i(a2, 0, true);
        }
    }
}
