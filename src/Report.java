import java.util.HashMap;

public class Report {
    private DataContainer res = new DataContainer();
    private String title;

    public Report(Customer c) {
        this.title = c.getName() + " Visit History";

        int custId = c.getId();
        res.metadata.add("No");
        res.metadata.add("Date");
        res.metadata.add("Time");
        res.metadata.add("Shop");
        int count = -1;

        for (Visit v : Visit.visits)
            if (v.getCustID() == custId) {
                int shopId = v.getShopID();
                String shopName = Shop.shops.get(shopId-1).getName();
                res.data.add(new HashMap<String, String>());
                res.data.get(++count).put("No", Integer.toString(count + 1));
                res.data.get(count).put("Date", v.getDate());
                res.data.get(count).put("Time", v.getTime());
                res.data.get(count).put("Shop", shopName);
            }
    }

    public void exportCSV() {
        CSV.export(title, res);
    }

    public void display() {
        Table.display(title, res);
    }
}
