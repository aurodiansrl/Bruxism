package logic;

public class DomainInteraction {
    public String primary;
    public String secondary;
    public String relation;
    public String action;

    public DomainInteraction(String primary, String secondary, String relation, String action) {
        this.primary = primary;
        this.secondary = secondary;
        this.relation = relation;
        this.action = action;
    }

    public String getPrimary() { return primary; }
    public String getSecondary() { return secondary; }
    public String getRelation() { return relation; }
    public String getAction() { return action; }
}
