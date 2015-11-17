package FCFS;

import PSO.Particle;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.Host;
import PSO.FitnessFunction;

/**
 * A Broker that schedules Tasks to the VMs 
 * as per FCFS Scheduling Policy
 * @author Linda J
 *
 */
public class FcfsBroker extends DatacenterBroker {

	public FcfsBroker(String name) throws Exception {
		super(name);
		// TODO Auto-generated constructor stub
	}

	//scheduling function
	public void scheduleTaskstoVms(){
		int reqTasks=cloudletList.size();
		int reqVms=vmList.size();
		double  foo = cloudletList.get(0).getCostPerSec(vmList.get(0).getId());

		FitnessFunction pso = new FitnessFunction();

		//pso.evaluate((Particle)cloudletList.get(2),vmList);






		System.out.print("Valor de foooo ---->"+foo);
		for(int i=0 ; i < reqTasks ; i++){

			for(int j=0; j<reqVms; j++){
				Host host = vmList.get(j).getHost();
				Vm vm = vmList.get(j);
				 foo = cloudletList.get(i).getActualCPUTime(vm.getId());

				System.out.print("Valor de la tarea :" + i +  " en la VM :"+ j+ " : "+ foo);
				System.out.print("\n");
			}
		}
		System.out.println("\n\tFCFS Broker Schedules\n");
    	for(int i=0;i<reqTasks;i++){
    		bindCloudletToVm(i, (i%reqVms));
    		System.out.println("Task"+cloudletList.get(i).getCloudletId()+" is bound with VM"+vmList.get(i%reqVms).getId() +  "tam: " + cloudletList.get(i).getCloudletFileSize());
    	}
    	
    	System.out.println("\n");
	}
}
