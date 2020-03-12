package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private void add(ArrayList<Contact> arrayList){

        System.out.print("Enter the name of the person:");
        Scanner scanner=new Scanner(System.in);
        String name=scanner.nextLine();
        System.out.print("Enter the surname of the person:");
        String surname=scanner.nextLine();
        System.out.print("Enter the number:");
        String ph=scanner.nextLine();;
        Contact contact=new Contact(name, surname, ph);
        arrayList.add(contact);
        System.out.println("A record created");
        System.out.println("A Phone Book with a single record created!");
        scanner.close();
    }

    private void list(ArrayList<Contact> arrayList)
    {
        int cnt=1;
        if (!arrayList.isEmpty()) {

            for (Contact c : arrayList) {
                String s1 = "".equals(c.getPhoneNumber())? "[no number]" : c.getPhoneNumber();
                String str = String.format("%d\u002E %s %s, %s", cnt, c.getName(), c.getSurname(), s1);
                System.out.println(str);
                cnt++;
            }
        }
        else
            System.out.println("The Phone Book has 0 records.");
    }

    private void remove( ArrayList<Contact> arrayList )
    {
        //printList(arrayList);
        System.out.print("Select a record:");
        Scanner scanner = new Scanner(System.in);
        int index=scanner.nextInt();
        if (index<=arrayList.size() && index>0) {
            arrayList.remove(index - 1);
            System.out.println("The record remove!");
        }
        else
            System.out.println("Wrong selection");
        scanner.close();
    }

    private void edit(ArrayList<Contact> arrayList)
    {
        if (!arrayList.isEmpty())
        {
            list(arrayList);
            System.out.print("Select a record:");
            Scanner scanner = new Scanner(System.in);
            int index=0;
            index = Integer.parseInt(scanner.nextLine());
            if (index<=arrayList.size() && index>0) {
                Contact c=arrayList.get(index - 1);
                System.out.print("Select a field (name, surname, number):");
                String selection=scanner.nextLine();
                switch (selection)
                {
                    case "name": {
                        System.out.print("Enter name:");
                        c.setName(scanner.nextLine());
                        break;
                    }
                    case "surname":{
                        System.out.print("Enter surname:");
                        c.setSurname(scanner.nextLine());
                        break;
                    }
                    case "number":{
                        System.out.print("Enter number:");
                        c.setPhoneNumber(scanner.nextLine());
                        break;
                    }
                    default:{
                        System.out.println("Wrong selection");
                        break;
                    }
                }
                scanner.close();
                arrayList.set(index-1,c);
                System.out.println("The record updated!");
            }
        }
        else
            System.out.println("No records to edit!");
    }

    private void count(ArrayList<Contact> arrayList)
    {
        int cnt = arrayList.isEmpty()? 0 : (arrayList.size());
        System.out.println("The Phone Book has "+cnt+" records.");
    }

    public void start()
    {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        boolean isRun=true;
        while(isRun)
        {
            System.out.print("Enter action (add, remove, edit, count, list, exit):");
            Scanner cmdSc = new Scanner(System.in);
            String cmd = cmdSc.nextLine();
            switch (cmd)
            {
                case "add":
                    add(contacts);
                    break;
                case "remove":
                    remove(contacts);
                    break;
                case "edit":
                    edit(contacts);
                    break;
                case "count":
                    count(contacts);
                    break;
                case "list":
                    list(contacts);
                    break;
                case "exit":
                    isRun=false;
                    break;
                default:
                    System.out.println("Error command!");
                    break;

            }
            cmdSc.close();
        }
    }
}
