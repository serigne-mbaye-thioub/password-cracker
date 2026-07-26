import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DictionaryHashCracker implements HashCracker {

    private static final String DEFAULT_DICTIONARY_PATH = "dictionary.txt";

    private final String dictionaryPath;
    private int attempts = 0;

    public DictionaryHashCracker() {
        this(DEFAULT_DICTIONARY_PATH);
    }

    public DictionaryHashCracker(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    @Override
    public String crack(String hash) {
        attempts = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryPath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                word = word.trim();
                if (word.isEmpty()) {
                    continue;
                }

                attempts++;
                if (MD5Utils.md5(word).equalsIgnoreCase(hash)) {
                    return word;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur : impossible de lire le dictionnaire '"
                    + dictionaryPath + "' (" + e.getMessage() + ")");
        }

        return null;
    }

    @Override
    public int getAttempts() {
        return attempts;
    }
}
