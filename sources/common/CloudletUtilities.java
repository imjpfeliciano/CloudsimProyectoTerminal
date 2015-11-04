package common;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Log;

import java.text.DecimalFormat;
import java.util.List;
import java.io.Writer;
import java.io.*;

import CloudReporter.CloudletReport;
/**
 * Created by jpfeliciano on 18/09/15.
 */
public class CloudletUtilities {

    public static void printCloudletList(List<Cloudlet> list) {
        int size = list.size();
        Cloudlet cloudlet;
        CloudletReport cloudletReport = new CloudletReport();
        double[] time = cloudletReport.getTotalCost(list);
        String[] name = {"totalProcessingCost","totalTime","totalUtilizationRam","totalUtilizationCPU"};



        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
                "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time" + indent + "Host ID");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            Log.print(indent + cloudlet.getCloudletId() + indent + indent);

            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
                Log.print("SUCCESS");

                Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
                        indent + indent + dft.format(cloudlet.getActualCPUTime()) + indent + indent + dft.format(cloudlet.getExecStartTime())+
                        indent + indent + dft.format(cloudlet.getFinishTime()) + indent + indent + indent + indent + cloudlet.getUtilizationOfRam(cloudlet.getActualCPUTime()));
            }
        }
        String tmp = "{";
        for(int i=0;i<time.length;i++){
            Log.printLine(name[i] + " :" + time[i]);
            tmp+= Double.toString(time[i]) + ",";
        }
        tmp+="}";
        Log.printLine(tmp);
        Log.printLine("total cloudlet :"+ list.size());

    }

    public static void reportCostTime(List<Cloudlet> list) {
        int size = list.size();
        CloudletReport cloudletReport = new CloudletReport();
        double[] time = cloudletReport.getTotalCost(list);
        String[] name = {"totalProcessingCost", "totalTime", "totalUtilizationRam", "totalUtilizationCPU"};
        String tmp = "{";
        for (int i = 0; i < time.length; i++) {
            Log.printLine(name[i] + " :" + time[i]);
            tmp += Double.toString(time[i]) + ",";
        }
        tmp += "}";
        Log.printLine(tmp);
        Log.printLine("total cloudlet :" + list.size());

    }

    public static void reportSimulationPerCloudlet(List<List<Cloudlet>> datos) {
        int size = datos.size();
        CloudletReport cloudletReport = new CloudletReport();

        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("executionTime.csv"), "utf-8"));
            writer.write("MinMin,Maxmin,FCFS,RR");
            writer.write("\n");
            for (int i = 0; i < datos.get(0).size(); i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(datos.get(j).get(i).getActualCPUTime() + "");
                    if (j != size - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }

        writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("executionCost.csv"), "utf-8"));
            writer.write("MinMin,Maxmin,FCFS,RR");
            writer.write("\n");
            for (int i = 0; i < datos.get(0).size(); i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(datos.get(j).get(i).getProcessingCost() + "");
                    if (j != size - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }

    }
}
