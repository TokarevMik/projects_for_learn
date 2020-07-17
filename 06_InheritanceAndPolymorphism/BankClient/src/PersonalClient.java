public class PersonalClient extends Client {
    @Override
    public void showTerms() {
        System.out.println("Пополнение и снятие происходит без комиссии. Остаток на балансе - " + showMoneyCount());
    }
}
