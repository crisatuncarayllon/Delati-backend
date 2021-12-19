/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.delati.entity;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.util.ArrayList;
import pe.edu.unmsm.delati.config.Connection;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.DatabaseLoader;

/**
 *
 * @author CHRISTOPER
 */
public class ResultKmeansDAO {
    Instances data=null;
    SimpleKMeans kmeans;
    
    public ResultKmeansDAO(String Query) throws IOException{
        Connection con = new Connection();
        DatabaseLoader db_giinwedb = con.getConnection(Query);
        if(Query.isEmpty()){
            db_giinwedb = con.getConnection("select distinct o.htitulo_cat, o.htitulo \n" +
            "from webscraping w inner join oferta o on \n" +
            "(w.id_webscraping=o.id_webscraping) \n" +
            "where o.id_estado is null order by 1,2;");
        }else{
            db_giinwedb = con.getConnection(Query);
        }
        
        data = new Instances(db_giinwedb.getDataSet());
    }
    
    public ResultKmeans getResult(JSONQueryKmeans request){
        if(request==null){
            return null;
        }else{
            return getKmeans(request);
        }
    }
    //ArrayList<NodeKmeans>
    
    public ResultKmeans getKmeans(JSONQueryKmeans request){
        kmeans = new SimpleKMeans();
        
        try{
            
            kmeans.setPreserveInstancesOrder(true);
            kmeans.setSeed(10);
            kmeans.setInitializationMethod(request.getInit());
            kmeans.setNumClusters(request.getN_clusters());
            kmeans.setMaxIterations(request.getMax_iter());
            kmeans.buildClusterer(data);
            double[] sizes=kmeans.getClusterSizes();
            //int[] centroid=kmeans.getAssignments();
            
            Instances instancias = kmeans.getClusterCentroids();
            ResultKmeans result = new ResultKmeans();
            result.initNodes(sizes);
            result.setRelationNames(instancias);
            result.setTotal_instances(data.size());
            
            /*Probando valores*/
            //System.out.println(kmeans);
            
            
            //para obtener los puntos
            for(int i=0;i<instancias.numInstances();i++){
                System.out.println(instancias.get(i).toString());
                
                System.out.println(instancias.get(i).numValues());
                for(int k=0;k<instancias.get(i).numAttributes();k++){
                    System.out.println(instancias.get(i).attribute(k));
                    System.out.println(instancias.get(i).value(k));//Es el valor o puntos de cada atributo(14 atributos en el query 1) por cada cluster 
                }
            }
            
            
            



            //OBTENEMOS A QUE CLUSTER PERTENECE CADA INSTANCIA DEL 0 A 5 
            /*int[] centroid=kmeans.getAssignments();
            System.out.println("puntos de los clusters: "+ centroid.length);
            for(int a=0;a<centroid.length;a++){
                System.out.println(centroid[a]);
            }*/

            //PARA OBTENER EL NOMBRE DE CADA COLUMNA
            /*for(int i=0;i<data.numAttributes();i++){
                
                System.out.println(data.get(0).attribute(i).name());
                
            }*/
            
            //PARA OBTENER LOS DATOS DE CADA FILA POR COLUMNA (I)
            /*for(int i=0;i<data.numInstances();i++){
                
                System.out.println(data.get(i).stringValue(0));
                
            }*/
            
            
            /*Fin prueba de valores*/
            
            
            //System.out.println(instancias.instance(0).numValues());
            //System.out.println(instancias.instance(0).stringValue(13));
            //return result.getListNodes();
            return result;
        }catch(Exception e1){
            System.out.println("Fallo el metodo 'getKmeans': "+ e1);
            return null;
        }
    }
}
