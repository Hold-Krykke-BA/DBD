
import java.util.BitSet;

/**
 * Very simple bloomfilter, using the BitSet over an long to store the data to avoid bit shifting.
 */
public class BloomFilter {
    private BitSet bitarray;

    public BloomFilter() {
        this.bitarray = new BitSet(65);
    }

    private int[] hash(String str){
        int[] hashes = new int[3];
        hashes[0] = Math.abs(str.hashCode() % 64);

        String strValue = "";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            strValue += String.valueOf((int) chars[i]);
        }

        hashes[1] = Math.abs(strValue.hashCode() % 64);
        hashes[2] = Math.abs(strValue.hashCode() * 3 % 64);

        return hashes;
    }

    public void add(String str){
        int[] hashes = hash(str);
        for (int hash : hashes) {
            bitarray.set(hash);
        }
    }

    public boolean check(String str){
        boolean isThere = true;

        int[] hashes = hash(str);
        for (int hash : hashes) {
            if (!bitarray.get(hash)) isThere = false;
        }

        return isThere;
    }

    public void lookAt (){
        for (int i = 0; i < 65; i++) {
            System.out.println(i + ": " +bitarray.get(i));

        }
    }
}
