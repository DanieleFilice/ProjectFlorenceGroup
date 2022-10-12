package com.filicedaniele.projectFG.service;

import com.filicedaniele.projectFG.Data.UserDto;
import com.filicedaniele.projectFG.Data.UserRequest;
import com.filicedaniele.projectFG.entity.User;
import com.filicedaniele.projectFG.exceptions.UserAlreadyExistException;
import com.filicedaniele.projectFG.exceptions.UserNotFoundException;
import com.filicedaniele.projectFG.exceptions.UsersNotFound;
import com.filicedaniele.projectFG.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public UserDto create(UserRequest u){

        if(userRepository.existsByEmail(u.getEmail()))
            throw new UserAlreadyExistException();

        return modelMapper.map(userRepository.save(new User(u.getNome(),u.getCognome(),u.getEmail())),UserDto.class);
    }

    public UserDto update(UserRequest u){

        User user = userRepository.findByEmail(u.getEmail()).orElseThrow(UserNotFoundException::new);
        user.setNome(u.getNome());
        user.setCognome(u.getCognome());
        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    public UserDto delete(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        UserDto dto = modelMapper.map(user,UserDto.class);
        userRepository.delete(user);
        return dto;
    }

    public List<UserDto> get(String nome,String cognome){
        List<User> users;
        if(nome == null && cognome == null)
            users = userRepository.findAll();
        else if(nome == null && cognome != null)
            users = userRepository.findByCognome(cognome);
        else if (nome != null && cognome == null)
            users = userRepository.findByNome(nome);
        else
            users = userRepository.findByNomeAndCognome(nome,cognome);

        if(users.isEmpty()) throw new UsersNotFound();

        return users.stream().map(x -> modelMapper.map(x,UserDto.class)).collect(Collectors.toList());

    }
}
