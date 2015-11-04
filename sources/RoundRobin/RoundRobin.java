package RoundRobin;

import java.util.*;

import common.CloudletUtilities;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import common.VirtualMachineCreator;
import common.CloudletCreator;
import common.DataCenterCreator;

/**
 * Created by jpfeliciano on 11/10/15.
 */
public class RoundRobin {
    private static List<Cloudlet> cloudletList;
    private static List<Vm> vmlist;

    public static List<Cloudlet> main(String[] args) {

        Log.printLine("Iniciando Round Robin...");
        try{
            int num_user = 1;
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;

            CloudSim.init(num_user, calendar, trace_flag);

            Datacenter datacenter0 = new DataCenterCreator().createDatacenter("Datacenter_0");

            RoundRobinBroker broker = createBroker();
            int brokerId = broker.getId();

            vmlist = new VirtualMachineCreator().createVM(brokerId, 5, 0);
            broker.submitVmList(vmlist);
            cloudletList = new CloudletCreator().createTasks(brokerId, 200, 0);
            broker.submitCloudletList(cloudletList);

            broker.scheduleTasksToVms();

            CloudSim.startSimulation();
            List<Cloudlet> newList = broker.getCloudletReceivedList();
            CloudSim.stopSimulation();

            CloudletUtilities.printCloudletList(newList);
            Log.printLine("Round Robin Finished");
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The Simulation has been terminated due to an unexpected error");
        }
        return null;
    }

    private static RoundRobinBroker createBroker(){
        RoundRobinBroker broker = null;
        try {
            broker = new RoundRobinBroker("Broker");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return broker;
    }

}
