package common;

import java.util.ArrayList;

import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Vm;;


/**
 * common.VmsCreator Creates VM Lists as per the User Requirements.
 * @author Linda J
 *
 */
public class VmsCreator {
	
	//vmlist creator function
	public ArrayList<Vm> createVirtualMachines(int brokerId){
        int numVms = 3;
		ArrayList<Vm> vmlist = new ArrayList<Vm>();
		
    	//VM description
    	int vmid;
    	int mips = 1000;
    	long size = 10000; //image size (MB)
    	int ram = 512; //vm memory (MB)
    	long bw = 1000;
    	int pesNumber = 1; //number of cpus
    	String vmm = "Xen"; //VMM name

    	
    	
    	for(vmid = 0; vmid < numVms; vmid++){
    		//add the VMs to the vmList
    		vmlist.add(new Vm(vmid, brokerId, mips, pesNumber, ram, bw, 
    				size, vmm, new CloudletSchedulerTimeShared()));
    	}

    	System.out.println("Máquinas virtuales creadas... SUCCESS:)");
		return vmlist;
		
	}

}
