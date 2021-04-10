package com.oculus.panelapp.debug;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.panelapp.debug.UserTab;
import com.oculus.vrshell.panels.views.ShellButton;
import java.util.ArrayList;

public class TwoFacMethodSelectAdapter extends RecyclerView.Adapter<TwoFacMethodSelectViewHolder> {
    private static final String TAG = "TwoFacMethodSelectAdapter";
    private final UserTab.TwoFacMethodSelectCallback mCallback;
    private ArrayList<AuthTwoFactorMethod> mItems;
    private String nonce;

    TwoFacMethodSelectAdapter(UserTab.TwoFacMethodSelectCallback twoFacMethodSelectCallback, ArrayList<AuthTwoFactorMethod> arrayList) {
        this.mCallback = twoFacMethodSelectCallback;
        this.mItems = arrayList;
    }

    /* access modifiers changed from: package-private */
    public void setItems(ArrayList<AuthTwoFactorMethod> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        this.mItems = arrayList;
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public void setNonce(String str) {
        this.nonce = str;
    }

    /* access modifiers changed from: package-private */
    public static class TwoFacMethodSelectViewHolder extends RecyclerView.ViewHolder {
        private final ShellButton mLabel;

        TwoFacMethodSelectViewHolder(ShellButton shellButton) {
            super(shellButton);
            this.mLabel = shellButton;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<AuthTwoFactorMethod> arrayList = this.mItems;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TwoFacMethodSelectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TwoFacMethodSelectViewHolder((ShellButton) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.twofac_method_item, viewGroup, false));
    }

    public void onBindViewHolder(TwoFacMethodSelectViewHolder twoFacMethodSelectViewHolder, int i) {
        final AuthTwoFactorMethod authTwoFactorMethod = this.mItems.get(i);
        twoFacMethodSelectViewHolder.mLabel.setText(authTwoFactorMethod.label);
        twoFacMethodSelectViewHolder.mLabel.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.TwoFacMethodSelectAdapter.AnonymousClass1 */

            public void onClick(View view) {
                if (view instanceof ShellButton) {
                    String str = TwoFacMethodSelectAdapter.TAG;
                    Log.d(str, "method id clicked = " + ((ShellButton) view).getText().toString());
                    if (TwoFacMethodSelectAdapter.this.mCallback != null) {
                        TwoFacMethodSelectAdapter.this.mCallback.sendTwoFacCode(TwoFacMethodSelectAdapter.this.nonce, authTwoFactorMethod.id);
                    }
                }
            }
        });
    }
}
