package Heuristics;

import Maxmin.MaxminBroker;
import PSO.Particle;
import common.CloudletCreator;
import common.CloudletUtilities;
import common.DataCenterCreator;
import common.VirtualMachineCreator;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;

import PSO.PSOUtilities;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Jake The Dog on 16/11/15.
 */
public class Heuristic {

    //Lista de tareas
    private static List<Cloudlet> cloudletList;
    private static List<Particle> particlesList;
    // Lista de máquinas virtuales
    private static List<Vm> vmlist;

    public static List<Cloudlet> main(String[] args) {

        Log.printLine("Iniciando Heuristica con PSO...");

        try {
            // Primer paso: inicializar Cloudsim. Se debe inicializar antes de crear cualquier entidad

            int num_user = 1;   // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events
            PSOUtilities psoUtilities = new PSOUtilities();
            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Segundo Paso: crear el datacenter
            @SuppressWarnings("unused")
            Datacenter datacenter0 = new DataCenterCreator().createDatacenter("Datacenter_0");

            //Tercer paso: Crear el broker
            HeuristicBroker broker = createBroker();
            int brokerId = broker.getId();

            // Cuarto paso: Crear las máquinas virtuales
            vmlist = new VirtualMachineCreator().createVM(brokerId, 5, 0);
            broker.submitVmList(vmlist);

            particlesList = new CloudletCreator().createParticles(brokerId, 50, 0);
            // Quinto paso: Crear la lista de tareas
            cloudletList = psoUtilities.particles2Cloudlets(particlesList); //new CloudletCreator().createParticles(brokerId, 50, 0);
            broker.submitCloudletList(cloudletList);

            //call the scheduling function via the broker
            broker.scheduleTaskstoVms(particlesList);

            // Sexto paso: Iniciamos la simulación
            CloudSim.startSimulation();

            // Paso final: imprimir resultados
            List<Cloudlet> newList = broker.getCloudletReceivedList();

            CloudSim.stopSimulation();

            CloudletUtilities.printCloudletList(newList);

            Log.printLine("Heuristic PSO  finished!");
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
        return null;
    }


    //We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
    //to the specific rules of the simulated scenario
    private static HeuristicBroker createBroker() {

        HeuristicBroker broker = null;
        try {
            broker = new HeuristicBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }
}
