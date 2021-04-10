package X;

import android.annotation.SuppressLint;
import com.facebook.annotations.OkToExtend;
import com.facebook.secure.context.IntentLaunchingPlugin;
import com.facebook.secure.intent.TrustedAppIntentScope;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@OkToExtend
@SuppressLint({"InstanceMethodCanBeStatic"})
/* renamed from: X.1SM  reason: invalid class name */
public final class AnonymousClass1SM {
    @Nullable
    public static AnonymousClass1SM A0B;
    public static final AnonymousClass1SQ A0C = new AnonymousClass1SQ();
    public static final C02580jL A0D = new C02580jL();
    public static final C02650jV A0E = new C02650jV();
    @Nullable
    public AnonymousClass1SN A00 = null;
    @Nullable
    public AnonymousClass1SN A01 = null;
    @Nullable
    public AnonymousClass1SN A02 = null;
    @Nullable
    public C00580Hj A03 = null;
    @Nullable
    public AnonymousClass0Um A04 = null;
    @Nullable
    public AnonymousClass0Hi A05 = null;
    public Map<AnonymousClass0kO, TrustedAppIntentScope> A06 = new HashMap();
    public Map<AnonymousClass0kO, TrustedAppIntentScope> A07 = new HashMap();
    public final List<IntentLaunchingPlugin> A08 = AnonymousClass1SP.A00;
    public final Map<AnonymousClass0kO, AnonymousClass1SN> A09 = new HashMap();
    public final Map<AnonymousClass0kO, AnonymousClass1SN> A0A = new HashMap();

    public static synchronized AnonymousClass1SM A00() {
        AnonymousClass1SM r0;
        synchronized (AnonymousClass1SM.class) {
            r0 = A0B;
            if (r0 == null) {
                r0 = new AnonymousClass1SM();
                A0B = r0;
            }
        }
        return r0;
    }
}
