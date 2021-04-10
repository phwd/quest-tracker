package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: pM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4222pM implements YH0 {
    public static void b(C3522lG lGVar, View view) {
        ((ImageView) view.findViewById(R.id.account_image)).setImageDrawable(lGVar.b);
        TextView textView = (TextView) view.findViewById(R.id.account_text_primary);
        TextView textView2 = (TextView) view.findViewById(R.id.account_text_secondary);
        String str = lGVar.c;
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
            textView2.setText(lGVar.f10337a);
            textView2.setVisibility(0);
            return;
        }
        textView.setText(lGVar.f10337a);
        textView2.setVisibility(8);
    }

    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = M1.f8455a;
        C3522lG lGVar = (C3522lG) uh0.g(th0);
        if (kh0 == M1.c) {
            view.setOnClickListener(new View$OnClickListenerC4051oM(uh0, lGVar));
        } else if (kh0 == th0) {
            b(lGVar, view);
        } else {
            QH0 qh0 = M1.b;
            if (kh0 == qh0) {
                ImageView imageView = (ImageView) view.findViewById(R.id.account_selection_mark);
                if (uh0.h(qh0)) {
                    imageView.setImageResource(R.drawable.f29730_resource_name_obfuscated_RES_2131231013);
                    Context context = view.getContext();
                    Object obj4 = K2.f8337a;
                    imageView.setImageTintList(context.getColorStateList(R.color.f11230_resource_name_obfuscated_RES_2131099813));
                    imageView.setVisibility(0);
                    return;
                }
                imageView.setVisibility(8);
                return;
            }
            throw new IllegalArgumentException("Cannot update the view for propertyKey: " + kh0);
        }
    }
}
