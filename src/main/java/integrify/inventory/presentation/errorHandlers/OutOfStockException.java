package integrify.inventory.presentation.errorHandlers;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message){
        super(message);
    }
}