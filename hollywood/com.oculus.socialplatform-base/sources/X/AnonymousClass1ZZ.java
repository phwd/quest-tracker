package X;

import com.facebook.msys.mci.NotificationCenter;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1ZZ  reason: invalid class name */
public class AnonymousClass1ZZ implements NotificationCenter.NotificationCallback {
    public final /* synthetic */ AnonymousClass1ZY A00;

    public AnonymousClass1ZZ(AnonymousClass1ZY r1) {
        this.A00 = r1;
    }

    @Override // com.facebook.msys.mci.NotificationCenter.NotificationCallback
    public final void onNewNotification(String str, @Nullable Object obj, @Nullable Map<Object, Object> map) {
        if (obj != null) {
            AnonymousClass1ZY r3 = this.A00;
            AnonymousClass1ZW remove = r3.A00.remove(obj);
            if (remove != null) {
                r3.A02.removeObserver(r3.A01, str, obj);
                remove.A7y(map);
            }
        }
    }
}
