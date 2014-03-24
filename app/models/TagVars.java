/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Map;

/**
 *
 * @author Eduardo Medeiros
 */
public class TagVars {

    Map<String, Map<String, Object>> a;
    public double fi31;//vazao 1
    public double fi31Tot; //vazao total 1
    public double fy31; //posicionador vazao 1
    public double tic31Sp; //posicionador vazao 1
    public double controlOutput;
    public double percenOvshoot;
    public double tOvershoot;
    public double tSubida;
    public double tAcomodacao;
    public double valorTq2;
    public double valorTq1;
    public double ganhoP;
    public double ganhoI;
    public double ganhoD;
    public double ganhoPID;
}
