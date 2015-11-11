package RoundRobin;

import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.lists.CloudletList;

/**
 * Created by jpfeliciano on 11/10/15.
 */
public class RoundRobinBroker extends DatacenterBroker{

    public RoundRobinBroker(String name) throws Exception {
        super(name);
    }

    public int get_quantum(){
        int numTareas = cloudletList.size();
        int resultado = 0;

        for(int i = 0; i < numTareas; i++){
            resultado += cloudletList.get(i).getActualCPUTime();
        }

        return resultado /= numTareas;
    }

    public void scheduleTasksToVms(){
        int numTareas = cloudletList.size();
        int numVirtualM = vmList.size();

        Log.printLine("Round Robin Scheduler");
        int index = 0;
        int i = 0;
        while( index < numTareas ){
            long capacidad = vmList.get(i).getBw();
            long costo_actual = 0;
            long currentCost = (costo_actual + cloudletList.get(index).getCloudletLength()) / 100;
            while (currentCost <= capacidad && index < numTareas) {
                costo_actual += cloudletList.get(index).getCloudletLength();
                bindCloudletToVm(index, i);
                System.out.println("Task" + cloudletList.get(index).getCloudletId() + " is bound with VM" + vmList.get(i).getId() + " tam: " + cloudletList.get(index).getCloudletLength());
                index++;
                if(index >= numTareas) {
                    //Si terminamos de asignar todas las tareas
                    break;
                }

            }

            i++;

            if(i >= numVirtualM) {
                //Reiniciamos la ronda de asignación a la máquina 0
                i = 0;
            }
        }
    }

}
