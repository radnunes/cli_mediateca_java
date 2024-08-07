package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.*;


public class Biblioteca {
    private int dia;
    private HashMap<Integer,User> utentes;
    private HashMap<Integer,Obra> obras;
    private HashMap<Integer,Requisicao> requisicoes;
    private int nextUserID;
    private int nextObraID;

    public int getNextRequisicaoID() {
        return nextRequisicaoID;
    }

    public void setNextRequisicaoID(int nextRequisicaoID) {
        this.nextRequisicaoID = nextRequisicaoID;
    }

    private int nextRequisicaoID;

    public Biblioteca() {
        this.dia = 0;
        this.utentes = new HashMap<>();
        this.obras = new HashMap<>();
        this.requisicoes = new HashMap<>();
        this.nextUserID = 1;
        this.nextObraID = 1;
        this.nextRequisicaoID = 1;
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>(this.utentes.values());
        Collections.sort(users);
        return users;
    }

    public ArrayList<Obra> getWorks(){
        ArrayList<Obra> obras = new ArrayList<>(this.obras.values());
        Collections.sort(obras);
        return obras;
    }


    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public HashMap<Integer, Obra> getObras() {
        return obras;
    }

    public void setObras(HashMap<Integer, Obra> obras) {
        this.obras = obras;
    }

    public int getNextUserID() {
        return nextUserID;
    }

    public void setNextUserID(int nextUserID) {
        this.nextUserID = nextUserID;
    }

    public int getNextObraID() {
        return nextObraID;
    }

    public void setNextObraID(int nextObraID) {
        this.nextObraID = nextObraID;
    }

    public ArrayList<Requisicao> getRequisicoes() {
        ArrayList<Requisicao> requisicoes = new ArrayList<>(this.requisicoes.values());
        return requisicoes;
    }

    public void setRequisicoes(HashMap<Integer, Requisicao> requisicoes) {
        this.requisicoes = requisicoes;
    }

    public int newUser(String nome, String email){
        User u=new User(this.nextUserID,nome,email);
        this.utentes.put(this.nextUserID,u);
        this.nextUserID ++;
        return u.getId();
    }

    public void newLivro(String titulo, String autor, int preco, Categoria categoria,String ISBN, int copias) {

        Obra o = new Livro(nextObraID,titulo,copias,autor, ISBN, categoria, preco);
        this.obras.put(this.nextObraID, o);
        this.nextObraID++;
    }

    public void newDVD(String titulo, String realizador, int preco, Categoria categoria, String IGAC, int copias) {
        Obra o = new DVD(nextObraID, titulo, realizador, preco, categoria, IGAC, copias);
        this.obras.put(this.nextObraID, o);
        this.nextObraID++;
    }

    public int newRequesicao(int id_user, int id_obra) throws CoreNosuchUserException, CoreNoSuchWorkException, CoreRuleFailedException {
        if (!this.utentes.containsKey(id_user)) {
            throw new CoreNosuchUserException(id_user);
        }
        if (!this.obras.containsKey(id_obra)) {
            throw new CoreNoSuchWorkException(id_obra);
        }

        Regras.RegrasTodas(this.utentes.get(id_user), this.obras.get(id_obra), this.requisicoes);

        Obra o = this.obras.get(id_obra);
        User user = this.utentes.get(id_user);
        int dia = this.diaEntrega(user, o);
        Requisicao req = new Requisicao(this.nextRequisicaoID, user, o, this.dia , dia);

        this.requisicoes.put(this.nextRequisicaoID, req);
        o.stock--;
        nextRequisicaoID++;
        return dia;
    }

    public void returnWork(int id_user, int id_obra) throws CoreNosuchUserException, CoreNoSuchWorkException, CoreWorkNotBorrowdByUserException {
        if (!this.utentes.containsKey(id_user)) {
            throw new CoreNosuchUserException(id_user);
        }
        if (!this.obras.containsKey(id_obra)) {
            throw new CoreNoSuchWorkException(id_obra);
        }

        User user = this.utentes.get(id_user);
        Obra obra = this.obras.get(id_obra);

        Requisicao targetReq = null;
        for (Requisicao req : this.requisicoes.values()) {
            if (req.getObra().getId() == id_obra && req.getUser().getId() == id_user && !(req.isEntregue())) {
                targetReq = req;
                break;
            }
        }
        if (targetReq == null) {
            throw new CoreWorkNotBorrowdByUserException(id_user, id_obra);
        }
        calculaComportamento(targetReq);
        targetReq.setEntregue();

        user.setStatus(Status.ACTIVO);
        obra.incrementStock();
    }

