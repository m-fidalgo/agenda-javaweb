package agenda.repositories.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AgendaRepository<T> {
  List<T> select() throws SQLException, IOException;
  void insert(T entity) throws SQLException, IOException;
  void update(T entity) throws Exception;
  void delete(T entity) throws Exception;
}