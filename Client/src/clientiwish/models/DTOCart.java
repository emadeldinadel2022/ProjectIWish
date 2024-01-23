package clientiwish.models;

public class DTOCart {
    private String user_name;
    private int item_id;

    public DTOCart(String user_name, int item_id) {
        this.user_name = user_name;
        this.item_id = item_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    
    
}