    private int diaEntrega(User u, Obra o){
        int dataEntrega = 0;
        if(o.getStock() <= 1){
            if(u.getComportamento() == Comportamento.NORMAL){
                dataEntrega = 3;
            } else if(u.getComportamento() == Comportamento.FALTOSO){
                dataEntrega = 2;
            }else if(u.getComportamento() == Comportamento.CUMPRIDOR){
                dataEntrega = 8;
            }
        }

        if(o.getStock() > 1 && o.getStock() <=5){
            if(u.getComportamento() == Comportamento.NORMAL){
                dataEntrega = 8;
            } else if(u.getComportamento() == Comportamento.FALTOSO){
                dataEntrega = 2;
            }else if(u.getComportamento() == Comportamento.CUMPRIDOR){
                dataEntrega = 15;
            }
        }
        if(o.getStock() > 5){
            if(u.getComportamento() == Comportamento.NORMAL){
                dataEntrega = 15;
            } else if(u.getComportamento() == Comportamento.FALTOSO){
                dataEntrega = 2;
            }else if(u.getComportamento() == Comportamento.CUMPRIDOR){
                dataEntrega = 30;
            }
        }

        return dataEntrega + this.dia;
    }

    public void pagarMulta(int user) throws CoreUserIsActiveException{
        User u = this.utentes.get(user);
        if(u.getStatus() == Status.ACTIVO){
            throw new CoreUserIsActiveException(user);
        }
        u.setMulta(0);
        u.setStatus(Status.ACTIVO);
        for(Requisicao req : this.requisicoes.values()) {
            if(!(req.isEntregue()) && req.getData_entrega()< this.dia && req.getUser().getId() == u.getId()){
                u.setStatus(Status.SUSPENSO);
            }
        }
    }

    private void calculaComportamento(Requisicao req){
        User user = req.getUser();

        if(req.getData_entrega() < this.dia && !req.isEntregue()){
            if(user.getAssiduidade() >= 0) {
                user.setAssiduidade(-1);
            } else {
                user.decrementAssiduidade();
            }
        } else if (req.getData_entrega() > this.dia && !req.isEntregue()){
            if(user.getAssiduidade() <= 0) {
                user.setAssiduidade(1);
            } else {
                user.incrementAssiduidade();
            }
        }

        if(user.getAssiduidade() <= -3){
            user.setComportamento(Comportamento.FALTOSO);
        } else if(user.getAssiduidade() >= 5){
            user.setComportamento(Comportamento.CUMPRIDOR);
        } else {
            user.setComportamento(Comportamento.NORMAL);
        }
    }


    public ArrayList<User> getAllUsers() {
        ArrayList<User> todos=new ArrayList<>(this.utentes.values());
        Collections.sort(todos);
        return todos;
    }

    public void incrementadia(int n) {
        this.dia += n;

        for (Requisicao req : this.requisicoes.values()) {
            User user = req.getUser();
            if (req.getData_entrega() < this.dia && !req.isEntregue()) {
                user.setStatus(Status.SUSPENSO);
            }
        }
        calculaMulta();


    }

    private void calculaMulta(){
        for(Requisicao req : this.requisicoes.values()){
            User user = req.getUser();
            if(req.getData_entrega() < this.dia && !req.isEntregue()){
               user.setMulta(5 * (this.dia - req.getData_entrega()));
            }
        }
    }

    public int mostradia(){
        return this.dia;
    }

    public String mostraUser(int id) throws CoreNosuchUserException {
        if(this.utentes.containsKey(id)){
            return this.utentes.get(id).toString();
        }
        throw new CoreNosuchUserException(id);
    }

    public String mostraWork(int id) throws CoreNoSuchWorkException {
        if(this.obras.containsKey(id)){
            return this.obras.get(id).toString();
        }
        throw new CoreNoSuchWorkException(id);
    }


    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException
     * @throws IOException
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {

        Scanner s = new Scanner(new File(filename));

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] elementos = line.split(":", 0);
            switch (elementos[0]) {
                case "USER":
                    this.newUser(elementos[1],elementos[2]);
                    break;
                case "BOOK":
                    int preco = Integer.parseInt(elementos[3]);
                    Categoria categoria = Categoria.valueOf(elementos[4]);
                    int copias = Integer.parseInt(elementos[6]);
                    this.newLivro(elementos[1],elementos[2],preco, categoria,elementos[5],copias);
                    break;
                case "DVD":
                    int preco1 = Integer.parseInt(elementos[3]);
                    Categoria categoria1 = Categoria.valueOf(elementos[4]);
                    int copias1 = Integer.parseInt(elementos[6]);
                    this.newDVD(elementos[1], elementos[2], preco1, categoria1, elementos[5], copias1);
                    break;
                default:
                    throw new BadEntrySpecificationException("Unknow type of category");
            }
        }
        s.close();
    }
}
