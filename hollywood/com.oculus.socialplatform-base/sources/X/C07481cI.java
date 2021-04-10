package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;
import java.util.Queue;

/* renamed from: X.1cI  reason: invalid class name and case insensitive filesystem */
public final class C07481cI implements AbstractC07011bT<AnonymousClass1Rx, InputStream> {
    public static final C07491cP<Integer> A01 = C07491cP.A00("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);
    @Nullable
    public final AnonymousClass1cN<AnonymousClass1Rx, AnonymousClass1Rx> A00;

    /* Return type fixed from 'X.1bb' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.util.Queue<X.1cM<?>> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC07011bT
    public final C07091bb<InputStream> A1r(@NonNull AnonymousClass1Rx r5, int i, int i2, @NonNull AnonymousClass1cO r8) {
        AnonymousClass1Rx r52 = r5;
        AnonymousClass1cN<AnonymousClass1Rx, AnonymousClass1Rx> r0 = this.A00;
        if (r0 != null) {
            AnonymousClass1cM A002 = AnonymousClass1cM.A00(r52);
            AnonymousClass1cJ<AnonymousClass1cM<A>, B> r2 = r0.A00;
            B A012 = r2.A01(A002);
            Queue<AnonymousClass1cM<?>> queue = AnonymousClass1cM.A01;
            synchronized (queue) {
                queue.offer(A002);
            }
            B b = A012;
            if (b == null) {
                r2.A04(AnonymousClass1cM.A00(r52), r52);
            } else {
                r52 = b;
            }
        }
        return new C07091bb(r52, new C06191Rw(r52, ((Number) r8.A00(A01)).intValue()));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull AnonymousClass1Rx r2) {
        return true;
    }

    public C07481cI() {
        this(null);
    }

    public C07481cI(@Nullable AnonymousClass1cN<AnonymousClass1Rx, AnonymousClass1Rx> r1) {
        this.A00 = r1;
    }
}
