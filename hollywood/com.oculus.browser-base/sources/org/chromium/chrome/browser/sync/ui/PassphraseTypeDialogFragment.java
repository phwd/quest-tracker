package org.chromium.chrome.browser.sync.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PassphraseTypeDialogFragment extends OE implements DialogInterface.OnClickListener, AdapterView.OnItemClickListener {
    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        String str;
        View inflate = u().getLayoutInflater().inflate(R.layout.f41690_resource_name_obfuscated_RES_2131624478, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.passphrase_types);
        int l1 = l1();
        int i = 3;
        if (l1 == 3) {
            TextViewWithClickableSpans textViewWithClickableSpans = new TextViewWithClickableSpans(u());
            textViewWithClickableSpans.setPadding(0, I().getDimensionPixelSize(R.dimen.f25330_resource_name_obfuscated_RES_2131166152), 0, 0);
            textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
            Activity u = u();
            textViewWithClickableSpans.setText(FY0.a(u.getString(R.string.f62870_resource_name_obfuscated_RES_2131953604), new EY0("<resetlink>", "</resetlink>", new C3978nx0(this, u))));
            listView.addFooterView(textViewWithClickableSpans);
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 2;
        if (l1 == 2 || l1 == 3) {
            arrayList.add(Integer.valueOf(l1));
            arrayList.add(1);
        } else {
            arrayList.add(3);
            arrayList.add(Integer.valueOf(l1));
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        int i3 = 0;
        while (i3 < size) {
            int intValue = ((Integer) arrayList.get(i3)).intValue();
            if (!(intValue == 0 || intValue == 1)) {
                if (intValue == i2) {
                    str = String.format(O(R.string.f62920_resource_name_obfuscated_RES_2131953609), DateFormat.getDateInstance(i2).format(new Date(this.K.getLong("arg_passphrase_time"))));
                } else if (intValue == i) {
                    str = O(R.string.f62900_resource_name_obfuscated_RES_2131953607);
                } else if (intValue != 4) {
                    str = "";
                }
                strArr[i3] = str;
                i3++;
                i = 3;
                i2 = 2;
            }
            str = O(R.string.f62930_resource_name_obfuscated_RES_2131953610);
            strArr[i3] = str;
            i3++;
            i = 3;
            i2 = 2;
        }
        C4149ox0 ox0 = new C4149ox0(this, arrayList, strArr, null);
        listView.setAdapter((ListAdapter) ox0);
        listView.setId(R.id.passphrase_type_list);
        listView.setOnItemClickListener(this);
        listView.setDividerHeight(0);
        listView.setSelection(ox0.F.indexOf(Integer.valueOf(l1)));
        C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, this);
        e4Var.g(R.string.f62940_resource_name_obfuscated_RES_2131953611);
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        return e4Var.a();
    }

    public int l1() {
        int i = this.K.getInt("arg_current_type", 5);
        if (i <= 4) {
            return i;
        }
        throw new IllegalStateException("Unable to find argument with current type.");
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            f1(false, false);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        int l1 = l1();
        int i2 = (int) j;
        if (((ArrayList) AbstractC1757ax0.a(l1, this.K.getBoolean("arg_is_encrypt_everything_allowed"))).contains(Integer.valueOf(i2))) {
            if (i2 != l1) {
                ManageSyncSettings manageSyncSettings = (ManageSyncSettings) ((AbstractC4320px0) R());
                if (manageSyncSettings.H0.h()) {
                    manageSyncSettings.H0.g();
                    manageSyncSettings.H0.o();
                    C0317Fe fe = new C0317Fe(manageSyncSettings.W);
                    PassphraseCreationDialogFragment passphraseCreationDialogFragment = new PassphraseCreationDialogFragment();
                    passphraseCreationDialogFragment.b1(manageSyncSettings, -1);
                    passphraseCreationDialogFragment.j1(fe, "custom_password");
                }
            }
            f1(false, false);
        }
    }
}
