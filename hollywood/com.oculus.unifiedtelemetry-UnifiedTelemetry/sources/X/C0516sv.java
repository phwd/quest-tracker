package X;

import android.content.Context;
import com.oculus.logging.funnel.FunnelLoggerManager;
import java.io.File;
import retrofit.Endpoints;

/* renamed from: X.sv  reason: case insensitive filesystem */
public final class C0516sv {
    public final File A00;
    public final FunnelLoggerManager.AnonymousClass1 A01;

    public C0516sv(Context context, String str, FunnelLoggerManager.AnonymousClass1 r6) {
        File file = new File(context.getDir("funnel_backup", 0), str == null ? Endpoints.DEFAULT_NAME : str);
        file.mkdirs();
        this.A00 = new File(file, "backup_for_all");
        this.A01 = r6;
    }
}
