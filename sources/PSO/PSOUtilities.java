package PSO;

import org.cloudbus.cloudsim.Cloudlet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jake The Dog on 16/11/15.
 */
public class PSOUtilities {

    public List<Particle> parseParticles(List<? extends Cloudlet> listCloudlet) {

        List<Particle> particles = new LinkedList<Particle>();

        for (int i = 0; i < listCloudlet.size(); i++) {
            Cloudlet cloudlet = listCloudlet.get(i);
            Particle particle = new Particle(cloudlet.getCloudletId(), cloudlet.getCloudletLength(), cloudlet.getNumberOfPes(), cloudlet.getCloudletFileSize(), cloudlet.getCloudletOutputSize(), cloudlet.getUtilizationModelCpu(), cloudlet.getUtilizationModelRam(), cloudlet.getUtilizationModelBw());

            particles.add(particle);
        }

        return particles;
    }

    public List<Cloudlet> particles2Cloudlets(List<Particle> list) {

        List<Cloudlet> cloudlets = new LinkedList<Cloudlet>();

        for (int i = 0; i < list.size(); i++) {
            Particle particle = list.get(i);
            Cloudlet cloudlet = new Cloudlet(particle.getCloudletId(), particle.getCloudletLength(), particle.getNumberOfPes(), particle.getCloudletFileSize(), particle.getCloudletOutputSize(), particle.getUtilizationModelCpu(), particle.getUtilizationModelRam(), particle.getUtilizationModelBw());
            cloudlet.setUserId(particle.getUserId());
            cloudlets.add(cloudlet);
        }
        return cloudlets;
    }
}
