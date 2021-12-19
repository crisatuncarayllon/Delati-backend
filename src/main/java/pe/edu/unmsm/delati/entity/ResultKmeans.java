/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.delati.entity;

import java.util.ArrayList;
import weka.core.Instances;

/**
 *
 * @author CHRISTOPER
 */
public class ResultKmeans {
    
    ArrayList<NodeKmeans> clusters = new ArrayList();
    //arreglo columnas
    //arreglo clase data
    //arreglo de clase centroid
    String n_iter ="";
    int total_instances=0;
    private double sumaTotal=0;
    public ResultKmeans() {
    }
    
    public void initNodes(double[] nodes){
        NodeKmeans temporal = null;
        
        for(int i=0; i<nodes.length;i++){
            String name = "cluster "+(i+1);
            temporal = new NodeKmeans(name,nodes[i]);
            sumaTotal=sumaTotal+nodes[i];
            this.clusters.add(temporal);
        }
        modifyPerce(nodes);
    }
    
    public void modifyPerce(double[] nodes){
        for(int i = 0; i<clusters.size();i++){
            clusters.get(i).setPercentage((nodes[i]/sumaTotal)*100);
        }
    }

    public void setRelationNames(Instances instancias){
        for(int i = 0; i<clusters.size();i++){
            clusters.get(i).setTitle_cluster(instancias.instance(i).toString());
        }
    }
    
    public ArrayList<NodeKmeans> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<NodeKmeans> clusters) {
        this.clusters = clusters;
    }

    public String getN_iter() {
        return n_iter;
    }

    public void setN_iter(String n_iter) {
        this.n_iter = n_iter;
    }

    public int getTotal_instances() {
        return total_instances;
    }

    public void setTotal_instances(int total_instances) {
        this.total_instances = total_instances;
    }
    
   
}
