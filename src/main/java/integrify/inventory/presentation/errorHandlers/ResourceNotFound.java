package integrify.inventory.presentation.errorHandlers;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message){
        super(message);
    }
}

