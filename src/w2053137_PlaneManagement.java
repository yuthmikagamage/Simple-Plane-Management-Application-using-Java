import java.util.*;

public class w2053137_PlaneManagement {
    public static void main(String[] args) {
        int choise=0;
        boolean quit=false;
        //Looping the loop until user select quit
        while (!quit){
            System.out.println("Welcome to the Plane Management application");
            System.out.println("******************************************************");
            System.out.println("*                      Menu                          *");
            System.out.println("******************************************************");
            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("******************************************************");

            boolean choose_excep=false;
            while(!choose_excep){
                try {
                    Scanner user_choise=new Scanner(System.in);
                    System.out.print("Please select an option: ");
                    choise=user_choise.nextInt();
                    choose_excep=true;
                }
                catch (InputMismatchException e){
                    System.out.println("Choose from above menu numbers");
                }
            }

            switch (choise) {
                case 1:
                    System.out.println("Buy a seat");
                    buy_seat();
                    break;
                case 2:
                    System.out.println("Cansel Seat");
                    cancel_seat();
                    break;
                case 3:
                    System.out.println("Find first available seat");
                    find_first_available();
                    break;
                case 4:
                    System.out.println("Show seat plan");
                    show_seating_plan();
                    break;
                case 5:
                    System.out.println("Print Ticket Info");
                    print_tickets_info();
                    break;
                case 6:
                    System.out.println("Search Ticket");
                    search_ticket();
                    break;
                case 0:
                    System.out.println("Exiting the code");
                    quit=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
    }
    //Array to store the seat plan
    private static final String [][] seat_plan ={
            {"0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0","0"},
            {"0","0","0","0","0","0","0","0","0","0","0","0","0","0"}
    };
    //Creating array to store ticket objects
    static Ticket[] tickets = new Ticket[52];
    // create variable to store tickets that sold
    static int ticketCount = 0;

    //Method to buy seat
    private static void buy_seat(){
        String row="";
        int seat=0;
        //loop for if seat already sold tell user to input another seat
        boolean seat_available=false;
        while (!seat_available){
            boolean rowloop=false;
            while (!rowloop){
                Scanner row_letter=new Scanner(System.in);
                System.out.print("Enter the row letter : ");
                row= row_letter.next();
                if (row.equalsIgnoreCase("A") || row.equalsIgnoreCase("B") || row.equalsIgnoreCase("C") || row.equalsIgnoreCase("D")){
                    rowloop=true;
                }
                else {
                    System.out.println("Invalid Row, Enter a valid row");
                }
            }

            // Loop for if entered seat number not maching to the row or invalid user to input another seat no
            boolean seatloop=false;
            while (!seatloop){
                try{
                    Scanner seat_no=new Scanner(System.in);
                    System.out.print("Enter the seat number : ");
                    seat=seat_no.nextInt();
                    if (row.equalsIgnoreCase("A")&& (seat<=14) && (seat>0) || row.equalsIgnoreCase("D")&& (seat<=14) && (seat>0)){
                        System.out.println("You Selected seat " + seat + " in " + row + " Row");
                        seatloop=true;
                        System.out.println();
                    }
                    else if (row.equalsIgnoreCase("B")&& (seat<=12) && (seat>0) || row.equalsIgnoreCase("C")&& (seat<=12) && (seat>0)){
                        System.out.println("You Selected seat " + seat + " in " + row + " Row");
                        seatloop=true;
                        System.out.println();
                    }
                    else {
                        System.out.println("Invalid seat number according to row");
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("Enter a valid seat No");
                }
            }

            // Initializing the rowno variable for convert row letter to row as number
            int rowno=0;
            if (row.equalsIgnoreCase("A")) {
                rowno = 0;
            } else if (row.equalsIgnoreCase("B")) {
                rowno = 1;
            } else if (row.equalsIgnoreCase("C")) {
                rowno = 2;
            } else if (row.equalsIgnoreCase("D")) {
                rowno = 3;
            }

            // Updating the array after sold seat
            if (seat_plan[rowno][seat-1].equals("0")){
                seat_plan[rowno][seat-1]="X";
                seat_available=true;
            }
            else {
                System.out.println("The seat is already sold");
                System.out.println("Try another seat");
            }
        }

        // Matching the prices according to seat numbers
        int price=0;
        if (seat<=5 && seat>0){
            price=200;
        }
        else if(seat>5 && seat<=9){
            price=150;
        }
        else if (seat>9 && seat<=14){
            price=180;
        }

        // Getting user's detail inputs to print after buying seat
        Scanner nameinput=new Scanner(System.in);
        System.out.print("Enter your name : ");
        String name=nameinput.next();

        Scanner surnameinput=new Scanner(System.in);
        System.out.print("Enter your surname : ");
        String  surname=surnameinput.next();

        Scanner emailinput=new Scanner(System.in);
        System.out.print("Enter your Email : ");
        String  email=emailinput.next();
        System.out.println("Ticket information saved to " + row+seat+".txt");

        System.out.println();

        // Creating object with Person class
        Person object=new Person(name,surname,email);
        object.setName(name);
        object.setSurname(surname);
        object.setEmail(email);

        //Creating object with Ticket class
        Ticket object2=new Ticket(row,seat,price,object);
        object2.setRow(row.toUpperCase());
        object2.setSeat(seat);
        object2.setPrice(price);
        object2.printticketinfo();
        tickets[ticketCount++] = object2;
        //Saving the ticket info after every time ticket sold
        object2.save();
    }

    //Method to cansel seat
    private static void cancel_seat(){
        boolean rowloop=false;
        String row="";
        // Loop for loop untill user enters correct row
        while (!rowloop){
            Scanner row_letter=new Scanner(System.in);
            System.out.print("Enter the row letter : ");
            row= row_letter.next();
            if (row.equalsIgnoreCase("A") || row.equalsIgnoreCase("B") || row.equalsIgnoreCase("C") || row.equalsIgnoreCase("D")){
                rowloop=true;
            }
            else {
                System.out.println("Invalid Row, Enter a valid row");
            }
        }
        int seat=0;
        // Loop for loop untill user enters correct seat according to row
        boolean seatloop=false;
        while (!seatloop){
            try{
                Scanner seat_no=new Scanner(System.in);
                System.out.print("Enter the seat number : ");
                seat=seat_no.nextInt();
                if (row.equalsIgnoreCase("A")&& (seat<=14) && (seat>0) || row.equalsIgnoreCase("D")&& (seat<=14) && (seat>0)){
                    System.out.println("You Selected seat " + seat + " in " + row + " Row");
                    seatloop=true;
                }
                else if (row.equalsIgnoreCase("B")&& (seat<=12) && (seat>0) || row.equalsIgnoreCase("C")&& (seat<=12) && (seat>0)){
                    System.out.println("You Selected seat " + seat + " in " + row + " Row");
                    seatloop=true;
                }
                else {
                    System.out.println("Invalid seat number according to row");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid seat No");
            }
        }
        //Matching the user entered Row letters with numbers
        int rowno = 0;
        if (row.equalsIgnoreCase("A")) {
            rowno = 0;
        } else if (row.equalsIgnoreCase("B")) {
            rowno = 1;
        } else if (row.equalsIgnoreCase("C")) {
            rowno = 2;
        } else if (row.equalsIgnoreCase("D")) {
            rowno = 3;
        }
        //Canselling the seat if seat isn't sold
        if (seat_plan[rowno][seat-1].equals("X")){
            seat_plan[rowno][seat-1]="0";
            System.out.println("Seat Cancelled Succesfully");
            // Removing the canceled ticket from array
            for (int i = 0; i < ticketCount; i++) {
                if (tickets[i].getRow().equalsIgnoreCase(row) && tickets[i].getSeat() == seat) {
                    // Shifting remaining elements to left
                    for (int j = i; j < ticketCount - 1; j++) {
                        tickets[j] = tickets[j + 1];
                    }
                    // Set the last element to null
                    tickets[ticketCount - 1] = null;
                    ticketCount--;
                    break;
                }
            }
        }
        else {
            System.out.println("Seat isn't Sold, Can't cansel");
        }
    }

    //Method for finding the first available seat
    private static void find_first_available() {
        String msg="The first available seat is on ";
        for (int i = 0; i < seat_plan.length; i++) {
            for (int j = 0; j < seat_plan[i].length; j++) {
                if (seat_plan[i][j].equals("0")) {
                    switch (i){
                        case 0:
                            System.out.println(msg+"Row - A Seat No - "+(j+1));
                            break;
                        case 1:
                            System.out.println(msg+"Row - B Seat No - "+(j+1));
                            break;
                        case 2:
                            System.out.println(msg+"Row - C Seat No - "+(j+1));
                            break;
                        case 3:
                            System.out.println(msg+"Row - D Seat No - "+(j+1));
                            break;
                    }
                    return;
                }
            }
        }
        System.out.println("No available seats");
    }

    //Method to display seat plan array
    private static void show_seating_plan(){
        for (int i=0;i< seat_plan.length;i++) {
            for (int s = 0; s < seat_plan[i].length; s++) {
                System.out.print(seat_plan[i][s] + " ");
            }
            System.out.println();
        }
    }
    //Creating Method Print ticket info after buying the seat using array
    private static void print_tickets_info(){
        double totalSales = 0.0;

        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.printticketinfo();
                totalSales += ticket.getPrice();
                System.out.println();
            }
        }
        System.out.println("The Total Sale of the session is : £" + totalSales);
    }
    //Method to search ticket and print the user info if seat sold
    private static void search_ticket() {
        try{
            boolean rowloop=false;
            String row="";
            // Loop for loop untill user enters correct row
            while (!rowloop){
                Scanner row_letter=new Scanner(System.in);
                System.out.print("Enter the row letter : ");
                row= row_letter.next();
                if (row.equalsIgnoreCase("A") || row.equalsIgnoreCase("B") || row.equalsIgnoreCase("C") || row.equalsIgnoreCase("D")){
                    rowloop=true;
                }
                else {
                    System.out.println("Invalid Row, Enter a valid row");
                }
            }
            int seat=0;
            // Loop for loop untill user enters correct seat according to row
            boolean seatloop=false;
            while (!seatloop){
                try{
                    Scanner seat_no=new Scanner(System.in);
                    System.out.print("Enter the seat number : ");
                    seat=seat_no.nextInt();
                    if (row.equalsIgnoreCase("A")&& (seat<=14) && (seat>0) || row.equalsIgnoreCase("D")&& (seat<=14) && (seat>0)){
                        System.out.println("You Selected seat " + seat + " in " + row + " Row");
                        seatloop=true;
                    }
                    else if (row.equalsIgnoreCase("B")&& (seat<=12) && (seat>0) || row.equalsIgnoreCase("C")&& (seat<=12) && (seat>0)){
                        System.out.println("You Selected seat " + seat + " in " + row + " Row");
                        seatloop=true;
                    }
                    else {
                        System.out.println("Invalid seat number according to row");
                    }
                }
                catch (InputMismatchException e){
                    System.out.println("Enter a valid seat No");
                }
            }
            boolean seatFound = false;
            // Searching ticket array if seat is sold
            for (Ticket ticket : tickets) {
                if (ticket != null && ticket.getRow().equalsIgnoreCase(row) && ticket.getSeat() == seat) {
                    seatFound = true;
                    ticket.printticketinfo();
                    System.out.println();
                    break;
                }
            }
            if (!seatFound) {
                System.out.println("This seat is available.");
            }
        }
        catch (InputMismatchException e){
            System.out.println("Enter correct row or Seat");
        }
    }
}