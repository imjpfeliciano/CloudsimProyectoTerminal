package PSO;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;


/**
 * Created by Jake The Dog on 28/10/15.
 */
public class Particle extends Cloudlet {

    /**
     * Allocates a new Cloudlet object. The Cloudlet length, input and output file sizes should be
     * greater than or equal to 1. By default this constructor sets the history of this object.
     *
     * @param cloudletId          the unique ID of this Cloudlet
     * @param cloudletLength      the length or size (in MI) of this cloudlet to be executed in a
     * PowerDatacenter
     * @param pesNumber           the pes number
     * @param cloudletFileSize    the file size (in byte) of this cloudlet <tt>BEFORE</tt> submitting
     * to a PowerDatacenter
     * @param cloudletOutputSize  the file size (in byte) of this cloudlet <tt>AFTER</tt> finish
     * executing by a PowerDatacenter
     * @param utilizationModelCpu the utilization model cpu
     * @param utilizationModelRam the utilization model ram
     * @param utilizationModelBw  the utilization model bw
     * @pre cloudletID >= 0
     * @pre cloudletLength >= 0.0
     * @pre cloudletFileSize >= 1
     * @pre cloudletOutputSize >= 1
     * @post $none
     */

    public boolean scheduled = false;

    public Particle(int cloudletId, long cloudletLength, int pesNumber, long cloudletFileSize, long cloudletOutputSize, UtilizationModel utilizationModelCpu, UtilizationModel utilizationModelRam, UtilizationModel utilizationModelBw) {
        super(cloudletId, cloudletLength, pesNumber, cloudletFileSize, cloudletOutputSize, utilizationModelCpu, utilizationModelRam, utilizationModelBw);
    }

    public void initialise() {


    }


    /* Extends attributes and methods */

    /**
     * the velocity of cloudlet particle is denoted by :
     * v[i][k+1] = (inertia weight)v[i][k] + (acceleration coefficient 1)Rand(0,1) * (pbest - x[i][k]) + (acceleration coefficient 2)Rand(0,1) * (gbest - x[i][k])
     * <p>
     * -----------------------------------
     */
    private int velocity;
    private int position;
    private double pbest;
    private double gbest;

    private int pbestPosition;
    private int gbestPosition;

    private int bestVm;

    /**
     * coefficients
     * W = inertia weight
     * C = acceleration coefficients
     * rand = rand number between 0,1
     */
    private int w = 1;
    private int c1 = 1;
    private int c2 = 1;

    public static int getRandom() {
        return ((int) (Math.random() * (1)));
    }

    public static int getRandomInteger(int maximun, int minimum) {
        return ((int) (Math.random() * (maximun - minimum))) + minimum;
    }

    public void initParticle(int vms, int velocityLimit) {
        this.position = getRandomInteger(vms, 0);
        this.velocity = getRandomInteger(velocityLimit, 1);
        this.pbest = Double.POSITIVE_INFINITY;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int gbest) {
        c1 = getRandomInteger(2, 1);
        c2 = getRandomInteger(2, 1);

        velocity = (w * position) + ((c1 * getRandom()) * (this.pbestPosition - this.position)) + ((c2 * getRandom()) * (gbest - this.position));
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int velocity) {
        this.position = this.position + velocity;
    }


    public void updatePbest(double fit) {
        //si el fit es menor que el pbest se actualiza
        if (fit < this.pbest) {
            this.pbest = fit;
        }
    }

    public void setPbest(double fit) {
        this.pbest = fit;
    }

    public double getPbest() {
        return this.pbest;
    }

    public void setBestVm(int x) {
        this.bestVm = x;
    }

    public int getBestVm() {
        return this.bestVm;
    }


}
