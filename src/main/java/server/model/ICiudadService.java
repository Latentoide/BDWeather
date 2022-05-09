package server.model;

import java.util.List;

public interface ICiudadService {
    List<Ciudad> getAll();
    Result<Ciudad> add(Ciudad ciudad);
}
