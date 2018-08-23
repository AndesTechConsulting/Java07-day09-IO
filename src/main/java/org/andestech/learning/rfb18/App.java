package org.andestech.learning.rfb18;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Account implements Serializable
{
    private String fname, lname;
    private int id;
    private double money;


    Account(String fname, String lname, int id) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        money = Math.floor(new Random().nextDouble()*100000);
    }

    public String toString()
    {
        return String.format("id=%8d, person: %-8s %-8s; money: %10f ",id,fname,lname,money);
    }

}


public class App
{
    public static void main( String[] args ) throws IOException {
    String myCat = "C:\\Users\\and\\IdeaProjects\\data\\";


// Binary

//        try(DataOutputStream dos =
//                    new DataOutputStream(
//                            new FileOutputStream(myCat+"file3.bin")))
//        {
//            dos.writeDouble(123.456789);
//            dos.writeInt(77777777);
//            dos.writeLong(28347928479287L);
//
//        }
//        catch (IOException ex){ex.printStackTrace();}

        try(DataInputStream dis =
                    new DataInputStream(
                            new FileInputStream(myCat+"file3.bin")))
        {
//            dos.writeDouble(123.456789);
//            dos.writeInt(77777777);
//            dos.writeLong(28347928479287L);

         double d1 = dis.readDouble();
         int i1 = dis.readInt();
         long L1 = dis.readLong();

            //L1 = dis.readLong();
            //d1 = dis.readDouble();

          /*  long L1 = dis.readLong();
            int i1 = dis.readInt();

            double d1 = dis.readDouble();*/



            System.out.printf("%f | %d | %d", d1,i1,L1);

        }
        catch (IOException ex){ex.printStackTrace();}

    // RAF

     try(RandomAccessFile raf =
             new RandomAccessFile(myCat+"file4.bin","rw"))
     {
        String dataStr =  "DATA 01 TEST Новый";

        raf.write(dataStr.getBytes());
        raf.writeInt(12345);
        raf.write("Тест данных..".getBytes());

        int pos =  (dataStr.getBytes()).length;

        raf.seek(pos);
         System.out.println("\nPointer pos1: " + raf.getFilePointer());
        raf.writeInt(678901234);
         System.out.println("\nPointer pos2: " + raf.getFilePointer());


        raf.seek(pos);
        int aa = raf.readInt();
        System.out.println("File size: " + raf.length());
        System.out.println("\npos=" + pos + ",aa="+ aa);

     }
     catch (IOException ex){ex.printStackTrace();}


  // Data IO

        ArrayList<Account> accounts =
        new ArrayList<>(10);

        accounts.add(new Account("Andy","Wolf", 1625));
        accounts.add(new Account("Пётр","Первый", 987));
        accounts.add(new Account("Юрий","Иванов", 2));
        //....


        for(Account ac: accounts) System.out.println(ac);


    try( ObjectOutputStream dos =
            new ObjectOutputStream(new FileOutputStream(myCat+"accounts.bin")))
    {
        dos.writeObject(accounts);

    }
    catch(IOException ex){ex.printStackTrace();}

        ArrayList<Account> accounts2 =
                new ArrayList<>(10);

        try( ObjectInputStream dis =
                     new ObjectInputStream(new FileInputStream(myCat+"accounts.bin")))
        {
           accounts2 = (ArrayList<Account>)dis.readObject();

        }
        catch(IOException | ClassNotFoundException ex){ex.printStackTrace();}

        System.out.println("-----------------------------------------------------");
        for(Account ac: accounts2) System.out.println(ac);

    //--------------------------------------------------------


        System.in.read();
        System.out.println("++++++++++++++++++++++++++++++++");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext())
        {
            String res = sc.next();
            if(res.equals("exit")) break;
            System.out.println(res);
        }


    }

}
