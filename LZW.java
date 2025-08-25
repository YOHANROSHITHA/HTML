import java.util.*;

public class LZW {

    // LZW Encode
    public static List<Integer> encode(String input) {
        // Initialize dictionary with single-character strings
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }

        String w = "";
        List<Integer> result = new ArrayList<>();
        int dictSize = 256;

        for (char c : input.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.add(dictionary.get(w));
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        if (!w.equals("")) {
            result.add(dictionary.get(w));
        }

        return result;
    }

    // LZW Decode
    public static String decode(List<Integer> codes) {
        // Initialize dictionary with single-character ASCII strings
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }

        int dictSize = 256;
        String w = "" + (char) (int) codes.get(0);
        StringBuilder result = new StringBuilder(w);

        for (int i = 1; i < codes.size(); i++) {
            int k = codes.get(i);
            String entry;

            if (dictionary.containsKey(k)) {
                entry = dictionary.get(k);
            } else if (k == dictSize) {
                entry = w + w.charAt(0); // Special case
            } else {
                throw new IllegalArgumentException("Bad compressed code: " + k);
            }

            result.append(entry);

            // Add new entry to dictionary
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Example input string
        String input = "ABABABABA";
        List<Integer> compressed = encode(input);
        String decompressed = decode(compressed);

        System.out.println("Original: " + input);
        System.out.println("Compressed: " + compressed);
        System.out.println("Decompressed: " + decompressed);

        // Example with manually given compressed codes
        List<Integer> sampleCompressed = Arrays.asList(65, 66, 256, 258);
        System.out.println("\nSample Compressed: " + sampleCompressed);
        System.out.println("Decompressed from sample: " + decode(sampleCompressed));
    }
}
