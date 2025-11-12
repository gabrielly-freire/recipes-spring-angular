package imd.ufrn.foodrecipes.domain.repository;

import imd.ufrn.foodrecipes.domain.entity.GenericEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity> extends JpaRepository<T, Long> {
    
    @Override
    @Transactional
    default void deleteById(Long id) {
        Optional<T> entity = findById(id);
        if (entity.isPresent()) {
            entity.get().setActive(false);
            save(entity.get());
        }
    }

    @Override
    @Transactional
    default void delete(T obj) {
        obj.setActive(false);
        save(obj);
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> arg0) {
        arg0.forEach(entity -> deleteById(entity.getId()));
    }

    @Query("select e from #{#entityName} e where e.active = true")
    List<T> findAllActive();

    @Query("select e from #{#entityName} e where e.active = true")
    Page<T> findAllActive(Pageable pageable);

    @Query("select e from #{#entityName} e where e.id = ?1 and e.active = true")
    Optional<T> findActiveById(Long id);
}
