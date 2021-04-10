package X;

import java.io.Serializable;

/* renamed from: X.0Jv  reason: invalid class name */
public final class AnonymousClass0Jv extends AnonymousClass0Zb implements Serializable {
    public static final AnonymousClass0Jv A00 = new AnonymousClass0Jv();
    public static final long serialVersionUID = 1;

    @Override // X.AnonymousClass0Zb
    public final Iterable<AbstractC06910oM> A06() {
        return new C07100oj(this._factoryConfig._additionalSerializers);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 603
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    @Override // X.AbstractC06900oL, X.AnonymousClass0Zb
    public final com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> A03(X.AnonymousClass0a8 r35, X.AnonymousClass0aI r36) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 2690
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Jv.A03(X.0a8, X.0aI):com.fasterxml.jackson.databind.JsonSerializer");
    }
}
