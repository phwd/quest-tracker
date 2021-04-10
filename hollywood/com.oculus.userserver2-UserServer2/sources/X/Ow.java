package X;

import java.util.Comparator;

public class Ow implements Comparator<gz> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(gz gzVar, gz gzVar2) {
        return gzVar.A01.rawType.getName().compareTo(gzVar2.A01.rawType.getName());
    }
}
