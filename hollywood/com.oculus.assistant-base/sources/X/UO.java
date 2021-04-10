package X;

public final class UO {
    public static boolean A00(UM um, Object obj) {
        if (obj != um) {
            if (obj instanceof UM) {
                UM um2 = (UM) obj;
                if (um.size() == um2.size() && um.entrySet().size() == um2.entrySet().size()) {
                    for (AbstractC1179ua uaVar : um2.entrySet()) {
                        if (um.A1V(uaVar.A01()) != uaVar.A00()) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }
}
