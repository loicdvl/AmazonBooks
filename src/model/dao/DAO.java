package model.dao;

/**
 *  Interface contenant les fonctions principales  pour traiter les données dans la BDD
 *  @author Corentin
 */
public interface DAO<T>  {
	
	 public T findById(int id);
	 public void create(T obj);
	 public void update(T obj);
	 public void delete(T obj);
	 public int getMaxId();

}
