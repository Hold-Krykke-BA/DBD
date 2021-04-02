import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String text1 = "beebs beepps!!!!! their eerie ears hear pears";
        Map codes = huffman.encode(text1);
        String code = "0110101001101111110011010101110111011110000000000000001100011000111100010010110101001000101011010011101011111100011110011101011011101001110101111";
        huffman.decode(code, codes);

        System.out.println("\n\n*********************************************************");
        String text2 = "pete is here";
        Map codes2 = huffman.encode(text2);

    }
}
