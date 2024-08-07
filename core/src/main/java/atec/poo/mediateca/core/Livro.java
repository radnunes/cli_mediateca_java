package atec.poo.mediateca.core;

public class Livro extends Obra{

        private String autor;
        private String ISBN;

        public Livro(int id, String titulo, int copias, String autor, String ISBN, Categoria categoria, int preco) {
                super(id, titulo, copias, categoria, preco);
                this.ISBN = ISBN;
                this.autor = autor;
        }

        public String getAutor() {
                return autor;
        }

        public void setAutor(String autor) {
                this.autor = autor;
        }

        public String getISBN() {
                return ISBN;
        }

        public void setISBN(String ISBN) {
                this.ISBN = ISBN;
        }

        @Override
        public String toString() {
                return super.id + " - " + super.stock + " de " + super.copias + " - LIVRO - " + this.titulo + " - " + this.preco + " - " + this.categoria + " - " + this.autor + " - " + this.ISBN;
        }

        @Override
        public boolean performSearch(String termo) {
                if(this.autor.contains(termo)) { return true; }
                if(super.titulo.contains(termo)) { return true; }
                return false;
        };


        @Override
        public int compareTo(Obra o) {
                int titleComparison = this.getTitulo().compareTo(o.getTitulo());
                if (titleComparison != 0) {
                        return titleComparison;
                }

                // Titles are the same, compare based on authors
                if (o instanceof Livro) {
                        Livro other = (Livro) o;
                        int authorComparison = this.getAutor().compareTo(other.getAutor());
                        if (authorComparison != 0) {
                                return authorComparison;
                        }

                        // Authors are the same, compare based on ISBNs
                        return -1;
                }

                // If o is not an instance of Livro, they are considered equal for the purpose of comparison
                return 0;




        }




}
