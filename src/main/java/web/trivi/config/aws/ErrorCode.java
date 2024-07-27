package web.trivi.config.aws;

public enum ErrorCode {
    EMPTY_FILE_EXCEPTION("File is empty or filename is null"),
    IO_EXCEPTION_ON_IMAGE_UPLOAD("IOException occurred while uploading image"),
    NO_FILE_EXTENTION("No file extension found"),
    INVALID_FILE_EXTENTION("Invalid file extension"),
    PUT_OBJECT_EXCEPTION("Exception occurred while putting object to S3"),
    IO_EXCEPTION_ON_IMAGE_DELETE("IOException occurred while deleting image");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
