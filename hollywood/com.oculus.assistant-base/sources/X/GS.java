package X;

import android.net.Uri;

public final class GS {
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
    public static final Uri A0D;

    static {
        Uri parse = Uri.parse("content://com.facebook.mobileconfigservice.contentprovider");
        A02 = parse;
        A03 = Uri.withAppendedPath(parse, "get");
        Uri uri = A02;
        A0C = Uri.withAppendedPath(uri, "subscribe");
        A0A = Uri.withAppendedPath(uri, "failed_to_save");
        A0B = Uri.withAppendedPath(uri, "params_map_parse_fail");
        A09 = Uri.withAppendedPath(uri, "exception");
        A00 = Uri.withAppendedPath(uri, "change");
        A01 = Uri.withAppendedPath(uri, "change_remove");
        A0D = Uri.withAppendedPath(uri, "update");
        A07 = Uri.withAppendedPath(uri, "get_multiple");
        A04 = Uri.withAppendedPath(uri, "debug_only_get_all_configs");
        A05 = Uri.withAppendedPath(uri, "debug_only_set_overridden_configs");
        A06 = Uri.withAppendedPath(uri, "debug_only_service_command");
        A08 = Uri.withAppendedPath(uri, "log");
    }
}
