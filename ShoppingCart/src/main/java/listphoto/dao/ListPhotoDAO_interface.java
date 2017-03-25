package listphoto.dao;

import java.util.Collection;
import listphoto.entity.ListPhoto;

public interface ListPhotoDAO_interface {
	
	Collection<ListPhoto> findAll();
	
	ListPhoto findById(int id);
	
	boolean delete(int id);
	
	int insert(ListPhoto insert);

	int update(ListPhoto update);

	
}
