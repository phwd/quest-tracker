package defpackage;

import J.N;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillEditorBase;

/* renamed from: Ad  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0009Ad extends AutofillEditorBase {
    public PersonalDataManager.CreditCard B0;
    public Spinner C0;
    public int D0;

    public void i1(View view) {
        ((Button) view.findViewById(R.id.button_secondary)).setOnClickListener(new View$OnClickListenerC0436Hd(this));
        Button button = (Button) view.findViewById(R.id.button_primary);
        button.setOnClickListener(new View$OnClickListenerC0497Id(this));
        button.setEnabled(false);
        this.C0.setOnItemSelectedListener(this);
        this.C0.setOnTouchListener(this);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View l0 = super.l0(layoutInflater, viewGroup, bundle);
        if (Build.VERSION.SDK_INT >= 26) {
            u().getWindow().getDecorView().setImportantForAutofill(8);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(u(), 17367048);
        arrayAdapter.setDropDownViewResource(17367049);
        PersonalDataManager.AutofillProfile autofillProfile = new PersonalDataManager.AutofillProfile();
        autofillProfile.p = u().getString(R.string.f61160_resource_name_obfuscated_RES_2131953433);
        arrayAdapter.add(autofillProfile);
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        ArrayList f = c.f(N.M6XJvXko(c.b, c), N.M4q3jK16(c.b, c));
        int i = 0;
        for (int i2 = 0; i2 < f.size(); i2++) {
            PersonalDataManager.AutofillProfile autofillProfile2 = (PersonalDataManager.AutofillProfile) f.get(i2);
            if (autofillProfile2.c && !TextUtils.isEmpty(autofillProfile2.getStreetAddress())) {
                arrayAdapter.add(autofillProfile2);
            }
        }
        Spinner spinner = (Spinner) l0.findViewById(R.id.autofill_credit_card_editor_billing_address_spinner);
        this.C0 = spinner;
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        if (arrayAdapter.getCount() == 1) {
            this.C0.setEnabled(false);
        }
        PersonalDataManager c2 = PersonalDataManager.c();
        String str = this.y0;
        Objects.requireNonNull(c2);
        Object obj2 = ThreadUtils.f10596a;
        PersonalDataManager.CreditCard creditCard = (PersonalDataManager.CreditCard) N.M3g2doJx(c2.b, c2, str);
        this.B0 = creditCard;
        if (creditCard != null && !TextUtils.isEmpty(creditCard.getBillingAddressId())) {
            while (true) {
                if (i >= this.C0.getAdapter().getCount()) {
                    break;
                } else if (TextUtils.equals(((PersonalDataManager.AutofillProfile) this.C0.getAdapter().getItem(i)).getGUID(), this.B0.getBillingAddressId())) {
                    this.D0 = i;
                    this.C0.setSelection(i);
                    break;
                } else {
                    i++;
                }
            }
        }
        return l0;
    }
}
