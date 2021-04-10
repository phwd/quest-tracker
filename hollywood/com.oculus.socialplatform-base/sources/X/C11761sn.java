package X;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* renamed from: X.1sn  reason: invalid class name and case insensitive filesystem */
public class C11761sn extends BaseAdapter {
    public int A00 = -1;
    public final /* synthetic */ C11711si A01;

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            C11711si r0 = this.A01;
            view = r0.A03.inflate(r0.A02, viewGroup, false);
        }
        ((AbstractC11971tg) view).A5i(getItem(i), 0);
        return view;
    }

    public C11761sn(C11711si r2) {
        this.A01 = r2;
        A00();
    }

    private final void A00() {
        C11581sN r0 = this.A01.A01;
        C11601sP r4 = r0.A04;
        if (r4 != null) {
            r0.A07();
            ArrayList<C11601sP> arrayList = r0.A08;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i) == r4) {
                    this.A00 = i;
                    return;
                }
            }
        }
        this.A00 = -1;
    }

    /* renamed from: A01 */
    public final C11601sP getItem(int i) {
        C11581sN r0 = this.A01.A01;
        r0.A07();
        ArrayList<C11601sP> arrayList = r0.A08;
        int i2 = i + 0;
        int i3 = this.A00;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return arrayList.get(i2);
    }

    public final int getCount() {
        C11581sN r0 = this.A01.A01;
        r0.A07();
        int size = r0.A08.size() - 0;
        if (this.A00 >= 0) {
            return size - 1;
        }
        return size;
    }

    public final void notifyDataSetChanged() {
        A00();
        super.notifyDataSetChanged();
    }
}
