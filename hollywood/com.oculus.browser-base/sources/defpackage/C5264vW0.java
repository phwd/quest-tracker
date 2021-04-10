package defpackage;

import J.N;
import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.signin.ui.PersonalizedSigninPromoView;

/* renamed from: vW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5264vW0 {

    /* renamed from: a  reason: collision with root package name */
    public C3522lG f11482a;
    public ViewTreeObserver$OnPreDrawListenerC1587a00 b;
    public final C1387Ws0 c = new C1387Ws0(new C4243pW0(this));
    public final int d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final int o;
    public final int p;
    public final BV0 q;
    public boolean r;
    public boolean s;

    public C5264vW0(int i2, BV0 bv0) {
        this.d = i2;
        this.q = bv0;
        if (i2 == 3) {
            this.e = "signin_promo_impressions_count_settings";
            this.f = "Signin_Impression_FromSettings";
            this.g = "Signin_ImpressionWithAccount_FromSettings";
            this.i = "Signin_SigninWithDefault_FromSettings";
            this.j = "Signin_SigninNotDefault_FromSettings";
            this.k = "Signin_SigninNewAccountNoExistingAccount_FromSettings";
            this.h = "Signin_ImpressionWithNoAccount_FromSettings";
            this.l = "MobileSignInPromo.SettingsManager.ImpressionsTilDismiss";
            this.m = "MobileSignInPromo.SettingsManager.ImpressionsTilSigninButtons";
            this.n = "MobileSignInPromo.SettingsManager.ImpressionsTilXButton";
            this.o = R.string.f62220_resource_name_obfuscated_RES_2131953539;
            this.p = R.string.f62230_resource_name_obfuscated_RES_2131953540;
        } else if (i2 == 9) {
            this.e = "signin_promo_impressions_count_bookmarks";
            this.f = "Signin_Impression_FromBookmarkManager";
            this.g = "Signin_ImpressionWithAccount_FromBookmarkManager";
            this.h = "Signin_ImpressionWithNoAccount_FromBookmarkManager";
            this.i = "Signin_SigninWithDefault_FromBookmarkManager";
            this.j = "Signin_SigninNotDefault_FromBookmarkManager";
            this.k = "Signin_SigninNewAccountNoExistingAccount_FromBookmarkManager";
            this.l = "MobileSignInPromo.BookmarkManager.ImpressionsTilDismiss";
            this.m = "MobileSignInPromo.BookmarkManager.ImpressionsTilSigninButtons";
            this.n = "MobileSignInPromo.BookmarkManager.ImpressionsTilXButton";
            this.o = R.string.f62160_resource_name_obfuscated_RES_2131953533;
            this.p = R.string.f62170_resource_name_obfuscated_RES_2131953534;
        } else if (i2 == 16) {
            this.e = null;
            this.f = "Signin_Impression_FromRecentTabs";
            this.g = "Signin_ImpressionWithAccount_FromRecentTabs";
            this.h = "Signin_ImpressionWithNoAccount_FromRecentTabs";
            this.i = "Signin_SigninWithDefault_FromRecentTabs";
            this.j = "Signin_SigninNotDefault_FromRecentTabs";
            this.k = "Signin_SigninNewAccountNoExistingAccount_FromRecentTabs";
            this.l = null;
            this.m = null;
            this.n = null;
            this.o = R.string.f62200_resource_name_obfuscated_RES_2131953537;
            this.p = R.string.f62210_resource_name_obfuscated_RES_2131953538;
        } else if (i2 == 20) {
            this.e = "Chrome.SigninPromo.NTPImpressions";
            this.f = "Signin_Impression_FromNTPContentSuggestions";
            this.g = "Signin_ImpressionWithAccount_FromNTPContentSuggestions";
            this.h = "Signin_ImpressionWithNoAccount_FromNTPContentSuggestions";
            this.i = "Signin_SigninWithDefault_FromNTPContentSuggestions";
            this.j = "Signin_SigninNotDefault_FromNTPContentSuggestions";
            this.k = "Signin_SigninNewAccountNoExistingAccount_FromNTPContentSuggestions";
            this.l = null;
            this.m = null;
            this.n = null;
            this.o = R.string.f62180_resource_name_obfuscated_RES_2131953535;
            this.p = R.string.f62190_resource_name_obfuscated_RES_2131953536;
        } else {
            throw new IllegalArgumentException(AbstractC2531fV.w("Unexpected value for access point: ", i2));
        }
    }

    public static boolean b(int i2) {
        PU0 pu0 = NU0.f8549a;
        if (i2 == 3) {
            return pu0.f("signin_promo_impressions_count_settings", 0) < 20;
        }
        if (i2 == 9) {
            return pu0.f("signin_promo_impressions_count_bookmarks", 0) < 20;
        }
        if (i2 == 16) {
            return true;
        }
        if (i2 != 20) {
            return false;
        }
        return pu0.f("Chrome.SigninPromo.NTPImpressions", 0) < N.M37SqSAy("EnhancedProtectionPromoCard", "MaxSigninPromoImpressions", Integer.MAX_VALUE);
    }

    public final int a() {
        return NU0.f8549a.e(this.e);
    }

    public final void c() {
        this.s = true;
        String str = this.m;
        if (str != null) {
            AbstractC3364kK0.c(str, a());
        }
    }

    public final void d(Context context, PersonalizedSigninPromoView personalizedSigninPromoView, int i2) {
        ViewGroup.LayoutParams layoutParams = personalizedSigninPromoView.F.getLayoutParams();
        layoutParams.height = context.getResources().getDimensionPixelSize(i2);
        layoutParams.width = context.getResources().getDimensionPixelSize(i2);
        personalizedSigninPromoView.F.setLayoutParams(layoutParams);
    }

    public void e(Context context, PersonalizedSigninPromoView personalizedSigninPromoView, C3522lG lGVar, AbstractC5094uW0 uw0) {
        this.f11482a = lGVar;
        this.r = true;
        ViewTreeObserver$OnPreDrawListenerC1587a00 a00 = new ViewTreeObserver$OnPreDrawListenerC1587a00(personalizedSigninPromoView);
        this.b = a00;
        a00.a(this.c);
        C3522lG lGVar2 = this.f11482a;
        if (lGVar2 == null) {
            personalizedSigninPromoView.F.setImageResource(R.drawable.f28740_resource_name_obfuscated_RES_2131230914);
            d(context, personalizedSigninPromoView, R.dimen.f25090_resource_name_obfuscated_RES_2131166128);
            personalizedSigninPromoView.I.setText(this.p);
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                personalizedSigninPromoView.f10765J.setText(R.string.f62990_resource_name_obfuscated_RES_2131953616);
            } else {
                personalizedSigninPromoView.f10765J.setText(R.string.f61930_resource_name_obfuscated_RES_2131953510);
            }
            personalizedSigninPromoView.f10765J.setOnClickListener(new View$OnClickListenerC4584rW0(this, context));
            personalizedSigninPromoView.K.setVisibility(8);
        } else {
            personalizedSigninPromoView.F.setImageDrawable(lGVar2.b);
            d(context, personalizedSigninPromoView, R.dimen.f25080_resource_name_obfuscated_RES_2131166127);
            personalizedSigninPromoView.I.setText(this.o);
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                personalizedSigninPromoView.f10765J.setText(R.string.f62990_resource_name_obfuscated_RES_2131953616);
            } else {
                Object[] objArr = new Object[1];
                C3522lG lGVar3 = this.f11482a;
                String str = lGVar3.d;
                if (str == null) {
                    str = lGVar3.a();
                }
                objArr[0] = str;
                personalizedSigninPromoView.f10765J.setText(context.getString(R.string.f62150_resource_name_obfuscated_RES_2131953532, objArr));
            }
            personalizedSigninPromoView.f10765J.setOnClickListener(new View$OnClickListenerC4754sW0(this, context));
            personalizedSigninPromoView.K.setText(R.string.f62140_resource_name_obfuscated_RES_2131953531);
            personalizedSigninPromoView.K.setOnClickListener(new View$OnClickListenerC4924tW0(this, context));
            personalizedSigninPromoView.K.setVisibility(0);
        }
        if (uw0 != null) {
            personalizedSigninPromoView.G.setVisibility(0);
            personalizedSigninPromoView.G.setOnClickListener(new View$OnClickListenerC4414qW0(this, uw0));
            return;
        }
        personalizedSigninPromoView.G.setVisibility(8);
    }
}
