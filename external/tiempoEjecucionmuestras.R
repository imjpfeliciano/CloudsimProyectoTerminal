#TIEMPO DE EJECUCION MUCHAS MUESTRAS

archivo <- read.csv(file = "C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/executionTime.csv")

#archivo <- read.csv(file = "/Users/CindyCanul/Desktop/executionTime.csv")

tamay <- max(archivo)

plot_colors <- c("blue","red","blueviolet","gray0")

png(filename="figure.png", height=295, width=500, 
    bg="white")


plot(archivo$MinMin, type="o", col=plot_colors[1],xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)")

# Create box around plot
box()

# Graph trucks with red dashed line and square points
lines(archivo$MaxMin, type="o", pch=24, lty=2, 
      col=plot_colors[2])

# Graph suvs with green dotted line and diamond points
lines(archivo$FCFS, type="o", pch=24, lty=2, 
      col=plot_colors[3])


lines(archivo$RR, type="o", pch=24, lty=2, 
      col=plot_colors[4])

title(main="Tiempo de ejecución 50 tareas", col.main="black", font.main=4)



legend("topleft", c("MIN-MIN","MAX-MIN","FCFS","Round Robin"), cex=0.5, col=c("blue","red","forestgreen","gray0"), lty=1:3, lwd=2, bty="n")



par(mar=c(5, 4, 4, 2) + 0.1)
#plot(xcloud, yfcfs, type="o", col="red", main="Tiempo de ejecución 50 tareas FCFS", xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)") 


dev.off()

a <- mean(archivo$FCFS)
b <- mean(archivo$MinMin)
c <- mean(archivo$Maxmin)
d <- mean(archivo$RR)

print(a)
print(b)
print(c)
print(d)

########### FCFS

png(filename="C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/external/fcfs.png", height=295, width=500, 
    bg="white")
plot(archivo$FCFS, type="o", col=plot_colors[1],xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)",main="Tiempo de ejecución 50 tareas FCFS")

dev.off()
########### MAXMIN

png(filename="C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/external/maxmin.png", height=295, width=500, 
    bg="white")
plot(archivo$Maxmin, type="o", col=plot_colors[2],xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)",main="Tiempo de ejecución 50 tareas Max-Min")

dev.off()
########### MINMIN
png(filename="C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/external/minmin.png", height=295, width=500, 
    bg="white")
plot(archivo$MinMin, type="o", col=plot_colors[3],xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)",main="Tiempo de ejecución 50 tareas Min-Min")

dev.off()

########### ROUNDROBIN

png(filename="C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/external/roundrobin.png", height=295, width=500, 
    bg="white")
plot(archivo$RR, type="o", col=plot_colors[4],xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)",main="Tiempo de ejecución 50 tareas Round Robin")

dev.off()

########### H

png(filename="C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/external/heuristics.png", height=295, width=500, 
    bg="white")
plot(archivo$Heuristics, type="o", col=plot_colors[4],xlab="Número de tareas (Cloudlets)",ylab="Tiempo de ejecución (ms)",main="Tiempo de ejecución 50 tareas Round Robin")

dev.off()

