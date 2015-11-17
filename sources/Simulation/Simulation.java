package Simulation;

import FCFS.FCFS;
import Heuristics.Heuristic;
import Maxmin.Maxmin;
import Minmin.Minmin;
import RoundRobin.RoundRobin;
import org.cloudbus.cloudsim.Cloudlet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import CloudReporter.CloudletReport;
import common.CloudletUtilities;

import java.io.*;

/**
 * Created by Jake The Dog on 28/10/15.
 */

public class Simulation {


    public static void main(String[] args) {
        String[] args2 = {};


        List<Cloudlet> MaxMinResult = Maxmin.main(args2);
        List<Cloudlet> MinMinResult = Minmin.main(args2);
        List<Cloudlet> RoundRobinResult = RoundRobin.main(args2);
        List<Cloudlet> FCFSResult = FCFS.main(args2);
        List<Cloudlet> HPSOResult = Heuristic.main(args2);

        List<List<Cloudlet>> datos = Arrays.asList(MaxMinResult, MinMinResult, FCFSResult, RoundRobinResult, HPSOResult);

        CloudletUtilities.reportSimulationPerCloudlet(datos);
        //Process p= Runtime.getRuntime().exec("RScript C:\\Users\\Jake The Dog\\IdeaProjects\\CloudsimProyectoTerminal\\external\\tiempoejecucionycosto.R");
        try {
            String command = "RScript C:\\Users\\Jake The Dog\\IdeaProjects\\CloudsimProyectoTerminal\\external\\tiempoEjecucionmuestras.R";
            Runtime.getRuntime().exec("cmd /c " + command);
            Process p = Runtime.getRuntime().exec("cmd /c " + command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e1) {
        } catch (InterruptedException e2) {
            System.out.println(e2.toString());
        }

        System.out.println("Done");



    }
}
