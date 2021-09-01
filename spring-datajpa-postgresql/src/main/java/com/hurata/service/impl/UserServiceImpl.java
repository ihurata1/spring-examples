package com.hurata.service.impl;

import com.hurata.dto.UserDto;
import com.hurata.entity.Address;
import com.hurata.entity.User;
import com.hurata.repo.AddressRepository;
import com.hurata.repo.UserRepository;
import com.hurata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; //RequiredArgsConstructor
    private final AddressRepository addressRepository; //RequiredArgsConstructor


    @Override
    @Transactional //tek seferde hepsini kaydeder edemezse geri alır
    public UserDto save(UserDto userDto) {
        //Assert.isNull(userDto.getName(), "Name is mandatory");
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        final User userDb = userRepository.save(user);

        List<Address> list = new ArrayList<>();
        userDto.getAddresses().forEach((item) -> {
            Address address = new Address();
            address.setAddress(item);
            address.setAddressType(Address.AddressType.OTHER);
            address.setIsActive(true);
            address.setUser(userDb);
            list.add(address);
        });
        addressRepository.saveAll(list);
        userDto.setId(userDb.getId());
        return userDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach((item) ->{
            UserDto userDto = new UserDto();
            userDto.setId(item.getId());
            userDto.setName(item.getName());
            userDto.setSurname(item.getSurname());
            userDto.setAddresses(item.getAddresses().stream().map(Address::getAddress)
                    .collect(Collectors.toList()));
            userDtos.add(userDto);
        });

        return userDtos;
    }


    @Override
    public Page<UserDto> getAll(Pageable pagable) {
        return null;
    }
}
