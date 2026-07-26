public class HashCrackerFactory {

    private HashCrackerFactory() {
    }

    public static HashCracker create(String method) {
        if (method == null) {
            throw new IllegalArgumentException("La méthode de cassage ne peut pas être null");
        }

        switch (method.toUpperCase()) {
            case "BRUTE":
                return new BruteForceHashCracker();
            case "DICO":
                return new DictionaryHashCracker();
            default:
                throw new IllegalArgumentException("Méthode de cassage inconnue : " + method);
        }
    }
}
