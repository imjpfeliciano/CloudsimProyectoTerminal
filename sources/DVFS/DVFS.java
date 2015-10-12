package DVFS;

import FCFS.FcfsBroker;
import common.CloudletCreator;
import common.CloudletUtilities;
import common.DataCenterCreator;
import common.VirtualMachineCreator;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Jake The Dog on 08/10/15.
 */
public class DVFS {


    //Lista de tareas
    private static List<Cloudlet> cloudletList;

    // Lista de máquinas virtuales
    private static List<Vm> vmlist;

    public static void main(String[] args) {

        Log.printLine("Iniciando FCFS...");

        try {

            int num_user = 1;   // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Second step: Create Datacenters
            //Datacenters are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
            @SuppressWarnings("unused")
            Datacenter datacenter0 =  new DataCenterCreator().createDatacenter("Datacenter_0");

            //Third step: Create Broker
            //DatacenterBroker broker = createBroker();
            FcfsBroker broker = createBroker();
            int brokerId = broker.getId();

            //Fourth step: Create one virtual machine
            //vmlist = new VmsCreator().createVirtualMachines(brokerId);
            vmlist = new VirtualMachineCreator().createVM(brokerId, 5, 0);
            cloudletList = new CloudletCreator().createTasks(brokerId, 50, 0);

            Log.printLine("Initial Cloudlet :"+cloudletList.size());

            //submit vm list to the broker
            broker.submitVmList(vmlist);


            //Fifth step: Create two Cloudlets
            //cloudletList = new CloudletCreator().createTasks(brokerId);
            //cloudletList = new common.DataCenterCreator.common.CloudletCreator().createUserCloudlet(brokerId);

            //submit cloudlet list to the broker
            broker.submitCloudletList(cloudletList);


            //call the scheduling function via the broker
            broker.scheduleTaskstoVms();


            // Sixth step: Starts the simulation
            CloudSim.startSimulation();


            // Final step: Print results when simulation is over
            List<Cloudlet> newList = broker.getCloudletReceivedList();

            CloudSim.stopSimulation();

            CloudletUtilities.printCloudletList(newList);

            Log.printLine("FCFS finished!");

        }
        catch (Exception e){
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
    }

    }
