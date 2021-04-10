package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialListNullRowV2Binding extends AnonymousClass1uW {
    @Bindable
    public boolean mIsFBLinked;
    @NonNull
    public final ConstraintLayout nullRow;
    @NonNull
    public final OCTextView nullRowBody;
    @NonNull
    public final ShellButton nullRowCta;
    @NonNull
    public final ImageView nullRowImage;
    @NonNull
    public final OCTextView nullRowTitle;

    public abstract void setIsFBLinked(boolean z);

    public boolean getIsFBLinked() {
        return this.mIsFBLinked;
    }

    public AnytimeTabletSocialListNullRowV2Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCTextView oCTextView, ShellButton shellButton, ImageView imageView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.nullRow = constraintLayout;
        this.nullRowBody = oCTextView;
        this.nullRowCta = shellButton;
        this.nullRowImage = imageView;
        this.nullRowTitle = oCTextView2;
    }

    public static AnytimeTabletSocialListNullRowV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialListNullRowV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialListNullRowV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_list_null_row_v2);
    }

    @NonNull
    public static AnytimeTabletSocialListNullRowV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialListNullRowV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListNullRowV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialListNullRowV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_list_null_row_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListNullRowV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialListNullRowV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_list_null_row_v2, null, false);
    }
}
