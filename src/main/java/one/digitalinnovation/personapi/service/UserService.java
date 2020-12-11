package one.digitalinnovation.personapi.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.dto.UserDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.ApiUser;
import one.digitalinnovation.personapi.mapper.UserMapper;
import one.digitalinnovation.personapi.repository.UserRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class UserService implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public MessageResponseDTO createUser(UserDTO userDTO) {
        ApiUser userToSave = userMapper.toModel(userDTO);

        ApiUser savedUser = userRepository.save(userToSave);
        return createMessageResponse(savedUser.getId(), "Created person with ID ");
    }

//    public List<UserDTO> listAll() {
//        List<User> allPeople = userRepository.findAll();
//        return allPeople.stream()
//                .map(personMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    public UserDTO findById(Long id) throws PersonNotFoundException {
//        User person = verifyIfExists(id);
//
//        return personMapper.toDTO(person);
//    }
//
//    public void delete(Long id) throws PersonNotFoundException {
//        verifyIfExists(id);
//        userRepository.deleteById(id);
//    }
//
//    public MessageResponseDTO updateById(Long id, UserDTO personDTO) throws PersonNotFoundException {
//        verifyIfExists(id);
//
//        User personToUpdate = personMapper.toModel(personDTO);
//
//        User updatedPerson = userRepository.save(personToUpdate);
//        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
//    }
//
//    private User verifyIfExists(Long id) throws PersonNotFoundException {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new PersonNotFoundException(id));
//    }
//
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    ApiUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), Collections.emptyList());
    }
}
