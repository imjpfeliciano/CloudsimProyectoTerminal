package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 * DatacenterCreator Creates Datacenter as per the requirements from the user.
 * @author Linda J
 *
 */

public class DataCenterCreator {


    public static Datacenter createDatacenter(String name){
        int numHosts = 30;
        //Pasos para crear un datacenter
        // 1. Necesitamos crear una lista para almacenar nuestra máquina
        List<Host> hostList = new ArrayList<Host>();
        // 2. Una máquina contiene uno o más PEs o CPUs/Cores.
        List<Pe> peList = new ArrayList<Pe>();
        int mips = 10000;
        // 3. Crear los PEs y agregarlos a la lista.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        //4. Create Host with its id and list of PEs and add them to the list of machines
        int hostId = 0;
        int ram = 2048 * 2; //host memory (MB)
        long storage = 1000000; //host storage
        int bw = 1000000;

        //Agregamos N máquinas a nuestro data center
        for(hostId = 0; hostId < numHosts; hostId++){
            hostList.add(
                    new Host(
                            hostId,
                            new RamProvisionerSimple(ram),
                            new BwProvisionerSimple(bw),
                            storage,
                            peList,
                            new VmSchedulerTimeShared(peList)
                    )
            );
        }



        // 5. Create a DatacenterCharacteristics object that stores the
        //    properties of a data center: architecture, OS, list of
        //    Machines, allocation policy: time- or space-shared, time zone
        //    and its price (G$/Pe time unit).
        String arch = "x86";      // system architecture
        String os = "Linux";          // operating system
        String vmm = "Xen";
        double time_zone = 10.0;         // time zone this resource located
        double cost = 2.0;              // the cost of using processing in this resource
        double costPerMem = 0.05;        // the cost of using memory in this resource
        double costPerStorage = 0.1;    // the cost of using storage in this resource
        double costPerBw = 0.2;            // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>();    //we are not adding SAN devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);


        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("Completo. El datacenter ha sido creado.");
        return datacenter;

    }
}