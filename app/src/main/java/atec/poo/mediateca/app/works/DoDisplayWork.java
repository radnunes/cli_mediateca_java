package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNoSuchWorkException;
import atec.poo.mediateca.core.exceptions.CoreNosuchUserException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;


/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {

    private LerInteiro id_work;
    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORK);
        this.id_work = new LerInteiro(Message.requestWorkId());
    }


    @Override
    public final void executar() throws NoSuchWorkException {
        ui.lerInput(this.id_work);
        try {
            ui.escreveLinha(this.getReceptor().mostraWork(this.id_work.getValor()));
        } catch (CoreNoSuchWorkException e) {
            throw new NoSuchWorkException(e.getId());
        }
    }
}
