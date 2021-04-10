package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.transition.TransitionFactory;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: X.1f3  reason: invalid class name */
public final class AnonymousClass1f3<R> implements AnonymousClass1h5, AbstractC09041fz {
    public static final boolean A0R = Log.isLoggable("Request", 2);
    @GuardedBy("requestLock")
    public int A00;
    @GuardedBy("requestLock")
    public int A01;
    @GuardedBy("requestLock")
    public long A02;
    @Nullable
    @GuardedBy("requestLock")
    public Drawable A03;
    @Nullable
    @GuardedBy("requestLock")
    public Drawable A04;
    @GuardedBy("requestLock")
    public AnonymousClass1fU A05;
    @GuardedBy("requestLock")
    public AnonymousClass1fR<R> A06;
    @GuardedBy("requestLock")
    public Integer A07;
    @GuardedBy("requestLock")
    public boolean A08;
    @Nullable
    @GuardedBy("requestLock")
    public Drawable A09;
    public final C08731fB A0A;
    public final AnonymousClass1g2<?> A0B;
    public final AbstractC08811fX A0C;
    @Nullable
    public final AnonymousClass1fS<R> A0D;
    public final AbstractC08781fH<R> A0E;
    public final AbstractC08341eQ A0F;
    @Nullable
    public final Object A0G;
    public final Object A0H;
    @Nullable
    public final List<AnonymousClass1fS<R>> A0I;
    public final int A0J;
    public final int A0K;
    public final Context A0L;
    public final AnonymousClass1cY A0M;
    public final TransitionFactory<? super R> A0N;
    public final Class<R> A0O;
    public final Executor A0P;
    public volatile AbstractC08671f5 A0Q;

    @GuardedBy("requestLock")
    private Drawable A00() {
        int i;
        Drawable drawable = this.A09;
        if (drawable != null) {
            return drawable;
        }
        AnonymousClass1g2<?> r0 = this.A0B;
        Drawable drawable2 = r0.placeholderDrawable;
        this.A09 = drawable2;
        if (drawable2 != null || (i = r0.placeholderId) <= 0) {
            return drawable2;
        }
        Drawable A012 = A01(i);
        this.A09 = A012;
        return A012;
    }

