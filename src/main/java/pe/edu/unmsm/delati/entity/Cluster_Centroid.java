/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.delati.entity;

import java.util.ArrayList;

/**
 *
 * @author CHRISTOPER
 */
public class Cluster_Centroid {
    private String title_cluster;
    private double[] point ;

    
    public String getTitle_cluster() {
        return title_cluster;
    }

    public void setTitle_cluster(String title_cluster) {
        this.title_cluster = title_cluster;
    }

    public Cluster_Centroid(String title_cluster, double[] point) {
        this.title_cluster = title_cluster;
        this.point = point;
    }

    public double[] getPoint() {
        return point;
    }

    public void setPoint(double[] point) {
        this.point = point;
    }

  
    
    
}
