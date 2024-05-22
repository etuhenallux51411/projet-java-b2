package main.exceptionPackage;

public class PostSearchException extends Exception {
    private String error;

    public PostSearchException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la recherche des posts : " + error;
    }
}
