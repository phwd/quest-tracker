package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
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
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1sd  reason: invalid class name and case insensitive filesystem */
public final class View$OnKeyListenerC11671sd extends AnonymousClass1sY implements AnonymousClass1t6, PopupWindow.OnDismissListener, View.OnKeyListener {
    public ViewTreeObserver A00;
    public boolean A01;
    public int A02 = 0;
    public int A03;
    public int A04 = 0;
    public int A05;
    public int A06;
    public View A07;
    public View A08;
    public PopupWindow.OnDismissListener A09;
    public boolean A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public AbstractC11941tc A0E;
    public final Handler A0F;
    public final ViewTreeObserver.OnGlobalLayoutListener A0G = new ViewTreeObserver$OnGlobalLayoutListenerC11801sr(this);
    public final int A0H;
    public final int A0I;
    public final Context A0J;
    public final AnonymousClass1tS A0K = new C11851sw(this);
    public final List<C11581sN> A0L = new ArrayList();
    public final List<AnonymousClass1tW> A0M = new ArrayList();
    public final int A0N;
    public final View.OnAttachStateChangeListener A0O = new AnonymousClass1tH(this);
    public final boolean A0P;

    public static void A01(@NonNull View$OnKeyListenerC11671sd r15, C11581sN r16) {
        AnonymousClass1tW r2;
        View view;
        Rect rect;
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        C11661sc r14;
        int i5;
        int firstVisiblePosition;
        Context context = r15.A0J;
        LayoutInflater from = LayoutInflater.from(context);
        C11661sc r10 = new C11661sc(r16, from, r15.A0P, R.layout.abc_cascading_menu_item_layout);
        if (!r15.A6B() && r15.A0A) {
            r10.A01 = true;
        } else if (r15.A6B()) {
            int size = r16.size();
            boolean z2 = false;
            int i6 = 0;
            while (true) {
                if (i6 >= size) {
                    break;
                }
                MenuItem item = r16.getItem(i6);
                if (item.isVisible() && item.getIcon() != null) {
                    z2 = true;
                    break;
                }
                i6++;
            }
            r10.A01 = z2;
        }
        int A002 = AnonymousClass1sY.A00(r10, context, r15.A0N);
        C11651sb r4 = new C11651sb(context, r15.A0H, r15.A0I);
        r4.A00 = r15.A0K;
        r4.A08 = r15;
        r4.A0A.setOnDismissListener(r15);
        r4.A07 = r15.A07;
        r4.A01 = r15.A02;
        r4.A0E = true;
        r4.A0A.setFocusable(true);
        r4.A0A.setInputMethodMode(2);
        r4.A9e(r10);
        r4.A00(A002);
        r4.A01 = r15.A02;
        List<AnonymousClass1tW> list = r15.A0M;
        if (list.size() > 0) {
            r2 = list.get(list.size() - 1);
            C11581sN r11 = r2.A01;
            int size2 = r11.size();
            int i7 = 0;
            while (true) {
                if (i7 >= size2) {
                    break;
                }
                MenuItem item2 = r11.getItem(i7);
                if (!item2.hasSubMenu() || r16 != item2.getSubMenu()) {
                    i7++;
                } else {
                    ListView A4G = r2.A02.A4G();
                    ListAdapter adapter = A4G.getAdapter();
                    int i8 = 0;
                    if (adapter instanceof HeaderViewListAdapter) {
                        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                        i5 = headerViewListAdapter.getHeadersCount();
                        r14 = (C11661sc) headerViewListAdapter.getWrappedAdapter();
                    } else {
                        r14 = (C11661sc) adapter;
                        i5 = 0;
                    }
                    int count = r14.getCount();
                    while (true) {
                        if (i8 >= count) {
                            break;
                        } else if (item2 != r14.getItem(i8)) {
                            i8++;
                        } else if (i8 != -1 && (firstVisiblePosition = (i8 + i5) - A4G.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < A4G.getChildCount()) {
                            view = A4G.getChildAt(firstVisiblePosition);
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
            r4.A01();
            r4.A0A.setEnterTransition(null);
            ListView A4G2 = list.get(list.size() - 1).A02.A4G();
            int[] iArr = new int[2];
            A4G2.getLocationOnScreen(iArr);
            Rect rect2 = new Rect();
            r15.A08.getWindowVisibleDisplayFrame(rect2);
            if (r15.A03 != 1 ? iArr[0] - A002 >= 0 : iArr[0] + A4G2.getWidth() + A002 > rect2.right) {
                i = 0;
                z = false;
            } else {
                i = 1;
                z = true;
            }
            r15.A03 = i;
            if (Build.VERSION.SDK_INT >= 26) {
                r4.A07 = view;
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr2 = new int[2];
                r15.A07.getLocationOnScreen(iArr2);
                int[] iArr3 = new int[2];
                view.getLocationOnScreen(iArr3);
                if ((r15.A02 & 7) == 5) {
                    iArr2[0] = iArr2[0] + r15.A07.getWidth();
                    iArr3[0] = iArr3[0] + view.getWidth();
                }
                i2 = iArr3[0] - iArr2[0];
                i3 = iArr3[1] - iArr2[1];
            }
            if ((r15.A02 & 5) == 5) {
                if (!z) {
                    A002 = view.getWidth();
                }
                i4 = i2 + A002;
                r4.A9u(i4);
                r4.A0G = true;
                r4.A0F = true;
                r4.AAG(i3);
            } else if (z) {
                A002 = view.getWidth();
                i4 = i2 + A002;
                r4.A9u(i4);
                r4.A0G = true;
                r4.A0F = true;
                r4.AAG(i3);
            }
            i4 = i2 - A002;
            r4.A9u(i4);
            r4.A0G = true;
            r4.A0F = true;
            r4.AAG(i3);
        } else {
            if (r15.A0B) {
                r4.A9u(r15.A05);
            }
            if (r15.A0C) {
                r4.AAG(r15.A06);
            }
            Rect rect3 = ((AnonymousClass1sY) r15).A00;
            if (rect3 != null) {
                rect = new Rect(rect3);
            } else {
                rect = null;
            }
            r4.A06 = rect;
        }
        list.add(new AnonymousClass1tW(r4, r16, r15.A03));
        r4.AAO();
        ListView A4G3 = r4.A4G();
        A4G3.setOnKeyListener(r15);
        if (r2 == null && r15.A0D && r16.A05 != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) A4G3, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(r16.A05);
            A4G3.addHeaderView(frameLayout, null, false);
            r4.AAO();
        }
    }

    @Override // X.AnonymousClass1t6
    public final boolean A3F() {
        return false;
    }

    @Override // X.AnonymousClass1FA
    public final ListView A4G() {
        List<AnonymousClass1tW> list = this.A0M;
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1).A02.A4G();
    }

    @Override // X.AnonymousClass1FA
    public final boolean A6B() {
        List<AnonymousClass1tW> list = this.A0M;
        if (list.size() <= 0 || !list.get(0).A02.A6B()) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void A6r(C11581sN r7, boolean z) {
        List<AnonymousClass1tW> list = this.A0M;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (r7 == list.get(i).A01) {
                if (i >= 0) {
                    int i2 = i + 1;
                    if (i2 < list.size()) {
                        list.get(i2).A01.A0F(false);
                    }
                    AnonymousClass1tW remove = list.remove(i);
                    remove.A01.A0D(this);
                    if (this.A01) {
                        C11651sb r1 = remove.A02;
                        r1.A0A.setExitTransition(null);
                        r1.A0A.setAnimationStyle(0);
                    }
                    remove.A02.dismiss();
                    int size2 = list.size();
                    if (size2 > 0) {
                        this.A03 = list.get(size2 - 1).A00;
                    } else {
                        int i3 = 1;
                        if (this.A07.getLayoutDirection() == 1) {
                            i3 = 0;
                        }
                        this.A03 = i3;
                        if (size2 == 0) {
                            dismiss();
                            AbstractC11941tc r12 = this.A0E;
                            if (r12 != null) {
                                r12.A6r(r7, true);
                            }
                            ViewTreeObserver viewTreeObserver = this.A00;
                            if (viewTreeObserver != null) {
                                if (viewTreeObserver.isAlive()) {
                                    this.A00.removeGlobalOnLayoutListener(this.A0G);
                                }
                                this.A00 = null;
                            }
                            this.A08.removeOnAttachStateChangeListener(this.A0O);
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

    @Override // X.AnonymousClass1t6
    public final boolean A89(SubMenuC11621sV r5) {
        Iterator<AnonymousClass1tW> it = this.A0M.iterator();
        while (true) {
            if (it.hasNext()) {
                AnonymousClass1tW next = it.next();
                if (r5 == next.A01) {
                    next.A02.A4G().requestFocus();
                    break;
                }
            } else if (!r5.hasVisibleItems()) {
                return false;
            } else {
                r5.A0E(this, this.A0J);
                if (A6B()) {
                    A01(this, r5);
                } else {
                    this.A0L.add(r5);
                }
                AbstractC11941tc r0 = this.A0E;
                if (r0 != null) {
                    r0.A7T(r5);
                    return true;
                }
            }
        }
        return true;
    }

    @Override // X.AnonymousClass1t6
    public final void AAw(boolean z) {
        for (AnonymousClass1tW r0 : this.A0M) {
            ListAdapter adapter = r0.A02.A4G().getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((C11661sc) adapter).notifyDataSetChanged();
        }
    }

    @Override // X.AnonymousClass1FA
    public final void dismiss() {
        List<AnonymousClass1tW> list = this.A0M;
        int size = list.size();
        if (size > 0) {
            AnonymousClass1tW[] r2 = (AnonymousClass1tW[]) list.toArray(new AnonymousClass1tW[size]);
            while (true) {
                size--;
                if (size >= 0) {
                    AnonymousClass1tW r1 = r2[size];
                    if (r1.A02.A6B()) {
                        r1.A02.dismiss();
                    }
                } else {
                    return;
                }
            }
        }
    }

    public final void onDismiss() {
        List<AnonymousClass1tW> list = this.A0M;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass1tW r1 = list.get(i);
            if (!r1.A02.A6B()) {
                r1.A01.A0F(false);
                return;
            }
        }
    }

    public View$OnKeyListenerC11671sd(@NonNull Context context, @NonNull View view, @AttrRes int i, @StyleRes int i2, boolean z) {
        this.A0J = context;
        this.A07 = view;
        this.A0H = i;
        this.A0I = i2;
        this.A0P = z;
        this.A0A = false;
        this.A03 = view.getLayoutDirection() == 1 ? 0 : 1;
        Resources resources = context.getResources();
        this.A0N = Math.max(resources.getDisplayMetrics().widthPixels >> 1, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.A0F = new Handler();
    }

    @Override // X.AnonymousClass1FA
    public final void AAO() {
        if (!A6B()) {
            List<C11581sN> list = this.A0L;
            for (C11581sN r0 : list) {
                A01(this, r0);
            }
            list.clear();
            View view = this.A07;
            this.A08 = view;
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
                this.A08.addOnAttachStateChangeListener(this.A0O);
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

    @Override // X.AnonymousClass1t6
    public final void A9h(AbstractC11941tc r1) {
        this.A0E = r1;
    }
}
