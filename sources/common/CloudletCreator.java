package common;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;

import java.util.ArrayList;

/**
 * common.DataCenterCreator.common.CloudletCreator Creates Cloudlets as per the User Requirements.
 * @author Linda J
 *
 */
public class CloudletCreator {

    public ArrayList<Cloudlet> createTasks(int brokerId){
        ArrayList<Cloudlet> cloudletList = new ArrayList<Cloudlet>();

        //int id = 0;
        int pesNumber = 1;
        int[] length = new int[]{9000, 2000, 3000, 7000, 5000, 6000, 4000, 8000, 2000, 5000};
        int fileSize = 300;
        int outputSize = 300;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        for(int id=0; id < length.length; id++){
            Cloudlet task = new Cloudlet(id, length[id] * 10, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            task.setUserId(brokerId);

            System.out.println("tam de la Tarea " + id + " = " + task.getCloudletLength());
            cloudletList.add(task);
        }

        // Podemos agregar estaticamente las tareas
        // cloudletList.add(new Cloudlet(0, 1000, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel));


        return cloudletList;
    }


}
