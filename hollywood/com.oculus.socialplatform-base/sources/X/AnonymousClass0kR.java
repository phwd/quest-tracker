package X;

import android.text.TextUtils;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.0kR  reason: invalid class name */
public final class AnonymousClass0kR {
    public final long A00;
    @Nullable
    public final AnonymousClass0kO A01;
    public final ArrayList<String> A02;
    public final ArrayList<String> A03;

    public static AnonymousClass0kR A00(String str) {
        AnonymousClass0kQ r1 = new AnonymousClass0kQ();
        if (!TextUtils.isEmpty(str)) {
            r1.A03.add(str);
            return r1.A00();
        }
        throw new IllegalArgumentException();
    }

    public AnonymousClass0kR(AnonymousClass0kQ r6) {
        AnonymousClass0kO r3 = r6.A01;
        this.A01 = r3;
        this.A02 = r6.A02;
        ArrayList<String> arrayList = r6.A03;
        this.A03 = arrayList;
        this.A00 = r6.A00;
        if (r3 == null && arrayList.isEmpty() && (1 & this.A00) == 0) {
            throw new IllegalArgumentException("TrustedCaller needs to be configured with at least 1 security check");
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 302
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public final boolean A01(android.content.Context r46, @javax.annotation.Nullable android.content.Intent r47, @javax.annotation.Nullable X.AbstractC02660jW r48) {
        /*
        // Method dump skipped, instructions count: 1276
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0kR.A01(android.content.Context, android.content.Intent, X.0jW):boolean");
    }
}
