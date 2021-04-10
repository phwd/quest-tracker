package X;

import android.os.Handler;
import android.os.Looper;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1n4  reason: invalid class name */
public final class AnonymousClass1n4 extends AnonymousClass1n6 {
    public ArrayList<DeferredReleaser.Releasable> A00 = new ArrayList<>();
    public ArrayList<DeferredReleaser.Releasable> A01 = new ArrayList<>();
    public final Object A02 = new Object();
    public final Handler A03 = new Handler(Looper.getMainLooper());
    public final Runnable A04 = new AnonymousClass1n5(this);
}
