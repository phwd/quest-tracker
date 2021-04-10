package X;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.analytics2.logger.BatchPayloadIteratorFactory;
import com.facebook.analytics2.logger.DefaultHandlerThreadFactory;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import com.facebook.analytics2.logger.PrivacyPolicy;
import com.facebook.analytics2.logger.UploadJobInstrumentation;
import com.facebook.flexiblesampling.SamplingPolicyConfig;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.annotation.Nullable;

public final class G7 {
    public static G7 A07;
    public final ArrayList<BatchPayloadIteratorFactory> A00 = new ArrayList<>();
    public final ArrayList<SamplingPolicyConfig> A01 = new ArrayList<>();
    public final ArrayList<UploadJobInstrumentation> A02 = new ArrayList<>();
    public final ArrayList<PrivacyPolicy> A03 = new ArrayList<>();
    public final Context A04;
    public final ArrayList<HandlerThreadFactory> A05 = new ArrayList<>();
    public final ArrayList<AbstractC0090Hb> A06 = new ArrayList<>();

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(Ljava/util/ArrayList<TT;>;Ljava/lang/String;)TT; */
    @Nullable
    public static synchronized Object A01(G7 g7, ArrayList arrayList, String str) {
        Object obj;
        synchronized (g7) {
            try {
                obj = g7.A02(arrayList, Class.forName(str));
            } catch (ClassNotFoundException e) {
                Mu.A0B("ContextConstructorHelper", e, "Cannot find class: %s", str);
            } catch (IllegalAccessException e2) {
                Mu.A0A("ContextConstructorHelper", e2, "IllegalAccessException");
            } catch (InstantiationException e3) {
                Mu.A0A("ContextConstructorHelper", e3, "InstantiationException");
            } catch (NoSuchMethodException e4) {
                Mu.A0A("ContextConstructorHelper", e4, "NoSuchMethodException");
            } catch (InvocationTargetException e5) {
                Mu.A0A("ContextConstructorHelper", e5, "InvocationTargetException");
            }
        }
        return obj;
        obj = null;
        return obj;
    }

    private synchronized <T> T A02(ArrayList<T> arrayList, Class<? extends T> cls) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            T t = arrayList.get(i);
            if (t.getClass().equals(cls)) {
                return t;
            }
        }
        T t2 = (T) cls.getConstructor(Context.class).newInstance(this.A04);
        arrayList.add(t2);
        return t2;
    }

    public static synchronized G7 A00(Context context) {
        G7 g7;
        synchronized (G7.class) {
            g7 = A07;
            if (g7 == null) {
                g7 = new G7(context.getApplicationContext());
                A07 = g7;
            }
        }
        return g7;
    }

    public final HandlerThreadFactory A03(String str) {
        HandlerThreadFactory handlerThreadFactory = (HandlerThreadFactory) A01(this, this.A05, str);
        if (handlerThreadFactory == null) {
            handlerThreadFactory = new DefaultHandlerThreadFactory(this.A04);
            if (Mu.A01.A3F(6)) {
                Mu.A01.A5z("ContextConstructorHelper", "Unable to create instance for HandlerThreadFactory");
            }
        }
        return handlerThreadFactory;
    }

    public final AbstractC0090Hb A04(String str) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj;
        ArrayList<AbstractC0090Hb> arrayList = this.A06;
        synchronized (this) {
            try {
                obj = A02(arrayList, Class.forName(str));
            } catch (ClassNotFoundException e) {
                Mu.A0B("ContextConstructorHelper", e, "Cannot find class: %s", str);
                obj = null;
            }
        }
        return (AbstractC0090Hb) obj;
    }

    @VisibleForTesting
    public G7(Context context) {
        this.A04 = context;
    }
}
