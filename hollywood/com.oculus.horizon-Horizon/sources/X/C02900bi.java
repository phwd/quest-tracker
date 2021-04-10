package X;

import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.0bi  reason: invalid class name and case insensitive filesystem */
public final class C02900bi {
    public final long A00;
    @Nullable
    public final C02870bf A01;
    public final ArrayList<String> A02;
    public final ArrayList<String> A03;

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 180
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public final boolean A00(android.content.Context r25, @javax.annotation.Nullable android.content.Intent r26, @javax.annotation.Nullable X.AnonymousClass0b1 r27) {
        /*
        // Method dump skipped, instructions count: 414
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02900bi.A00(android.content.Context, android.content.Intent, X.0b1):boolean");
    }

    public C02900bi(C02890bh r6) {
        C02870bf r3 = r6.A01;
        this.A01 = r3;
        this.A02 = r6.A02;
        ArrayList<String> arrayList = r6.A03;
        this.A03 = arrayList;
        this.A00 = r6.A00;
        if (r3 == null && arrayList.isEmpty() && (1 & this.A00) == 0) {
            throw new IllegalArgumentException("TrustedCaller needs to be configured with at least 1 security check");
        }
    }
}
