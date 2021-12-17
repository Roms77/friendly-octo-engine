package rmignac.owner.exceptions;

public class UnautorizedException extends Exception{

    public UnautorizedException(){
        super("Action non autorisée");
    }
}
