package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.WorkNotBorrowedByUserException;
import atec.poo.mediateca.app.works.Message;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.CoreNoSuchWorkException;
import atec.poo.mediateca.core.exceptions.CoreNosuchUserException;
import atec.poo.mediateca.core.exceptions.CoreWorkNotBorrowdByUserException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;


/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Comando<LibraryManager> {

    private LerInteiro id_user;
    private LerInteiro id_work;
    /**
     * @param receiver
     */
    public DoReturnWork(LibraryManager receiver) {
        super(receiver, Label.RETURN_WORK);
        this.id_user = new LerInteiro(atec.poo.mediateca.app.users.Message.requestUserId());
        this.id_work = new LerInteiro(Message.requestWorkId());
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id_user);
        ui.lerInput(this.id_work);

        try {
            this.getReceptor().returnWork(this.id_user.getValor(), this.id_work.getValor());
        } catch (CoreNosuchUserException e) {
            throw new NoSuchUserException(e.getId());
        } catch (CoreNoSuchWorkException e) {
            throw new NoSuchWorkException(e.getId());
        } catch (CoreWorkNotBorrowdByUserException e) {
            throw new WorkNotBorrowedByUserException(e.getId_user(), e.getId_obra());
        }


    }
}
