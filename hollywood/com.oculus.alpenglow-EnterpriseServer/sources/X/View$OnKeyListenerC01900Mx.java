package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.oculus.alpenglow.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.0Mx  reason: invalid class name and case insensitive filesystem */
public final class View$OnKeyListenerC01900Mx extends AbstractC04220eS implements AbstractC000603b, PopupWindow.OnDismissListener, View.OnKeyListener {
    public ViewTreeObserver A00;
    public boolean A01;
    public int A02 = 0;
    public int A03;
    public View A04;
    public View A05;
    public int A06 = 0;
    public int A07;
    public int A08;
    public PopupWindow.OnDismissListener A09;
    public AbstractC000503a A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public boolean A0E;
    public final Handler A0F;
    public final ViewTreeObserver.OnGlobalLayoutListener A0G = new AnonymousClass03O(this);
    public final int A0H;
    public final int A0I;
    public final Context A0J;
    public final AbstractC004304x A0K = new C04300ed(this);
    public final List<AnonymousClass03R> A0L = new ArrayList();
    public final int A0M;
    public final View.OnAttachStateChangeListener A0N = new AnonymousClass03P(this);
    public final List<C04280eZ> A0O = new ArrayList();
    public final boolean A0P;

    @Override // X.AbstractC04220eS
    public final void A03(int i) {
        this.A0C = true;
        this.A07 = i;
    }

    @Override // X.AbstractC04220eS
    public final void A04(int i) {
        this.A0D = true;
        this.A08 = i;
    }

    @Override // X.AbstractC04220eS
    public final boolean A0A() {
        return false;
    }

    @Override // X.AbstractC000603b
    public final boolean A2p() {
        return false;
    }

