package ru.project.task4.procedures.toDataBaseWriter;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//помечаем что этот бин - репозиторий
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}