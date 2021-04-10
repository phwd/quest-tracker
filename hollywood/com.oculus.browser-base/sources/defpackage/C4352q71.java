package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.tasks.tab_management.TabGridIphDialogView;

/* renamed from: q71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4352q71 {

    /* renamed from: a  reason: collision with root package name */
    public final View f11118a;
    public final TabGridIphDialogView b;
    public final UH0 c;
    public final C2746gl0 d;
    public final ViewTreeObserver.OnGlobalLayoutListener e;

    public C4352q71(Context context, ViewGroup viewGroup, C2746gl0 gl0) {
        TabGridIphDialogView tabGridIphDialogView = (TabGridIphDialogView) LayoutInflater.from(context).inflate(R.layout.f38860_resource_name_obfuscated_RES_2131624195, (ViewGroup) null, false);
        this.b = tabGridIphDialogView;
        this.d = gl0;
        this.f11118a = viewGroup;
        C4181p71 p71 = new C4181p71(this, gl0);
        Map c2 = UH0.c(AbstractC3258jl0.r);
        OH0 oh0 = AbstractC3258jl0.f10235a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = p71;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(oh0, lh0);
        QH0 qh0 = AbstractC3258jl0.m;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        TH0 th0 = AbstractC3258jl0.g;
        String string = context.getResources().getString(R.string.f56550_resource_name_obfuscated_RES_2131952972);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = string;
        hashMap.put(th0, lh02);
        TH0 th02 = AbstractC3258jl0.f;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = tabGridIphDialogView;
        hashMap.put(th02, lh03);
        this.c = new UH0(c2, null);
        tabGridIphDialogView.L = viewGroup;
        ViewTreeObserver$OnGlobalLayoutListenerC4010o71 o71 = new ViewTreeObserver$OnGlobalLayoutListenerC4010o71(tabGridIphDialogView);
        this.e = o71;
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(o71);
    }
}
