package X;

import android.util.Log;
import android.util.SparseArray;
import com.oculus.aidl.OVRServiceInterface;

/* renamed from: X.p3  reason: case insensitive filesystem */
public final class C0928p3 implements Id {
    public Object A00;
    public final SparseArray A01 = new SparseArray(8);
    public final C0935pA A02;

    private Object A00(int i) {
        int i2;
        String str;
        Object obj = this.A00;
        if (obj != null) {
            switch (i) {
                case 1:
                    i2 = 0;
                    str = "string";
                    break;
                case 2:
                case 3:
                    i2 = 1;
                    str = "int";
                    break;
                case 4:
                    i2 = 7;
                    str = "string_array";
                    break;
                case 5:
                case 10:
                    i2 = 6;
                    str = "int_array";
                    break;
                case 6:
                    i2 = 4;
                    str = "double";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    i2 = 5;
                    str = "double_array";
                    break;
                case 8:
                    i2 = 2;
                    str = "bool";
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    i2 = 3;
                    str = "bool_array";
                    break;
                default:
                    throw new IllegalArgumentException(AnonymousClass08.A00("Unknown annotation type: ", i));
            }
            SparseArray sparseArray = this.A01;
            Object obj2 = sparseArray.get(i2);
            if (obj2 != null) {
                return obj2;
            }
            C0847jr A05 = ((C0847jr) obj).A05(str);
            sparseArray.put(i2, A05);
            return A05;
        }
        throw new NullPointerException("Attempting to use visitor without destination");
    }

    @Override // X.Id
    public final void A5X(String str, String str2, int i) {
        if (str2 != null) {
            switch (i) {
                case 1:
                    C0847jr.A01((C0847jr) A00(i), str, str2);
                    return;
                case 2:
                    this.A02.A00(A00(i), str, Integer.parseInt(str2));
                    return;
                case 3:
                    this.A02.A01(A00(i), str, Long.parseLong(str2));
                    return;
                case 4:
                    this.A02.A05(A00(i), str, str2.split(",,,"));
                    return;
                case 5:
                case 10:
                    this.A02.A04(A00(i), str, str2.split(",,,"));
                    return;
                case 6:
                    C0847jr.A01((C0847jr) A00(i), str, Double.valueOf(Double.parseDouble(str2)));
                    return;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    this.A02.A03(A00(i), str, str2.split(",,,"));
                    return;
                case 8:
                    C0847jr.A01((C0847jr) A00(i), str, Boolean.valueOf(Boolean.parseBoolean(str2)));
                    return;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    this.A02.A02(A00(i), str, str2.split(",,,"));
                    return;
                default:
                    try {
                        throw new IllegalArgumentException(AnonymousClass08.A00("Unsupported type: ", i));
                    } catch (NumberFormatException e) {
                        Log.w("QPL", "Failed to parse int annotation", e);
                        return;
                    }
            }
        }
    }

    public C0928p3(C0935pA pAVar) {
        this.A02 = pAVar;
    }
}
