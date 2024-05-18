package main.exception;

public class ConnectionAccessException extends Exception {
    private String error;
    public ConnectionAccessException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Error connecting to the database: " + error;
    }
}
