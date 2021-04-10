package defpackage;

import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* renamed from: YS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YS {

    /* renamed from: a  reason: collision with root package name */
    public Ou1 f9274a;
    public AK0 b;
    public AbstractC3841n80 c;
    public ViewPager2 d;
    public long e = -1;
    public final /* synthetic */ AbstractC1664aT f;

    public YS(AbstractC1664aT aTVar) {
        this.f = aTVar;
    }

    public final ViewPager2 a(RecyclerView recyclerView) {
        ViewParent parent = recyclerView.getParent();
        if (parent instanceof ViewPager2) {
            return (ViewPager2) parent;
        }
        throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
    }

    public void b(boolean z) {
        AbstractComponentCallbacksC3550lS lSVar;
        if (!this.f.z() && this.d.R.f == 0 && !this.f.K.g()) {
            Objects.requireNonNull(this.f);
            int i = this.d.f9488J;
            Objects.requireNonNull(this.f);
            if (i < 2) {
                Objects.requireNonNull(this.f);
                long j = (long) i;
                if ((j != this.e || z) && (lSVar = (AbstractComponentCallbacksC3550lS) this.f.K.e(j)) != null && lSVar.U()) {
                    this.e = j;
                    C0317Fe fe = new C0317Fe(this.f.f9431J);
                    AbstractComponentCallbacksC3550lS lSVar2 = null;
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < this.f.K.k(); i2++) {
                        long h = this.f.K.h(i2);
                        AbstractComponentCallbacksC3550lS lSVar3 = (AbstractComponentCallbacksC3550lS) this.f.K.l(i2);
                        if (lSVar3.U()) {
                            if (h != this.e) {
                                EnumC3328k80 k80 = EnumC3328k80.STARTED;
                                fe.r(lSVar3, k80);
                                arrayList.add(this.f.O.a(lSVar3, k80));
                            } else {
                                lSVar2 = lSVar3;
                            }
                            lSVar3.X0(h == this.e);
                        }
                    }
                    if (lSVar2 != null) {
                        EnumC3328k80 k802 = EnumC3328k80.RESUMED;
                        fe.r(lSVar2, k802);
                        arrayList.add(this.f.O.a(lSVar2, k802));
                    }
                    if (!fe.f8026a.isEmpty()) {
                        fe.h();
                        Collections.reverse(arrayList);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            this.f.O.b((List) it.next());
                        }
                    }
                }
            }
        }
    }
}
