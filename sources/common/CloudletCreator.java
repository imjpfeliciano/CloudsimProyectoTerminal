package common;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * common.DataCenterCreator.common.CloudletCreator Creates Cloudlets as per the User Requirements.
 * @author Linda J
 *
 */
public class CloudletCreator {

    public static int getRandomInteger(int maximun, int minimum){
        return ((int)(Math.random() * (maximun - minimum))) + minimum;
    }
    public static List<Cloudlet> createTasks(int userId, int cloudlets, int idShift){
        LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
        cloudlets = 500;
        Random rand = new Random();
        //parámetros de las tareas
        long length = 10000;
        long fileSize = 8000;
        long outputSize = 10000;
        int pesNuimber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        Cloudlet[] cloudlet = new Cloudlet[cloudlets];

        for(int i=0; i<cloudlets; i++){
            cloudlet[i] = new Cloudlet(idShift + i, length * getRandomInteger(100, 1), pesNuimber, fileSize * (getRandomInteger(10, 1)), outputSize * (getRandomInteger(100, 1)), utilizationModel, utilizationModel, utilizationModel);
            cloudlet[i].setUserId(userId);
            list.add(cloudlet[i]);
        }

        return list;
    }
}
