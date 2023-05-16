package com.school.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.springboot.VO.UserVO;
import com.school.springboot.entity.User;
import java.util.List;

/**
 * @author shijiu
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    User selectByPhone(String phone);

    User getUserByUserName(String username);

    User getIdentityByPhone(String phone);

    User getUserInfoByPhone(String phone);

    int insUser(User user);

    int updateStatus(String phone);

    List<User> selectUserByStatus();

    List<User> selectAllCrossTable(List<String> list);
    
}
