import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class FlagSystem {
    public static void flagCase() {

        for(int i = 0; i < Case.caseArrayList.size(); i++){
            for(int j = 0; j < Shop.shops.size(); j++){
                if(Shop.shops.get(j).getId() == Case.caseArrayList.get(i).getShopID())
                    Shop.shops.get(j).setStatus("Case");
                for(int k = 0; k < Customer.custs.size(); k++){
                    if(Customer.custs.get(k).getId() == Case.caseArrayList.get(i).getCustID()){
                        Customer.custs.get(k).setStatus("Case");
                    }
                }
            }
        }

    }

    public static void flagClosed(){

        for(int i = 0; i < Case.caseArrayList.size(); i++){
            for(int j = 0; j < Visit.visits.size(); j++){
                    //if shop true > then compared time, if time yes then add into closed list.
                    if(Visit.visits.get(j).getShopID() == Case.caseArrayList.get(i).getShopID()){
                        int visitCustID = Visit.visits.get(j).getCustID();
                        int visitShopID = Visit.visits.get(j).getShopID();
                        LocalDateTime VisitDT = Visit.visits.get(j).getDt();
                        LocalDateTime CaseDT = Case.caseArrayList.get(i).getDt();
                        LocalDateTime BfCaseDT = CaseDT.minusHours(1);
                        LocalDateTime AfCaseDT = CaseDT.plusHours(1);
                        if((VisitDT.isBefore(CaseDT) && VisitDT.isAfter(BfCaseDT))
                                || (VisitDT.isAfter(CaseDT) && VisitDT.isBefore(AfCaseDT)))
                            Closed.ClosedArrayList.add(new Closed(VisitDT,visitCustID,visitShopID));

                    }
            }
        }

        for(int i = 0; i < Closed.ClosedArrayList.size(); i++)
            for(int j = 0; j < Customer.custs.size(); j++){
                if(Customer.custs.get(j).getId() == Closed.ClosedArrayList.get(i).getCustID())
                    Customer.custs.get(j).setStatus("Closed");
            }

    }

}


