package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.UserIsActiveException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNoSuchWorkException;
import atec.poo.mediateca.core.exceptions.CoreNosuchUserException;
import atec.poo.mediateca.core.exceptions.CoreRuleFailedException;
import atec.poo.mediateca.core.exceptions.CoreUserIsActiveException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.5. Pagar Multa
 */
public class DoPayFine extends Comando<LibraryManager> {

    private LerInteiro id_user;
    /**
     * @param receiver
     */
    public DoPayFine(LibraryManager receiver) {
        super(receiver, Label.PAY_FINE);
        this.id_user = new LerInteiro(Message.requestUserId());

    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id_user);
        try {
            this.getReceptor().pagarMulta(id_user.getValor());
        } catch (CoreUserIsActiveException e) {
            throw new UserIsActiveException(e.getId());
        }

    }

}
