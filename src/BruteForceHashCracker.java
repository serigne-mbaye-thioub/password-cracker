public class BruteForceHashCracker implements HashCracker {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final int DEFAULT_MAX_LENGTH = 4;

    private final int maxLength;
    private int attempts = 0;

    public BruteForceHashCracker() {
        this(DEFAULT_MAX_LENGTH);
    }

    public BruteForceHashCracker(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String crack(String hash) {
        attempts = 0;

        for (int length = 1; length <= maxLength; length++) {
            String result = tryAllCombinations(hash, length);
            if (result != null) {
                return result;
            }
        }

        return null;
    }


    private String tryAllCombinations(String hash, int length) {
        char[] current = new char[length];

        long total = (long) Math.pow(ALPHABET.length(), length);

        for (long i = 0; i < total; i++) {
            long value = i;
            for (int pos = length - 1; pos >= 0; pos--) {
                int letterIndex = (int) (value % ALPHABET.length());
                current[pos] = ALPHABET.charAt(letterIndex);
                value /= ALPHABET.length();
            }

            String candidate = new String(current);
            attempts++;

            if (MD5Utils.md5(candidate).equalsIgnoreCase(hash)) {
                return candidate;
            }
        }

        return null;
    }

    @Override
    public int getAttempts() {
        return attempts;
    }
}
