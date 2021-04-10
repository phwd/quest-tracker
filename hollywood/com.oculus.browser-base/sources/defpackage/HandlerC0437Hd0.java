package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.oculus.os.Version;
import java.util.List;
import java.util.Objects;

/* renamed from: Hd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC0437Hd0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8166a = false;
    public final /* synthetic */ AbstractC0559Jd0 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC0437Hd0(AbstractC0559Jd0 jd0, Looper looper) {
        super(looper);
        this.b = jd0;
    }

    public void handleMessage(Message message) {
        if (this.f8166a) {
            switch (message.what) {
                case 1:
                    C0571Jh0.a(message.getData());
                    AbstractC0559Jd0 jd0 = this.b;
                    String str = (String) message.obj;
                    Objects.requireNonNull(jd0);
                    return;
                case 2:
                    this.b.b((PlaybackStateCompat) message.obj);
                    return;
                case 3:
                    this.b.a((MediaMetadataCompat) message.obj);
                    return;
                case 4:
                    AbstractC0559Jd0 jd02 = this.b;
                    AbstractC0802Nd0 nd0 = (AbstractC0802Nd0) message.obj;
                    Objects.requireNonNull(jd02);
                    return;
                case 5:
                    AbstractC0559Jd0 jd03 = this.b;
                    List list = (List) message.obj;
                    Objects.requireNonNull(jd03);
                    return;
                case 6:
                    AbstractC0559Jd0 jd04 = this.b;
                    CharSequence charSequence = (CharSequence) message.obj;
                    Objects.requireNonNull(jd04);
                    return;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    C0571Jh0.a((Bundle) message.obj);
                    Objects.requireNonNull(this.b);
                    return;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    this.b.c();
                    return;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    AbstractC0559Jd0 jd05 = this.b;
                    ((Integer) message.obj).intValue();
                    Objects.requireNonNull(jd05);
                    return;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                default:
                    return;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    AbstractC0559Jd0 jd06 = this.b;
                    ((Boolean) message.obj).booleanValue();
                    Objects.requireNonNull(jd06);
                    return;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    AbstractC0559Jd0 jd07 = this.b;
                    ((Integer) message.obj).intValue();
                    Objects.requireNonNull(jd07);
                    return;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    Objects.requireNonNull(this.b);
                    return;
            }
        }
    }
}
