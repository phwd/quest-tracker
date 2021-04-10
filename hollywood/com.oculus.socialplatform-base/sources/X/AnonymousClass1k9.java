package X;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1k9  reason: invalid class name */
public final class AnonymousClass1k9 extends AbstractC09891kF<AbstractC00820Ju<AnonymousClass0VM>, AnonymousClass0mX> {
    public AnonymousClass0H3 A00;
    public AbstractC00750Ik<AnonymousClass0M8<AbstractC00820Ju<AnonymousClass0VM>>> A01;
    public AnonymousClass1mJ A02;
    @GuardedBy("this")
    @Nullable
    public AnonymousClass1j6 A03;
    @Nullable
    public AnonymousClass1kA A04;
    public boolean A05;
    @Nullable
    public final AbstractC03450mg<AnonymousClass0H3, AnonymousClass0VM> A06;
    public final AnonymousClass0PY A07;
    public final Resources A08;

    @Nullable
    public static AnonymousClass1jV A00(@Nullable Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof AnonymousClass1jV) {
                return (AnonymousClass1jV) drawable;
            }
            if (drawable instanceof AnonymousClass0Mg) {
                return A00(((AnonymousClass0Mg) drawable).A3p());
            }
            if (drawable instanceof AnonymousClass0o5) {
                AnonymousClass0o5 r4 = (AnonymousClass0o5) drawable;
                int length = r4.A04.length;
                for (int i = 0; i < length; i++) {
                    AnonymousClass1jV A002 = A00(r4.A01(i));
                    if (A002 != null) {
                        return A002;
                    }
                }
            }
        }
        return null;
    }

    public final synchronized void A0D(AnonymousClass1j6 r3) {
        AnonymousClass1j6 r1 = this.A03;
        if (r1 instanceof C10001ke) {
            C10001ke r12 = (C10001ke) r1;
            synchronized (r12) {
                r12.A00.add(r3);
            }
        } else if (r1 != null) {
            this.A03 = new C10001ke(r1, r3);
        } else {
            this.A03 = r3;
        }
    }

    public static void A01(@Nullable AnonymousClass1k9 r5, AnonymousClass0VM r6) {
        String str;
        AnonymousClass1jV A002;
        if (r5.A05) {
            if (((AbstractC09891kF) r5).A00 == null) {
                C09671jT r2 = new C09671jT();
                C10371lu r1 = new C10371lu(r2);
                r5.A02 = new AnonymousClass1mJ();
                r5.A0B(r1);
                ((AbstractC09891kF) r5).A00 = r2;
                AnonymousClass1lX r0 = ((AbstractC09891kF) r5).A04;
                if (r0 != null) {
                    C10251lh r02 = r0.A04;
                    r02.A00 = r2;
                    r02.invalidateSelf();
                }
            }
            if (r5.A03 == null) {
                r5.A0D(r5.A02);
            }
            Drawable drawable = ((AbstractC09891kF) r5).A00;
            if (drawable instanceof C09671jT) {
                C09671jT r4 = (C09671jT) drawable;
                String str2 = ((AbstractC09891kF) r5).A07;
                if (str2 == null) {
                    str2 = "none";
                }
                r4.A09 = str2;
                r4.invalidateSelf();
                AnonymousClass1mY A45 = r5.A45();
                AnonymousClass2eu r03 = null;
                if (!(A45 == null || (A002 = A00(A45.A53())) == null)) {
                    r03 = A002.A03;
                }
                r4.A08 = r03;
                int i = r5.A02.A00;
                switch (i) {
                    case 2:
                        str = "network";
                        break;
                    case 3:
                        str = "disk";
                        break;
                    case 4:
                        str = "memory_encoded";
                        break;
                    case 5:
                        str = "memory_bitmap";
                        break;
                    case 6:
                        str = "memory_bitmap_shortcut";
                        break;
                    case 7:
                        str = "local";
                        break;
                    default:
                        str = "unknown";
                        break;
                }
                int i2 = AnonymousClass1GO.A00.get(i, -1);
                r4.A0A = str;
                r4.A03 = i2;
                r4.invalidateSelf();
                if (r6 != null) {
                    int A5L = r6.A5L();
                    int A44 = r6.A44();
                    r4.A07 = A5L;
                    r4.A00 = A44;
                    r4.invalidateSelf();
                    r4.A01 = r6.A00();
                    return;
                }
                r4.A01();
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/res/Resources;LX/0Mb;LX/0PY;Ljava/util/concurrent/Executor;LX/0mg<LX/0H3;LX/0VM;>;LX/0Ic<LX/0PY;>;)V */
    public AnonymousClass1k9(Resources resources, AbstractC00890Mb r3, Executor executor, AbstractC03450mg r5) {
        super(r3, executor);
        this.A08 = resources;
        this.A07 = new AnonymousClass1jN(resources);
        this.A06 = r5;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.String, java.lang.Object] */
    @Override // X.AbstractC09891kF
    public final void A0C(String str, AbstractC00820Ju<AnonymousClass0VM> r6) {
        super.A0C(str, r6);
        synchronized (this) {
            AnonymousClass1j6 r3 = this.A03;
            if (r3 != null) {
                r3.A79(str, 6, true, "PipelineDraweeController");
            }
        }
    }

    @Override // X.AnonymousClass1m0, X.AbstractC09891kF
    public final void A9t(@Nullable AnonymousClass1mY r2) {
        super.A9t(r2);
        A01(this, null);
    }

    @Override // X.AbstractC09891kF
    public final String toString() {
        C00720Ig A002 = C00730Ih.A00(this);
        C00720Ig.A00(A002, "super", super.toString());
        C00720Ig.A00(A002, "dataSourceSupplier", this.A01);
        return A002.toString();
    }
}
