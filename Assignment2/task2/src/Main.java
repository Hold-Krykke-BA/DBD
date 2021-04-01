
public class Main {
    public static void main(String args[]){

        BloomFilter bloomfilter = new BloomFilter();

        String[] words = {"hallohest","invest","mongodb","grimes","hat","velour","giraf","discord","glasfrø","sildeben"};
        String[] otherwords = {"hejhest","isbjerg","mange","grimer","hatte","velskabt","elefant","accord","frøglas","fiskeben"};

        System.out.println("\nAdding words to Bloomfilter...");
        for (String word : words) {
            bloomfilter.add(word);
        }
        System.out.println("\nChecking if the added words are caught in the filter...\n");
        for (String word : words) {
            System.out.println(word + " is probably there -- " +bloomfilter.check(word));
        }
        System.out.println("\n\nChecking if some other NOT added words are caught in the filter...\n");
        for (String word : otherwords) {
            System.out.println(word + " is probably there -- " +bloomfilter.check(word));
        }
        System.out.println("\n\nChecking the entire filter\n");
        bloomfilter.lookAt();


        System.out.println("\n\n\n");

        // bits per element
        // log2 N = log10 N / log10 2
        double result1 = (1.44 * Math.log(1.0/0.01)/Math.log(2));
        double result5 = (1.44 * Math.log(1.0/0.05)/Math.log(2));
        System.out.println("bits per element required for 1% FPR: " + result1);
        System.out.println("bits per element required for 5% FPR: " + result5);

    }
}
