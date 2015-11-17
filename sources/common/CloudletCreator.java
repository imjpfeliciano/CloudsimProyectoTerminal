package common;

import PSO.Particle;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * common.DataCenterCreator.common.CloudletCreator Creates Cloudlets as per the User Requirements.
 *
 *
 */
public class CloudletCreator {

    public static int getRandomInteger(int maximun, int minimum){
        return ((int)(Math.random() * (maximun - minimum))) + minimum;
    }

    public static List<Particle> createParticles(int userId, int cloudlets, int idShift) {
        LinkedList<Particle> list = new LinkedList<Particle>();
        cloudlets = 50;
        Random rand = new Random();
        //parámetros de las tareas
        long length = 100;
        long fileSize = 10;
        long outputSize = 10;
        int pesNuimber = 1;

        double costPerSec = 0.00003;
        double costPerBw = 0.00003;

        UtilizationModel utilizationModel = new UtilizationModelFull();

        Particle[] cloudlet = new Particle[cloudlets];

        for (int i = 0; i < cloudlets; i++) {
            long lenght_i = length * getRandomInteger(1000, 100);
            long fileSize_i = fileSize * (getRandomInteger(209715, 100));
            long outputSize_i = outputSize * (getRandomInteger(209715, 100));

            cloudlet[i] = new Particle(idShift + i, lenght_i, pesNuimber, fileSize_i, outputSize_i, utilizationModel,
                    utilizationModel, utilizationModel);
            cloudlet[i].setUserId(userId);
            cloudlet[i].setResourceParameter(userId, costPerSec * lenght_i, costPerBw * outputSize_i);
            list.add(cloudlet[i]);
        }

        return list;
    }
    public static List<Cloudlet> createTasks(int userId, int cloudlets, int idShift){
        LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();
        cloudlets = 50;
        Random rand = new Random();
        //parámetros de las tareas
        long length = 100;
        long fileSize = 10;
        long outputSize = 10;
        int pesNuimber = 1;

        double costPerSec = 0.00003;
        double costPerBw = 0.00003;

        UtilizationModel utilizationModel = new UtilizationModelFull();

        Cloudlet[] cloudlet = new Cloudlet[cloudlets];

        for(int i=0; i<cloudlets; i++){
            long lenght_i = length * getRandomInteger(1000, 100);
            long fileSize_i = fileSize * (getRandomInteger(209715, 100));
            long outputSize_i = outputSize * (getRandomInteger(209715, 100));

            cloudlet[i] = new Cloudlet(idShift + i, lenght_i, pesNuimber, fileSize_i, outputSize_i, utilizationModel,
                    utilizationModel, utilizationModel);
            cloudlet[i].setUserId(userId);
            cloudlet[i].setResourceParameter(userId, costPerSec * lenght_i, costPerBw * outputSize_i);
            list.add(cloudlet[i]);
        }

        return list;
    }
}
