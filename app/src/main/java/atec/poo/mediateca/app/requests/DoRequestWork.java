package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.exceptions.RuleFailedException;
import atec.poo.mediateca.app.works.Message;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Requisicao;
import atec.poo.mediateca.core.exceptions.CoreNoSuchWorkException;
import atec.poo.mediateca.core.exceptions.CoreNosuchUserException;
import atec.poo.mediateca.core.exceptions.CoreRuleFailedException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;



/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {

    private LerInteiro id_user;
    private LerInteiro id_work;

    /**
     * @param receiver
     */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.id_user = new LerInteiro(atec.poo.mediateca.app.users.Message.requestUserId());
        this.id_work = new LerInteiro(Message.requestWorkId());

    }


    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id_user);
        ui.lerInput(this.id_work);
        try {
            int dia = this.getReceptor().newRequesicao(this.id_user.getValor(), this.id_work.getValor());

            ui.escreveLinha(atec.poo.mediateca.app.requests.Message.workReturnDay(this.id_work.getValor(), dia));
        } catch (CoreNosuchUserException e) {
            throw new NoSuchUserException(e.getId());
        } catch (CoreNoSuchWorkException e) {
            throw new NoSuchWorkException(e.getId());
        } catch (CoreRuleFailedException e) {
            throw new RuleFailedException(e.getUser(),e.getWork(), e.getRuleIndex());
        }

    }
}
