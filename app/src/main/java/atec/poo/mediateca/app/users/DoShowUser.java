package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNosuchUserException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme Enunciado
 * 4.2.2. Mostrar Utente.
 */
public class DoShowUser extends Comando<LibraryManager> {

    private LerInteiro id_user;
    /**
     * @param receiver
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER);
        this.id_user=new LerInteiro(Message.requestUserId());

    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id_user);
        try {
            ui.escreveLinha(this.getReceptor().mostraUser(this.id_user.getValor()));
        } catch (CoreNosuchUserException e) {
            throw new NoSuchUserException(e.getId());
        }
    }

}
