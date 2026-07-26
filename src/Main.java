public class Main {

    public static void main(String[] args) {

        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
        }

        String method = null;
        String hash = null;


        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-m")) {
                method = args[i + 1];
            } else if (args[i].equals("-h")) {
                hash = args[i + 1];
            }
        }

        if (method == null || hash == null) {
            System.out.println("Usage : passwordCracker -m <BRUTE|DICO> -h <hash_md5>");
            return;
        }

        HashCracker cracker;
        try {
            cracker = HashCrackerFactory.create(method);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            return;
        }

        long start = System.currentTimeMillis();
        String password = cracker.crack(hash);
        long elapsed = System.currentTimeMillis() - start;

        System.out.println();
        if (password != null) {
            System.out.println("Password found: " + password);
        } else {
            System.out.println("Password not found");
        }

        System.out.println("Méthode utilisée   : " + method.toUpperCase());
        System.out.println("Tentatives          : " + cracker.getAttempts());
        System.out.println("Temps d'exécution   : " + elapsed + " ms");
    }
}
