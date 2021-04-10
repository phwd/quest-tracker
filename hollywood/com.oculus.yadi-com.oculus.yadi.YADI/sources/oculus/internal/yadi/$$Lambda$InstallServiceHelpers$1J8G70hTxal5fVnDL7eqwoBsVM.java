package oculus.internal.yadi;

import android.content.pm.PackageInstaller;
import java.util.function.Function;

/* renamed from: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$1J8G70hTxal-5fVnDL7eqwoBsVM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$InstallServiceHelpers$1J8G70hTxal5fVnDL7eqwoBsVM implements Function {
    public static final /* synthetic */ $$Lambda$InstallServiceHelpers$1J8G70hTxal5fVnDL7eqwoBsVM INSTANCE = new $$Lambda$InstallServiceHelpers$1J8G70hTxal5fVnDL7eqwoBsVM();

    private /* synthetic */ $$Lambda$InstallServiceHelpers$1J8G70hTxal5fVnDL7eqwoBsVM() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Integer.valueOf(((PackageInstaller.SessionInfo) obj).getSessionId());
    }
}
