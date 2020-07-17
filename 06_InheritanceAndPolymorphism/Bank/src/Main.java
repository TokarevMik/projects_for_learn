public class Main {
    public static void main(String[] args) {
        
        EscrowAccount dep = new EscrowAccount();
        CardBankAccount card = new CardBankAccount();
        System.out.println("Депозит " + dep.showMoneyCount());
        System.out.println("Карта " + card.showMoneyCount());
        card.send(dep, 15);
        System.out.println("Депозит " + dep.showMoneyCount());
        System.out.println("Карта " + card.showMoneyCount());
        card.send(dep, 195);
    }
}
