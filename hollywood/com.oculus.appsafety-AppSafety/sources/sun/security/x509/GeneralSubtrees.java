package sun.security.x509;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class GeneralSubtrees implements Cloneable {
    private static final int NAME_DIFF_TYPE = -1;
    private static final int NAME_MATCH = 0;
    private static final int NAME_NARROWS = 1;
    private static final int NAME_SAME_TYPE = 3;
    private static final int NAME_WIDENS = 2;
    private final List<GeneralSubtree> trees;

    public GeneralSubtrees() {
        this.trees = new ArrayList();
    }

    private GeneralSubtrees(GeneralSubtrees source) {
        this.trees = new ArrayList(source.trees);
    }

    public GeneralSubtrees(DerValue val) throws IOException {
        this();
        if (val.tag == 48) {
            while (val.data.available() != 0) {
                add(new GeneralSubtree(val.data.getDerValue()));
            }
            return;
        }
        throw new IOException("Invalid encoding of GeneralSubtrees.");
    }

    public GeneralSubtree get(int index) {
        return this.trees.get(index);
    }

    public void remove(int index) {
        this.trees.remove(index);
    }

    public void add(GeneralSubtree tree) {
        if (tree != null) {
            this.trees.add(tree);
            return;
        }
        throw new NullPointerException();
    }

    public boolean contains(GeneralSubtree tree) {
        if (tree != null) {
            return this.trees.contains(tree);
        }
        throw new NullPointerException();
    }

    public int size() {
        return this.trees.size();
    }

    public Iterator<GeneralSubtree> iterator() {
        return this.trees.iterator();
    }

    public List<GeneralSubtree> trees() {
        return this.trees;
    }

    public Object clone() {
        return new GeneralSubtrees(this);
    }

    public String toString() {
        return "   GeneralSubtrees:\n" + this.trees.toString() + "\n";
    }

    public void encode(DerOutputStream out) throws IOException {
        DerOutputStream seq = new DerOutputStream();
        int n = size();
        for (int i = 0; i < n; i++) {
            get(i).encode(seq);
        }
        out.write((byte) 48, seq);
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

    private GeneralNameInterface getGeneralNameInterface(int ndx) {
        return getGeneralNameInterface(get(ndx));
    }

    private static GeneralNameInterface getGeneralNameInterface(GeneralSubtree gs) {
        return gs.getName().getName();
    }

    private void minimize() {
        int i = 0;
        while (i < size() - 1) {
            GeneralNameInterface current = getGeneralNameInterface(i);
            boolean remove1 = false;
            int j = i + 1;
            while (true) {
                if (j >= size()) {
                    break;
                }
                int constrains = current.constrains(getGeneralNameInterface(j));
                if (constrains != -1) {
                    if (constrains == 0) {
                        remove1 = true;
                        break;
                    } else if (constrains != 1) {
                        if (constrains == 2) {
                            remove1 = true;
                            break;
                        } else if (constrains != 3) {
                            break;
                        }
                    } else {
                        remove(j);
                        j--;
                    }
                }
                j++;
            }
            if (remove1) {
                remove(i);
                i--;
            }
            i++;
        }
    }

    private GeneralSubtree createWidestSubtree(GeneralNameInterface name) {
        GeneralName newName;
        try {
            switch (name.getType()) {
                case 0:
                    newName = new GeneralName(new OtherName(((OtherName) name).getOID(), null));
                    break;
                case 1:
                    newName = new GeneralName(new RFC822Name(""));
                    break;
                case 2:
                    newName = new GeneralName(new DNSName(""));
                    break;
                case 3:
                    newName = new GeneralName(new X400Address((byte[]) null));
                    break;
                case 4:
                    newName = new GeneralName(new X500Name(""));
                    break;
                case 5:
                    newName = new GeneralName(new EDIPartyName(""));
                    break;
                case 6:
                    newName = new GeneralName(new URIName(""));
                    break;
                case 7:
                    newName = new GeneralName(new IPAddressName((byte[]) null));
                    break;
                case 8:
                    newName = new GeneralName(new OIDName(new ObjectIdentifier((int[]) null)));
                    break;
                default:
                    throw new IOException("Unsupported GeneralNameInterface type: " + name.getType());
            }
            return new GeneralSubtree(newName, 0, -1);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error: " + ((Object) e), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        r8 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public sun.security.x509.GeneralSubtrees intersect(sun.security.x509.GeneralSubtrees r15) {
        /*
        // Method dump skipped, instructions count: 249
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.GeneralSubtrees.intersect(sun.security.x509.GeneralSubtrees):sun.security.x509.GeneralSubtrees");
    }

    public void union(GeneralSubtrees other) {
        if (other != null) {
            int n = other.size();
            for (int i = 0; i < n; i++) {
                add(other.get(i));
            }
            minimize();
        }
    }

    public void reduce(GeneralSubtrees excluded) {
        if (excluded != null) {
            int n = excluded.size();
            for (int i = 0; i < n; i++) {
                GeneralNameInterface excludedName = excluded.getGeneralNameInterface(i);
                int j = 0;
                while (j < size()) {
                    int constrains = excludedName.constrains(getGeneralNameInterface(j));
                    if (constrains != -1) {
                        if (constrains == 0) {
                            remove(j);
                            j--;
                        } else if (constrains == 1) {
                            remove(j);
                            j--;
                        } else if (constrains != 2) {
                        }
                    }
                    j++;
                }
            }
        }
    }
}
