package Minmin;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.lists.CloudletList;

import static java.util.Comparator.comparing;

import java.util.Collections;

public class MinminBroker extends DatacenterBroker {

    public MinminBroker(String name) throws Exception {
        super(name);
        // TODO Auto-generated constructor stub
    }

    //scheduling function
    public void scheduleTaskstoVms(){
        int numTareas = cloudletList.size();
        int numVirtualM = vmList.size();

        System.out.println("\n\tMinmin Broker Schedules\n");

        CloudletList.sort(cloudletList);

        //Asignamos las tareas a cada máquina virtual de manera que se atiendan primero las de menor tamaño
        for(int i=0;i<numTareas;i++){
            bindCloudletToVm(i, (i%numVirtualM));
            System.out.println("Task"+cloudletList.get(i).getCloudletId()+" is bound with VM"+vmList.get(i%numVirtualM).getId() +  " tam: " + cloudletList.get(i).getCloudletLength());
        }


        System.out.println("\n");
    }
}
