package domain.entities.actores.reputaciones.verificador;

public class VerificadorDeContratacion {
    private Integer periodoDeMaximosEnSemanas;
    private Integer maxCantVecesPorPeriodo;
    private Integer cantVecesContratadoEnPeriodo;

    public VerificadorDeContratacion(Integer periodoDeMaximosEnSemanas, Integer maxCantVecesPorPeriodo, Integer cantVecesContratadoEnPeriodo) {
        this.periodoDeMaximosEnSemanas = periodoDeMaximosEnSemanas;
        this.maxCantVecesPorPeriodo = maxCantVecesPorPeriodo;
        this.cantVecesContratadoEnPeriodo = cantVecesContratadoEnPeriodo;
    }

    public Boolean prestadorPuedeSerContratado(Integer cantSemanasDesdeUltimoTrabajo){
        return estaFueraDelPeriodo(cantSemanasDesdeUltimoTrabajo) || estaDentroDePeriodoPeroNoSuperaLimite(cantSemanasDesdeUltimoTrabajo);
    }

    public Integer valorParaContadorDeContrataciones(Integer cantSemanasDesdeUltimoTrabajo){
        return estaDentroDePeriodoPeroNoSuperaLimite(cantSemanasDesdeUltimoTrabajo)?
                this.cantVecesContratadoEnPeriodo +1 :
                1;
    }

    private Boolean estaFueraDelPeriodo(Integer cantSemanasDesdeUltimoTrabajo){
        return cantSemanasDesdeUltimoTrabajo >= periodoDeMaximosEnSemanas;
    }

    private Boolean estaDentroDePeriodoPeroNoSuperaLimite(Integer cantSemanasDesdeUltimoTrabajo){
        return cantSemanasDesdeUltimoTrabajo < periodoDeMaximosEnSemanas &&
                this.cantVecesContratadoEnPeriodo < maxCantVecesPorPeriodo;
    }
}
