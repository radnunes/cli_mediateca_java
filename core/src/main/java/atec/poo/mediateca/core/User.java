package atec.poo.mediateca.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class User implements Comparable<User> {
    private int id;
    private String name;
    private String email;
    private int multa;
    protected Status status;
    protected Comportamento comportamento;
    protected int assiduidade;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMulta() {
        return multa;
    }



    public Comportamento getComportamento() {
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public int getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(int assiduidade) {
        this.assiduidade = assiduidade;
    }

    public void incrementAssiduidade() {
        this.assiduidade++;
    }

    public void decrementAssiduidade() {
        this.assiduidade--;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.multa=0;
        this.status = Status.ACTIVO;
        this.comportamento = Comportamento.NORMAL;
        this.assiduidade = 0;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name + " - " + this.email + " - " + this.getComportamento() + " - " +  this.status +  (this.getStatus() == Status.SUSPENSO ? " - EUR " + this.getMulta() : "");
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.getName());
    }
}
