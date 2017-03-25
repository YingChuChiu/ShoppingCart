package listphoto.server;

import java.util.Collection;
import listphoto.dao.ListPhotoDaoJNDI;
import listphoto.entity.ListPhoto;

public class ListphotoService {

	ListPhotoDaoJNDI photodao = new ListPhotoDaoJNDI();

	public Collection<ListPhoto> findAll() {
		return photodao.findAll();
	}

	public ListPhoto findById(int id) {
		return photodao.findById(id);
	}

	public boolean delete(int id) {
		return photodao.delete(id);
	}

	public int insert(ListPhoto insert) {
		return photodao.insert(insert);
	}

	public int update(ListPhoto update) {
		return photodao.update(update);
	}

}
