import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class FlagSystem {
    public static void Flag(){
        /** Testing Purpose */
        /** Copy static customer arraylist to temporary list */
        String status = "Normal";
        ArrayList<Customer> TempCustList = new ArrayList<Customer>();
        ArrayList<Visit> Visitlog = new ArrayList<Visit>();
        TempCustList.addAll(Customer.custs);
        Visitlog.addAll(Visit.visits);

        /** To store customer id who need to close. */
        int[] closeCustList = new int[Visitlog.size()];
        /** To store DateTime in long */
        Long[] DTinEpoch = new Long[Visitlog.size()];

        for(int i = 0; i < Visitlog.size(); i++)
            DTinEpoch[i] = Visitlog.get(i).getDt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        /** Choose 1 local date */
        LocalDateTime ExactDt = Visitlog.get(0).getDt();
        long ExactTime = ExactDt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        /** Derived BeforeExactDT and last ExactDT from ExactDT */
        final long hour = 60*60*1000;
        Long BfExactDT = ExactDt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - hour;
        Long AfExactDT = ExactDt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + hour;

        for(int i = 0; i < Visitlog.size(); i++){
            if(DTinEpoch[i] < AfExactDT || DTinEpoch[i] >BfExactDT)
                closeCustList[i] = Visitlog.get(i).getCustID();
        }

        for(int i = 0; i < TempCustList.size(); i++){
            if(closeCustList[i] == TempCustList.get(i).getId())
                TempCustList.get(i).setStatus("Close");
        }

        System.out.println("Visit Log: ");
        for(Visit v : Visitlog)
            System.out.println(v);

        System.out.println("\n\n\nCustomer List");
        for(Customer c : TempCustList)
            System.out.println(c);

    }
}
