package entitadesdenegocio;

public class Estudiante {
    // declaración de las propiedades de la entidad
    private int id;
    private String codigo;
    private String nombre;
    private String apellido;
    private String carrera;

    // declaración de los constructores de la clase (constructor vacío y constructor parametrizado)
    public Estudiante(){}

    public Estudiante(int id, String codigo, String nombre, String apellido, String carrera) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
    }

    // declaración de los métodos Get y Set para cada propiedad
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
