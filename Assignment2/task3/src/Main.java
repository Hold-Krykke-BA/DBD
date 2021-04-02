import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String text1 = "beebs beepps!!!!! their eerie ears hear pears";
        Map codes = huffman.encode(text1);
        String code = "0110101001101111110011010101110111011110000000000000001100011000111100010010110101001000101011010011101011111100011110011101011011101001110101111";
        System.out.println("The decoded string is:");
        System.out.println(huffman.decode(code, codes));

        System.out.println("\n\n*********************************************************");
        String text2 = "pete is here";
        Map codes2 = huffman.encode(text2);

        System.out.println("\n\n*********************************************************");
        System.out.println("Decoding Camillas encoded string\n");
        System.out.println("Provided info:");

        String camillaCode = "101100011100000011100000110100010010111011101100110001001010001101111101100011111100011010100111000011010100101011110100101011110010110111100100111101100001111110110101110001010111101001100101111001010011";
        Map<Character, String> camillaCodes = new HashMap<>();
        camillaCodes.put(' ',"00");
        camillaCodes.put('a',"0100");
        camillaCodes.put('c',"110100");
        camillaCodes.put('d',"110101");
        camillaCodes.put('e',"011");
        camillaCodes.put('æ',"11110");
        camillaCodes.put('g',"101111");
        camillaCodes.put('h',"101100");
        camillaCodes.put('i',"1110");
        camillaCodes.put('l',"1100");
        camillaCodes.put('m',"101110");
        camillaCodes.put('n',"0101");
        camillaCodes.put('o',"11111");
        camillaCodes.put('0',"110110");
        camillaCodes.put('1',"110111");
        camillaCodes.put('r',"1000");
        camillaCodes.put('s',"1010");
        camillaCodes.put('t',"1001");
        camillaCodes.put('v',"101101");

        System.out.println("    String:\n" + "      " + camillaCode);
        System.out.println("    Codes:\n" + "      " + camillaCodes);
        System.out.println("\nThe decoded string is:");
        Huffman huffman3 = new Huffman();
        System.out.println(huffman3.decode(camillaCode, camillaCodes));

    }
}
