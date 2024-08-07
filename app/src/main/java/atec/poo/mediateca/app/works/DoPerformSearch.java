package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.DVD;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Livro;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.exceptions.CoreNoSuchWorkException;
import atec.poo.ui.*;

import java.util.ArrayList;

/**
 * Conforme Enunciado
 * 4.3.3. Pesquisar Obras
 */
public class DoPerformSearch extends Comando<LibraryManager> {

  private LerString termo;
  /**
   * @param receiver
   */
  public DoPerformSearch(LibraryManager receiver) {
    super(receiver, Label.PERFORM_SEARCH);
    this.termo =new LerString(Message.requestSearchTerm(), null);
  }

  /*
  @Override
  public final void executar() {

    ui.lerInput(this.termo);
    String searchTerm = this.termo.getValor();
    ArrayList<Obra> obras = this.getReceptor().getWorks();
    ArrayList<Obra> foundWorks = new ArrayList<>();

    for (Obra obra : obras) {
      if (obra.getTitulo().toLowerCase().contains(searchTerm.toLowerCase())) {
        foundWorks.add(obra);
      }
    }

    if (foundWorks.isEmpty()) {
      ui.escreveLinha("Nenhum resultado encontrado.");
    } else {
      for (Obra obra : foundWorks) {
        ui.escreveLinha(obra.toString());
      }
    }
    */
  public final void executar() {
    ui.lerInput(this.termo);
    String searchTerm = this.termo.getValor().toLowerCase(); // Convert to lower case once
    ArrayList<Obra> obras = this.getReceptor().getWorks();
    ArrayList<Obra> foundWorks = new ArrayList<>();

    for (Obra obra : obras) {
      boolean matchFound = false;

      // Check if the title matches the search term
      if (obra.getTitulo().toLowerCase().contains(searchTerm)) {
        matchFound = true;
      }

      // If the obra is an instance of Livro, check the autor
      if (obra instanceof Livro) {
        Livro livro = (Livro) obra;
        if (livro.getAutor().toLowerCase().contains(searchTerm)) {
          matchFound = true;
        }
      }

      // If the obra is an instance of DVD, check the realizador
      if (obra instanceof DVD) {
        DVD dvd = (DVD) obra;
        if (dvd.getRealizador().toLowerCase().contains(searchTerm)) {
          matchFound = true;
        }
      }

      if (matchFound) {
        foundWorks.add(obra);
      }
    }

    if (foundWorks.isEmpty()) {
      ui.escreveLinha("Nenhum resultado encontrado.");
    } else {
      for (Obra obra : foundWorks) {
        ui.escreveLinha(obra.toString());
      }
    }
  }

}




