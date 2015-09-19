package Maxmin;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.lists.CloudletList;

public class MaxminBroker extends DatacenterBroker {

    public MaxminBroker(String name) throws Exception {
        super(name);
        // TODO Auto-generated constructor stub
    }

    //scheduling function
    public void scheduleTaskstoVms(){
        int reqTasks=cloudletList.size();
        int reqVms=vmList.size();

        System.out.println("\n\tMaxmin Broker Schedules\n");

        CloudletList.revert(cloudletList);

        for(int i=0;i<reqTasks;i++){
            bindCloudletToVm(i, (i%reqVms));
            System.out.println("Task"+cloudletList.get(i).getCloudletId()+" is bound with VM"+vmList.get(i%reqVms).getId() +  "tam: " + cloudletList.get(i).getCloudletLength());
        }

        System.out.println("\n");
    }
}
