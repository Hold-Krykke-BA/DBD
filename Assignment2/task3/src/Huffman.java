import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    Node root;
    StringBuilder strB;

    private void buildTree(String text){
        if (text == null || text.length() == 0) {
            return;
        }

        Map<Character, Integer> frequencies = new HashMap<>();
        for (char letter: text.toCharArray()) {
            frequencies.put(letter, frequencies.getOrDefault(letter, 0) + 1);
        }
        System.out.println("Map\n" + frequencies);

        PriorityQueue<Node> queue;
        queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() != 1) {
            // Remove the two nodes of the highest priority (the lowest frequency) from the queue
            Node left = queue.poll();
            Node right = queue.poll();

            // create a new node with these two nodes as children and with a frequency
            // equal to the sum of both and add the new node to the priority queue.
            int sum = left.frequency + right.frequency;
            queue.add(new Node(null, sum, left, right));
        }
        // pointer to the root of Huffman Tree
        root = queue.peek();
    }

    public Map encode(String text){
        buildTree(text);
        Map<Character, String> huffmanCodes = new HashMap<>();
        _encode(root, "", huffmanCodes);

        System.out.println("\n\nOriginal string is:\n" + text + "\n");
        System.out.println("The codes are:\n" + huffmanCodes + "\n");

        strB = new StringBuilder();
        for (char letter: text.toCharArray()) {
            strB.append(huffmanCodes.get(letter));
        }

        System.out.println("The encoded string is:\n" + strB+ "\n");
        return huffmanCodes;
    }

    private void _encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        if (isLeafNode(root)) {
            huffmanCode.put(root.letter, str.length() > 0 ? str : "1");
        }

        _encode(root.left, str + '0', huffmanCode);
        _encode(root.right, str + '1', huffmanCode);
    }

    public String decode(String text, Map<Character, String> huffmanCodes){
        char[] bits = text.toCharArray();

        HashMap<String, String> reverseMap = new HashMap<>();

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            reverseMap.put(entry.getValue(), String.valueOf(entry.getKey()));
        }

        StringBuffer result = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < bits.length; i++) {
            temp.append(bits[i]);
            String val = (String) reverseMap.get(temp.toString());
            if (val == null) {
                continue;
            }
            result.append(val);
            temp.setLength(0);
        }
        return result.toString();
    }

    public boolean isLeafNode(Node node){
        return node.left == null && node.right == null;
    }

}

class Node {
    Character letter;
    Integer frequency;
    Node left = null;
    Node right = null;

    Node(Character letter, Integer frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    public Node(Character letter, Integer frequency, Node left, Node right) {
        this.letter = letter;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

}
