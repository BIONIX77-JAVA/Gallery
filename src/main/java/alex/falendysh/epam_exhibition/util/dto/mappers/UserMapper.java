package alex.falendysh.epam_exhibition.util.dto.mappers;

import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.util.dto.UserDto;

public class UserMapper implements DtoMapper<UserDto, User> {

    @Override
    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public User mapFromDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setRole();
        return user;
    }
}
