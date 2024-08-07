package atec.poo.mediateca.core.exceptions;

public class CoreNosuchUserException extends Exception {

    private int id;

    public CoreNosuchUserException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
