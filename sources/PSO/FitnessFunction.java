package PSO;

import PSO.Particle;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.Cloudlet;
import common.CloudletUtilities;

import java.util.List;

/**
 * Created by Jake The Dog on 15/11/15.
 */
public class FitnessFunction {

    public double evaluate(Cloudlet particle, Vm vm) {
        CloudletUtilities utilities = new CloudletUtilities();

        System.out.println("Begin PSO RESULT");
        //  Vm vm = vms.get(particle.getPosition());


        //System.out.println( "Ram " + vm.getRam() );
        // System.out.println( "proces " + particle.getCloudletLength() );
        // System.out.println( "calculate " +t );

        System.out.println("cots2 " + ((particle.getProcessingCost() * vm.getMips()) * 0.000000001) + (vm.getSize() * particle.getCostPerSec()));
        // System.out.println("cots " + utilities.getProcessingCostBefore(vm, particle));


        double foo = particle.getProcessingCost();
        //System.out.println( t );

        System.out.println("END PSO RESULT");


        return utilities.getProcessingCostBefore(vm, particle);
    }
    /*
    public PSOResult evaluate(Particle particle , List<Vm> vms){
        PSOResult result =  new PSOResult();
        for(int i = 0; i < vms.size(); i++){
            System.out.println( particle.getCostPerSec(vms.get(i).getId()) );
        }

        result.setCost(0.1);
        result.setVmId(1);

        return result;
    }*/


}
