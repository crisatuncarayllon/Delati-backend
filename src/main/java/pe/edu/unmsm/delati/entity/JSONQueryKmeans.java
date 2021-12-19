/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.delati.entity;

import weka.clusterers.SimpleKMeans;
import weka.core.SelectedTag;

/**
 *
 * @author CHRISTOPER
 */
public class JSONQueryKmeans {
    private String type;
    private String query;
    private int n_clusters;
    private int init;// si es 0 se referira a "random"
    private int max_iter;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getN_clusters() {
        return n_clusters;
    }

    public void setN_clusters(int n_clusters) {
        this.n_clusters = n_clusters;
    }

    public SelectedTag getInit() {
        SelectedTag _init=null;
        if(init==0){
            _init= new SelectedTag(SimpleKMeans.RANDOM, SimpleKMeans.TAGS_SELECTION);
        }else if(init==1){
            _init= new SelectedTag(SimpleKMeans.KMEANS_PLUS_PLUS, SimpleKMeans.TAGS_SELECTION);
        }else if(init==2){
            _init= new SelectedTag(SimpleKMeans.CANOPY, SimpleKMeans.TAGS_SELECTION);
        }else if(init==3){
            _init= new SelectedTag(SimpleKMeans.FARTHEST_FIRST, SimpleKMeans.TAGS_SELECTION);
        }
         
        return _init;
    }

    public void setInit(int init) {
        this.init = init;
    }

 
    
    public int getMax_iter() {
        return max_iter;
    }

    public void setMax_iter(int max_iter) {
        this.max_iter = max_iter;
    }

    @Override
    public String toString() {
        return "JSONQueryKmeans{" + "type=" + type + ", query=" + query + '}';
    }
    
    
}
