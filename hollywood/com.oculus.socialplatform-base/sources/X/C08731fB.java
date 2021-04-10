package X;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.List;
import java.util.Map;

/* renamed from: X.1fB  reason: invalid class name and case insensitive filesystem */
public final class C08731fB extends ContextWrapper {
    @VisibleForTesting
    public static final AbstractC08971fp<?, ?> A09 = new C08961fo();
    @Nullable
    @GuardedBy("this")
    public AnonymousClass1g7 A00;
    public final int A01 = 4;
    public final C07641cm A02;
    public final AbstractC08671f5 A03;
    public final AnonymousClass1hX A04;
    public final List<AnonymousClass1fS<Object>> A05;
    public final Map<Class<?>, AbstractC08971fp<?, ?>> A06;
    public final C08101dy A07;
    public final AnonymousClass1e0 A08;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Lcom/bumptech/glide/load/engine/bitmap_recycle/ArrayPool;LX/1cm;LX/1e0;Lcom/bumptech/glide/Glide$RequestOptionsFactory;Ljava/util/Map<Ljava/lang/Class<*>;LX/1fp<**>;>;Ljava/util/List<LX/1fS<Ljava/lang/Object;>;>;LX/1f5;ZI)V */
    public C08731fB(@NonNull Context context, @NonNull AnonymousClass1hX r4, @NonNull C07641cm r5, @NonNull AnonymousClass1e0 r6, @NonNull C08101dy r7, @NonNull Map map, @NonNull List list, @NonNull AbstractC08671f5 r10) {
        super(context.getApplicationContext());
        this.A04 = r4;
        this.A02 = r5;
        this.A08 = r6;
        this.A07 = r7;
        this.A05 = list;
        this.A06 = map;
        this.A03 = r10;
    }
}
