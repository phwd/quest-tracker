package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* renamed from: X.1cc  reason: invalid class name and case insensitive filesystem */
public final class C07541cc implements AnonymousClass1dN<InputStream, Bitmap> {
    public final AnonymousClass1hX A00;
    public final AnonymousClass1gC A01;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull InputStream inputStream, int i, int i2, @NonNull AnonymousClass1cO r16) throws IOException {
        C06741ax r13;
        boolean z;
        AnonymousClass1cV poll;
        InputStream inputStream2 = inputStream;
        if (inputStream2 instanceof C06741ax) {
            r13 = (C06741ax) inputStream2;
            z = false;
        } else {
            r13 = new C06741ax(inputStream2, this.A00);
            z = true;
        }
        Queue<AnonymousClass1cV> queue = AnonymousClass1cV.A02;
        synchronized (queue) {
            poll = queue.poll();
        }
        if (poll == null) {
            poll = new AnonymousClass1cV();
        }
        poll.A01 = r13;
        AnonymousClass1OL r2 = new AnonymousClass1OL(poll);
        C07571cf r11 = new C07571cf(r13, poll);
        try {
            AnonymousClass1gC r6 = this.A01;
            AnonymousClass1fR A012 = AnonymousClass1gC.A01(r6, new C07581cg(r2, r6.A04, r6.A02), i, i2, r16, r11);
            poll.A00 = null;
            poll.A01 = null;
            synchronized (queue) {
                queue.offer(poll);
            }
            if (z) {
                r13.A01();
            }
            return A012;
        } catch (Throwable th) {
            poll.A00 = null;
            poll.A01 = null;
            synchronized (queue) {
                queue.offer(poll);
                if (z) {
                    r13.A01();
                }
                throw th;
            }
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull InputStream inputStream, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }

    public C07541cc(AnonymousClass1gC r1, AnonymousClass1hX r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
