package PSO;

import org.cloudbus.cloudsim.Vm;

/**
 * Created by Jake The Dog on 15/11/15.
 */
public class PSOResult {

    private double cost;
    private int vmId;
    private Particle particle;

    public PSOResult() {
        this.cost = 0.0;
        this.vmId = 0;
        this.particle = null;
    }

    public void setCost(double x) {
        this.cost = x;
    }

    public double getCost() {
        return this.cost;
    }

    public void setVmId(int x) {
        this.vmId = x;
    }

    public int getVmId() {
        return this.vmId;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }

    public Particle getParticle() {
        return this.particle;
    }
}
