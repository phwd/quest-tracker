package sun.security.x509;

import java.util.ArrayList;
import java.util.List;
import sun.security.util.DerOutputStream;

public class GeneralSubtrees implements Cloneable {
    private final List trees;

    public GeneralSubtrees() {
        this.trees = new ArrayList();
    }

    private GeneralSubtrees(GeneralSubtrees generalSubtrees) {
        this.trees = new ArrayList(generalSubtrees.trees);
    }

    public GeneralSubtree get(int i) {
        return (GeneralSubtree) this.trees.get(i);
    }

    public int size() {
        return this.trees.size();
    }

    public Object clone() {
        return new GeneralSubtrees(this);
    }

    public String toString() {
        return "   GeneralSubtrees:\n" + this.trees.toString() + "\n";
    }

    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).encode(derOutputStream2);
        }
        derOutputStream.write((byte) 48, derOutputStream2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeneralSubtrees)) {
            return false;
        }
        return this.trees.equals(((GeneralSubtrees) obj).trees);
    }

    public int hashCode() {
        return this.trees.hashCode();
    }
}
