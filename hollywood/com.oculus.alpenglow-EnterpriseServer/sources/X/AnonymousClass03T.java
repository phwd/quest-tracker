package X;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* renamed from: X.03T  reason: invalid class name */
public class AnonymousClass03T extends BaseAdapter {
    public int A00 = -1;
    public final /* synthetic */ C04290ea A01;

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            C04290ea r0 = this.A01;
            view = r0.A03.inflate(r0.A02, viewGroup, false);
        }
        ((AbstractC000703c) view).A5G(getItem(i), 0);
        return view;
    }

    public AnonymousClass03T(C04290ea r2) {
        this.A01 = r2;
        A00();
    }

    private final void A00() {
        C04280eZ r0 = this.A01.A01;
        C04250eW r4 = r0.A04;
        if (r4 != null) {
            r0.A07();
            ArrayList<C04250eW> arrayList = r0.A08;
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
    public final C04250eW getItem(int i) {
        C04280eZ r0 = this.A01.A01;
        r0.A07();
        ArrayList<C04250eW> arrayList = r0.A08;
        int i2 = i + 0;
        int i3 = this.A00;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return arrayList.get(i2);
    }

    public final int getCount() {
        C04280eZ r0 = this.A01.A01;
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
