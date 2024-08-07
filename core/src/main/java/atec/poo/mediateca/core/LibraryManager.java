package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryManager{

    private Biblioteca _biblioteca;

    public LibraryManager() {
        this._biblioteca = new Biblioteca();
    }

    public String mostraUser(int id) throws CoreNosuchUserException {
        return this._biblioteca.mostraUser(id);
    }

    public String mostraWork(int id) throws CoreNoSuchWorkException {
        return this._biblioteca.mostraWork(id);
    }

    public int newUser(String nome, String email){
        return this._biblioteca.newUser(nome, email);
    }

    public int newRequesicao(int id_user, int id_obra) throws CoreNoSuchWorkException, CoreNosuchUserException, CoreRuleFailedException {
        return this._biblioteca.newRequesicao(id_user, id_obra);
    }

    public void returnWork (int id_user, int id_obra) throws CoreNoSuchWorkException, CoreNosuchUserException, CoreWorkNotBorrowdByUserException {
        this._biblioteca.returnWork(id_user, id_obra);
    }

    public void pagarMulta (int id_user) throws CoreUserIsActiveException{
        this._biblioteca.pagarMulta(id_user);
    }


    public ArrayList<User> getUsers(){
        return this._biblioteca.getUsers();
    }

    public ArrayList<Obra> getWorks(){
        return this._biblioteca.getWorks();
    }

    /**
     * Recebe ficheiro de entrada
     * @param //datafile
     * @throws ImportFileException
     */

    public void incrementadias(int dias){
        this._biblioteca.incrementadia(dias);
    }

    public int mostraData(){
        return this._biblioteca.mostradia();
    }

    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }


}
