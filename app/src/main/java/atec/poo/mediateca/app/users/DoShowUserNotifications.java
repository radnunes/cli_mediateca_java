package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Conforme enunciado
 * 4.2.3. Mostrar Notificações do Utente
 *
 */
public class DoShowUserNotifications extends Comando<LibraryManager> {

  private LerInteiro id_user;
  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(receiver, Label.SHOW_USER_NOTIFICATIONS);
    this.id_user=new LerInteiro(Message.requestUserId());
  }

  @Override
  public final void executar() throws DialogException {
    ui.lerInput(this.id_user);
  }

}
