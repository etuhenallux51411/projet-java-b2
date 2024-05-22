package main.exceptionPackage;

public class UserResearchExecption  extends Exception{
    private String error;

    public UserResearchExecption(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la recherche de l'utilisateur : " + error;
    }
}