    private void A00(@NonNull C04280eZ r18) {
        AnonymousClass03R r2;
        View view;
        Rect rect;
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        AnonymousClass03U r14;
        int i5;
        int firstVisiblePosition;
        Context context = this.A0J;
        LayoutInflater from = LayoutInflater.from(context);
        AnonymousClass03U r10 = new AnonymousClass03U(r18, from, this.A0P, R.layout.abc_cascading_menu_item_layout);
        if (!A5a() && this.A0B) {
            r10.A01 = true;
        } else if (A5a()) {
            int size = r18.size();
            boolean z2 = false;
            int i6 = 0;
            while (true) {
                if (i6 >= size) {
                    break;
                }
                MenuItem item = r18.getItem(i6);
                if (item.isVisible() && item.getIcon() != null) {
                    z2 = true;
                    break;
                }
                i6++;
            }
            r10.A01 = z2;
        }
        int A012 = AbstractC04220eS.A01(r10, context, this.A0M);
        C01840Md r4 = new C01840Md(context, this.A0H, this.A0I);
        r4.A00 = this.A0K;
        r4.A08 = this;
        r4.A0A.setOnDismissListener(this);
        r4.A07 = this.A04;
        r4.A01 = this.A02;
        r4.A0E = true;
        r4.A0A.setFocusable(true);
        r4.A0A.setInputMethodMode(2);
        r4.A7j(r10);
        r4.A01(A012);
        r4.A01 = this.A02;
        List<AnonymousClass03R> list = this.A0L;
        if (list.size() > 0) {
            r2 = list.get(list.size() - 1);
            C04280eZ r11 = r2.A01;
            int size2 = r11.size();
            int i7 = 0;
            while (true) {
                if (i7 >= size2) {
                    break;
                }
                MenuItem item2 = r11.getItem(i7);
                if (!item2.hasSubMenu() || r18 != item2.getSubMenu()) {
                    i7++;
                } else {
                    ListView A3u = r2.A02.A3u();
                    ListAdapter adapter = A3u.getAdapter();
                    int i8 = 0;
                    if (adapter instanceof HeaderViewListAdapter) {
                        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                        i5 = headerViewListAdapter.getHeadersCount();
                        r14 = (AnonymousClass03U) headerViewListAdapter.getWrappedAdapter();
                    } else {
                        r14 = (AnonymousClass03U) adapter;
                        i5 = 0;
                    }
                    int count = r14.getCount();
                    while (true) {
                        if (i8 >= count) {
                            break;
                        } else if (item2 != r14.getItem(i8)) {
                            i8++;
                        } else if (i8 != -1 && (firstVisiblePosition = (i8 + i5) - A3u.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < A3u.getChildCount()) {
                            view = A3u.getChildAt(firstVisiblePosition);
                        }
                    }
                }
            }
            view = null;
        } else {
            r2 = null;
            view = null;
        }
        if (view != null) {
            r4.A02();
            r4.A0A.setEnterTransition(null);
            ListView A3u2 = list.get(list.size() - 1).A02.A3u();
            int[] iArr = new int[2];
            A3u2.getLocationOnScreen(iArr);
            Rect rect2 = new Rect();
            this.A05.getWindowVisibleDisplayFrame(rect2);
            if (this.A03 != 1 ? iArr[0] - A012 >= 0 : iArr[0] + A3u2.getWidth() + A012 > rect2.right) {
                i = 0;
                z = false;
            } else {
                i = 1;
                z = true;
            }
            this.A03 = i;
            if (Build.VERSION.SDK_INT >= 26) {
                r4.A07 = view;
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr2 = new int[2];
                this.A04.getLocationOnScreen(iArr2);
                int[] iArr3 = new int[2];
                view.getLocationOnScreen(iArr3);
                if ((this.A02 & 7) == 5) {
                    iArr2[0] = iArr2[0] + this.A04.getWidth();
                    iArr3[0] = iArr3[0] + view.getWidth();
                }
                i2 = iArr3[0] - iArr2[0];
                i3 = iArr3[1] - iArr2[1];
            }
            if ((this.A02 & 5) == 5) {
                if (!z) {
                    A012 = view.getWidth();
                }
                i4 = i2 + A012;
                r4.A7u(i4);
                r4.A0G = true;
                r4.A0F = true;
                r4.A8E(i3);
            } else if (z) {
                A012 = view.getWidth();
                i4 = i2 + A012;
                r4.A7u(i4);
                r4.A0G = true;
                r4.A0F = true;
                r4.A8E(i3);
            }
            i4 = i2 - A012;
            r4.A7u(i4);
            r4.A0G = true;
            r4.A0F = true;
            r4.A8E(i3);
        } else {
            if (this.A0C) {
                r4.A7u(this.A07);
            }
            if (this.A0D) {
                r4.A8E(this.A08);
            }
            Rect rect3 = super.A00;
            if (rect3 != null) {
                rect = new Rect(rect3);
            } else {
                rect = null;
            }
            r4.A06 = rect;
        }
        list.add(new AnonymousClass03R(r4, r18, this.A03));
        r4.A8P();
        ListView A3u3 = r4.A3u();
        A3u3.setOnKeyListener(this);
        if (r2 == null && this.A0E && r18.A05 != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) A3u3, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(r18.A05);
            A3u3.addHeaderView(frameLayout, null, false);
            r4.A8P();
        }
    }

    @Override // X.AbstractC04220eS
    public final void A02(int i) {
        if (this.A06 != i) {
            this.A06 = i;
            this.A02 = Gravity.getAbsoluteGravity(i, this.A04.getLayoutDirection());
        }
    }

    @Override // X.AbstractC04220eS
    public final void A05(@NonNull View view) {
        if (this.A04 != view) {
            this.A04 = view;
            this.A02 = Gravity.getAbsoluteGravity(this.A06, view.getLayoutDirection());
        }
    }

    @Override // X.AbstractC04220eS
    public final void A07(C04280eZ r2) {
        r2.A0E(this, this.A0J);
        if (A5a()) {
            A00(r2);
        } else {
            this.A0O.add(r2);
        }
    }

