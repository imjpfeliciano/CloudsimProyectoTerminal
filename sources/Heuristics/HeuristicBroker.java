package Heuristics;

import PSO.PSOResult;
import PSO.PSOUtilities;
import PSO.Particle;
import PSO.Swarm;

import org.cloudbus.cloudsim.DatacenterBroker;

import java.util.List;

/**
 * Created by Jake The Dog on 16/11/15.
 */
public class HeuristicBroker extends DatacenterBroker {

    public HeuristicBroker(String name) throws Exception {
        super(name);
    }

    public void scheduleTaskstoVms(List<Particle> particleList) {
        PSOUtilities psoUtilities = new PSOUtilities();
        //List<Particle> particles = psoUtilities.parseParticles(cloudletList);
        Swarm swarm = new Swarm();
        PSOResult result = swarm.init(particleList, vmList, 10, 2);


        int cont = 0;
        while (cont < particleList.size()) {

            int idCloudlet = result.getParticle().getCloudletId();
            int idVm = result.getVmId();

            bindCloudletToVm(result.getParticle().getCloudletId(), result.getVmId());
            particleList.get(result.getParticle().getCloudletId()).scheduled = true;
            System.out.println("Task" + result.getParticle().getCloudletId() + " is bound with VM" + result.getVmId() + "tam: " + result.getParticle().getCloudletFileSize());
            cont++;
            result = swarm.init(particleList, vmList, 10, 2);

        }

    }
}
