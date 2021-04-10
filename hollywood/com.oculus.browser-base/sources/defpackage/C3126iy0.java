package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* renamed from: iy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3126iy0 {

    /* renamed from: a  reason: collision with root package name */
    public final View$OnLayoutChangeListenerC3639ly0 f10178a;
    public UH0 b;

    public C3126iy0(C2746gl0 gl0, View view, AbstractC2400ek ekVar, int i) {
        this.f10178a = new View$OnLayoutChangeListenerC3639ly0(new HH0(AbstractC3258jl0.r), gl0, view, ekVar, i);
    }

    public void a(Context context, C2785gy0 gy0) {
        View view;
        if (gy0.g != null) {
            view = LayoutInflater.from(context).inflate(R.layout.f40400_resource_name_obfuscated_RES_2131624349, (ViewGroup) null);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.f40390_resource_name_obfuscated_RES_2131624348, (ViewGroup) null);
        }
        OH0 oh0 = AbstractC3810my0.d;
        OH0 oh02 = AbstractC3810my0.e;
        Map c = UH0.c(new KH0[]{AbstractC3810my0.f10462a, AbstractC3810my0.b, AbstractC3810my0.c, oh0, oh02});
        String str = gy0.f10038a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = str;
        HashMap hashMap = (HashMap) c;
        hashMap.put(oh0, lh0);
        String str2 = gy0.b;
        C2614fy0[] fy0Arr = gy0.h;
        SpannableString spannableString = new SpannableString(str2);
        for (C2614fy0 fy0 : fy0Arr) {
            spannableString.setSpan(new StyleSpan(1), fy0.f9970a, fy0.b, 17);
        }
        LH0 lh02 = new LH0(null);
        lh02.f8415a = spannableString;
        hashMap.put(oh02, lh02);
        NH0 nh0 = AbstractC3810my0.b;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = R.drawable.f34450_resource_name_obfuscated_RES_2131231485;
        hashMap.put(nh0, jh0);
        OH0 oh03 = AbstractC3810my0.f10462a;
        Runnable runnable = gy0.g;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = runnable;
        UH0 o = AbstractC2531fV.o(hashMap, oh03, lh03, c, null);
        this.b = o;
        View$OnLayoutChangeListenerC3639ly0 ly0 = this.f10178a;
        Objects.requireNonNull(ly0);
        ly0.L = view.getResources();
        ly0.K = o;
        HH0 hh0 = ly0.I;
        hh0.e(AbstractC3258jl0.f, view);
        hh0.e(AbstractC3258jl0.f10235a, new C3468ky0(gy0.e));
        hh0.e(AbstractC3258jl0.b, gy0.f10038a);
        hh0.e(AbstractC3258jl0.g, gy0.c);
        hh0.e(AbstractC3258jl0.j, gy0.d);
        hh0.b(AbstractC3258jl0.q, gy0.f);
        ly0.f10390J = hh0.a();
        ZH0.a(this.b, view, new C2956hy0());
    }

    public void b() {
        View$OnLayoutChangeListenerC3639ly0 ly0 = this.f10178a;
        ly0.K.j(AbstractC3810my0.c, ly0.a(ly0.G.getHeight()));
        UH0 a2 = ly0.I.a();
        ly0.f10390J = a2;
        ly0.F.i(a2, 0, false);
    }
}
