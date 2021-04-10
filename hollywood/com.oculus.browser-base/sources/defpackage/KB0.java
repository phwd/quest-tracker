package defpackage;

import java.util.Objects;
import org.chromium.components.permissions.PermissionDialogController;

/* renamed from: KB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class KB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PermissionDialogController f8348a;

    public KB0(PermissionDialogController permissionDialogController) {
        this.f8348a = permissionDialogController;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        UH0 uh0;
        PermissionDialogController permissionDialogController = this.f8348a;
        Objects.requireNonNull(permissionDialogController);
        if (((Integer) obj).intValue() == 1 && (uh0 = permissionDialogController.F) != null) {
            permissionDialogController.I.b(uh0, 5);
        }
        permissionDialogController.G = null;
    }
}
