package atec.poo.mediateca.core;

public enum Comportamento {
    NORMAL("Normal"),
    CUMPRIDOR("Cumpridor"),
    FALTOSO("Faltoso");

    private final String comportamento;

    Comportamento(String comportamento) {
        this.comportamento = comportamento;
    }

    @Override
    public String toString() {
        return this.comportamento;
    }
};
