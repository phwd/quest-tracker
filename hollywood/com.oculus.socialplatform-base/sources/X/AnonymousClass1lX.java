package X;

import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1lX  reason: invalid class name */
public final class AnonymousClass1lX implements AnonymousClass1mY {
    @Nullable
    public AnonymousClass1lm A00;
    public final Drawable A01 = new ColorDrawable(0);
    public final AnonymousClass1lW A02;
    public final AnonymousClass1lb A03;
    public final C10251lh A04;
    public final Resources A05;

    public static AnonymousClass0Mg A01(AnonymousClass1lX r5) {
        AnonymousClass1lW r3 = r5.A02;
        boolean z = true;
        C00740Ii.A01(true);
        AnonymousClass0Mg[] r1 = ((AnonymousClass0o5) r3).A05;
        if (2 >= r1.length) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        if (r1[2] == null) {
            r1[2] = new AnonymousClass0o9(r3);
        }
        AnonymousClass0Mg r12 = r1[2];
        r12.A3p();
        if (r12.A3p() instanceof AnonymousClass1jV) {
            return (AnonymousClass1lb) r12.A3p();
        }
        return r12;
    }

    public static void A05(AnonymousClass1lX r1) {
        r1.A04(1);
        r1.A04(2);
        r1.A04(3);
        r1.A04(4);
        r1.A04(5);
    }

    @Nullable
    private Drawable A00(@Nullable Drawable drawable, @Nullable AnonymousClass2eu r4) {
        Drawable A002 = AnonymousClass1ld.A00(drawable, this.A00, this.A05);
        C01060Pq.A00();
        if (A002 == null || r4 == null) {
            C01060Pq.A00();
            return A002;
        }
        AnonymousClass1jV r0 = new AnonymousClass1jV(A002, r4);
        C01060Pq.A00();
        return r0;
    }

    private void A03(float f) {
        Drawable A012 = this.A02.A01(3);
        if (A012 != null) {
            if (f >= 0.999f) {
                if (A012 instanceof Animatable) {
                    ((Animatable) A012).stop();
                }
                A04(3);
            } else {
                if (A012 instanceof Animatable) {
                    ((Animatable) A012).start();
                }
                A07(this, 3);
            }
            A012.setLevel(Math.round(f * 10000.0f));
        }
    }

    private void A04(int i) {
        AnonymousClass1lW r2 = this.A02;
        r2.A04 = 0;
        r2.A08[i] = false;
        r2.invalidateSelf();
    }

    public static void A06(AnonymousClass1lX r3) {
        AnonymousClass1lW r2 = r3.A02;
        if (r2 != null) {
            r2.A03++;
            r2.A04 = 0;
            Arrays.fill(r2.A08, true);
            r2.invalidateSelf();
            A05(r3);
            A07(r3, 1);
            r2.A03();
            r2.A03--;
            r2.invalidateSelf();
        }
    }

    public static void A07(AnonymousClass1lX r2, int i) {
        AnonymousClass1lW r22 = r2.A02;
        r22.A04 = 0;
        r22.A08[i] = true;
        r22.invalidateSelf();
    }

    public final void A08(float f, boolean z) {
        AnonymousClass1lW r1 = this.A02;
        if (r1.A01(3) != null) {
            r1.A03++;
            A03(f);
            if (z) {
                r1.A03();
            }
            r1.A03--;
            r1.invalidateSelf();
        }
    }

    public final void A09(Drawable drawable, float f, boolean z) {
        Drawable A002 = AnonymousClass1ld.A00(drawable, this.A00, this.A05);
        A002.mutate();
        this.A03.A9r(A002);
        AnonymousClass1lW r1 = this.A02;
        r1.A03++;
        A05(this);
        A07(this, 2);
        A03(f);
        if (z) {
            r1.A03();
        }
        r1.A03--;
        r1.invalidateSelf();
    }

    @Override // X.AnonymousClass1mY
    public final Drawable A53() {
        return this.A04;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v9, resolved type: X.1lY */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass1lX(C10271lj r9) {
        int i;
        C01060Pq.A00();
        this.A05 = r9.A02;
        this.A00 = r9.A0E;
        this.A03 = new AnonymousClass1lb(this.A01);
        List<Drawable> list = r9.A0F;
        int i2 = 1;
        int i3 = ((list == null || (i = list.size()) == 0) ? 1 : i) + (r9.A06 != null ? 1 : 0);
        Drawable[] drawableArr = new Drawable[(i3 + 6)];
        drawableArr[0] = A00(r9.A03, null);
        drawableArr[1] = A00(r9.A05, r9.A0B);
        AnonymousClass1lb r2 = this.A03;
        AnonymousClass2eu r1 = r9.A09;
        r2.setColorFilter(null);
        C01060Pq.A00();
        if (r1 == null) {
            C01060Pq.A00();
        } else {
            AnonymousClass1jV r0 = new AnonymousClass1jV(r2, r1);
            C01060Pq.A00();
            r2 = r0;
        }
        drawableArr[2] = r2;
        drawableArr[3] = A00(r9.A07, r9.A0C);
        drawableArr[4] = A00(r9.A08, r9.A0D);
        drawableArr[5] = A00(r9.A04, r9.A0A);
        if (i3 > 0) {
            List<Drawable> list2 = r9.A0F;
            if (list2 != null) {
                i2 = 0;
                for (Drawable drawable : list2) {
                    drawableArr[i2 + 6] = A00(drawable, null);
                    i2++;
                }
            }
            Drawable drawable2 = r9.A06;
            if (drawable2 != null) {
                drawableArr[i2 + 6] = A00(drawable2, null);
            }
        }
        AnonymousClass1lW r3 = new AnonymousClass1lW(drawableArr);
        this.A02 = r3;
        r3.A02 = r9.A01;
        if (r3.A04 == 1) {
            r3.A04 = 0;
        }
        AnonymousClass1lm r22 = this.A00;
        try {
            if (r22 == null || r22.A04 != AnonymousClass007.A00) {
                C01060Pq.A00();
            } else {
                AnonymousClass1lY r12 = new AnonymousClass1lY(r3);
                AnonymousClass1ld.A02(r12, r22);
                r12.A01 = r22.A03;
                r12.invalidateSelf();
                C01060Pq.A00();
                r3 = r12;
            }
            C10251lh r02 = new C10251lh(r3);
            this.A04 = r02;
            r02.mutate();
            A06(this);
            C01060Pq.A00();
        } finally {
            C01060Pq.A00();
        }
    }

    public static AnonymousClass1jV A02(AnonymousClass1lX r3) {
        AnonymousClass0Mg A012 = A01(r3);
        if (A012 instanceof AnonymousClass1jV) {
            return (AnonymousClass1jV) A012;
        }
        AnonymousClass2eu r2 = AnonymousClass2eu.A08;
        Drawable A9r = A012.A9r(AnonymousClass1ld.A00);
        C01060Pq.A00();
        if (A9r == null || r2 == null) {
            C01060Pq.A00();
        } else {
            AnonymousClass1jV r0 = new AnonymousClass1jV(A9r, r2);
            C01060Pq.A00();
            A9r = r0;
        }
        A012.A9r(A9r);
        C00740Ii.A02(A9r, "Parent has no child drawable!");
        return (AnonymousClass1jV) A9r;
    }
}
