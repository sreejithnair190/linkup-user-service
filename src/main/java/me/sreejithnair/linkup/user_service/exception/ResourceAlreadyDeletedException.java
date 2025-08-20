package me.sreejithnair.linkup.user_service.exception;

public class ResourceAlreadyDeletedException extends RuntimeException {
    public ResourceAlreadyDeletedException(String message) {
        super(message);
    }
}
