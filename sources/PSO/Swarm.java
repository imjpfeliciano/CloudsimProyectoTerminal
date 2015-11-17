package PSO;

import org.cloudbus.cloudsim.Vm;

import java.util.List;

/**
 * Created by Jake The Dog on 16/11/02.
 */
public class Swarm {

    public static int getRandomInteger(int maximun, int minimum) {
        return ((int) (Math.random() * (maximun - minimum))) + minimum;
    }

    public static void initialize(List<Particle> list, int vms, int velocityLimit) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).initParticle(vms, velocityLimit);
        }
    }

    public static Particle getBestParticle(List<Particle> list) {
        int bIndex = 0;
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPbest() < best) {
                best = list.get(i).getPbest();
                bIndex = i;

            }
        }
        return list.get(bIndex);
    }

    public static PSOResult init(List<Particle> particles, List<? extends Vm> vms, int iteration, int velocityLimit) {
        double gbest = 0;
        int gBestPosition = 0;
        Particle gBestParticle;
        PSOResult result = new PSOResult();
        // Se inicia de manera aleatoria la posición y la velocidad de cada particula
        initialize(particles, vms.size(), velocityLimit);
        //Criterio de terminación de PSO
        for (int x = 0; x < iteration; x++) {


            //Para cada partícula, calcular su fitness value
            FitnessFunction fitness = new FitnessFunction();
            for (int i = 0; i < particles.size(); i++) {
                if (!particles.get(i).scheduled) {
                    double fitnessValue = fitness.evaluate(particles.get(i), vms.get(particles.get(i).getPosition() % vms.size()));
                    //Si el acutal fitnessValue es mejor que el previo pbest, colocamos el fitnesValue como nuevo pbest
                    if (fitnessValue < particles.get(i).getPbest()) {
                        particles.get(i).setPbest(fitnessValue);
                        particles.get(i).setBestVm(particles.get(i).getPosition() % vms.size());
                    }
                }
            }
            // se selecciona la mejor particula como gbest
            gBestParticle = getBestParticle(particles);
            gbest = gBestParticle.getPbest();
            gBestPosition = gBestParticle.getPosition();

            //Actualizamos la velocidad y la posición de cada particula
            result.setParticle(gBestParticle);
            result.setVmId(gBestParticle.getBestVm());
            result.setCost(gbest);
            for (int i = 0; i < particles.size(); i++) {

                particles.get(i).setVelocity(gBestPosition);
                int velocity = particles.get(i).getVelocity();
                particles.get(i).setPosition(velocity);
            }
        }


        return result;


    }
}
