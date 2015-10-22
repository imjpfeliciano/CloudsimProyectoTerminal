/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 */
package FCFS;

import java.util.*;

import common.CloudletUtilities;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import common.VirtualMachineCreator;
import common.CloudletCreator;
import common.DataCenterCreator;
/**
 * FCFS Task scheduling
 * @author Linda J
 */
public class FCFS {

	//Lista de tareas
	private static List<Cloudlet> cloudletList;

	// Lista de máquinas virtuales
	private static List<Vm> vmlist;






	public static void main(String[] args) {

		Log.printLine("Iniciando FCFS...");

        try {
            // First step: Initialize the CloudSim package. It should be called
                // before creating any entities.
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
        catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
    }



	    //We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
	    //to the specific rules of the simulated scenario
	    private static FcfsBroker createBroker(){

	    	FcfsBroker broker = null;
	        try {
				broker = new FcfsBroker("Broker");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	    	return broker;
	    }


}
