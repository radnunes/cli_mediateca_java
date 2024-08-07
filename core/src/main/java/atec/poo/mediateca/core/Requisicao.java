package atec.poo.mediateca.core;

public class Requisicao  {


    protected int id;
    protected User user;
    protected Obra obra;
    protected int data_levantamento;
    protected int data_entrega;
    protected boolean entregue;

    public Requisicao(int id,User user,Obra obra, int data_levantamento, int data_entrega) {
        this.data_entrega = data_entrega;
        this.data_levantamento = data_levantamento;
        this.obra = obra;
        this.user = user;
        this.id = id;
        this.entregue = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public int getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(int data_entrega) {
        this.data_entrega = data_entrega;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue() {
        this.entregue = true;
    }

    public int getData_levantamento() {
        return data_levantamento;
    }

    public void setData_levantamento(int data_levantamento) {
        this.data_levantamento = data_levantamento;
    }

    @Override
    public String toString() {
        return "REQUISIÇÃO EFETUADA: " +
                "id_requesição=" + id +
                ", id_user=" + user +
                ", obra= ## " + obra +
                " ## , data_levantamento=" + data_levantamento +
                ", data_entrega=" + data_entrega;
    }
}
