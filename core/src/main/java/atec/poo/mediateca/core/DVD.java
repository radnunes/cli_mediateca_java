package atec.poo.mediateca.core;

import javax.print.attribute.standard.Media;

public class DVD extends Obra  {


    private String realizador;
    private String IGAC;

    public DVD(int id, String titulo, String realizador, int preco, Categoria categoria, String IGAC, int copias) {
        super(id,titulo, copias, categoria, preco);
        this.realizador = realizador;
        this.IGAC = IGAC;
    }

    public String getRealizador() {
        return realizador;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }

    public String getIGAC() {
        return IGAC;
    }

    public void setIGAC(String IGAC) {
        this.IGAC = IGAC;
    }

    @Override
    public String toString() {
        return super.id + " - " + super.stock + " de " + super.copias + " - DVD - " + this.titulo + " - " + this.preco + " - " + this.categoria + " - " + this.realizador + " - " + this.IGAC;
    }

    @Override
    public boolean performSearch(String termo) {
        if(this.realizador.contains(termo)) { return true; }
        if(super.titulo.contains(termo)) { return true; }
        return false;
    }

    @Override
    public int compareTo(Obra o) {

        int titleComparison = this.getTitulo().compareTo(o.getTitulo());
        if (titleComparison != 0) {
            return titleComparison;
        }


        if (o instanceof DVD) {
            DVD other = (DVD) o;
            int directorComparison = this.realizador.compareTo(other.getRealizador());
            if (directorComparison != 0) {
                return directorComparison;
            }
            return -1;
        }


        return 0;
    }

}
