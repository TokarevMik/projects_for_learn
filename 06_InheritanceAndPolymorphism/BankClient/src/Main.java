public class Main {
    public static void main(String[] args) {

        /*EntrepreneurClient ent = new EntrepreneurClient();
        System.out.println(ent.showMoneyCount());
        ent.showTerms();
        ent.plusMoneyCount(10000);
        System.out.println(ent.showMoneyCount() + "***");
        ent.plusMoneyCount(500);
        System.out.println(ent.showMoneyCount());
        ent.minusMoneyCount(300);
        System.out.println(ent.showMoneyCount());*/

        /*CorpClient corp = new CorpClient();
        System.out.println(corp.showMoneyCount());
        corp.showTerms();
        corp.minusMoneyCount(1000);
        System.out.println(corp.getDepositComission(1000));
        System.out.println("************");
        corp.minusMoneyCount(10);
        System.out.println(corp.showMoneyCount());
        corp.plusMoneyCount(10);
        System.out.println(corp.getWithdrawalComission(10));
        System.out.println(corp.getDepositComission(10));
        System.out.println(corp.showMoneyCount());*/


        PersonalClient pers = new PersonalClient();
        System.out.println(pers.showMoneyCount());
        pers.showTerms();
        pers.plusMoneyCount(10);
        System.out.println(pers.showMoneyCount());
        pers.minusMoneyCount(10);
        System.out.println(pers.showMoneyCount());

    }
}
