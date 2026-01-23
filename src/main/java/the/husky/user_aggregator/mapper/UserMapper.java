package the.husky.user_aggregator.mapper;

import org.mapstruct.Mapper;
import the.husky.user_aggregator.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;


@Mapper(componentModel = "spring")
public interface UserMapper {

    default UserDto mapFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserDto(
                resultSet.getString("id") != null ?
                        resultSet.getString("id") :
                        "",
                resultSet.getString("username") != null ?
                        resultSet.getString("username") :
                        "",
                resultSet.getString("name") != null ?
                        resultSet.getString("name") :
                        "",
                resultSet.getString("surname") != null ?
                        resultSet.getString("surname") :
                        ""
        );
    }
}
