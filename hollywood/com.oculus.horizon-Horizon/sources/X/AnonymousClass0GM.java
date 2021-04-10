package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.analytics2.logger.BatchPayloadIteratorFactory;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import com.facebook.analytics2.logger.PrivacyPolicy;
import com.facebook.analytics2.logger.UploadJobInstrumentation;
import com.facebook.analytics2.uploader.Uploader;
import com.facebook.flexiblesampling.SamplingPolicyConfig;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* renamed from: X.0GM  reason: invalid class name */
public final class AnonymousClass0GM {
    public static AnonymousClass0GM A07;
    public final Context A00;
    public final ArrayList<BatchPayloadIteratorFactory> A01 = new ArrayList<>();
    public final ArrayList<HandlerThreadFactory> A02 = new ArrayList<>();
    public final ArrayList<SamplingPolicyConfig> A03 = new ArrayList<>();
    public final ArrayList<UploadJobInstrumentation> A04 = new ArrayList<>();
    public final ArrayList<Uploader> A05 = new ArrayList<>();
    public final ArrayList<PrivacyPolicy> A06 = new ArrayList<>();

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(Ljava/util/ArrayList<TT;>;Ljava/lang/Class<+TT;>;)TT; */
    public static synchronized Object A01(AnonymousClass0GM r5, ArrayList arrayList, Class cls) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        synchronized (r5) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = arrayList.get(i);
                if (obj.getClass().equals(cls)) {
                    return obj;
                }
            }
            Object newInstance = cls.getConstructor(Context.class).newInstance(r5.A00);
            arrayList.add(newInstance);
            return newInstance;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(Ljava/util/ArrayList<TT;>;Ljava/lang/String;)TT; */
    @Nullable
    public static synchronized Object A02(AnonymousClass0GM r5, ArrayList arrayList, String str) {
        Object obj;
        synchronized (r5) {
            try {
                obj = A01(r5, arrayList, Class.forName(str));
            } catch (ClassNotFoundException e) {
                AnonymousClass0NO.A0K("ContextConstructorHelper", e, "Cannot find class: %s", str);
            } catch (IllegalAccessException e2) {
                AnonymousClass0NO.A0J("ContextConstructorHelper", e2, "IllegalAccessException");
            } catch (InstantiationException e3) {
                AnonymousClass0NO.A0J("ContextConstructorHelper", e3, "InstantiationException");
            } catch (NoSuchMethodException e4) {
                AnonymousClass0NO.A0J("ContextConstructorHelper", e4, "NoSuchMethodException");
            } catch (InvocationTargetException e5) {
                AnonymousClass0NO.A0J("ContextConstructorHelper", e5, "InvocationTargetException");
            }
        }
        return obj;
        obj = null;
        return obj;
    }

    public static synchronized AnonymousClass0GM A00(Context context) {
        AnonymousClass0GM r1;
        synchronized (AnonymousClass0GM.class) {
            r1 = A07;
            if (r1 == null) {
                r1 = new AnonymousClass0GM(context.getApplicationContext());
                A07 = r1;
            }
        }
        return r1;
    }

    @VisibleForTesting
    public AnonymousClass0GM(Context context) {
        this.A00 = context;
    }
}
