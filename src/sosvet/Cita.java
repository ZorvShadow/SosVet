package sosvet;

public class Cita {
    //public static ArrayList<String[]> citas = new ArrayList<>();
    // Ejemplo de datos contenidos dentro de Array: {'id paciente' ,'dia', 'hora', 'veterinario asignado'}
    private Paciente paciente;
    private String dia;
    private String hora;
    private String vetAsignado;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getVetAsignado() {
        return vetAsignado;
    }

    public void setVetAsignado(String vetAsignado) {
        this.vetAsignado = vetAsignado;
    }
}