    @GuardedBy("requestLock")
    private Drawable A01(@DrawableRes int i) {
        Resources.Theme theme = this.A0B.theme;
        if (theme == null) {
            theme = this.A0L.getTheme();
        }
        C08731fB r0 = this.A0A;
        return AnonymousClass1pV.A00(r0, r0, i, theme);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ab, code lost:
        if (r1 == false) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00dd, code lost:
        if (r1 == null) goto L_0x00df;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c7 A[Catch:{ all -> 0x010b }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e3 A[Catch:{ all -> 0x010b }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f7 A[Catch:{ all -> 0x010b }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0106  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(X.AnonymousClass1f3 r10, X.AnonymousClass1Or r11, int r12) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1f3.A02(X.1f3, X.1Or, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: X.1fR<?> */
    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: X.1fS<R> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v27, resolved type: X.1fH<R> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        if (r5.A4q().A5q() == false) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(X.AnonymousClass1fR<?> r15, X.AnonymousClass1fM r16) {
        /*
        // Method dump skipped, instructions count: 300
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1f3.A03(X.1fR, X.1fM):void");
    }

    @Override // X.AnonymousClass1h5
    public final void A1a() {
        AbstractC08811fX r0;
        int i;
        synchronized (this.A0H) {
            if (!this.A08) {
                this.A0F.A00();
                this.A02 = SystemClock.elapsedRealtimeNanos();
                if (this.A0G == null) {
                    int i2 = this.A0K;
                    int i3 = this.A0J;
                    if (C08381eW.A06(i2, i3)) {
                        this.A01 = i2;
                        this.A00 = i3;
                    }
                    Drawable drawable = this.A04;
                    if (drawable == null) {
                        AnonymousClass1g2<?> r02 = this.A0B;
                        drawable = r02.fallbackDrawable;
                        this.A04 = drawable;
                        if (drawable == null && (i = r02.fallbackId) > 0) {
                            drawable = A01(i);
                            this.A04 = drawable;
                        }
                    }
                    int i4 = 3;
                    if (drawable == null) {
                        i4 = 5;
                    }
                    A02(this, new AnonymousClass1Or("Received null model", Collections.emptyList()), i4);
                } else {
                    Integer num = this.A07;
                    Integer num2 = AnonymousClass007.A01;
                    if (num == num2) {
                        throw new IllegalArgumentException("Cannot restart a running request");
                    } else if (num == AnonymousClass007.A04) {
                        A03(this.A06, AnonymousClass1fM.MEMORY_CACHE);
                    } else {
                        Integer num3 = AnonymousClass007.A03;
                        this.A07 = num3;
                        int i5 = this.A0K;
                        int i6 = this.A0J;
                        if (C08381eW.A06(i5, i6)) {
                            A84(i5, i6);
                        } else {
                            this.A0E.getSize(this);
                        }
                        Integer num4 = this.A07;
                        if ((num4 == num2 || num4 == num3) && ((r0 = this.A0C) == null || r0.A1x(this))) {
                            this.A0E.onLoadStarted(A00());
                        }
                        if (A0R) {
                            SystemClock.elapsedRealtimeNanos();
                        }
                    }
                }
            } else {
                throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
            }
        }
    }

    @Override // X.AnonymousClass1h5
    public final boolean A5q() {
        boolean z;
        synchronized (this.A0H) {
            z = false;
            if (this.A07 == AnonymousClass007.A04) {
                z = true;
            }
        }
        return z;
    }

    @Override // X.AnonymousClass1h5
    public final boolean A5s() {
        boolean z;
        synchronized (this.A0H) {
            z = false;
            if (this.A07 == AnonymousClass007.A06) {
                z = true;
            }
        }
        return z;
    }

    @Override // X.AnonymousClass1h5
    public final boolean A5v(AnonymousClass1h5 r16) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        AnonymousClass1g2<?> r10;
        AnonymousClass1cY r9;
        int i3;
        int i4;
        int i5;
        Object obj2;
        Class<R> cls2;
        AnonymousClass1g2<?> r3;
        AnonymousClass1cY r2;
        int i6;
        boolean z = false;
        if (r16 instanceof AnonymousClass1f3) {
            synchronized (this.A0H) {
                i = this.A0K;
                i2 = this.A0J;
                obj = this.A0G;
                cls = this.A0O;
                r10 = this.A0B;
                r9 = this.A0M;
                List<AnonymousClass1fS<R>> list = this.A0I;
                if (list != null) {
                    i3 = list.size();
                } else {
                    i3 = 0;
                }
            }
            AnonymousClass1f3 r1 = (AnonymousClass1f3) r16;
            synchronized (r1.A0H) {
                i4 = r1.A0K;
                i5 = r1.A0J;
                obj2 = r1.A0G;
                cls2 = r1.A0O;
                r3 = r1.A0B;
                r2 = r1.A0M;
                List<AnonymousClass1fS<R>> list2 = r1.A0I;
                if (list2 != null) {
                    i6 = list2.size();
                } else {
                    i6 = 0;
                }
            }
            if (i != i4 || i2 != i5) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
            if (!cls.equals(cls2) || !r10.equals(r3) || r9 != r2) {
                return false;
            }
            z = true;
            if (i3 != i6) {
                return false;
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d4, code lost:
        if (r3 != null) goto L_0x00d6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f1  */
    @Override // X.AbstractC09041fz
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A84(int r36, int r37) {
        /*
        // Method dump skipped, instructions count: 529
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1f3.A84(int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0054, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0056, code lost:
        X.AbstractC08671f5.A00(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return;
     */
    @Override // X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void clear() {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1f3.clear():void");
    }

    @Override // X.AnonymousClass1h5
    public final boolean isComplete() {
        boolean z;
        synchronized (this.A0H) {
            z = false;
            if (this.A07 == AnonymousClass007.A04) {
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r2 == X.AnonymousClass007.A03) goto L_0x000e;
     */
    @Override // X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isRunning() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A0H
            monitor-enter(r3)
            java.lang.Integer r2 = r4.A07     // Catch:{ all -> 0x0011 }
            java.lang.Integer r0 = X.AnonymousClass007.A01     // Catch:{ all -> 0x0011 }
            if (r2 == r0) goto L_0x000e
            java.lang.Integer r1 = X.AnonymousClass007.A03     // Catch:{ all -> 0x0011 }
            r0 = 0
            if (r2 != r1) goto L_0x000f
        L_0x000e:
            r0 = 1
        L_0x000f:
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1f3.isRunning():boolean");
    }

    @Override // X.AnonymousClass1h5
    public final void pause() {
        synchronized (this.A0H) {
            if (isRunning()) {
                clear();
            }
        }
    }

    public AnonymousClass1f3(Context context, C08731fB r3, @NonNull Object obj, @Nullable Object obj2, Class<R> cls, AnonymousClass1g2<?> r7, int i, int i2, AnonymousClass1cY r10, AbstractC08781fH<R> r11, @Nullable AnonymousClass1fS<R> r12, @Nullable List<AnonymousClass1fS<R>> list, AbstractC08811fX r14, AbstractC08671f5 r15, TransitionFactory<? super R> transitionFactory, Executor executor) {
        if (A0R) {
            super.hashCode();
        }
        this.A0F = new C08331eP();
        this.A0H = obj;
        this.A0L = context;
        this.A0A = r3;
        this.A0G = obj2;
        this.A0O = cls;
        this.A0B = r7;
        this.A0K = i;
        this.A0J = i2;
        this.A0M = r10;
        this.A0E = r11;
        this.A0D = r12;
        this.A0I = list;
        this.A0C = r14;
        this.A0Q = r15;
        this.A0N = transitionFactory;
        this.A0P = executor;
        this.A07 = AnonymousClass007.A00;
    }
}
