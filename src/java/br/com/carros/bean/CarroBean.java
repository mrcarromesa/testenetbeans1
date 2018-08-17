/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carros.bean;

import br.com.carros.dao.CarrosDAO;
import br.com.carros.entity.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author carlosrodolfosantos
 */
@ManagedBean
@SessionScoped
public class CarroBean {

    private Carro carro = new Carro();
    private Carro carroUpdate = new Carro();
    private List<Carro> carros = new ArrayList<>();
    private final CarrosDAO carroDao = new CarrosDAO();
    
    public CarroBean() {
        this.listar();
    }

    public void adicionar() {
        if (carros.indexOf(this.carroUpdate) > -1) {
            carroDao.alterar(carro, this.carroUpdate.getId());
            carros.set(carros.indexOf(this.carroUpdate), carro);
        } else {
            int id = carroDao.salvar(carro);
            carro.setId(id);
            carros.add(carro);
            
            //carro = new Carro();    
            
        }
        this.carroUpdate = new Carro();
        carro = new Carro();
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    
    public final void listar(){
        this.carros = carroDao.lista();
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
    
    public void removeItem(Carro carro) {
        new CarrosDAO().deletar(carro.getId());
        this.carros.remove(carro);
        //return this.carros;
    }
    
    public void editItem(Carro carro) {
        this.carroUpdate = carro;
        this.carro = carro;
    }

}
