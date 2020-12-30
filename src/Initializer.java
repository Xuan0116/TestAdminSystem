import jdk.jshell.execution.LoaderDelegate;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Initializer {
    public static void Initialize() throws MaxCharException {
        Admin.Deserialize();
        Customer.Deserialize();
        Shop.Deserialize();
        Visit.Deserialize();
        Case.Deserialize();

        if (Admin.adminArrayList.isEmpty())
            preAdmin();

        if(Case.caseArrayList.isEmpty())
            preCase();

        if(Visit.visits.isEmpty())
            preVisit();
    }

    public static void preCase(){
        new Case((LocalDateTime.now().minusHours(12)),1,2 );
        new Case((LocalDateTime.now().minusDays(1)),4,3 );
    }

    public static void preAdmin() throws MaxCharException {
        new Admin("User1", "User1");
        new Admin("User2", "User2");
        new Admin("User3", "User3");
    }

    public static void preVisit() {
        LocalDateTime[] randomDTs = timeGene();
        final Random random = new Random();
        int CustAmt = Customer.custs.size();
        int ShopAmt = Shop.shops.size();

        for (int i = 0; i < 30; i++) {
            Customer randomCust = Customer.custs.get(random.nextInt(CustAmt));
            Shop randomShop = Shop.shops.get(random.nextInt(ShopAmt));
            new Visit(randomDTs[i], randomCust.getId(), randomShop.getId());
        }
    }

    public static LocalDateTime[] timeGene() {
        final Random random = new Random();
        /** Declared a day in millisecond */
        final int millisInDay = 24*60*60*1000;

        /**Local Date Time array with size 30 */
        LocalDateTime[] randomDTs = new LocalDateTime[30];
        long[] diffSec = new long[30];
        /** Current DateTime */
        LocalDateTime dt = LocalDateTime.now();
        /** ZoneId.Systemdefault() = take current GMT convert to zoneID */
        /**toEpochMilli() take the zoneID convert to epoch Milli calculation */
        long curTime = dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        for (int i = 0; i < 30; i++)
            diffSec[i] = random.nextInt(millisInDay);
        Arrays.sort(diffSec);

        for (int i = 0; i < 30; i++) {
            /** To get Randam time (either pm /am/the last day) */
            long newDT = curTime - diffSec[29-i];
            /**Instant.ofEpochMilli(newDT) - Get instance of Instant using Millisecond form the epoch*/
            randomDTs[i] = LocalDateTime.ofInstant(Instant.ofEpochMilli(newDT), ZoneId.systemDefault());
        }
        /** Return a LocalDateTime array with random DT */
        return randomDTs;
    }

}
