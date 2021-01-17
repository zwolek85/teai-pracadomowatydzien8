package pl.zwolennik.notebook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zwolennik.notebook.entities.Notebook;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook , Long> {
}
