import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    // instance variables
    private String row;
    private int seat;
    private int price;

    private Person person;

    // creating the constructor
    public Ticket(String row,int seat,int price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }

    public void setRow(String row){
        this.row=row;
    }
    public String getRow(){
        return row;
    }

    public void setSeat(int seat){
        this.seat=seat;
    }
    public int getSeat(){
        return seat;
    }

    public void setPrice(int price){
        this.price=price;
    }
    public int getPrice(){
        return price;
    }

    public void setPerson(Person person){
        this.person=person;
    }
    public Person getPerson(){
        return person;
    }
    //printing the ticket information and saving information to text file
    public void printticketinfo(){
        System.out.println("Ticket Informations");
        System.out.println("Row No is : " + row);
        System.out.println("Seat Number is :" + seat);
        System.out.println("Price of the seat is : £" +price);
        person.printdetails();
    }

    //Creating method to save the ticket info to a text file
    public void save() {
        String fileName = row + seat;

        try  {
            FileWriter fileWriter = new FileWriter(fileName+".txt");
            fileWriter.write("Ticket Information:\n");
            fileWriter.write("Row: " + row + "\n");
            fileWriter.write("Seat: " + seat + "\n");
            fileWriter.write("Price: " + price + "\n");
            fileWriter.write("Person Information:\n");
            fileWriter.write("Name: " + person.getName() + "\n");
            fileWriter.write("Surname: " + person.getSurname() + "\n");
            fileWriter.write("Email: " + person.getEmail() + "\n");
            fileWriter.flush();

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to add data into a text file" + e.getMessage());
        }
    }
}
