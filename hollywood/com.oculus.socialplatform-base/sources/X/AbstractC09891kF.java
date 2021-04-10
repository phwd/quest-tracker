package X;

import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.MotionEvent;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1kF  reason: invalid class name and case insensitive filesystem */
public interface AbstractC09891kF<T, INFO> extends AnonymousClass1m0, AbstractC00880Ma {
    public static final Map<String, Object> A0G = C00690Id.A00("component_tag", "drawee");
    public static final Map<String, Object> A0H = C00690Id.A01("origin", "memory_bitmap", "origin_sub", "shortcut");
    @Nullable
    public Drawable A00;
    @Nullable
    public Drawable A01;
    @Nullable
    public AnonymousClass0M8<T> A02;
    @Nullable
    public AnonymousClass1l9<INFO> A03;
    @Nullable
    public AnonymousClass1lX A04;
    public C09981ka<INFO> A05 = new C09981ka<>();
    public Object A06;
    public String A07;
    public boolean A08;
    public boolean A09;
    public boolean A0A = true;
    @Nullable
    public T A0B;
    public boolean A0C;
    public final C00920Me A0D = new C00920Me();
    public final Executor A0E;
    public final AbstractC00890Mb A0F;

    default AbstractC09891kF(AbstractC00890Mb r3, Executor executor) {
        this.A0F = r3;
        this.A0E = executor;
        A07(this, null, null);
    }

