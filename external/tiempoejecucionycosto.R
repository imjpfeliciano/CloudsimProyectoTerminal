#BARCHAR COSTO DE EJECUCION

# costo de tiempo de ejecucion
archivo <- read.csv(file = "C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/executionCost.csv") 

png(filename="C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/external/tiempoejecucion.png", height=300, width=500, 
    bg="white")

barplot(as.matrix(archivo), main="Promedio tiempo de ejecución",ylab= "Tiempo ejecución (s)",xlab = "Número de tareas (Cloudlets)",
        beside=TRUE, col=rainbow(5))

legend("topleft", c("100","200","300","400","500"), cex=0.6,bty="n",fill=rainbow(5))

dev.off()



archivo2 <- read.csv(file = "C:/Users/Jake The Dog/IdeaProjects/CloudsimProyectoTerminal/costoproce.csv") 

png(filename="/Users/CindyCanul/Desktop/costoproce.png", height=300, width=500, 
    bg="white")

barplot(as.matrix(archivo2), main="Promedio costo de procesamiento",ylab= "Tiempo ejecución (s)",xlab = "Número de tareas (Cloudlets)",
        beside=TRUE, col=rainbow(5))

legend("topleft", c("100","200","300","400","500"), cex=0.6,bty="n",fill=rainbow(5))

dev.off()

