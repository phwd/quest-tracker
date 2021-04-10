package X;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: X.1uR  reason: invalid class name */
public class AnonymousClass1uR extends AnonymousClass1uS {
    public List<String> A00 = new CopyOnWriteArrayList();
    public List<AnonymousClass1uS> A01 = new CopyOnWriteArrayList();
    public Set<Class<? extends AnonymousClass1uS>> A02 = new HashSet();

    private boolean A00() {
        boolean z = false;
        for (String str : this.A00) {
            try {
                Class<?> cls = Class.forName(str);
                if (AnonymousClass1uS.class.isAssignableFrom(cls)) {
                    A01((AnonymousClass1uS) cls.newInstance());
                    this.A00.remove(str);
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            } catch (IllegalAccessException | InstantiationException e) {
                Log.e("MergedDataBinderMapper", AnonymousClass006.A07("unable to add feature mapper for ", str), e);
            }
        }
        return z;
    }

    @Override // X.AnonymousClass1uS
    public final String convertBrIdToString(int i) {
        for (AnonymousClass1uS r0 : this.A01) {
            String convertBrIdToString = r0.convertBrIdToString(i);
            if (convertBrIdToString != null) {
                return convertBrIdToString;
            }
        }
        if (A00()) {
            return convertBrIdToString(i);
        }
        return null;
    }

    @Override // X.AnonymousClass1uS
    public final int getLayoutId(String str) {
        for (AnonymousClass1uS r0 : this.A01) {
            int layoutId = r0.getLayoutId(str);
            if (layoutId != 0) {
                return layoutId;
            }
        }
        if (A00()) {
            return getLayoutId(str);
        }
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Set<java.lang.Class<? extends X.1uS>> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A01(AnonymousClass1uS r3) {
        if (this.A02.add(r3.getClass())) {
            this.A01.add(r3);
            for (AnonymousClass1uS r0 : r3.collectDependencies()) {
                A01(r0);
            }
        }
    }

    @Override // X.AnonymousClass1uS
    public final AnonymousClass1uW getDataBinder(AbstractC003408r r3, View view, int i) {
        for (AnonymousClass1uS r0 : this.A01) {
            AnonymousClass1uW dataBinder = r0.getDataBinder(r3, view, i);
            if (dataBinder != null) {
                return dataBinder;
            }
        }
        if (A00()) {
            return getDataBinder(r3, view, i);
        }
        return null;
    }

    @Override // X.AnonymousClass1uS
    public final AnonymousClass1uW getDataBinder(AbstractC003408r r3, View[] viewArr, int i) {
        for (AnonymousClass1uS r0 : this.A01) {
            AnonymousClass1uW dataBinder = r0.getDataBinder(r3, viewArr, i);
            if (dataBinder != null) {
                return dataBinder;
            }
        }
        if (A00()) {
            return getDataBinder(r3, viewArr, i);
        }
        return null;
    }
}
