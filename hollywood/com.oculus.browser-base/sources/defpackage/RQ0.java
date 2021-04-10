package defpackage;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.SearchView;
import com.oculus.browser.R;

/* renamed from: RQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class RQ0 {
    public static void a(MenuItem menuItem, Activity activity) {
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.x(null, false);
        searchView.w(true);
        menuItem.collapseActionView();
        e(menuItem, null, activity);
    }

    public static ImageView b(SearchView searchView) {
        return (ImageView) searchView.findViewById(R.id.search_close_btn);
    }

    public static boolean c(MenuItem menuItem, MenuItem menuItem2, String str, Activity activity) {
        if (menuItem.getItemId() != 16908332 || str == null) {
            return false;
        }
        a(menuItem2, activity);
        return true;
    }

    public static void d(MenuItem menuItem, String str, Activity activity, QQ0 qq0) {
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.V.setImeOptions(33554432);
        if (str != null) {
            menuItem.expandActionView();
            searchView.w(false);
            searchView.x(str, false);
            e(menuItem, str, activity);
        }
        menuItem.setOnMenuItemClickListener(new LQ0(menuItem, activity, qq0));
        ((ImageView) searchView.findViewById(R.id.search_close_btn)).setOnClickListener(new MQ0(searchView, menuItem, activity, qq0));
        ((ImageView) searchView.findViewById(R.id.search_close_btn)).addOnLayoutChangeListener(new NQ0(menuItem, searchView, activity));
        searchView.t0 = new OQ0(menuItem, activity, qq0);
        searchView.r0 = new PQ0(menuItem, activity, qq0);
    }

    public static void e(MenuItem menuItem, String str, Activity activity) {
        Drawable drawable;
        ActionMenuView actionMenuView;
        int i = 8;
        boolean z = false;
        b((SearchView) menuItem.getActionView()).setVisibility((str == null || str.equals("")) ? 8 : 0);
        if (activity != null) {
            if (str == null) {
                i = 0;
            }
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.action_bar);
            int childCount = viewGroup.getChildCount();
            while (true) {
                int i2 = childCount - 1;
                drawable = null;
                if (childCount <= 0) {
                    actionMenuView = null;
                    break;
                } else if (viewGroup.getChildAt(i2) instanceof ActionMenuView) {
                    actionMenuView = (ActionMenuView) viewGroup.getChildAt(i2);
                    break;
                } else {
                    childCount = i2;
                }
            }
            if (actionMenuView != null) {
                View childAt = actionMenuView.getChildAt(actionMenuView.getChildCount() - 1);
                if (childAt != null && (childAt instanceof ImageView)) {
                    Drawable drawable2 = ((ImageView) childAt).getDrawable();
                    actionMenuView.t();
                    C4676s2 s2Var = actionMenuView.b0;
                    C4164p2 p2Var = s2Var.N;
                    if (p2Var != null) {
                        drawable = p2Var.getDrawable();
                    } else if (s2Var.P) {
                        drawable = s2Var.O;
                    }
                    if (drawable2 == drawable) {
                        z = true;
                    }
                }
                if (z) {
                    childAt.setVisibility(i);
                }
            }
        }
    }
}
