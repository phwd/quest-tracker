package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1bW  reason: invalid class name and case insensitive filesystem */
public class C07041bW<Data> implements AbstractC07051bX<Data>, AnonymousClass1Ry<Data> {
    public int A00;
    public AnonymousClass1cY A01;
    public AnonymousClass1Ry<? super Data> A02;
    @Nullable
    public List<Throwable> A03;
    public boolean A04;
    public final AnonymousClass06o<List<Throwable>> A05;
    public final List<AbstractC07051bX<Data>> A06;

    @Override // X.AbstractC07051bX
    public final void cancel() {
        this.A04 = true;
        for (AbstractC07051bX<Data> r0 : this.A06) {
            r0.cancel();
        }
    }

    private void A00() {
        if (this.A04) {
            return;
        }
        if (this.A00 < this.A06.size() - 1) {
            this.A00++;
            A6H(this.A01, this.A02);
            return;
        }
        List<Throwable> list = this.A03;
        AnonymousClass1S2.A00(list);
        this.A02.A7F(new AnonymousClass1Or("Fetch failed", new ArrayList(list)));
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        List<Throwable> list = this.A03;
        if (list != null) {
            this.A05.A8z(list);
        }
        this.A03 = null;
        for (AbstractC07051bX<Data> r0 : this.A06) {
            r0.A26();
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<Data> A3h() {
        return this.A06.get(0).A3h();
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return this.A06.get(0).A3i();
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r3, @NonNull AnonymousClass1Ry<? super Data> r4) {
        this.A01 = r3;
        this.A02 = r4;
        this.A03 = this.A05.A19();
        this.A06.get(this.A00).A6H(r3, this);
        if (this.A04) {
            cancel();
        }
    }

    @Override // X.AnonymousClass1Ry
    public final void A6x(@Nullable Data data) {
        if (data != null) {
            this.A02.A6x(data);
        } else {
            A00();
        }
    }

    @Override // X.AnonymousClass1Ry
    public final void A7F(@NonNull Exception exc) {
        List<Throwable> list = this.A03;
        AnonymousClass1S2.A00(list);
        list.add(exc);
        A00();
    }

    public C07041bW(@NonNull List<AbstractC07051bX<Data>> list, @NonNull AnonymousClass06o<List<Throwable>> r4) {
        this.A05 = r4;
        if (!list.isEmpty()) {
            this.A06 = list;
            this.A00 = 0;
            return;
        }
        throw new IllegalArgumentException("Must not be empty.");
    }
}
