
package server;

public class Response<TransferObject> {
    private static final long serialVersionUID = 1L; // Ensure version compatibility
    private final boolean success;
    private final String message;
    private final TransferObject data; // This can hold any additional data you want to send

    public Response(boolean success, String message, TransferObject data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
}
