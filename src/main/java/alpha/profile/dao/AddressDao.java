package alpha.profile.dao;

import alpha.profile.model.Address;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends CassandraRepository<Address,String> {
    //fetch list of categories belong to a group by its groupId
    List<Address> findByEmail(String emailId);

}
