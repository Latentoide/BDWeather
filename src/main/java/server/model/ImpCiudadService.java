package server.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ImpCiudadService implements ICiudadService{

    public List<Ciudad> getAll() {
        List<Ciudad> ciudadList = new ArrayList<>();
        DataSource ds = MyDataSource.getMyMariaDBDataSource();

        try (Connection con = ds.getConnection();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("select * from ciudad"))
        {
            int lon;
            int lat;
            String nombre;
            String imagen;
            while(resultSet.next()){
                lon = resultSet.getInt("lon");
                lat = resultSet.getInt("lat");
                nombre = resultSet.getString("nombre");
                imagen = resultSet.getString("imagen");

                ciudadList.add(new Ciudad(nombre, lon, lat, imagen));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ciudadList;
    }

    public Result<Ciudad> add(Ciudad ciudad){
        DataSource ds  = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = ds.getConnection();
            Statement st = con.createStatement();){
            String sql = "INSERT INTO " +
                    "ciudad VALUES ('"+ciudad.getNombre()+"','"+ciudad.getImg()+"','"+ciudad.getLon()+"',"+ciudad.getLat()+")";

            int count = st.executeUpdate(sql);
            if(count == 1){
                return new Result.Success<Ciudad>(ciudad);
            }else{
                return new Result.Error(404,"No se ha podido añadir la ciudad");
            }
        }catch (SQLException e){
            return new Result.Error(404,e.getMessage());
        }
    }
}
