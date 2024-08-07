package atec.poo.mediateca.core.exceptions;

public class CoreRuleFailedException extends Exception {

    int _idUser;


    int _idWork;


    int _ruleIndex = -1;

    /**
     * @param idUser
     * @param idWork
     * @param ruleIndex
     */
    public CoreRuleFailedException(int idUser, int idWork, int ruleIndex) {
        _idUser = idUser;
        _idWork = idWork;
        _ruleIndex = ruleIndex;
    }

    public int getRuleIndex() {
        return _ruleIndex;
    }

    public int getWork() {
        return _idWork;
    }

    public int getUser() {
        return _idUser;
    }

}
