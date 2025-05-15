package uz.pdp.appneedtostarttest.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import uz.pdp.appneedtostarttest.enums.UserRoleEnum;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class UserRoleEnumSetConverter implements AttributeConverter<Set<UserRoleEnum>, String> {
    @Override
    public String convertToDatabaseColumn(Set<UserRoleEnum> roles) {

        return roles.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    @Override
    public Set<UserRoleEnum> convertToEntityAttribute(String rolesData) {
        return Arrays.stream(rolesData.split(","))
                .map(UserRoleEnum::valueOf)
                .collect(Collectors.toSet());
    }
}
