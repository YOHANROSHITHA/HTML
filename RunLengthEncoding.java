public class RunLengthEncoding {

    // Run-Length Encode
    public static String encode(String input) {
        if (input == null || input.isEmpty()) return "";

        StringBuilder encoded = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= input.length(); i++) {
            if (i < input.length() && input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(input.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        return encoded.toString();
    }

    // Run-Length Decode
    public static String decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) return "";

        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < encoded.length(); i++) {
            char ch = encoded.charAt(i);
            StringBuilder num = new StringBuilder();

            // Extract full number (in case it's > 9)
            while (i + 1 < encoded.length() && Character.isDigit(encoded.charAt(i + 1))) {
                num.append(encoded.charAt(++i));
            }

            int count = Integer.parseInt(num.toString());
            for (int j = 0; j < count; j++) {
                decoded.append(ch);
            }
        }

        return decoded.toString();
    }

    public static void main(String[] args) {
        String input = "AAABBBCCDAA";
        String encoded = encode(input);
        String decoded = decode(encoded);

        System.out.println("Original:   " + input);
        System.out.println("Encoded:    " + encoded);
        System.out.println("Decoded:    " + decoded);
    }
}
