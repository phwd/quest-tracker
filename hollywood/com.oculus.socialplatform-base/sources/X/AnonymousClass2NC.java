package X;

import android.view.View;
import android.view.ViewStub;
import androidx.annotation.NonNull;

/* renamed from: X.2NC  reason: invalid class name */
public final class AnonymousClass2NC {
    public View A00;
    public ViewStub.OnInflateListener A01;
    public ViewStub A02;
    public AnonymousClass1uW A03;
    public AnonymousClass1uW A04;
    public ViewStub.OnInflateListener A05;

    public AnonymousClass2NC(@NonNull ViewStub viewStub) {
        AnonymousClass2N5 r0 = new AnonymousClass2N5(this);
        this.A05 = r0;
        this.A02 = viewStub;
        viewStub.setOnInflateListener(r0);
    }
}
