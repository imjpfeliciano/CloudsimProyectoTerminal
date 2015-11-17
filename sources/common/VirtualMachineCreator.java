package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.*;
;


/**
 * common.VmsCreator Creates VM Lists as per the User Requirements.
 * @author Linda J
 *
 */
public class VirtualMachineCreator {
    public static int getRandomInteger(int maximun, int minimum) {
        return ((int) (Math.random() * (maximun - minimum))) + minimum;
    }

    public static List<Vm> createVM(int user_id, int vms, int idShift) {
        LinkedList<Vm> list = new LinkedList<Vm>();
        vms = 15;
        //parámetros de las máquinas virtuales
        long size = 100000;  //MB
        int ram = 512;
        int mips = 250;
        long bw = 100000;
        int pesNumber = 1;   //Num de procesadores
        String vmm = "Xen";

        //Creamos las máquinas virtuales
        Vm[] vm = new Vm[vms];

        for (int i = 0; i < vms; i++) {
            //Modificar new CloudletSchedulerTimeShared por FCFS
            if (i % 2 == 0) {
                vm[i] = new Vm(idShift + i, user_id, mips * (getRandomInteger(10, 2)), pesNumber * 2, ram * 2, bw * 2,
                        size * 2, vmm, new CloudletSchedulerTimeShared());
            } else {
                vm[i] = new Vm(idShift + i, user_id, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            }
            list.add(vm[i]);
        }


        // Log.printLine("Maquinas virtuales creadas... Success");
        return list;
    }
}
