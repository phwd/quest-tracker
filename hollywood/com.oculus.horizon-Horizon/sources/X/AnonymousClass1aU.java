package X;

import android.net.Uri;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.config.updater.ConfigUpdaterDumperPlugin;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1aU  reason: invalid class name */
public final class AnonymousClass1aU {
    public static final Uri A00;
    public static final Uri A01;
    public static final Uri A02;
    public static final Uri A03;
    public static final Uri A04;
    public static final Uri A05;
    public static final Uri A06;
    public static final Uri A07;
    public static final Uri A08;
    public static final Uri A09;
    public static final Uri A0A;
    public static final Uri A0B;
    public static final Uri A0C;

    static {
        Uri parse = Uri.parse("content://com.facebook.mobileconfigservice.contentprovider");
        A02 = parse;
        A03 = Uri.withAppendedPath(parse, "get");
        Uri uri = A02;
        A0B = Uri.withAppendedPath(uri, "subscribe");
        A09 = Uri.withAppendedPath(uri, "failed_to_save");
        A0A = Uri.withAppendedPath(uri, "params_map_parse_fail");
        A08 = Uri.withAppendedPath(uri, "exception");
        A00 = Uri.withAppendedPath(uri, "change");
        A01 = Uri.withAppendedPath(uri, "change_remove");
        A0C = Uri.withAppendedPath(uri, ConfigUpdaterDumperPlugin.COMMAND_UPDATE);
        A06 = Uri.withAppendedPath(uri, "get_multiple");
        A04 = Uri.withAppendedPath(uri, "debug_only_get_all_configs");
        A05 = Uri.withAppendedPath(uri, "debug_only_set_overridden_configs");
        A07 = Uri.withAppendedPath(uri, "log");
    }
}
