package atec.poo.mediateca.core;

public enum Status {
    ACTIVO("ACTIVO"),
    SUSPENSO("SUSPENSO");

    private final String nome;

    Status(String status) {
        this.nome = status;
    }


    @Override
    public String toString() {
        return this.nome;
    }
};
