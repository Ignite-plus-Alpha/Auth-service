package alpha.profile.dao;

import alpha.profile.model.Address;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends CassandraRepository<Address,String> {

}
