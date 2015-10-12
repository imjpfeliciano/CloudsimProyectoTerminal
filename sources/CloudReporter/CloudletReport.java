package CloudReporter;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;

import java.security.acl.LastOwnerException;
import java.util.List;
/**
 * Created by Jake The Dog on 19/09/15.
 */
public class CloudletReport {

   // public int[]
    public double[] getTotalCost(List<Cloudlet> list){
        double totalProcessingCost = 0.0;
        double totalTime=0.0,totalUtilizationRam=0.0,totalUtilizationCPU=0.0;
        String values = "{";
        for(int i = 0; i< list.size(); i++){
            totalProcessingCost+= list.get(i).getProcessingCost();
            totalTime+= list.get(i).getActualCPUTime();
            values+= Double.toString(list.get(i).getActualCPUTime()) +",";
            totalUtilizationRam += list.get(i).getUtilizationOfRam(list.get(i).getActualCPUTime());
            totalUtilizationCPU += list.get(i).getUtilizationOfCpu(list.get(i).getActualCPUTime());
        }
        values=values+"}";
        Log.printLine(list.size());
        Log.printLine(values);
        double n = list.size();

        double[] result = {totalProcessingCost/n,totalTime/n,totalUtilizationRam/n,totalUtilizationCPU/n};
        return result;

    }



}
