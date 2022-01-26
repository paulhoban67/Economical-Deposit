/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author paulh
 */
import java.time.LocalDateTime;

public class Produs {
    private int id;
    private String denumire;
    private int cantitate;
    private double pretInitial;
    private double pretExtern;
    private LocalDateTime data;

    public Produs(int id, String denumire, int cantitate, double pretInitial, double pretExtern, LocalDateTime data) {
        this.id = id;
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.pretInitial = pretInitial;
        this.pretExtern = pretExtern;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public double getPretInitial() {
        return pretInitial;
    }

    public void setPretInitial(double pretInitial) {
        this.pretInitial = pretInitial;
    }

    public double getPretExtern() {
        return pretExtern;
    }

    public void setPretExtern(double pretExtern) {
        this.pretExtern = pretExtern;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", cantitate=" + cantitate +
                ", pretInitial=" + pretInitial +
                ", pretExtern=" + pretExtern +
                ", data=" + data +
                '}';
    }
}

