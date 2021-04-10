package com.oculus.panelapp.anytimeui.v2.tablet.destinationui;

import androidx.databinding.BindingAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

public class BindingUtils {
    @BindingAdapter({"frescoImageUri"})
    public static void loadImage(SimpleDraweeView simpleDraweeView, String str) {
        simpleDraweeView.setImageURI(str);
    }
}
