package Game;


public class Main {
    public static void main(String[] args) throws Exception {

        // just for debugging methods
        Menu.mainMenu();

        System.out.println("How many players: ");
        System.out.println("1");
        System.out.println("Name: ");
        String name = GameHelper.input.nextLine();
        System.out.println("How many monetaassss: ");
        int money = 1000;
        Player player1 = new Player(name, money);

        Store store = new Store(player1, true);
        Menu.clearScreen();
        Menu.playerDisplay();
        store.displayPokemon();
            
        
        



    }
}