    static synchronized default void A07(AbstractC09891kF r4, String str, Object obj) {
        AbstractC00890Mb r0;
        synchronized (r4) {
            C01060Pq.A00();
            r4.A0D.A00(EnumC00910Md.ON_INIT_CONTROLLER);
            if (!r4.A0A && (r0 = r4.A0F) != null) {
                r0.A00(r4);
            }
            r4.A0C = false;
            r4.A03();
            AnonymousClass1l9<INFO> r1 = r4.A03;
            if (r1 instanceof AnonymousClass1mK) {
                AnonymousClass1mK r12 = (AnonymousClass1mK) r1;
                synchronized (r12) {
                    r12.A00.clear();
                }
            } else {
                r4.A03 = null;
            }
            AnonymousClass1lX r2 = r4.A04;
            if (r2 != null) {
                r2.A03.A9r(r2.A01);
                AnonymousClass1lX.A06(r2);
                C10251lh r02 = r4.A04.A04;
                r02.A00 = null;
                r02.invalidateSelf();
                r4.A04 = null;
            }
            r4.A00 = null;
            r4.A07 = str;
            r4.A06 = obj;
            C01060Pq.A00();
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;TT;)V */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private default void A08(Object obj) {
        if (AnonymousClass0J5.A00.A64(2)) {
            A0A(obj);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;LX/0M8<TT;>;)Z */
    static default boolean A09(AbstractC09891kF r2, String str, AnonymousClass0M8 r4) {
        if (r4 == null && r2.A02 == null) {
            return true;
        }
        return str.equals(r2.A07) && r4 == r2.A02 && r2.A09;
    }

    default void A0C(String str, T t) {
    }

    private default AnonymousClass1lF A02(@Nullable Map<String, Object> map, @Nullable Map<String, Object> map2, @Nullable Uri uri) {
        Rect bounds;
        AnonymousClass2eu r0;
        AnonymousClass1lX r1 = this.A04;
        String str = null;
        if (r1 != null) {
            if (!(AnonymousClass1lX.A01(r1) instanceof AnonymousClass1jV)) {
                r0 = null;
            } else {
                r0 = AnonymousClass1lX.A02(r1).A03;
            }
            str = String.valueOf(r0);
            AnonymousClass1lX r12 = this.A04;
            if (AnonymousClass1lX.A01(r12) instanceof AnonymousClass1jV) {
                AnonymousClass1lX.A02(r12);
            }
        }
        Map<String, Object> map3 = A0G;
        Map<String, Object> map4 = A0H;
        AnonymousClass1lX r02 = this.A04;
        if (r02 == null) {
            bounds = null;
        } else {
            bounds = r02.A04.getBounds();
        }
        Object obj = this.A06;
        AnonymousClass1lF r03 = new AnonymousClass1lF();
        if (bounds != null) {
            bounds.width();
            bounds.height();
        }
        r03.A02 = str;
        r03.A01 = obj;
        r03.A00 = uri;
        r03.A04 = map;
        r03.A05 = map2;
        r03.A06 = map4;
        r03.A03 = map3;
        return r03;
    }

    private default void A03() {
        Map<String, Object> map;
        Map<String, Object> A3v;
        boolean z = this.A09;
        this.A09 = false;
        this.A08 = false;
        AnonymousClass0M8<T> r0 = this.A02;
        Map<String, Object> map2 = null;
        if (r0 != null) {
            map = r0.A3v();
            r0.A29();
            this.A02 = null;
        } else {
            map = null;
        }
        this.A01 = null;
        T t = this.A0B;
        if (t != null) {
            T t2 = t;
            C00740Ii.A03(AbstractC00820Ju.A04(t2));
            AbstractC01000Pb r02 = (AbstractC01000Pb) t2.A06();
            if (r02 == null) {
                A3v = null;
            } else {
                A3v = r02.A3v();
            }
            A08(this.A0B);
            AbstractC00820Ju.A03(this.A0B);
            this.A0B = null;
            map2 = A3v;
        }
        if (z) {
            AnonymousClass1l9 r1 = this.A03;
            if (r1 == null) {
                r1 = AnonymousClass1lG.NO_OP_LISTENER;
            }
            r1.onRelease(this.A07);
            this.A05.A01(this.A07, A02(map, map2, null));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        if (r0 != null) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final default void A04(X.AnonymousClass0M8<T> r7, @javax.annotation.Nullable INFO r8) {
        /*
            r6 = this;
            X.1l9<INFO> r2 = r6.A03
            if (r2 != 0) goto L_0x0006
            X.1l9<java.lang.Object> r2 = X.AnonymousClass1lG.NO_OP_LISTENER
        L_0x0006:
            java.lang.String r1 = r6.A07
            java.lang.Object r0 = r6.A06
            r2.onSubmit(r1, r0)
            X.1ka<INFO> r5 = r6.A05
            java.lang.String r4 = r6.A07
            java.lang.Object r3 = r6.A06
            r1 = r6
            boolean r0 = r6 instanceof X.AnonymousClass1k9
            if (r0 == 0) goto L_0x0042
            X.1k9 r1 = (X.AnonymousClass1k9) r1
            X.1kA r1 = r1.A04
            X.0Ib<X.1kA, android.net.Uri> r0 = X.AnonymousClass1kA.A0G
            if (r1 == 0) goto L_0x0042
            java.lang.Object r0 = r0.apply(r1)
            android.net.Uri r0 = (android.net.Uri) r0
            if (r0 == 0) goto L_0x0042
        L_0x0028:
            if (r7 != 0) goto L_0x003d
            r2 = 0
        L_0x002b:
            X.0Pb r8 = (X.AbstractC01000Pb) r8
            if (r8 != 0) goto L_0x0038
            r1 = 0
        L_0x0030:
            X.1lF r0 = r6.A02(r2, r1, r0)
            r5.A03(r4, r3, r0)
            return
        L_0x0038:
            java.util.Map r1 = r8.A3v()
            goto L_0x0030
        L_0x003d:
            java.util.Map r2 = r7.A3v()
            goto L_0x002b
        L_0x0042:
            r0 = 0
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC09891kF.A04(X.0M8, java.lang.Object):void");
    }

    private final default int A0A(@Nullable T t) {
        if (!(this instanceof AnonymousClass1k9)) {
            return System.identityHashCode(t);
        }
        T t2 = t;
        if (t2 == null || !t2.A07()) {
            return 0;
        }
        return System.identityHashCode(t2.A01.A01());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.1l9<? super INFO> */
    /* JADX WARN: Multi-variable type inference failed */
    final default void A0B(AnonymousClass1l9<? super INFO> r3) {
        AnonymousClass1l9<INFO> r1 = this.A03;
        if (r1 instanceof AnonymousClass1mK) {
            ((AnonymousClass1kY) r1).A01(r3);
        } else if (r1 != null) {
            C01060Pq.A00();
            AnonymousClass1mK r0 = new AnonymousClass1mK();
            r0.A01(r1);
            r0.A01(r3);
            C01060Pq.A00();
            this.A03 = r0;
        } else {
            this.A03 = r3;
        }
    }

    @Override // X.AnonymousClass1m0
    @Nullable
    final default AnonymousClass1mY A45() {
        return this.A04;
    }

    @Override // X.AbstractC00880Ma
    final default void A8x() {
        this.A0D.A00(EnumC00910Md.ON_RELEASE_CONTROLLER);
        AnonymousClass1lX r2 = this.A04;
        if (r2 != null) {
            r2.A03.A9r(r2.A01);
            AnonymousClass1lX.A06(r2);
        }
        A03();
    }

    @Override // X.AnonymousClass1m0
    default void A9t(@Nullable AnonymousClass1mY r3) {
        EnumC00910Md r0;
        C00920Me r1 = this.A0D;
        if (r3 != null) {
            r0 = EnumC00910Md.ON_SET_HIERARCHY;
        } else {
            r0 = EnumC00910Md.ON_CLEAR_HIERARCHY;
        }
        r1.A00(r0);
        if (this.A09) {
            this.A0F.A00(this);
            A8x();
        }
        AnonymousClass1lX r02 = this.A04;
        if (r02 != null) {
            C10251lh r03 = r02.A04;
            r03.A00 = null;
            r03.invalidateSelf();
            this.A04 = null;
        }
        if (r3 != null) {
            C00740Ii.A01(Boolean.valueOf(r3 instanceof AnonymousClass1lX));
            AnonymousClass1lX r32 = (AnonymousClass1lX) r3;
            this.A04 = r32;
            Drawable drawable = this.A00;
            C10251lh r04 = r32.A04;
            r04.A00 = drawable;
            r04.invalidateSelf();
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;LX/0M8<TT;>;Ljava/lang/Throwable;Z)V */
    static default void A06(AbstractC09891kF r4, String str, AnonymousClass0M8 r6, Throwable th, boolean z) {
        EnumC00910Md r0;
        Map<String, Object> A3v;
        C01060Pq.A00();
        if (!A09(r4, str, r6)) {
            r6.A29();
        } else {
            C00920Me r1 = r4.A0D;
            if (z) {
                r0 = EnumC00910Md.ON_DATASOURCE_FAILURE;
            } else {
                r0 = EnumC00910Md.ON_DATASOURCE_FAILURE_INT;
            }
            r1.A00(r0);
            if (z) {
                r4.A02 = null;
                r4.A08 = true;
                AnonymousClass1lX r3 = r4.A04;
                AnonymousClass1lW r2 = r3.A02;
                r2.A03++;
                AnonymousClass1lX.A05(r3);
                if (r2.A01(5) != null) {
                    AnonymousClass1lX.A07(r3, 5);
                } else {
                    AnonymousClass1lX.A07(r3, 1);
                }
                r2.A03--;
                r2.invalidateSelf();
                if (r6 == null) {
                    A3v = null;
                } else {
                    A3v = r6.A3v();
                }
                AnonymousClass1lF A022 = r4.A02(A3v, null, null);
                AnonymousClass1l9 r12 = r4.A03;
                if (r12 == null) {
                    r12 = AnonymousClass1lG.NO_OP_LISTENER;
                }
                r12.onFailure(r4.A07, th);
                r4.A05.A04(r4.A07, th, A022);
            } else {
                AnonymousClass1l9 r13 = r4.A03;
                if (r13 == null) {
                    r13 = AnonymousClass1lG.NO_OP_LISTENER;
                }
                r13.onIntermediateImageFailed(r4.A07, th);
            }
        }
        C01060Pq.A00();
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r14v0, resolved type: X.1kF<T, INFO> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass1m0
    final default void A6n() {
        AbstractC00820Ju<AnonymousClass0VM> r9;
        AnonymousClass0H3 r0;
        C01060Pq.A00();
        C00920Me r5 = this.A0D;
        r5.A00(EnumC00910Md.ON_ATTACH_CONTROLLER);
        if (this.A04 != null) {
            this.A0F.A00(this);
            this.A0C = true;
            if (!this.A09) {
                C01060Pq.A00();
                if (!(this instanceof AnonymousClass1k9)) {
                    r9 = null;
                } else {
                    AnonymousClass1k9 r3 = (AnonymousClass1k9) this;
                    C01060Pq.A00();
                    try {
                        AbstractC03450mg<AnonymousClass0H3, AnonymousClass0VM> r2 = r3.A06;
                        if (!(r2 == null || (r0 = r3.A00) == null)) {
                            r9 = r2.A3K(r0);
                            if (r9 == null || r9.A06().A01().A01) {
                                C01060Pq.A00();
                            } else {
                                r9.close();
                            }
                        }
                        C01060Pq.A00();
                        r9 = null;
                    } catch (Throwable th) {
                        C01060Pq.A00();
                        throw th;
                    }
                }
                if (r9 != null) {
                    C01060Pq.A00();
                    this.A02 = null;
                    this.A09 = true;
                    this.A08 = false;
                    r5.A00(EnumC00910Md.ON_SUBMIT_CACHE_HIT);
                    AnonymousClass0M8<T> r1 = this.A02;
                    C00740Ii.A03(AbstractC00820Ju.A04(r9));
                    A04(r1, r9.A06());
                    A0C(this.A07, r9);
                    A05(this, this.A07, this.A02, r9, 1.0f, true, true, true);
                    C01060Pq.A00();
                } else {
                    r5.A00(EnumC00910Md.ON_DATASOURCE_SUBMIT);
                    this.A04.A08(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, true);
                    this.A09 = true;
                    this.A08 = false;
                    C01060Pq.A00();
                    AnonymousClass0M8<T> r02 = (AnonymousClass0M8<T>) ((AnonymousClass1k9) this).A01.get();
                    C01060Pq.A00();
                    this.A02 = r02;
                    A04(r02, null);
                    this.A02.AAY(new AnonymousClass1jO(this, this.A07, this.A02.A5S()), this.A0E);
                }
                C01060Pq.A00();
            }
            C01060Pq.A00();
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass1m0
    final default void A70() {
        C01060Pq.A00();
        this.A0D.A00(EnumC00910Md.ON_DETACH_CONTROLLER);
        this.A0C = false;
        this.A0F.A01(this);
        C01060Pq.A00();
    }

    default String toString() {
        C00720Ig A002 = C00730Ih.A00(this);
        C00720Ig.A00(A002, "isAttached", String.valueOf(this.A0C));
        C00720Ig.A00(A002, "isRequestSubmitted", String.valueOf(this.A09));
        C00720Ig.A00(A002, "hasFetchFailed", String.valueOf(this.A08));
        C00720Ig.A00(A002, "fetchedImage", String.valueOf(A0A(this.A0B)));
        C00720Ig.A00(A002, "events", this.A0D.toString());
        return A002.toString();
    }

    @Override // X.AnonymousClass1m0
    final default boolean A8E(MotionEvent motionEvent) {
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;LX/0M8<TT;>;TT;FZZZ)V */
    /* JADX INFO: finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v4, types: [X.1l9] */
    /* JADX WARN: Type inference failed for: r0v23, types: [X.1l9] */
    static default void A05(AbstractC09891kF r6, String str, @Nullable AnonymousClass0M8 r8, Object obj, float f, boolean z, boolean z2, boolean z3) {
        EnumC00910Md r0;
        Animatable animatable;
        Map<String, Object> map;
        Map<String, Object> map2;
        try {
            C01060Pq.A00();
            if (!A09(r6, str, r8)) {
                r6.A08(obj);
                AbstractC00820Ju.A03((AbstractC00820Ju) obj);
                r8.A29();
            } else {
                C00920Me r1 = r6.A0D;
                if (z) {
                    r0 = EnumC00910Md.ON_DATASOURCE_RESULT;
                } else {
                    r0 = EnumC00910Md.ON_DATASOURCE_RESULT_INT;
                }
                r1.A00(r0);
                try {
                    AnonymousClass1k9 r3 = (AnonymousClass1k9) r6;
                    AbstractC00820Ju r12 = (AbstractC00820Ju) obj;
                    try {
                        C01060Pq.A00();
                        C00740Ii.A03(AbstractC00820Ju.A04(r12));
                        AnonymousClass0VM r2 = (AnonymousClass0VM) r12.A06();
                        AnonymousClass1k9.A01(r3, r2);
                        Drawable A2Q = r3.A07.A2Q(r2);
                        if (A2Q != null) {
                            C01060Pq.A00();
                            T t = r6.A0B;
                            r6.A0B = obj;
                            r6.A01 = A2Q;
                            if (z) {
                                r6.A08(obj);
                                r6.A02 = null;
                                r6.A04.A09(A2Q, 1.0f, z2);
                            } else if (z3) {
                                try {
                                    r6.A08(obj);
                                    r6.A04.A09(A2Q, 1.0f, z2);
                                } catch (Throwable th) {
                                    if (!(t == null || t == obj)) {
                                        r6.A08(t);
                                        AbstractC00820Ju.A03(t);
                                    }
                                    throw th;
                                }
                            } else {
                                r6.A08(obj);
                                r6.A04.A09(A2Q, f, z2);
                                C00740Ii.A03(AbstractC00820Ju.A04(r12));
                                Object A062 = r12.A06();
                                AnonymousClass1l9<INFO> r02 = r6.A03;
                                AnonymousClass1l9<INFO> r03 = r02;
                                if (r02 == null) {
                                    r03 = (AnonymousClass1l9<INFO>) AnonymousClass1lG.NO_OP_LISTENER;
                                }
                                r03.onIntermediateImageSet(str, A062);
                                if (!(t == null || t == obj)) {
                                    r6.A08(t);
                                    AbstractC00820Ju.A03(t);
                                }
                            }
                            C00740Ii.A03(AbstractC00820Ju.A04(r12));
                            Object A063 = r12.A06();
                            AnonymousClass1l9<INFO> r32 = r6.A03;
                            AnonymousClass1l9<INFO> r33 = r32;
                            if (r32 == null) {
                                r33 = (AnonymousClass1l9<INFO>) AnonymousClass1lG.NO_OP_LISTENER;
                            }
                            Drawable drawable = r6.A01;
                            if (drawable instanceof Animatable) {
                                animatable = (Animatable) drawable;
                            } else {
                                animatable = null;
                            }
                            r33.onFinalImageSet(str, A063, animatable);
                            C09981ka<INFO> r4 = r6.A05;
                            if (r8 == null) {
                                map = null;
                            } else {
                                map = r8.A3v();
                            }
                            AbstractC01000Pb r04 = (AbstractC01000Pb) A063;
                            if (r04 == null) {
                                map2 = null;
                            } else {
                                map2 = r04.A3v();
                            }
                            r4.A02(str, A063, r6.A02(map, map2, null));
                            r6.A08(t);
                            AbstractC00820Ju.A03(t);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unrecognized image class: ");
                            sb.append(r2);
                            throw new UnsupportedOperationException(sb.toString());
                        }
                    } catch (Throwable th2) {
                        C01060Pq.A00();
                        throw th2;
                    }
                } catch (Exception e) {
                    r6.A08(obj);
                    AbstractC00820Ju.A03((AbstractC00820Ju) obj);
                    A06(r6, str, r8, e, z);
                }
            }
            C01060Pq.A00();
        } catch (Throwable th3) {
            C01060Pq.A00();
            throw th3;
        }
    }
}
