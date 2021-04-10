package X;

import android.content.Context;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1pE  reason: invalid class name */
public abstract class AnonymousClass1pE<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> {
    public static final AbstractC10181rh<Object> A03 = new AnonymousClass1pI();
    public static final NullPointerException A04 = new NullPointerException("No image request was specified!");
    public static final AtomicLong A05 = new AtomicLong();
    @Nullable
    public AbstractC09911pz A00 = null;
    @Nullable
    public REQUEST A01 = null;
    public final Context A02;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/util/Set<LX/1rh;>;Ljava/util/Set<Lcom/facebook/fresco/ui/common/ControllerListener2;>;)V */
    public AnonymousClass1pE(Context context) {
        this.A02 = context;
    }
}
