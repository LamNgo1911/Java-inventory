package integrify.inventory.presentation.errorHandlers;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }
}
