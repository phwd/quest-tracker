package X;

import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* renamed from: X.1rR  reason: invalid class name */
public final class AnonymousClass1rR extends AbstractC11251rX {
    public boolean A00;
    public Window.Callback A01;
    public AbstractC06001Eq A02;
    public ArrayList<ActionBar.OnMenuVisibilityListener> A03 = new ArrayList<>();
    public boolean A04;
    public boolean A05;
    public final Runnable A06 = new AnonymousClass1rf(this);
    public final AbstractC12031tn A07 = new C11501s5(this);

    public AnonymousClass1rR(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        AnonymousClass1sI r1 = new AnonymousClass1sI(toolbar, false);
        this.A02 = r1;
        C11361rr r0 = new C11361rr(this, callback);
        this.A01 = r0;
        r1.setWindowCallback(r0);
        toolbar.A0C = this.A07;
        r1.setWindowTitle(charSequence);
    }
}
