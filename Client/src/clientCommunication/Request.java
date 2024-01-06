
package clientCommunication;

import java.io.Serializable;


public class Request<TransferObject> implements Serializable{
    private static final long serialVersionUID = 1L; // Ensure version compatibility
    private final String requestTyep;
    private final TransferObject requestObject;

    public Request(String requestTyep, TransferObject requestObject) {
        this.requestTyep = requestTyep;
        this.requestObject = requestObject;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRequestTyep() {
        return requestTyep;
    }

    public TransferObject getRequestObject() {
        return requestObject;
    }
    
    
    
}
