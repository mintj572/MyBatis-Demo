package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.Country;

import java.util.List;

/**
 * @author ASUS
 * @date 2019/7/19 19:15
 */
public interface CountryMapper {
    List<Country> selectAll();
}
