package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions;

import java.util.Comparator;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.permissions.-$$Lambda$PermissionsUtil$VjQOKie8SnXkMK9q0n3valbwMYw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PermissionsUtil$VjQOKie8SnXkMK9q0n3valbwMYw implements Comparator {
    public static final /* synthetic */ $$Lambda$PermissionsUtil$VjQOKie8SnXkMK9q0n3valbwMYw INSTANCE = new $$Lambda$PermissionsUtil$VjQOKie8SnXkMK9q0n3valbwMYw();

    private /* synthetic */ $$Lambda$PermissionsUtil$VjQOKie8SnXkMK9q0n3valbwMYw() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((PermissionGroup) obj).getDisplayName().compareTo(((PermissionGroup) obj2).getDisplayName());
    }
}
