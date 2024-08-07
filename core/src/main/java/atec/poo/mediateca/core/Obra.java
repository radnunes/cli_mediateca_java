package atec.poo.mediateca.core;

public abstract class Obra implements Comparable<Obra> {

        protected int id;
        protected String titulo;
        protected int copias;
        protected int stock;
        protected Categoria categoria;
        protected int preco;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public int getCopias() {
                return copias;
        }

        public void setCopias(int copias) {
                this.copias = copias;
        }

        public Categoria getCategoria() {
                return categoria;
        }

        public void setCategoria(Categoria categoria) {
                this.categoria = categoria;
        }

        public int getPreco() {
                return preco;
        }

        public void setPreco(int preco) {
                this.preco = preco;
        }

        public int getStock() {
                return stock;
        }

        public void incrementStock(){
                this.stock += 1;
        }

        public void setStock(int stock) {
                this.stock = stock;
        }

        public Obra(int id, String titulo, int copias, Categoria categoria, int preco) {
                this.id = id;
                this.titulo = titulo;
                this.copias = copias;
                this.categoria = categoria;
                this.preco = preco;
                this.stock = copias;

        }


        public String toString() {
                return this.id + " - " + this.categoria + " - " + this.titulo + " - " + this.preco;
        }


        public abstract boolean performSearch(String termo);





}
