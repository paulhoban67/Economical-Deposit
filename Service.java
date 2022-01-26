/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author paulh
 */
import Domain.Produs;
import Repository.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Service {
    private final Repository repository;
    private double taxaVamala;
    private double comisionVamal;
    private double cursValutar;
    private double acciza;
    private double tva;

    public Service(Repository repository, double taxaVamala, double comisionVamal, double cursValutar, double acciza, double tva) {
        this.repository = repository;
        this.taxaVamala = taxaVamala;
        this.comisionVamal = comisionVamal;
        this.cursValutar = cursValutar;
        this.acciza = acciza;
        this.tva = tva;
    }

    public double getTaxaVamala() {
        return taxaVamala;
    }

    public void setTaxaVamala(double taxaVamala) {
        this.taxaVamala = taxaVamala;
    }

    public double getComisionVamal() {
        return comisionVamal;
    }

    public void setComisionVamal(double comisionVamal) {
        this.comisionVamal = comisionVamal;
    }

    public double getCursValutar() {
        return cursValutar;
    }

    public void setCursValutar(double cursValutar) {
        this.cursValutar = cursValutar;
    }

    public double getAcciza() {
        return acciza;
    }

    public void setAcciza(double acciza) {
        this.acciza = acciza;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public void addProdus(int id, String denumire, int cantitate, double pretInitial, double pretExtern, LocalDateTime data) throws Exception {
        Produs p = new Produs(id, denumire, cantitate, pretInitial, pretExtern, data);
        repository.addProdus(p);
        }

    public void updateProdus(int id, String denumire, int cantitate, double pretInitial, double pretExtern, LocalDateTime data) throws Exception {
        Produs p = new Produs(id, denumire, cantitate, pretInitial, pretExtern, data);
        repository.updateProdus(id, p);

    }

    public void deleteProdus(int id) throws Exception {
       repository.deleteProdus(id);
    }

    public ArrayList<Produs> showProduse(){
        return repository.show();
    }
    
    public int getPosition(int id) {
        int pos = -1;
        for(Produs produs : repository.show()) {
            pos++;
            if(produs.getId() == id) {
                return pos;
            }
        }
        return -1;
    }
    
    public boolean equalsDates(LocalDateTime data1, LocalDateTime data2) {
        if((data1.getYear() == data2.getYear() && data1.getMonth() == data2.getMonth() && data1.getDayOfMonth() == data2.getDayOfMonth() ) &&
                (data1.getHour() == data2.getHour() && data1.getMinute() == data2.getMinute() && data1.getSecond() == data2.getSecond()))
            return true;
        return false;
    }
    
    public int getIdByDate(LocalDateTime data) {
        for(Produs produs : repository.show()) {
            if(equalsDates(produs.getData(), data)) {
                return produs.getId();
            }
        }
        return -1;
    }

    public double valoareaVama(int id) {
        System.out.println(" ");
        System.out.println(cursValutar);
        System.out.println(" ");
        for(Produs produs : repository.show()) {
            if(produs.getId() == id) {
                double val = produs.getCantitate() * produs.getPretInitial() * cursValutar + produs.getPretExtern() * cursValutar;
                return val;
            }
        }
        return -1;
    }

    public double taxeVamale(int id) {
        for(Produs produs : repository.show()) {
            if(produs.getId() == id) {
                System.out.println(getTaxaVamala());
                double val = valoareaVama(id) * taxaVamala/100;
                return val;
            }
        }
        return -1;
    }

    public double comisioaneVamale(int id) {
        for(Produs produs : repository.show()) {
            if(produs.getId() == id) {
                double val = valoareaVama(id) * comisionVamal/100;
                return val;
            }
        }
        return -1;
    }

    public double accize(int id) {
        for(Produs produs : repository.show()) {
            if(produs.getId() == id) {
                double val = ( valoareaVama(id) + taxeVamale(id) + comisioaneVamale(id) ) * acciza/100;
                return val;
            }
        }
        return -1;
    }

    public double tva(int id) {
        for (Produs produs : repository.show()) {
            if (produs.getId() == id) {
                double val = (valoareaVama(id) + taxeVamale(id) + comisioaneVamale(id) + accize(id) ) * tva / 100;
                return val;
            }
        }
        return -1;
    }
}
