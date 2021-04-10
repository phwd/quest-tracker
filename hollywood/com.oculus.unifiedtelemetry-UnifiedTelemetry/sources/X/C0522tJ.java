package X;

import android.content.Context;

/* renamed from: X.tJ  reason: case insensitive filesystem */
public final class C0522tJ {
    public static final C0310cY A03 = new C0310cY("a", "add", true, "Add event config");
    public static final C0310cY A04 = new C0310cY("b", "blacklist", true, "Blacklist event");
    public static final C0310cY A05 = new C0310cY("r", "remove", false, "Remove the content of DB");
    public static final C0310cY A06 = new C0310cY("c", "clear", false, "Clear sampling config");
    public static final C0310cY A07 = new C0310cY("i", "invalidate-checksum", false, "Invalidate checksum");
    public static final C0310cY A08 = new C0310cY("c", "core", false, "Is core event");
    public static final C0310cY A09 = new C0310cY("h", "high-pri", false, "Is high-pri event");
    public static final C0310cY A0A = new C0310cY("h", "high-pri", false, "Is high-pri upload");
    public static final C0310cY A0B = new C0310cY("l", "list", false, "List configured events");
    public static final C0310cY A0C = new C0310cY("i", "insert", false, "Insert a test event to DB");
    public static final C0310cY A0D = new C0310cY("n", "num", true, "Number of events to log");
    public static final C0310cY A0E = new C0310cY("r", "remove", true, "Remove event config");
    public static final C0310cY A0F = new C0310cY("e", "eventname", true, "Event name to check sampling on");
    public static final C0310cY A0G = new C0310cY("o", "core", false, "whether event is core event.");
    public static final C0310cY A0H = new C0310cY("s", "show", false, "Show the content of DB");
    public static final C0310cY A0I = new C0310cY("n", "number", false, "Show the count of events");
    public static final C0309cX A0J;
    public static final C0309cX A0K;
    public static final C0309cX A0L;
    public static final C0309cX A0M;
    public final Context A00;
    public final Fe A01;
    public final GE A02;

    static {
        C0309cX cXVar = new C0309cX();
        A0L = cXVar;
        cXVar.A00(A08);
        C0309cX cXVar2 = A0L;
        cXVar2.A00(A09);
        cXVar2.A00(A0D);
        C0309cX cXVar3 = new C0309cX();
        A0M = cXVar3;
        cXVar3.A00(A0A);
        C0309cX cXVar4 = new C0309cX();
        A0K = cXVar4;
        cXVar4.A00(A0I);
        C0309cX cXVar5 = A0K;
        cXVar5.A00(A0H);
        cXVar5.A00(A05);
        cXVar5.A00(A0C);
        C0309cX cXVar6 = new C0309cX();
        A0J = cXVar6;
        cXVar6.A00(A06);
        C0309cX cXVar7 = A0J;
        cXVar7.A00(A07);
        cXVar7.A00(A03);
        cXVar7.A00(A0E);
        cXVar7.A00(A04);
        cXVar7.A00(A0B);
        cXVar7.A00(A0F);
        cXVar7.A00(A0G);
        A03.numberOfArgs = 2;
    }

    public C0522tJ(Context context, Fe fe) {
        this.A00 = context;
        this.A01 = fe;
        this.A02 = new GE(context, fe);
    }
}
