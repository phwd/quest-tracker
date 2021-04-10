package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* renamed from: fQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC2522fQ0 implements View.OnClickListener {
    public final /* synthetic */ SearchActivity F;

    public View$OnClickListenerC2522fQ0(SearchActivity searchActivity) {
        this.F = searchActivity;
    }

    public void onClick(View view) {
        SearchActivity searchActivity = this.F;
        Object obj = SearchActivity.o0;
        searchActivity.finish();
        searchActivity.overridePendingTransition(0, R.anim.f130_resource_name_obfuscated_RES_2130771981);
    }
}
