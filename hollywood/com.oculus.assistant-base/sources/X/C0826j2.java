package X;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;

/* renamed from: X.j2  reason: case insensitive filesystem */
public final class C0826j2 implements C2 {
    public final ComponentName A00;
    public final String A01;
    public final Messenger A02;

    @Override // X.C2
    public final void A3d(int i) {
        Messenger messenger = this.A02;
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.arg1 = i;
        Bundle bundle = new Bundle();
        bundle.putString("tag", this.A01);
        bundle.putParcelable("component", this.A00);
        obtain.setData(bundle);
        messenger.send(obtain);
    }

    public C0826j2(Messenger messenger, String str, ComponentName componentName) {
        this.A02 = messenger;
        this.A01 = str;
        this.A00 = componentName;
    }
}
