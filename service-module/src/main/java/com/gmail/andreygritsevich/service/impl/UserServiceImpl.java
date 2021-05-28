package com.gmail.andreygritsevich.service.impl;

import com.gmail.andreygritsevich.repository.UserRepository;
import com.gmail.andreygritsevich.repository.model.User;
import com.gmail.andreygritsevich.service.PasswordService;
import com.gmail.andreygritsevich.service.UserService;
import com.gmail.andreygritsevich.service.model.UpdateUserDTO;
import com.gmail.andreygritsevich.service.model.UserDTO;
import com.gmail.andreygritsevich.service.util.PaginationUtil;
import com.gmail.andreygritsevich.service.util.UserConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final int SUPER_ADMINISTRATOR_ID = 1;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordService passwordService;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            PasswordService passwordService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordService = passwordService;
    }

    @Override
    @Transactional
    public UserDTO add(UserDTO userDTO) {
        User user = getObjectFromDTO(userDTO);
        String newUserPassword = passwordService.getNewPassword();
        String encodedPassword = passwordEncoder.encode(newUserPassword);
        user.setPassword(encodedPassword);
        user.setDeleted(false);
        userRepository.persist(user);
        passwordService.sendPasswordToEmail(newUserPassword, user.getEmail());
        return getDTOFromObject(user);
    }

    @Override
    @Transactional
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        UserDTO userDTO = null;
        if (user != null) {
            userDTO = getDTOFromObject(user);
        }
        return userDTO;
    }

    @Override
    @Transactional
    public Long getCountUsers() {
        return userRepository.getCount();
    }

    @Override
    @Transactional
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }
        return getDTOFromObject(user);
    }

    @Override
    @Transactional
    public UserDTO changeUserRole(UpdateUserDTO updateUserDTO) {
        Long id = updateUserDTO.getId();
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }
        if (id != SUPER_ADMINISTRATOR_ID) {
            user.setRole(updateUserDTO.getRole());
        }
        return getDTOFromObject(user);
    }

    @Override
    @Transactional
    public List<UserDTO> getItemsByPageSorted(int page) {
        int startPosition = PaginationUtil.getPositionByPage(page);
        List<User> users = userRepository.getItemsByPageSorted(startPosition, PaginationUtil.ITEMS_BY_PAGE);
        return convertItemsToItemsDTO(users);
    }

    @Override
    @Transactional
    public void deleteUsersByIds(Long[] userIds) {
        for (Long id : userIds) {
            if (id != SUPER_ADMINISTRATOR_ID) {
                User user = userRepository.findById(id);
                userRepository.remove(user);
            }
        }
    }

    @Override
    @Transactional
    public void changeUserPassword(Long id) {
        User user = userRepository.findById(id);
        String newUserPassword = passwordService.getNewPassword();
        String encodedPassword = passwordEncoder.encode(newUserPassword);
        user.setPassword(encodedPassword);
        passwordService.sendPasswordToEmail(newUserPassword, user.getEmail());
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setMiddleName(userDTO.getMiddleName());
        user.getUserInformation().setAddress(userDTO.getAddress());
        user.getUserInformation().setPhone(userDTO.getPhone());
        userRepository.merge(user);
        return getDTOFromObject(user);
    }

    private List<UserDTO> convertItemsToItemsDTO(List<User> items) {
        return items.stream()
                .map(this::getDTOFromObject)
                .collect(Collectors.toList());
    }

    private UserDTO getDTOFromObject(User user) {
        return UserConverterUtil.getDTOFromObject(user);
    }

    private User getObjectFromDTO(UserDTO userDTO) {
        return UserConverterUtil.getObjectFromDTO(userDTO);
    }

}
