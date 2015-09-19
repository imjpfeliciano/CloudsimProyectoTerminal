/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 */
package Minmin;

import java.util.*;

import common.CloudletUtilities;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import common.VirtualMachineCreator;
import common.CloudletCreator;
import common.DataCenterCreator;


public class Minmin {

    //Lista de tareas
    private static List<Cloudlet> cloudletList;

    // Lista de máquinas virtuales
    private static List<Vm> vmlist;

    public static void main(String[] args) {

        Log.printLine("Iniciando Minmin...");

        try {
            // Primer paso: inicializar Cloudsim. Se debe inicializar antes de crear cualquier entidad

            int num_user = 1;   // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Segundo Paso: crear el datacenter
            @SuppressWarnings("unused")
            Datacenter datacenter0 =  new DataCenterCreator().createDatacenter("Datacenter_0");

            //Tercer paso: Crear el broker
            MinminBroker broker = createBroker();
            int brokerId = broker.getId();

            // Cuarto paso: Crear las máquinas virtuales
            vmlist = new VirtualMachineCreator().createVM(brokerId, 5, 0);
            broker.submitVmList(vmlist);


            // Quinto paso: Crear la lista de tareas
            cloudletList = new CloudletCreator().createTasks(brokerId, 200, 0);
            broker.submitCloudletList(cloudletList);

            //call the scheduling function via the broker
            broker.scheduleTaskstoVms();

            // Sexto paso: Iniciamos la simulación
            CloudSim.startSimulation();

            // Paso final: imprimir resultados
            List<Cloudlet> newList = broker.getCloudletReceivedList();

            CloudSim.stopSimulation();

            CloudletUtilities.printCloudletList(newList);

            Log.printLine("Minmin finished!");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
    }



    //We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
    //to the specific rules of the simulated scenario
    private static MinminBroker createBroker(){

        MinminBroker broker = null;
        try {
            broker = new MinminBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }


}
