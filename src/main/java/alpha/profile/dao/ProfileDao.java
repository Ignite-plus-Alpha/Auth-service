package alpha.profile.dao;

import alpha.profile.model.Profile;
import org.springframework.data.cassandra.repository.CassandraRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends CassandraRepository<Profile,String> {

}