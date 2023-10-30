package dominio.clasesGenericas;

import java.util.Map;
import java.util.NoSuchElementException;

public abstract class Tabla {
    protected Map<Integer, Elemento> tabla;
    protected Configuracion conf;

    public Elemento get(int indice) {
        if (!this.tabla.containsKey(indice)) throw new NoSuchElementException("El elemento no existe.");
        return this.tabla.get(indice);
    }
    public void put(int indice, Elemento elemento) {
        if (this.tabla.containsKey(indice)) throw new NoSuchElementException("El elemento ya existe");
        this.tabla.put(indice, elemento);
    }

    public Map<Integer, Elemento> getTabla() {
        return tabla;
    }

}
