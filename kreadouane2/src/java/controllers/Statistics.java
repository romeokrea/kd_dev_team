/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import sessions.TransporteurFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import sessions.CadFacadeLocal;
import sessions.AcconierFacadeLocal;

/**
 *
 * @author admin
 */
public class Statistics implements Serializable {

    @EJB
    private TransporteurFacadeLocal transporteurFacade;
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    @EJB
    private CadFacadeLocal cadFacade;
    @EJB
    private AcconierFacadeLocal acconierFacade;

    private BarChartModel barModel;
    private LineChartModel areaModel;

    private int numberTransporteur = 0;
    private int numberUtilisateur = 0;
    private int numberCad = 0;
    private int numberAcconier = 0;

    private Date d9 = new Date();
    private Date d10 = new Date();

    public Statistics() {
    }

    @PostConstruct
    public void init() {
        numberTransporteur = transporteurFacade.count();
        numberUtilisateur = utilisateurFacade.count();
        numberCad = cadFacade.count();
        numberAcconier = acconierFacade.count();

//        createBarModel();

    }

//    private void createBarModel() {
//        barModel = initBarModel();
//
//        barModel.setTitle("Sales per month");
//        barModel.setLegendPosition("ne");
//
//        Axis xAxis = barModel.getAxis(AxisType.X);
//        xAxis.setLabel("Months");
//
//        Axis yAxis = barModel.getAxis(AxisType.Y);
//        yAxis.setLabel("Sales");
//        yAxis.setMin(0);
//        yAxis.setMax(20);
//    }
//
//    private BarChartModel initBarModel() {
//        BarChartModel model = new BarChartModel();
//
////        Date d = new Date(2016-12-31);
////        Date d1 = new Date(2017-1-31);
////        Date d2 = new Date(2017-2-28);
////        Date d3 = new Date(2017-3-31);
////        Date d4 = new Date(2017-4-30);
////        Date d5 = new Date(2017-5-31);
////        Date d6 = new Date(2017-6-30);
////        Date d7 = new Date(2017-7-31);
////        Date d8 = new Date(2017-8-31);
////        Date d11 = new Date(2017-11-30);
////        Date d12 = new Date(2017-12-31);
//        ChartSeries sale = new ChartSeries();
//        sale.setLabel("Sales");
//        sale.set("JAN", 0);
//        sale.set("FEB", 0);
//        sale.set("MAR", 0);
//        sale.set("APL", 0);
//        sale.set("MAY", 0);
//        sale.set("JUN", 0);
//        sale.set("JUL", 0);
//        sale.set("AUG", 0);
//        sale.set("SEP", 0);
//        sale.set("OCT", saleFacade.findByMonth(sessionController.getCurrentOrganization().getId(), d9, d10));
//        sale.set("NOV", 0);
//        sale.set("DEC", 0);
//
//        model.addSeries(sale);
//
//        return model;
//    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public TransporteurFacadeLocal getTransporteurFacade() {
        return transporteurFacade;
    }

    public void setTransporteurFacade(TransporteurFacadeLocal transporteurFacade) {
        this.transporteurFacade = transporteurFacade;
    }

    public UtilisateurFacadeLocal getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(UtilisateurFacadeLocal utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }

    public CadFacadeLocal getCadFacade() {
        return cadFacade;
    }

    public void setCadFacade(CadFacadeLocal cadFacade) {
        this.cadFacade = cadFacade;
    }

    public AcconierFacadeLocal getAcconierFacade() {
        return acconierFacade;
    }

    public void setAcconierFacade(AcconierFacadeLocal acconierFacade) {
        this.acconierFacade = acconierFacade;
    }

    public int getNumberTransporteur() {
        return numberTransporteur;
    }

    public void setNumberTransporteur(int numberTransporteur) {
        this.numberTransporteur = numberTransporteur;
    }

    public int getNumberUtilisateur() {
        return numberUtilisateur;
    }

    public void setNumberUtilisateur(int numberUtilisateur) {
        this.numberUtilisateur = numberUtilisateur;
    }

    public int getNumberCad() {
        return numberCad;
    }

    public void setNumberCad(int numberCad) {
        this.numberCad = numberCad;
    }

    public int getNumberAcconier() {
        return numberAcconier;
    }

    public void setNumberAcconier(int numberAcconier) {
        this.numberAcconier = numberAcconier;
    }

    

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(LineChartModel areaModel) {
        this.areaModel = areaModel;
    }

    public Date getD9() {
        return d9;
    }

    public void setD9(Date d9) {
        this.d9 = d9;
    }

    public Date getD10() {
        return d10;
    }

    public void setD10(Date d10) {
        this.d10 = d10;
    }

}
