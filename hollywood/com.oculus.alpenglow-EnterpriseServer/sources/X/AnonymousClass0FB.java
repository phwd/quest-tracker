package X;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.database.ApplicationDatabase;
import java.util.concurrent.Executor;

/* renamed from: X.0FB  reason: invalid class name */
public final class AnonymousClass0FB {
    @NonNull
    public final Context A00;
    public final AnonymousClass0Fp A01;
    @NonNull
    public final AnonymousClass0Fq A02;
    @NonNull
    public final AnonymousClass0GU A03;
    @Nullable
    public final String A04 = ApplicationDatabase.DATABASE_NAME;
    @NonNull
    public final Executor A05;
    @NonNull
    public final Executor A06;
    public final boolean A07;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/String;LX/0GU;LX/0Fq;Ljava/util/List<Landroidx/room/RoomDatabase$Callback;>;ZLX/0Fp;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set<Ljava/lang/Integer;>;Ljava/lang/String;Ljava/io/File;)V */
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public AnonymousClass0FB(@NonNull Context context, @Nullable AnonymousClass0GU r4, @NonNull AnonymousClass0Fq r5, @NonNull AnonymousClass0Fp r6, @Nullable Executor executor, Executor executor2) {
        this.A03 = r4;
        this.A00 = context;
        this.A02 = r5;
        this.A01 = r6;
        this.A05 = executor;
        this.A06 = executor2;
        this.A07 = true;
    }
}
