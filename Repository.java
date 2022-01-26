/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

/**
 *
 * @author paulh
 */
import Domain.Produs;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Repository {
    private final ArrayList<Produs> produsArrayList = new ArrayList<Produs>();
    private String fileName;

    public Repository(String fileName) {
        this.fileName = fileName;
    }

    public void readFromFile(){
        try
        {
            this.produsArrayList.clear();
            BufferedReader fileIn=new BufferedReader(new FileReader(this.fileName));
            String s;
            while((s=fileIn.readLine())!=null){
                String[] parts =s.split(",");

                int id=Integer.parseInt(parts[0]);
                String denumire=parts[1];
                int cantitate=Integer.parseInt(parts[2]);
                double pretInitial=Double.parseDouble(parts[3]);
                double pretExtern=Double.parseDouble(parts[4]);
                LocalDateTime data=LocalDateTime.parse(parts[5]);

                Produs p = new Produs(id,denumire,cantitate,pretInitial,pretExtern,data);
                this.produsArrayList.add(p);
            }
            fileIn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeOnFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
        for (Produs produs : this.produsArrayList)
        {
            String line = produs.getId() + "," + produs.getDenumire() + "," + produs.getCantitate() + "," +
                    produs.getPretInitial() + "," + produs.getPretExtern() + "," +produs.getData();
            line += "\n";
            writer.write(line);
        }
        writer.close();
    }

    public void addProdus(Produs p) throws IOException {
        this.produsArrayList.add(p);
        writeOnFile();
    }

    public void updateProdus(int id, Produs newProdus) throws IOException {
        for (Produs produs : produsArrayList)
        {
            if (produs.getId() == id)
            {
                produs.setDenumire(newProdus.getDenumire());
                produs.setCantitate(newProdus.getCantitate());
                produs.setPretInitial(newProdus.getPretInitial());
                produs.setPretExtern(newProdus.getPretExtern());
                produs.setData(newProdus.getData());
                break;
            }
        }
        writeOnFile();
    }

    public void deleteProdus(int id) throws IOException {
        for(int i=0; i<produsArrayList.size();i++)
        {
            if(produsArrayList.get(i).getId()==id)
            {
                this.produsArrayList.remove(produsArrayList.get(i));
                break;
            }
        }
        writeOnFile();
    }

    public ArrayList<Produs> show(){
        readFromFile();
        return this.produsArrayList;
    }
}
