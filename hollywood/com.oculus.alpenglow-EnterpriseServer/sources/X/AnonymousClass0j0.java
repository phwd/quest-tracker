package X;

import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.0j0  reason: invalid class name */
public final class AnonymousClass0j0 {
    public final long A00;
    @Nullable
    public final C05280ix A01;
    public final ArrayList<String> A02;
    public final ArrayList<String> A03;

    public AnonymousClass0j0(C05300iz r6) {
        C05280ix r3 = r6.A01;
        this.A01 = r3;
        this.A02 = r6.A02;
        ArrayList<String> arrayList = r6.A03;
        this.A03 = arrayList;
        long j = r6.A00;
        this.A00 = j;
        if (r3 == null && arrayList.isEmpty() && (1 & j) == 0) {
            throw new IllegalArgumentException("TrustedCaller needs to be configured with at least 1 security check");
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 335
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public final boolean A00(android.content.Context r46, @javax.annotation.Nullable android.content.Intent r47, @javax.annotation.Nullable X.AbstractC04970iB r48) {
        /*
        // Method dump skipped, instructions count: 1478
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0j0.A00(android.content.Context, android.content.Intent, X.0iB):boolean");
    }
}
