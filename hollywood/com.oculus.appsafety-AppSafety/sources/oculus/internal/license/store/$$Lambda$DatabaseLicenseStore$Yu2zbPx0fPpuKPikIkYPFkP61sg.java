package oculus.internal.license.store;

import android.database.Cursor;
import java.util.function.Function;

/* renamed from: oculus.internal.license.store.-$$Lambda$DatabaseLicenseStore$Yu2zbPx0fPpuKPikIkYPFkP61sg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DatabaseLicenseStore$Yu2zbPx0fPpuKPikIkYPFkP61sg implements Function {
    public static final /* synthetic */ $$Lambda$DatabaseLicenseStore$Yu2zbPx0fPpuKPikIkYPFkP61sg INSTANCE = new $$Lambda$DatabaseLicenseStore$Yu2zbPx0fPpuKPikIkYPFkP61sg();

    private /* synthetic */ $$Lambda$DatabaseLicenseStore$Yu2zbPx0fPpuKPikIkYPFkP61sg() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return DatabaseLicenseStore.cursorRowToContentValues((Cursor) obj);
    }
}
