
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

    }
}
