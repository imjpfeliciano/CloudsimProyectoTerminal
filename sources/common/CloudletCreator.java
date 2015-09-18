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

    public static List<Cloudlet> createTasks(int userId, int cloudlets, int idShift){
        LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();

        Random rand = new Random();
        //parámetros de las tareas
        long length = 10000;
        long fileSize = 300;
        long outputSize = 300;
        int pesNuimber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        Cloudlet[] cloudlet = new Cloudlet[cloudlets];

        for(int i=0; i<cloudlets; i++){
            cloudlet[i] = new Cloudlet(idShift + i, length * (rand.nextInt() * 10 + 1), pesNuimber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            cloudlet[i].setUserId(userId);
            list.add(cloudlet[i]);
        }

        return list;
    }
}
