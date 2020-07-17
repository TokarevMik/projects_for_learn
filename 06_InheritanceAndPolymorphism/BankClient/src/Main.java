public class Main {
    public static void main(String[] args) {

        EntrepreneurClient ent = new EntrepreneurClient();
        System.out.println(ent.showMoneyCount());
        ent.showTerms();
        ent.plusMoneyCount(10000);
        System.out.println(ent.showMoneyCount() + "***");
        ent.plusMoneyCount(500);
        System.out.println(ent.showMoneyCount());
        ent.minusMoneyCount(300);
        System.out.println(ent.showMoneyCount());

        CorpClient copr = new CorpClient();
        System.out.println(copr.showMoneyCount());
        copr.showTerms();
        copr.minusMoneyCount(1000);
        System.out.println("************");
        copr.minusMoneyCount(10);
        System.out.println(copr.showMoneyCount());

    }
}