    @Override // X.AbstractC000903e
    public final ListView A3u() {
        List<AnonymousClass03R> list = this.A0L;
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1).A02.A3u();
    }

    @Override // X.AbstractC000903e
    public final boolean A5a() {
        List<AnonymousClass03R> list = this.A0L;
        if (list.size() <= 0 || !list.get(0).A02.A5a()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC000603b
    public final void A5x(C04280eZ r7, boolean z) {
        List<AnonymousClass03R> list = this.A0L;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (r7 == list.get(i).A01) {
                if (i >= 0) {
                    int i2 = i + 1;
                    if (i2 < list.size()) {
                        list.get(i2).A01.A0F(false);
                    }
                    AnonymousClass03R remove = list.remove(i);
                    remove.A01.A0D(this);
                    if (this.A01) {
                        C01840Md r1 = remove.A02;
                        r1.A0A.setExitTransition(null);
                        r1.A0A.setAnimationStyle(0);
                    }
                    remove.A02.dismiss();
                    int size2 = list.size();
                    if (size2 > 0) {
                        this.A03 = list.get(size2 - 1).A00;
                    } else {
                        int i3 = 1;
                        if (this.A04.getLayoutDirection() == 1) {
                            i3 = 0;
                        }
                        this.A03 = i3;
                        if (size2 == 0) {
                            dismiss();
                            AbstractC000503a r12 = this.A0A;
                            if (r12 != null) {
                                r12.A5x(r7, true);
                            }
                            ViewTreeObserver viewTreeObserver = this.A00;
                            if (viewTreeObserver != null) {
                                if (viewTreeObserver.isAlive()) {
                                    this.A00.removeGlobalOnLayoutListener(this.A0G);
                                }
                                this.A00 = null;
                            }
                            this.A05.removeOnAttachStateChangeListener(this.A0N);
                            this.A09.onDismiss();
                            return;
                        }
                    }
                    if (z) {
                        list.get(0).A01.A0F(false);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
        }
    }

    @Override // X.AbstractC000603b
    public final boolean A6e(SubMenuC01890Mu r5) {
        Iterator<AnonymousClass03R> it = this.A0L.iterator();
        while (true) {
            if (it.hasNext()) {
                AnonymousClass03R next = it.next();
                if (r5 == next.A01) {
                    next.A02.A3u().requestFocus();
                    break;
                }
            } else if (!r5.hasVisibleItems()) {
                return false;
            } else {
                A07(r5);
                AbstractC000503a r0 = this.A0A;
                if (r0 != null) {
                    r0.A6L(r5);
                    return true;
                }
            }
        }
        return true;
    }

    @Override // X.AbstractC000603b
    public final void A8k(boolean z) {
        for (AnonymousClass03R r0 : this.A0L) {
            ListAdapter adapter = r0.A02.A3u().getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((AnonymousClass03U) adapter).notifyDataSetChanged();
        }
    }

    @Override // X.AbstractC000903e
    public final void dismiss() {
        List<AnonymousClass03R> list = this.A0L;
        int size = list.size();
        if (size > 0) {
            AnonymousClass03R[] r2 = (AnonymousClass03R[]) list.toArray(new AnonymousClass03R[size]);
            while (true) {
                size--;
                if (size >= 0) {
                    AnonymousClass03R r1 = r2[size];
                    if (r1.A02.A5a()) {
                        r1.A02.dismiss();
                    }
                } else {
                    return;
                }
            }
        }
    }

    public final void onDismiss() {
        List<AnonymousClass03R> list = this.A0L;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass03R r1 = list.get(i);
            if (!r1.A02.A5a()) {
                r1.A01.A0F(false);
                return;
            }
        }
    }

    public View$OnKeyListenerC01900Mx(@NonNull Context context, @NonNull View view, @AttrRes int i, @StyleRes int i2, boolean z) {
        this.A0J = context;
        this.A04 = view;
        this.A0H = i;
        this.A0I = i2;
        this.A0P = z;
        this.A0B = false;
        this.A03 = view.getLayoutDirection() == 1 ? 0 : 1;
        Resources resources = context.getResources();
        this.A0M = Math.max(resources.getDisplayMetrics().widthPixels >> 1, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.A0F = new Handler();
    }

    @Override // X.AbstractC000903e
    public final void A8P() {
        if (!A5a()) {
            List<C04280eZ> list = this.A0O;
            for (C04280eZ r0 : list) {
                A00(r0);
            }
            list.clear();
            View view = this.A04;
            this.A05 = view;
            if (view != null) {
                boolean z = false;
                if (this.A00 == null) {
                    z = true;
                }
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                this.A00 = viewTreeObserver;
                if (z) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.A0G);
                }
                this.A05.addOnAttachStateChangeListener(this.A0N);
            }
        }
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // X.AbstractC04220eS
    public final void A06(PopupWindow.OnDismissListener onDismissListener) {
        this.A09 = onDismissListener;
    }

    @Override // X.AbstractC04220eS
    public final void A08(boolean z) {
        this.A0B = z;
    }

    @Override // X.AbstractC04220eS
    public final void A09(boolean z) {
        this.A0E = z;
    }

    @Override // X.AbstractC000603b
    public final void A7m(AbstractC000503a r1) {
        this.A0A = r1;
    }
}
