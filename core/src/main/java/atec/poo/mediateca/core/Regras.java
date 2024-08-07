package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.CoreRuleFailedException;

import java.util.HashMap;

public final class Regras {

    private User user;
    private Obra obra;
    private HashMap<Integer, Requisicao> requisicoes;


    public static void RegrasTodas(User user, Obra obra, HashMap<Integer, Requisicao> requisicoes) throws CoreRuleFailedException {
        Regra_1(user, obra, requisicoes);
        Regra_2(user, obra);
        Regra_3(user, obra);
        Regra_4(user, obra, requisicoes);
        Regra_5(user ,obra);
        Regra_6(user, obra);
    }

    public static void Regra_1(User user, Obra obra, HashMap<Integer, Requisicao> requisicoes) throws CoreRuleFailedException {
        for (Requisicao req : requisicoes.values()) {
            if (req.getObra().getId() == obra.getId() && !req.isEntregue() && req.getUser().getId() == user.getId()) {
                throw new CoreRuleFailedException(user.getId(), obra.getId(), 1);
            }
        }
    };

    private static void Regra_2(User user, Obra obra) throws CoreRuleFailedException {
        if (user.getStatus() == Status.SUSPENSO) {
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 2);
        }
    };

    private static void Regra_3(User user, Obra obra)  throws CoreRuleFailedException{
        if (obra.getStock() == 0) {
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 3);
        }
    };


    private static void Regra_4(User user, Obra obra, HashMap<Integer, Requisicao> req) throws CoreRuleFailedException {

        int flag = 0;
        for(Requisicao requisicao : req.values()) {
            if(requisicao.getUser().getId()== user.getId() && !(requisicao.isEntregue()) ){
                flag++;
            }
        }

        if(user.getComportamento() == Comportamento.CUMPRIDOR && flag >=5 ){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 4);
        }
        if(user.getComportamento() == Comportamento.NORMAL && flag >=3 ){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 4);
        }
        if(user.getComportamento() == Comportamento.FALTOSO && flag >=1 ){
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 4);
        }
    };

    private static void Regra_5(User user, Obra obra) throws CoreRuleFailedException {
        if (obra.getCategoria() == Categoria.REFERENCE) {
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 5);
        }
    };

    private static void Regra_6(User user, Obra obra) throws CoreRuleFailedException {

        if (user.getComportamento() != Comportamento.CUMPRIDOR && obra.getPreco() >= 25) {
            throw new CoreRuleFailedException(user.getId(), obra.getId(), 6);
        }
    };



}
