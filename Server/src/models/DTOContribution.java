package models;

public class DTOContribution {
    private String contributor_name;
    private String owner_name;
    private int item_id;
    private int contributed_amount;

    public DTOContribution(String contributor_name, String owner_name, int item_id, int contributed_amount) {
        this.contributor_name = contributor_name;
        this.owner_name = owner_name;
        this.item_id = item_id;
        this.contributed_amount = contributed_amount;
    }

    public String getContributor_name() {
        return contributor_name;
    }

    public void setContributor_name(String contributor_name) {
        this.contributor_name = contributor_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getContributed_amount() {
        return contributed_amount;
    }

    public void setContributed_amount(int contributed_amount) {
        this.contributed_amount = contributed_amount;
    }
    
    
}
