package one.digitalinnovation.personapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import one.digitalinnovation.personapi.dto.UserDTO;
import one.digitalinnovation.personapi.entity.ApiUser;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    
    ApiUser toModel(UserDTO userDTO );

    UserDTO toDTO(ApiUser user );
}
