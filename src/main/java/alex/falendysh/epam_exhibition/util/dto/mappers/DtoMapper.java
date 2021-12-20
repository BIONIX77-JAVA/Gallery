package alex.falendysh.epam_exhibition.util.dto.mappers;

public interface DtoMapper<T, E> {

    T mapToDto(E e);

    E mapFromDto(T t);
}
