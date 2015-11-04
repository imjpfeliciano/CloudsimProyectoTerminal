package Simulation;

import FCFS.FCFS;
import Maxmin.Maxmin;
import Minmin.Minmin;
import RoundRobin.RoundRobin;
import org.cloudbus.cloudsim.Cloudlet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import CloudReporter.CloudletReport;
import common.CloudletUtilities;

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
        List<List<Cloudlet>> datos = Arrays.asList(MaxMinResult, MinMinResult, FCFSResult, RoundRobinResult);

        CloudletUtilities.reportSimulationPerCloudlet(datos);


    }
}
