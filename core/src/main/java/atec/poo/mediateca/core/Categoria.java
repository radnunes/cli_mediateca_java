package atec.poo.mediateca.core;

public enum Categoria {
    FICTION("Ficção"),
    SCITECH("Ciências Técnicas"),
    REFERENCE("Referência");

    private final String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
