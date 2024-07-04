package integrify.inventory.presentation.errorHandlers;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
