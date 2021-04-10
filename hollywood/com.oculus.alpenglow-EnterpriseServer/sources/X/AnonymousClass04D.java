package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.NonNull;
import com.oculus.alpenglow.R;

/* renamed from: X.04D  reason: invalid class name */
public class AnonymousClass04D implements AnonymousClass17H {
    public final int[] A00 = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
    public final int[] A01 = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};
    public final int[] A02 = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
    public final int[] A03 = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};
    public final int[] A04 = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
    public final int[] A05 = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};

    public static boolean A01(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b  */
    @Override // X.AnonymousClass17H
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A8b(@androidx.annotation.NonNull android.content.Context r7, int r8, @androidx.annotation.NonNull android.graphics.drawable.Drawable r9) {
        /*
            r6 = this;
            android.graphics.PorterDuff$Mode r3 = X.AnonymousClass04E.A02
            int[] r0 = r6.A02
            boolean r0 = A01(r0, r8)
            r4 = 16842801(0x1010031, float:2.3693695E-38)
            r2 = -1
            r1 = 0
            r5 = 1
            if (r0 == 0) goto L_0x002f
            r4 = 2130837585(0x7f020051, float:1.7280128E38)
        L_0x0013:
            r0 = -1
        L_0x0014:
            boolean r1 = X.C002704d.A02(r9)
            if (r1 == 0) goto L_0x001e
            android.graphics.drawable.Drawable r9 = r9.mutate()
        L_0x001e:
            int r1 = X.AnonymousClass05V.A01(r7, r4)
            android.graphics.PorterDuffColorFilter r1 = X.AnonymousClass04E.A00(r1, r3)
            r9.setColorFilter(r1)
            if (r0 == r2) goto L_0x002e
            r9.setAlpha(r0)
        L_0x002e:
            return r5
        L_0x002f:
            int[] r0 = r6.A01
            boolean r0 = A01(r0, r8)
            if (r0 == 0) goto L_0x003b
            r4 = 2130837583(0x7f02004f, float:1.7280124E38)
            goto L_0x0013
        L_0x003b:
            int[] r0 = r6.A00
            boolean r0 = A01(r0, r8)
            if (r0 == 0) goto L_0x0046
            android.graphics.PorterDuff$Mode r3 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x0013
        L_0x0046:
            r0 = 2131099689(0x7f060029, float:1.7811738E38)
            if (r8 != r0) goto L_0x0056
            r4 = 16842800(0x1010030, float:2.3693693E-38)
            r0 = 1109603123(0x42233333, float:40.8)
            int r0 = java.lang.Math.round(r0)
            goto L_0x0014
        L_0x0056:
            r0 = 2131099667(0x7f060013, float:1.7811694E38)
            if (r8 == r0) goto L_0x0013
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass04D.A8b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public static void A00(Drawable drawable, int i, PorterDuff.Mode mode) {
        if (C002704d.A02(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(AnonymousClass04E.A00(i, mode));
    }

    @Override // X.AnonymousClass17H
    public final Drawable A1y(@NonNull AnonymousClass17F r3, @NonNull Context context, int i) {
        if (i == R.drawable.abc_cab_background_top_material) {
            return new LayerDrawable(new Drawable[]{r3.A04(context, R.drawable.abc_cab_background_internal_bg), r3.A04(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
        }
        return null;
    }

    @Override // X.AnonymousClass17H
    public final ColorStateList A4f(@NonNull Context context, int i) {
        int i2;
        int i3;
        int i4;
        int[][] iArr;
        int[] iArr2;
        int A012;
        if (i == R.drawable.abc_edit_text_material) {
            i2 = R.color.abc_tint_edittext;
        } else if (i == R.drawable.abc_switch_track_mtrl_alpha) {
            i2 = R.color.abc_tint_switch_track;
        } else {
            if (i == R.drawable.abc_switch_thumb_material) {
                iArr = new int[3][];
                iArr2 = new int[3];
                ColorStateList A022 = AnonymousClass05V.A02(context, R.attr.colorSwitchThumbNormal);
                if (A022 == null || !A022.isStateful()) {
                    iArr[0] = AnonymousClass05V.A02;
                    iArr2[0] = AnonymousClass05V.A00(context, R.attr.colorSwitchThumbNormal);
                    iArr[1] = AnonymousClass05V.A01;
                    iArr2[1] = AnonymousClass05V.A01(context, R.attr.colorControlActivated);
                    iArr[2] = AnonymousClass05V.A03;
                    A012 = AnonymousClass05V.A01(context, R.attr.colorSwitchThumbNormal);
                } else {
                    iArr[0] = AnonymousClass05V.A02;
                    iArr2[0] = A022.getColorForState(iArr[0], 0);
                    iArr[1] = AnonymousClass05V.A01;
                    iArr2[1] = AnonymousClass05V.A01(context, R.attr.colorControlActivated);
                    iArr[2] = AnonymousClass05V.A03;
                    A012 = A022.getDefaultColor();
                }
                iArr2[2] = A012;
            } else {
                if (i == R.drawable.abc_btn_default_mtrl_shape) {
                    i3 = R.attr.colorButtonNormal;
                } else if (i == R.drawable.abc_btn_borderless_material) {
                    i4 = 0;
                    int A013 = AnonymousClass05V.A01(context, R.attr.colorControlHighlight);
                    int A002 = AnonymousClass05V.A00(context, R.attr.colorButtonNormal);
                    int[] iArr3 = AnonymousClass05V.A02;
                    int[] iArr4 = AnonymousClass05V.A05;
                    int A003 = AnonymousClass08O.A00(A013, i4);
                    int[] iArr5 = AnonymousClass05V.A04;
                    int A004 = AnonymousClass08O.A00(A013, i4);
                    iArr = new int[][]{iArr3, iArr4, iArr5, AnonymousClass05V.A03};
                    iArr2 = new int[]{A002, A003, A004, i4};
                } else if (i == R.drawable.abc_btn_colored_material) {
                    i3 = R.attr.colorAccent;
                } else if (i == R.drawable.abc_spinner_mtrl_am_alpha || i == R.drawable.abc_spinner_textfield_background_material) {
                    i2 = R.color.abc_tint_spinner;
                } else if (A01(this.A04, i)) {
                    return AnonymousClass05V.A02(context, R.attr.colorControlNormal);
                } else {
                    if (A01(this.A05, i)) {
                        i2 = R.color.abc_tint_default;
                    } else if (A01(this.A03, i)) {
                        i2 = R.color.abc_tint_btn_checkable;
                    } else if (i != R.drawable.abc_seekbar_thumb_material) {
                        return null;
                    } else {
                        i2 = R.color.abc_tint_seek_thumb;
                    }
                }
                i4 = AnonymousClass05V.A01(context, i3);
                int A0132 = AnonymousClass05V.A01(context, R.attr.colorControlHighlight);
                int A0022 = AnonymousClass05V.A00(context, R.attr.colorButtonNormal);
                int[] iArr32 = AnonymousClass05V.A02;
                int[] iArr42 = AnonymousClass05V.A05;
                int A0032 = AnonymousClass08O.A00(A0132, i4);
                int[] iArr52 = AnonymousClass05V.A04;
                int A0042 = AnonymousClass08O.A00(A0132, i4);
                iArr = new int[][]{iArr32, iArr42, iArr52, AnonymousClass05V.A03};
                iArr2 = new int[]{A0022, A0032, A0042, i4};
            }
            return new ColorStateList(iArr, iArr2);
        }
        return context.getColorStateList(i2);
    }

    @Override // X.AnonymousClass17H
    public final PorterDuff.Mode A4g(int i) {
        if (i == R.drawable.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    @Override // X.AnonymousClass17H
    public final boolean A8a(@NonNull Context context, int i, @NonNull Drawable drawable) {
        LayerDrawable layerDrawable;
        PorterDuff.Mode mode;
        Drawable findDrawableByLayerId;
        int i2;
        if (i == R.drawable.abc_seekbar_track_material) {
            layerDrawable = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(16908288);
            int A012 = AnonymousClass05V.A01(context, R.attr.colorControlNormal);
            mode = AnonymousClass04E.A02;
            A00(findDrawableByLayerId2, A012, mode);
            findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
            i2 = R.attr.colorControlNormal;
        } else if (i != R.drawable.abc_ratingbar_material && i != R.drawable.abc_ratingbar_indicator_material && i != R.drawable.abc_ratingbar_small_material) {
            return false;
        } else {
            layerDrawable = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId3 = layerDrawable.findDrawableByLayerId(16908288);
            int A002 = AnonymousClass05V.A00(context, R.attr.colorControlNormal);
            mode = AnonymousClass04E.A02;
            A00(findDrawableByLayerId3, A002, mode);
            findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
            i2 = R.attr.colorControlActivated;
        }
        A00(findDrawableByLayerId, AnonymousClass05V.A01(context, i2), mode);
        A00(layerDrawable.findDrawableByLayerId(16908301), AnonymousClass05V.A01(context, R.attr.colorControlActivated), mode);
        return true;
    }
}
