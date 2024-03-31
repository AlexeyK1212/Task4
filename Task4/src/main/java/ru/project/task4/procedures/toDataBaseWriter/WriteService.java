package ru.project.task4.procedures.toDataBaseWriter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
//import ru.java.rush.entities.FruitEntity;
//import ru.java.rush.repositories.FruitRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor//аннотация от Ломбок
public class WriteService {

    /////////////////для логинов
    private final LoginRepository loginRepository;


    public Optional<LoginEntity> getById(Integer id) {
        return loginRepository.findById(id);
    }

    public void delByLoginId(Integer id) {
        loginRepository.deleteById(id);
    }

    public Boolean existLogin(Example<? extends LoginEntity> example) {
        return loginRepository.exists(example);
    }

    public void saveLogin(LoginEntity loginEntity) {
        loginRepository.save(loginEntity);
    }

    public List<LoginEntity> getAllLogin() {
        return loginRepository.findAll();
    }

    public void saveAllLogin(List<LoginEntity> logins) {
        loginRepository.saveAll(logins);
    }


    /////////////////для юзеров
    private final UserRepository userRepository;


    public Optional<UserEntity> getByUserId(Integer id) {
        return userRepository.findById(id);
    }

    public void delByUserId(Integer id) {
        userRepository.deleteById(id);
    }

    public Boolean existUser(Example<? extends UserEntity> example) {
        return userRepository.exists(example);
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    public void saveAllUser(List<UserEntity> users) {
        userRepository.saveAll(users);
    }




}
