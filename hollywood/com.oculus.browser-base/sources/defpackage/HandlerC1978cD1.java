package defpackage;

import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.util.Objects;

/* renamed from: cD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC1978cD1 extends HandlerC3356kH1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ YC1 f9591a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC1978cD1(YC1 yc1, Looper looper) {
        super(looper);
        this.f9591a = yc1;
    }

    public final void handleMessage(Message message) {
        YC1 yc1 = this.f9591a;
        Objects.requireNonNull(yc1);
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        yc1.j = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        yc1.i = (Messenger) parcelableExtra;
                    }
                }
                yc1.f((Intent) message.obj);
                return;
            }
            Log.w("InstanceID", "Dropping invalid message");
        }
    }
}
