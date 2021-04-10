package defpackage;

import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: f9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnKeyListenerC2476f9 implements AdapterView.OnItemClickListener, View.OnKeyListener, AbstractC4867t9 {
    public final Menu F;
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9902J;
    public final int[] K;
    public final boolean L;
    public PopupWindow M;
    public ListView N;
    public C4185p9 O;
    public C5887z9 P;
    public View Q;
    public int R = -1;
    public boolean S;
    public AnimatorSet T;
    public long U;
    public boolean V;
    public final Queue W = new ArrayDeque();

    public View$OnKeyListenerC2476f9(Menu menu, int i, C5887z9 z9Var, Resources resources, boolean z) {
        this.F = menu;
        this.G = i;
        this.P = z9Var;
        this.I = resources.getDimensionPixelSize(R.dimen.f20650_resource_name_obfuscated_RES_2131165684);
        this.H = resources.getDimensionPixelSize(R.dimen.f20680_resource_name_obfuscated_RES_2131165687);
        this.f9902J = resources.getDimensionPixelSize(R.dimen.f20660_resource_name_obfuscated_RES_2131165685);
        this.K = new int[2];
        this.L = z;
    }

    public void a() {
        if (b()) {
            this.M.dismiss();
        }
    }

    public boolean b() {
        PopupWindow popupWindow = this.M;
        if (popupWindow == null) {
            return false;
        }
        return popupWindow.isShowing();
    }

    public void c(MenuItem menuItem) {
        boolean z;
        if (menuItem.isEnabled()) {
            int itemId = menuItem.getItemId();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (!this.W.isEmpty() && elapsedRealtime - ((Long) ((Pair) this.W.peek()).second).longValue() > 10000) {
                this.W.remove();
            }
            for (Pair pair : this.W) {
                C5887z9 z9Var = this.P;
                int intValue = ((Integer) pair.first).intValue();
                Objects.requireNonNull((F9) z9Var.M);
                Pair pair2 = (Pair) F9.c.get(Integer.valueOf(itemId));
                int intValue2 = (pair2 == null || !((Set) pair2.first).contains(Integer.valueOf(intValue))) ? -1 : ((Integer) pair2.second).intValue();
                if (intValue2 == -1) {
                    z = false;
                    continue;
                } else {
                    AbstractC3364kK0.g("Mobile.AppMenu.SimilarSelection", intValue2, 4);
                    z = true;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            this.W.add(new Pair(Integer.valueOf(itemId), Long.valueOf(elapsedRealtime)));
            this.V = true;
            a();
            C5887z9 z9Var2 = this.P;
            AbstractC5207v9 v9Var = z9Var2.N;
            int itemId2 = menuItem.getItemId();
            F9 f9 = (F9) z9Var2.M;
            Objects.requireNonNull(f9);
            Bundle bundle = new Bundle();
            if (menuItem.getItemId() == R.id.add_to_homescreen_id || menuItem.getItemId() == R.id.add_to_homescreen_menu_id || menuItem.getItemId() == R.id.install_app_id) {
                bundle.putInt("AppMenuTitleShown", f9.q);
            }
            ((ChromeActivity) v9Var).l1(itemId2, bundle);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        c(this.O.getItem(i));
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (this.N != null && keyEvent.getKeyCode() == 82) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyEvent.startTracking();
                view.getKeyDispatcherState().startTracking(keyEvent, this);
                return true;
            } else if (keyEvent.getAction() == 1) {
                view.getKeyDispatcherState().handleUpEvent(keyEvent);
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    a();
                    return true;
                }
            }
        }
        return false;
    }
}
