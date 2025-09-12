package com.menekse.airlines.mapper;

import com.menekse.airlines.common.BaseMapper;
import com.menekse.airlines.model.domain.User;
import com.menekse.airlines.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityToUserMapper extends BaseMapper<User, UserEntity> {

    UserEntityToUserMapper INSTANCE = Mappers.getMapper(UserEntityToUserMapper.class);
}
