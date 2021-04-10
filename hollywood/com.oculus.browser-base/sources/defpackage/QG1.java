package defpackage;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.cast.framework.media.MediaNotificationService;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: QG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QG1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaNotificationService f8751a;

    public QG1(MediaNotificationService mediaNotificationService) {
        this.f8751a = mediaNotificationService;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean z;
        PendingIntent pendingIntent;
        ComponentName componentName = (ComponentName) intent.getParcelableExtra("targetActivity");
        Intent intent2 = new Intent();
        intent2.setComponent(componentName);
        C1557Zm zm = this.f8751a.f9652J;
        Objects.requireNonNull(zm);
        SE0.e("Must be called from the main thread.");
        try {
            MH1 mh1 = (MH1) zm.d;
            Parcel d = mh1.d(12, mh1.c());
            int i = AbstractC4376qF1.f11128a;
            z = d.readInt() != 0;
            d.recycle();
        } catch (RemoteException unused) {
            NF1 nf1 = C1557Zm.f9368a;
            Object[] objArr = {"hasActivityInRecents", AbstractC2502fH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
            z = false;
        }
        if (z) {
            intent2.setFlags(603979776);
            pendingIntent = PendingIntent.getActivity(context, 1, intent2, 134217728);
        } else {
            MediaNotificationService mediaNotificationService = this.f8751a;
            ArrayList arrayList = new ArrayList();
            int size = arrayList.size();
            try {
                for (Intent b = AbstractC0522Im0.b(mediaNotificationService, componentName); b != null; b = AbstractC0522Im0.b(mediaNotificationService, b.getComponent())) {
                    arrayList.add(size, b);
                }
                arrayList.add(intent2);
                if (!arrayList.isEmpty()) {
                    Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
                    intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
                    pendingIntent = PendingIntent.getActivities(mediaNotificationService, 1, intentArr, 134217728, null);
                } else {
                    throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                throw new IllegalArgumentException(e);
            }
        }
        try {
            pendingIntent.send(context, 1, new Intent().setFlags(268435456));
        } catch (PendingIntent.CanceledException unused2) {
            NF1 nf12 = MediaNotificationService.F;
            Object[] objArr2 = new Object[0];
            if (nf12.d()) {
                nf12.c("Sending PendingIntent failed", objArr2);
            }
        }
    }
}
